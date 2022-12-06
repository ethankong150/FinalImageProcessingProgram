import org.junit.Test;

import java.io.StringReader;

import controller.IPControllerImpl;
import model.IPModel;
import model.IPModelImpl;
import view.IPView;
import view.IPViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for the ImageProcessing class. More specifically, we are
 * testing the read method with the startIP method.
 */
public class ImageProcessingTest {
  IPModel model = new IPModelImpl();
  Appendable ap = new StringBuilder();
  IPView view = new IPViewImpl(this.ap);
  
  @Test
  public void testReadIPStart() {
    String str = ImageProcessing.read("res/examplescript.txt");
    assertEquals("load res/image1.ppm i1 " +
        "vertical-flip i1 i1vert " +
        "sepia i1vert i1vertsepia q", str);
    new IPControllerImpl(model, view, new StringReader(str)).startIP();
    assertEquals("What would you like to do?\n" +
        "load success!\n" +
        "What would you like to do?\n" +
        "vertical-flip success!\n" +
        "What would you like to do?\n" +
        "sepia success!\n" +
        "What would you like to do?\n" +
        "IP quit!", this.ap.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testReadIAE() {
    ImageProcessing.read("mickey mouse's clubhouse");
  }
}