import org.junit.Test;

import java.awt.image.BufferedImage;

import model.IPModel;
import model.IPModelImpl;

import static controller.BufferedImageUtils.createBI;
import static controller.BufferedImageUtils.turnIntoBI;
import static org.junit.Assert.assertEquals;

/**
 * This class represents a test class for the BufferedImageUtils class.
 */
public class BufferedImageUtilsTest {

  @Test(expected = IllegalArgumentException.class)
  public void testTurnIntoBIIAE() {
    turnIntoBI("wow");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateBIIAE() {
    IPModel m = new IPModelImpl();
    createBI(5, 5, m.getPixels("wow"));
  }

  @Test
  public void testTurnIntoBI() {
    BufferedImage img = turnIntoBI("res/image1.png");
    assertEquals(img.getWidth(), 2);
    assertEquals(img.getHeight(), 3);
  }

  @Test
  public void testCreateBI() {
    IPModel m = new IPModelImpl();
    m.load("res/image1.ppm", "i1");
    BufferedImage img = createBI(2, 3, m.getPixels("i1"));
    assertEquals(img.getWidth(), 2);
    assertEquals(img.getHeight(), 3);
  }

  @Test
  public void testTurnIntoBICreateBIEquality() {
    BufferedImage b1 = turnIntoBI("res/image1.png");
    IPModel m = new IPModelImpl();
    m.load("res/image1.ppm", "i1");
    BufferedImage b2 = createBI(2, 3, m.getPixels("i1"));
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(b1.getRGB(j, i), b2.getRGB(j, i));
      }
    }
  }
}