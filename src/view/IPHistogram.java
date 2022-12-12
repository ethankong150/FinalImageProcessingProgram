package view;

import model.PixelInfo;

/**
 * Interface representing the creation of the histograms used in the GUI.
 */
public interface IPHistogram {

  /**
   * Creates a model of each of the four histogram graphs for a given image.
   *
   * @param pixels 2D array of pixels representing an image
   * @throws IllegalArgumentException when the imgName is not an image in this model.
   */
  void createHistogramData(PixelInfo[][] pixels) throws IllegalArgumentException;
}
