package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import controller.BufferedImageUtils;
import model.PixelInfo;

public class IPViewPreviewGUIImpl extends JFrame {
  
  private final JLabel imgLabel;
  
  public IPViewPreviewGUIImpl() {
    this.setTitle("Preview");
    this.setSize(200, 200);
    this.setResizable(false);
    this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    
    this.imgLabel = new JLabel();
    JScrollPane scrollImg = new JScrollPane(this.imgLabel);
    this.add(scrollImg);
  }
  
  public void seeGUI() {
    this.setVisible(true);
  }
  
  public void drawImage(PixelInfo[][] pixels) throws IllegalArgumentException {
    int width = pixels[0].length;
    int height = pixels.length;
    BufferedImage img = BufferedImageUtils.createBI(width, height, pixels);
    this.imgLabel.setIcon(new ImageIcon(img));
  }
  
  public void renderPopUpMessage(String body, String title, int type) {
    JOptionPane.showMessageDialog(this, body, title, type);
  }
}
