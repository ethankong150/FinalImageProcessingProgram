package view;

/**
 * Interface representing the creation of the histograms used in the GUI.
 */
public interface IPHistogram {

  /**
   * Creates a model of each of the four histogram graphs for a given image.
   *
   * @param imgName name of the image
   * @throws IllegalArgumentException when the imgName is not an image in this model.
   */
  void createHistogramData(String imgName) throws IllegalArgumentException;
}
