package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.IPModel;
import model.IPModelState.PixelComponents;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Red;

/**
 * Represents the implementation of the creation of the four histogram graphs for a given image
 */
public class IPHistogramImpl extends JPanel implements IPHistogram {

  private ArrayList<DataBar> redBarsData;
  private ArrayList<DataBar> greenBarsData;
  private ArrayList<DataBar> blueBarsData;
  private ArrayList<DataBar> intensityBarsData;
  private final IPModel model;
  private final int height;
  private final int width;

  /**
   * Represents the constructor to create histogram models
   *
   * @param height height of each histogram
   * @param width width of each histogram
   * @param model an IPModel object
   */
  public IPHistogramImpl(int height, int width, IPModel model) {
    this.redBarsData = new ArrayList();
    this.greenBarsData = new ArrayList();
    this.blueBarsData = new ArrayList();
    this.intensityBarsData = new ArrayList();
    this.height = height;
    this.width = width;
    this.model = model;

    this.setPreferredSize(new Dimension(width, height));

  }

  private void emptyArrayLists() {
    this.redBarsData = new ArrayList();
    this.greenBarsData = new ArrayList();
    this.blueBarsData = new ArrayList();
    this.intensityBarsData = new ArrayList();
  }

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
  public void createHistogramModel(String imgName) {
    this.emptyArrayLists();

    Map<Integer, Integer> redData = collectData(Red, imgName);
    Map<Integer, Integer> greenData = collectData(Green, imgName);
    Map<Integer, Integer> blueData = collectData(Blue, imgName);
    Map<Integer, Integer> intensityData = collectData(Intensity, imgName);

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
        this.intensityBarsData.add(new DataBar( 0, 1));
      }
    }
  }

  private Map<Integer, Integer> collectData(PixelComponents component, String imgName) {
    Map<Integer, Integer> data = new HashMap<>();
    for (int i = 0; i < this.model.getHeight(imgName); i++) {
      for (int j = 0; j < this.model.getWidth(imgName); j++) {

        int currNum = this.model.getPixelInfo(imgName, i, j).get(component);
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

    g.drawString("x axis represents frequency and y axis represents color values [0, 255]", 29, 15);

    g.drawString("red histogram", 123, 40);
    g.drawString("green histogram", 123 + shiftRight, 40);
    g.drawString("blue histogram", 123, 40 + shiftDown);
    g.drawString("intensity histogram", 123 + shiftRight, 40 + shiftDown);

    if (this.redBarsData.size() > 0 && this.blueBarsData.size() > 0
            && this.greenBarsData.size() > 0 && this.intensityBarsData.size() > 0) {
      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.red);
        g.fillRect(i * this.redBarsData.get(i).getWidth() + ((int)(0.05 * (double) this.width)),
                this.height - this.redBarsData.get(i).getHeight() - (int) (this.height / 1.65),
                this.redBarsData.get(i).getWidth(), this.redBarsData.get(i).getHeight());
      }

      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.green);
        g.fillRect(i * this.greenBarsData.get(i).getWidth() + ((int) (0.55 * (double) this.width)),
                this.height - this.greenBarsData.get(i).getHeight() - (int) (this.height / 1.65),
                this.greenBarsData.get(i).getWidth(), this.greenBarsData.get(i).getHeight());
      }

      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.blue);
        g.fillRect(i * this.blueBarsData.get(i).getWidth() + ((int)(0.05 * (double) this.width)),
                this.height - this.blueBarsData.get(i).getHeight() - this.height/7,
                this.blueBarsData.get(i).getWidth(), this.blueBarsData.get(i).getHeight());
      }

      for (int i = 0; i <= 255; i++) {
        g.setColor(Color.gray);
        g.fillRect(i * this.intensityBarsData.get(i).getWidth() + ((int) (0.55 * (double) this.width)),
                this.height - this.intensityBarsData.get(i).getHeight() - this.height/7,
                this.intensityBarsData.get(i).getWidth(), this.intensityBarsData.get(i).getHeight());
      }
    }
  }
}
