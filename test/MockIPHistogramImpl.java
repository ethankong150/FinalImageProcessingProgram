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
  public void createHistogramData(String imgName) throws IllegalArgumentException {
    this.log.append("createHistogramData with this imgName: " + imgName);
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
