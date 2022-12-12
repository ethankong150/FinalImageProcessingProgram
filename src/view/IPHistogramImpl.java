package view;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.IPModel;
import model.IPModelState.PixelComponents;
import model.PixelInfo;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Red;

/**
 * Represents the implementation of the creation of the four histogram graphs for a given image.
 */
public class IPHistogramImpl extends JPanel implements IPHistogram {
  
  private ArrayList<DataBar> redBarsData;
  private ArrayList<DataBar> greenBarsData;
  private ArrayList<DataBar> blueBarsData;
  private ArrayList<DataBar> intensityBarsData;
  private final int height;
  private final int width;
  
  /**
   * Represents the constructor to create histogram models.
   *
   * @param height height of each histogram
   * @param width  width of each histogram
   * @throws IllegalArgumentException when height or width are negative or if the model is null.
   */
  public IPHistogramImpl(int height, int width) throws IllegalArgumentException {
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("illegal parameter given");
    }
    this.redBarsData = new ArrayList();
    this.greenBarsData = new ArrayList();
    this.blueBarsData = new ArrayList();
    this.intensityBarsData = new ArrayList();
    this.height = height;
    this.width = width;
    
    this.setPreferredSize(new Dimension(width, height));
    
  }
  
  /**
   * This method resets the class's arraylists to be empty.
   */
  private void emptyArrayLists() {
    this.redBarsData = new ArrayList();
    this.greenBarsData = new ArrayList();
    this.blueBarsData = new ArrayList();
    this.intensityBarsData = new ArrayList();
  }
  
  /**
   * This method finds the maximum value that lies in all four of the given Maps.
   *
   * @param redData       A Map representing the red color data of an image.
   * @param greenData     A Map representing the green color data of an image.
   * @param blueData      A Map representing the blue color data of an image.
   * @param intensityData A Map representing the intensity data of an image.
   * @return An integer representing the max value of all the Map's values.
   */
  private int mostFrequent(Map<Integer, Integer> redData, Map<Integer, Integer> greenData,
                           Map<Integer, Integer> blueData, Map<Integer, Integer> intensityData) {
    int max = 0;
    
    for (Integer i : redData.keySet()) {
      max = Math.max(max, redData.get(i));
    }
    
    for (Integer i : greenData.values()) {
      max = Math.max(max, i);
    }
    
    for (Integer i : blueData.values()) {
      max = Math.max(max, i);
    }
    
    for (Integer i : intensityData.values()) {
      max = Math.max(max, i);
    }
    
    return max;
  }
  
  @Override
  public void createHistogramData(PixelInfo[][] pixels) throws IllegalArgumentException {
    this.emptyArrayLists();
    
    Map<Integer, Integer> redData = collectData(Red, pixels);
    Map<Integer, Integer> greenData = collectData(Green, pixels);
    Map<Integer, Integer> blueData = collectData(Blue, pixels);
    Map<Integer, Integer> intensityData = collectData(Intensity, pixels);
    
    int max = mostFrequent(redData, greenData, blueData, intensityData);
    
    for (int i = 0; i <= 255; i++) {
      if (redData.containsKey(i)) {
        this.redBarsData.add(new DataBar(
            (int) ((double) redData.get(i) * this.height) / max / 4,
            1));
      } else {
        this.redBarsData.add(new DataBar(0, 1));
      }
    }
    
    for (int j = 0; j <= 255; j++) {
      if (greenData.containsKey(j)) {
        this.greenBarsData.add(new DataBar(
            (int) ((double) greenData.get(j) * this.height) / max / 4,
            1));
      } else {
        this.greenBarsData.add(new DataBar(0, 1));
      }
    }
    
    for (int k = 0; k <= 255; k++) {
      if (blueData.containsKey(k)) {
        this.blueBarsData.add(new DataBar(
            (int) ((double) blueData.get(k) * this.height) / max / 4,
            1));
      } else {
        this.blueBarsData.add(new DataBar(0, 1));
      }
    }
    
    for (int l = 0; l <= 255; l++) {
      if (intensityData.containsKey(l)) {
        this.intensityBarsData.add(new DataBar(
            (int) ((double) intensityData.get(l) * this.height) / max / 4,
            1));
      } else {
        this.intensityBarsData.add(new DataBar(0, 1));
      }
    }
  }
  
  /**
   * This method finds the frequencies of a given component's values (0 through 255) from the
   * given image.
   *
   * @param component A PixelComponent representing the desired component values.
   * @param pixels   A 2D array representing the image's pixels that should be analyzed for values.
   * @return A Map containing the frequencies of a given component's values.
   * @throws IllegalArgumentException when an image is not found in this model.
   */
  private Map<Integer, Integer> collectData(PixelComponents component, PixelInfo[][] pixels)
      throws IllegalArgumentException {
    Map<Integer, Integer> data = new HashMap<>();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        
        int currNum = pixels[i][j].getPixelInfo().get(component);
        if (data.containsKey(currNum)) {
          data.put(currNum, data.get(currNum) + 1);
        } else {
          data.put(currNum, 1);
        }
      }
    }
    return data;
  }
  
  // frequency  |
  //            |
  //            |
  //            |
  //            |
  //            |
  //            |
  //             ---------------------------------
  //             color values
  
  protected void paintComponent(Graphics g) {
    
    // red histogram axis
    g.drawLine(29, 44, 29, 119); // y axis
    g.drawLine(29, 119, 285, 119); // x axis
    
    // green histogram axis
    int shiftRight = 300;
    g.drawLine(29 + shiftRight, 44, 29 + shiftRight, 119);
    g.drawLine(29 + shiftRight, 119, 285 + shiftRight, 119);
    
    // blue histogram axis
    int shiftDown = 139;
    g.drawLine(29, 44 + shiftDown, 29, 119 + shiftDown);
    g.drawLine(29, 119 + shiftDown, 285, 119 + shiftDown);
    
    // intensity histogram axis
    g.drawLine(29 + shiftRight, 44 + shiftDown, 29 + shiftRight, 119 + shiftDown);
    g.drawLine(29 + shiftRight, 119 + shiftDown, 285 + shiftRight, 119 + shiftDown);
    
    g.drawString("x axis represents frequency and y axis represents color values [0, 255]",
        29, 15);
    
    g.drawString("red histogram", 123, 40);
    g.drawString("green histogram", 123 + shiftRight, 40);
    g.drawString("blue histogram", 123, 40 + shiftDown);
    g.drawString("intensity histogram", 123 + shiftRight, 40 + shiftDown);
    
    if (this.redBarsData.size() > 0 && this.blueBarsData.size() > 0
        && this.greenBarsData.size() > 0 && this.intensityBarsData.size() > 0) {
      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.red);
        g.fillRect(i * this.redBarsData.get(i).getWidth() + ((int) (0.05 * (double) this.width)),
            this.height - this.redBarsData.get(i).getHeight() - (int) (this.height / 1.65),
            this.redBarsData.get(i).getWidth(), this.redBarsData.get(i).getHeight());
      }
      
      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.green);
        g.fillRect(i * this.greenBarsData.get(i).getWidth() +
                ((int) (0.55 * (double) this.width)),
            this.height - this.greenBarsData.get(i).getHeight() - (int) (this.height / 1.65),
            this.greenBarsData.get(i).getWidth(), this.greenBarsData.get(i).getHeight());
      }
      
      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.blue);
        g.fillRect(i * this.blueBarsData.get(i).getWidth() +
                ((int) (0.05 * (double) this.width)),
            this.height - this.blueBarsData.get(i).getHeight() - this.height / 7,
            this.blueBarsData.get(i).getWidth(), this.blueBarsData.get(i).getHeight());
      }
      
      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.gray);
        g.fillRect(i * this.intensityBarsData.get(i).getWidth() +
                ((int) (0.55 * (double) this.width)),
            this.height - this.intensityBarsData.get(i).getHeight() - this.height / 7,
            this.intensityBarsData.get(i).getWidth(),
            this.intensityBarsData.get(i).getHeight());
      }
    }
  }
}
