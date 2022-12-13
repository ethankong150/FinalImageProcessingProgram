package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.filechooser.FileNameExtensionFilter;

import controller.BufferedImageUtils;
import controller.IPControllerGUI;
import controller.IPControllerGUIImpl;
import controller.IPPreviewGUIControllerImpl;
import model.IPModel;
import model.PixelInfo;

/**
 * This class represents a view of the IP program with a GUI.
 */
public class IPViewGUIImpl extends JFrame implements IPViewGUI, ActionListener {
  
  private IPControllerGUIImpl controllerGUI;
  private final JLabel imgLabel;
  private final IPHistogram histogramPanel;
  
  /**
   * This first constructor creates the view with a GUI of an IP program.
   */
  public IPViewGUIImpl() {
    // create list of buttons required
    String[] buttons = new String[]{"load", "save", "brighten", "darken", "vertical-flip",
        "horizontal-flip", "red-component", "green-component", "blue-component",
        "value-component", "intensity-component", "luma-component", "blur",
        "sharpen", "greyscale-luma", "sepia", "downsize", "preview"};
    
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
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new GridLayout(2, 1));
    this.add(rightPanel);
    
    // place panel to top right (for histogram) and make it scrollable
    this.histogramPanel = new IPHistogramImpl(300, 600);
    JScrollPane scrollHisto = new JScrollPane((Component) this.histogramPanel);
    rightPanel.add(scrollHisto);
    
    // place panel to bottom right (for buttons)
    JPanel buttonsPanel = new JPanel();
//    buttonsPanel.setLayout(new GridLayout(4, 4));
    rightPanel.add(buttonsPanel);
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    // create buttons
    for (String button : buttons) {
      JButton newButton = new JButton(button);
      newButton.setActionCommand(button);
      newButton.addActionListener(this);
      buttonsPanel.add(newButton);
    }
  }
  
  @Override
  public void setControllerGUI(IPControllerGUIImpl controller) throws IllegalArgumentException {
    if (controller == null) {
      throw new IllegalArgumentException("controller given is null");
    }
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
  public void drawImage(PixelInfo[][] pixels) throws IllegalArgumentException {
    int width = pixels[0].length;
    int height = pixels.length;
    BufferedImage img = BufferedImageUtils.createBI(width, height, pixels);
    this.imgLabel.setIcon(new ImageIcon(img));
  }
  
  // method that takes in a IPHistogramImpl, gets four drawn histograms, and places them in their
  // respective panels
  @Override
  public void drawHistogram(PixelInfo[][] pixels) throws IllegalArgumentException {
    this.histogramPanel.createHistogramData(pixels);
    this.repaint();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    String specialArgument = "";
    
    switch (command) {
      case "load":
        specialArgument = this.browseFiles(true);
        this.controllerGUI.commandHandler(command, specialArgument);
        break;
      case "save":
        specialArgument = this.browseFiles(false);
        this.controllerGUI.commandHandler(command, specialArgument);
        break;
      case "brighten":
        specialArgument = JOptionPane.showInputDialog("Enter amount to brighten:");
        this.controllerGUI.commandHandler(command, specialArgument);
        break;
      case "darken":
        specialArgument = JOptionPane.showInputDialog("Enter amount to darken:");
        this.controllerGUI.commandHandler(command, specialArgument);
        break;
      case "downsize":
        String newHeight = JOptionPane.showInputDialog("Enter percentage of this image's height to " +
            "downsize this image to (must be between 0 and 100):");
        String newWidth = JOptionPane.showInputDialog("Enter percentage of this image's width to " +
            "downsize this image to (must be between 0 and 100):");
        specialArgument = newHeight + " " + newWidth;
        this.controllerGUI.commandHandler(command, specialArgument);
        break;
      case "preview":
        // drop down menu of what methods are allowed to be previewed (everything except flips, downsize, save, and load)
        
        JPanel dropDown = new JPanel();
        dropDown.add(new JLabel("What would you like to preview:"));
        DefaultComboBoxModel options = new DefaultComboBoxModel();
        options.addElement("brighten");
        options.addElement("darken");
        options.addElement("red-component");
        options.addElement("green-component");
        options.addElement("blue-component");
        options.addElement("value-component");
        options.addElement("intensity-component");
        options.addElement("luma-component");
        options.addElement("blur");
        options.addElement("sharpen");
        options.addElement("greyscale-luma");
        options.addElement("sepia");
        JComboBox box = new JComboBox(options);
        dropDown.add(box);
        
        int i = JOptionPane.showConfirmDialog(this, dropDown, "preview command",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (i == JOptionPane.OK_OPTION) {
          String prevCommand = (String) box.getSelectedItem();
          switch (prevCommand) {
            case "brighten":
              specialArgument = JOptionPane.showInputDialog("Enter amount to brighten:");
              break;
            case "darken":
              specialArgument = JOptionPane.showInputDialog("Enter amount to darken:");
              break;
            default:
              break;
          }
          IPPreviewGUIControllerImpl c = this.controllerGUI.createPreviewC();
          c.commandHandler(prevCommand, specialArgument);
        }
        
        break;
      default:
        this.controllerGUI.commandHandler(command, specialArgument);
        break;
    }
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
