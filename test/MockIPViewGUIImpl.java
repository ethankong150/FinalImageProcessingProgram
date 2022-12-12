import controller.IPControllerGUI;
import controller.IPControllerGUIImpl;
import model.PixelInfo;
import view.IPViewGUI;

/**
 * This class represents a mock class for the IPViewGUIImpl class.
 */
public class MockIPViewGUIImpl implements IPViewGUI {
  private final MockIPHistogramImpl histogramPanel;
  private final StringBuilder log;

  /**
   * This first constructor creates a mock IPViewGUI with a log in the form of a StringBuilder
   * and a mock IPHistogram to replicate an actual IPHistogramImpl's relationship with an actual
   * IPViewGUIImpl.
   */
  public MockIPViewGUIImpl() {
    this.histogramPanel = new MockIPHistogramImpl();
    this.log = new StringBuilder();
  }

  @Override
  public void setControllerGUI(IPControllerGUIImpl controller) throws IllegalArgumentException {
    this.log.append("setControllerGUI called");
  }

  @Override
  public void seeGUI() {
    this.log.append("seeGUI called");
  }

  @Override
  public void renderPopUpMessage(String body, String title, int type) {
    this.log.append("renderPopUpMessage called with this body, title, and type: "
            + body + ", " + title + ", " + type);
  }

  @Override
  public void drawImage(PixelInfo[][] pixels) throws IllegalArgumentException {
    this.log.append("drawImage called with array of (height, width): " + pixels.length +
        ", " + pixels[0].length);
  }

  @Override
  public void drawHistogram(PixelInfo[][] pixels) throws IllegalArgumentException {
    this.histogramPanel.createHistogramData(pixels);
    this.log.append("drawHistogram called with array of (height, width): " + pixels.length +
        ", " + pixels[0].length);
  }

  /**
   * This method retrieves the private log of the mock view impl.
   *
   * @return A String representing a log of all methods called.
   */
  public String getLog() {
    return this.log.toString();
  }

  /**
   * This method retrieves the private log of the mock histogram impl created in the constructor.
   *
   * @return A String representing a log of all methods called in the histogram.
   */
  public String getHistogramLog() {
    return this.histogramPanel.getLog();
  }
}
