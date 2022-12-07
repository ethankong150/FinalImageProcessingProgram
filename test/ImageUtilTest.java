import org.junit.Test;

import model.PixelInfo;

import static model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for the ImageUtil class.
 */
public class ImageUtilTest {
  
  @Test
  public void testReadPPM() {
    PixelInfo[][] actual = readPPM("res/Image1.ppm");
    PixelInfo[][] expected = new PixelInfo[][]{
        {new PixelInfo(0, 0, 255, 255),
            new PixelInfo(255, 0, 255, 255)},
        {new PixelInfo(0, 0, 255, 255),
            new PixelInfo(255, 0, 255, 255)},
        {new PixelInfo(0, 0, 255, 255),
            new PixelInfo(255, 0, 255, 255)}};
    
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(actual[i][j].getPixelInfo(), expected[i][j].getPixelInfo());
      }
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testReadPPMIAE() {
    readPPM("notathing/yah/hello");
  }
}