package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.IPControllerGUI;
import model.IPModel;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents;
import static model.IPModelState.PixelComponents.Red;

/**
 * This class represents a view of the IP program with a GUI.
 */
public class IPViewGUIImpl extends JFrame implements IPViewGUI, ActionListener {
  
  private final String[] buttons;
  private final IPModel model;
  private IPControllerGUI controllerGUI;
  private final JLabel imgLabel;
  private final JPanel rightPanel;
  private IPHistogram histogramPanel;
  private final JPanel buttonsPanel;
  
  /**
   * This first constructor creates the view with a GUI of an IP program.
   */
  public IPViewGUIImpl(IPModel model) {
    super();
    
    // initialize model
    this.model = model;
    
    // create list of buttons required
    this.buttons = new String[]{"load", "save", "brighten", "darken", "vertical-flip",
        "horizontal-flip", "red-component", "green-component", "blue-component",
        "value-component", "intensity-component", "luma-component", "blur",
        "sharpen", "greyscale-luma", "sepia"};
    
    // set up JFrame
    this.setTitle("IP Program");
    this.setSize(1500, 1000);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    // create the layout of the GUI
    this.setLayout(new GridLayout(1, 2));

//    |-----------------------||-----------------------|
//    |                       ||    histogramPanel     |
//    |       imgPanel        ||------rightPanel-------|
//    |                       ||     buttonsPanel      |
//    |_______________________||_______________________|

    // histogramPanel:
    // top left: red; top right: green; bottom left: blue; bottom right: intensity;
    
    // place panel to left (for image) and make it scrollable
    this.imgLabel = new JLabel();
    JScrollPane scrollImg = new JScrollPane(this.imgLabel);
    this.add(scrollImg);
    
    // place panel to the right (for histogram and buttons)
    this.rightPanel = new JPanel();
    this.rightPanel.setLayout(new GridLayout(2, 1));
    this.add(this.rightPanel);
    
    // place panel to top right (for histogram) and make it scrollable
    this.histogramPanel = new IPHistogramImpl(300, 600, this.model);
    JScrollPane scrollHisto = new JScrollPane((Component) this.histogramPanel);
    this.rightPanel.add(scrollHisto);
    
    // place panel to bottom right (for buttons)
    this.buttonsPanel = new JPanel();
    this.buttonsPanel.setLayout(new GridLayout(4, 4));
    this.rightPanel.add(this.buttonsPanel);
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    // create buttons
    for (String button : this.buttons) {
      JButton newButton = new JButton(button);
      newButton.setActionCommand(button);
      newButton.addActionListener(this);
      this.buttonsPanel.add(newButton);
    }
  }
  
  @Override
  public void controllerGUI(IPControllerGUI controller) {
    this.controllerGUI = controller;
  }
  
  @Override
  public void seeGUI() {
    this.setVisible(true);
  }
  
  @Override
  public void renderPopUpMessage(String body, String title, int type) {
    JOptionPane.showMessageDialog(this, body, title, type);
  }
  
  @Override
  public void drawImage(String imgName) throws IllegalArgumentException {
    int width = this.model.getWidth(imgName);
    int height = this.model.getHeight(imgName);
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Map<PixelComponents, Integer> thisPixelInfo = this.model.getPixelInfo(imgName, i, j);
        int red = thisPixelInfo.get(Red);
        int green = thisPixelInfo.get(Green);
        int blue = thisPixelInfo.get(Blue);
        
        int rgb = (red  << 16) | (green << 8) | (blue << 0);
        
        img.setRGB(j, i, rgb);
      }
    }
    this.imgLabel.setIcon(new ImageIcon(img));
  }

  // method that takes in a IPHistogramImpl, gets four drawn histograms, and places them in their
  // respective panels
  @Override
  public void drawHistogram(String imgName) {
    this.histogramPanel.createHistogramModel(imgName);
    this.repaint();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    String specialArgument = "";
    
    switch (command) {
      case "load":
        specialArgument = this.browseFiles(true);
        break;
      case "save":
        specialArgument = this.browseFiles(false);
        break;
      case "brighten":
        specialArgument = JOptionPane.showInputDialog("Enter amount to brighten:");
        break;
      case "darken":
        specialArgument = JOptionPane.showInputDialog("Enter amount to darken:");
        break;
      default:
        break;
    }
    
    this.controllerGUI.commandHandler(command, specialArgument);
    
  }
  
  /**
   * This method allows the user to browse through files to either upload or save an image
   * appropriately.
   *
   * @param load A boolean representing if the method should help a user load an image (true)
   *             or save an image (false)
   * @return A String representing the path of a file that has either been selected to be
   * uploaded or the desired saving destination.
   */
  private String browseFiles(boolean load) {
    
    JFileChooser fileChooser = new JFileChooser();
    
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "ppm, png, jpg, jpeg, bmp images", "ppm", "png", "jpg", "jpeg", "bmp");
    
    fileChooser.setFileFilter(filter);
    
    int valid;
    
    if (load) {
      valid = fileChooser.showOpenDialog(this);
    } else {
      valid = fileChooser.showSaveDialog(this);
    }
    
    if (valid == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      return f.getAbsolutePath();
    } else {
      return "";
    }
  }
}
