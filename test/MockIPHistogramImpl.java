import model.PixelInfo;
import view.IPHistogram;

/**
 * This class represents a mock IPHistogram class.
 */
public class MockIPHistogramImpl implements IPHistogram {
  private final StringBuilder log;

  /**
   * This constructor creates a mock IPHistogram object with a log in the form of a StringBuilder.
   */
  public MockIPHistogramImpl() {
    this.log = new StringBuilder();
  }

  @Override
  public void createHistogramData(PixelInfo[][] pixels) throws IllegalArgumentException {
    this.log.append("createHistogramData with array of (height, width): " + pixels.length +
        ", " + pixels[0].length);
  }

  /**
   * This method retrieves the private log of the mock histogram impl.
   *
   * @return A String representing a log of all methods called.
   */
  public String getLog() {
    return this.log.toString();
  }
}
