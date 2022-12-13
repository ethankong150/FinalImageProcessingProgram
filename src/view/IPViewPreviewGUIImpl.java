package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import controller.BufferedImageUtils;
import controller.IPPreviewGUIControllerImpl;
import model.PixelInfo;

public class IPViewPreviewGUIImpl extends JFrame implements AdjustmentListener {
  
  private final JLabel imgLabel;
  private final JScrollBar horiBar;
  private final JScrollBar vertBar;
  private IPPreviewGUIControllerImpl con;
  
  public IPViewPreviewGUIImpl() {
    this.setTitle("Preview");
    this.setSize(200, 200);
    this.setResizable(false);
    this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    
    this.imgLabel = new JLabel();
    JScrollPane scrollImg = new JScrollPane(this.imgLabel);
    this.add(scrollImg);
    this.horiBar = scrollImg.getHorizontalScrollBar();
    this.vertBar = scrollImg.getVerticalScrollBar();
    
    this.horiBar.addAdjustmentListener(e -> con.updatePreviewImage(getBarInfo()));
    this.vertBar.addAdjustmentListener(e -> con.updatePreviewImage(getBarInfo()));
  }
  
  public void seeGUI() {
    this.setVisible(true);
  }
  
  public void connect(IPPreviewGUIControllerImpl c) {
    this.con = c;
  }
  
  public void drawImage(PixelInfo[][] pixels) throws IllegalArgumentException {
    int width = pixels[0].length;
    int height = pixels.length;
    BufferedImage img = BufferedImageUtils.createBI(width, height, pixels);
    this.imgLabel.setIcon(new ImageIcon(img));
  }
  
  public ArrayList<Integer> getBarInfo() {
    // ArrayList -> [0] horiBar getValue(), [1] horiBar getMaximum(), [2] vertBar getValue(), [3] vertBar getMaximum()
    ArrayList<Integer> output = new ArrayList<>(4);
    output.add(this.vertBar.getValue());
    output.add(this.horiBar.getValue());
    output.add(this.vertBar.getMaximum());
    output.add(this.horiBar.getMaximum());
    return output;
  }
  
  public void renderPopUpMessage(String body, String title, int type) {
    JOptionPane.showMessageDialog(this, body, title, type);
  }
  
  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    this.con.updatePreviewImage(this.getBarInfo());
  }
}
