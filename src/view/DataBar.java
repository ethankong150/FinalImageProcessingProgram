package view;

/**
 * Class represents each data point/bar on a histogram.
 */
public class DataBar {
  private final int height;
  private final int width;

  /**
   * Constructor that represents each individual histogram bar.
   *
   * @param height Height of the bar
   * @param width Width of the bar
   * @throws IllegalArgumentException when a parameter given is negative.
   */
  public DataBar(int height, int width) throws IllegalArgumentException {
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("negative parameter given");
    }
    this.height = height;
    this.width = width;
  }

  /**
   * This method gets information about the state of this
   * datapoint object -- specifically, the height.
   * @return An integer representing the height of the data point/bar.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * This method gets information about the state of this
   * datapoint object -- specifically, the width.
   * @return An integer representing the width of the data point/bar.
   */
  public int getWidth() {
    return this.width;
  }
}
