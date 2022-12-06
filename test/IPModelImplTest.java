import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.IPModelImpl;
import model.IPModelState.PixelComponents;
import model.PixelInfo;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Luma;
import static model.IPModelState.PixelComponents.Max;
import static model.IPModelState.PixelComponents.Red;
import static model.IPModelState.PixelComponents.Value;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents tests for the IPModelImpl class.
 */
public class IPModelImplTest {
  
  IPModelImpl m1 = new IPModelImpl();
  
  // 3 x 2 (height x width) image
  PixelInfo[][] i1 = new PixelInfo[][]{
      {new PixelInfo(0, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)},
      {new PixelInfo(0, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)},
      {new PixelInfo(0, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)}};
  
  // 4 x 3 (height x width) image
  PixelInfo[][] i2 = new PixelInfo[][]{
      {new PixelInfo(255, 255, 255, 255),
          new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 255, 0, 255)},
      {new PixelInfo(255, 255, 255, 255),
          new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 255, 0, 255)},
      {new PixelInfo(255, 255, 255, 255),
          new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 255, 0, 255)},
      {new PixelInfo(255, 255, 255, 255),
          new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 255, 0, 255)}};
  
  // 4 x 3 (height x width) image
  PixelInfo[][] i3 = new PixelInfo[][]{
      {new PixelInfo(255, 255, 255, 255),
          new PixelInfo(255, 255, 255, 255),
          new PixelInfo(255, 255, 255, 255)},
      {new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 0, 0, 255)},
      {new PixelInfo(255, 0, 0, 255),
          new PixelInfo(255, 0, 0, 255),
          new PixelInfo(255, 0, 0, 255)},
      {new PixelInfo(255, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)}};
  
  // 3 x 3 (height x width) image
  PixelInfo[][] i4 = new PixelInfo[][]{
      {new PixelInfo(255, 255, 255, 255),
          new PixelInfo(255, 255, 255, 255),
          new PixelInfo(255, 255, 255, 255)},
      {new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 0, 0, 255),
          new PixelInfo(0, 0, 0, 255)},
      {new PixelInfo(255, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)}};
  
  // tests for first constructor
  // test addImage-ing an image into model
  @Test
  public void testFirstConstructorAddImageOneImage() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // tests for addImage method
  // addImage an image into the model
  @Test
  public void testAddImageOneImage() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // addImage two images into the model
  @Test
  public void testAddImageTwoImages() {
    // addImage-ing first image
    
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // addImage-ing second image
    
    // addImage image
    this.m1.addImage("image2", this.i2);
    
    // test dimensions
    assertEquals(4, this.m1.getHeight("image2"));
    assertEquals(3, this.m1.getWidth("image2"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image2"); i++) {
      for (int j = 0; j < this.m1.getWidth("image2"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image2", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 2:
            assertEquals(0, red);
            assertEquals(255, green);
            assertEquals(0, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // check first image is still correct
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // overwrite one image over the other by naming the second image the same as the first
  @Test
  public void testAddImageOverwrite() {
    // addImage-ing first image
    
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // addImage-ing second image
    
    // addImage image
    this.m1.addImage("image1", this.i2);
    
    // test dimensions
    assertEquals(4, this.m1.getHeight("image1"));
    assertEquals(3, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 2:
            assertEquals(0, red);
            assertEquals(255, green);
            assertEquals(0, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // tests for IAE in addImage method
  // null image name
  @Test(expected = IllegalArgumentException.class)
  public void testAddImageIAENullImageName() {
    // addImage image
    this.m1.addImage(null, this.i1);
  }
  
  // empty string image name
  @Test(expected = IllegalArgumentException.class)
  public void testAddImageIAEEmptyStringImageName() {
    // addImage image
    this.m1.addImage("", this.i1);
  }
  
  // null pixel arraylist
  @Test(expected = IllegalArgumentException.class)
  public void testAddImageIAENullPixelArrayList() {
    // addImage image
    this.m1.addImage("test", null);
  }
  
  // tests for greyscale method
  // red greyscale
  @Test
  public void testGreyscaleRed() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply red greyscale
    this.m1.greyscale(Red, "image1", "image1redgreyscale");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1redgreyscale"));
    assertEquals(2, this.m1.getWidth("image1redgreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1redgreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1redgreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1redgreyscale", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // green greyscale
  @Test
  public void testGreyscaleGreen() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply green greyscale
    this.m1.greyscale(Green, "image1", "image1greengreyscale");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1greengreyscale"));
    assertEquals(2, this.m1.getWidth("image1greengreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1greengreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1greengreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1greengreyscale", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        assertEquals(0, red);
        assertEquals(0, green);
        assertEquals(0, blue);
      }
    }
  }
  
  // blue greyscale
  @Test
  public void testGreyscaleBlue() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply blue greyscale
    this.m1.greyscale(Blue, "image1", "image1bluegreyscale");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1bluegreyscale"));
    assertEquals(2, this.m1.getWidth("image1bluegreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1bluegreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1bluegreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1bluegreyscale", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        assertEquals(255, red);
        assertEquals(255, green);
        assertEquals(255, blue);
      }
    }
  }
  
  // value greyscale
  @Test
  public void testGreyscaleValue() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply value greyscale
    this.m1.greyscale(Value, "image1", "image1valuegreyscale");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1valuegreyscale"));
    assertEquals(2, this.m1.getWidth("image1valuegreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1valuegreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1valuegreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1valuegreyscale", i, j);
        int value = pInfo.get(Value);
        assertEquals(255, value);
        assertEquals(255, value);
        assertEquals(255, value);
      }
    }
  }
  
  // intensity greyscale
  @Test
  public void testGreyscaleIntensity() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply intensity greyscale
    this.m1.greyscale(Intensity, "image1", "image1intensitygreyscale");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1intensitygreyscale"));
    assertEquals(2, this.m1.getWidth("image1intensitygreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1intensitygreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1intensitygreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1intensitygreyscale", i, j);
        int intensity = pInfo.get(Intensity);
        switch (j) {
          case 0:
            assertEquals(85, intensity);
            assertEquals(85, intensity);
            assertEquals(85, intensity);
            break;
          case 1:
            assertEquals(170, intensity);
            assertEquals(170, intensity);
            assertEquals(170, intensity);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // luma greyscale
  @Test
  public void testGreyscaleLuma() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply luma greyscale
    this.m1.greyscale(Luma, "image1", "image1lumagreyscale");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1lumagreyscale"));
    assertEquals(2, this.m1.getWidth("image1lumagreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1lumagreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1lumagreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1lumagreyscale", i, j);
        int intensity = pInfo.get(Intensity);
        switch (j) {
          case 0:
            assertEquals(18, intensity);
            assertEquals(18, intensity);
            assertEquals(18, intensity);
            break;
          case 1:
            assertEquals(72, intensity);
            assertEquals(72, intensity);
            assertEquals(72, intensity);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // addImage all 6 types of greyscale images and check that all 7 are correctly added
  @Test
  public void testAddImageGreyscale() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    // apply red greyscale
    this.m1.greyscale(Red, "image1", "image1redgreyscale");
    // apply green greyscale
    this.m1.greyscale(Green, "image1", "image1greengreyscale");
    // apply blue greyscale
    this.m1.greyscale(Blue, "image1", "image1bluegreyscale");
    // apply value greyscale
    this.m1.greyscale(Value, "image1", "image1valuegreyscale");
    // apply intensity greyscale
    this.m1.greyscale(Intensity, "image1", "image1intensitygreyscale");
    // apply luma greyscale
    this.m1.greyscale(Luma, "image1", "image1lumagreyscale");
    
    // testing red
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1redgreyscale"));
    assertEquals(2, this.m1.getWidth("image1redgreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1redgreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1redgreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1redgreyscale", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // testing green
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1greengreyscale"));
    assertEquals(2, this.m1.getWidth("image1greengreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1greengreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1greengreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1greengreyscale", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        assertEquals(0, red);
        assertEquals(0, green);
        assertEquals(0, blue);
      }
    }
    
    // testing blue
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1bluegreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1bluegreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1bluegreyscale", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        assertEquals(255, red);
        assertEquals(255, green);
        assertEquals(255, blue);
      }
    }
    
    // testing value
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1valuegreyscale"));
    assertEquals(2, this.m1.getWidth("image1valuegreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1valuegreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1valuegreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1valuegreyscale", i, j);
        int value = pInfo.get(Value);
        assertEquals(255, value);
        assertEquals(255, value);
        assertEquals(255, value);
      }
    }
    
    // testing intensity
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1intensitygreyscale"));
    assertEquals(2, this.m1.getWidth("image1intensitygreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1intensitygreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1intensitygreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1intensitygreyscale", i, j);
        int intensity = pInfo.get(Intensity);
        switch (j) {
          case 0:
            assertEquals(85, intensity);
            assertEquals(85, intensity);
            assertEquals(85, intensity);
            break;
          case 1:
            assertEquals(170, intensity);
            assertEquals(170, intensity);
            assertEquals(170, intensity);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // testing luma
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1lumagreyscale"));
    assertEquals(2, this.m1.getWidth("image1lumagreyscale"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1lumagreyscale"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1lumagreyscale"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1lumagreyscale", i, j);
        int intensity = pInfo.get(Intensity);
        switch (j) {
          case 0:
            assertEquals(18, intensity);
            assertEquals(18, intensity);
            assertEquals(18, intensity);
            break;
          case 1:
            assertEquals(72, intensity);
            assertEquals(72, intensity);
            assertEquals(72, intensity);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // tests for IAE in greyscale method
  // no added images
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGreyscaleNoAddedImages() {
    this.m1.greyscale(Red, "image1", "image1redgreyscale");
  }
  
  // image called has not been Added
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGreyscaleNotInAddedImages() {
    this.m1.addImage("image1", this.i1);
    this.m1.greyscale(Red, "image2", "image1redgreyscale");
  }
  
  // tests for brighten method
  // brighten by 66
  @Test
  public void testBrightenPos66() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply brighten
    this.m1.brighten(66, "image1", "image1brighten");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1brighten"));
    assertEquals(2, this.m1.getWidth("image1brighten"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1brighten"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1brighten"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1brighten", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(66, red);
            assertEquals(66, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(66, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // brighten by -66
  @Test
  public void testBrightenNeg66() {
    // addImage image
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // apply brighten
    this.m1.brighten(-66, "image1", "image1brighten");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1brighten"));
    assertEquals(2, this.m1.getWidth("image1brighten"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1brighten"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1brighten"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("image1brighten", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(189, blue);
            break;
          case 1:
            assertEquals(189, red);
            assertEquals(0, green);
            assertEquals(189, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // tests for IAE in brighten method
  // no added images
  @Test(expected = IllegalArgumentException.class)
  public void testIAEBrightenNoAddedImages() {
    this.m1.brighten(2, "image1", "image1brighten");
  }
  
  // image called has not been Added
  @Test(expected = IllegalArgumentException.class)
  public void testIAEBrightenNotInAddedImages() {
    this.m1.addImage("image1", this.i1);
    this.m1.brighten(5, "image2", "image1brighten");
  }
  
  @Test
  public void testFlipHorizontal() {
    this.m1.addImage("image1", this.i1);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    this.m1.flip(false, "image1", "flippedHorizontal");
    
    //cases are flipped  from the previous test
    for (int i = 0; i < this.m1.getHeight("flippedHorizontal"); i++) {
      for (int j = 0; j < this.m1.getWidth("flippedHorizontal"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("flippedHorizontal", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    // addImage image
    this.m1.addImage("image2", this.i2);
    
    // test dimensions
    assertEquals(4, this.m1.getHeight("image2"));
    assertEquals(3, this.m1.getWidth("image2"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image2"); i++) {
      for (int j = 0; j < this.m1.getWidth("image2"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image2", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 2:
            assertEquals(0, red);
            assertEquals(255, green);
            assertEquals(0, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    this.m1.flip(false, "image2", "flippedHoriztonal2");
    
    for (int i = 0; i < this.m1.getHeight("flippedHoriztonal2"); i++) {
      for (int j = 0; j < this.m1.getWidth("flippedHoriztonal2"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("flippedHoriztonal2", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(255, green);
            assertEquals(0, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 2:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  @Test
  public void testFlipVertical() {
    
    this.m1.addImage("image3", this.i3);
    
    // test dimensions
    assertEquals(4, this.m1.getHeight("image3"));
    assertEquals(3, this.m1.getWidth("image3"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image3"); i++) {
      for (int j = 0; j < this.m1.getWidth("image3"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image3", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (i) {
          case 0:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          
          case 2:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 3:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    this.m1.flip(true, "image3", "flippedVertical");
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("flippedVertical"); i++) {
      for (int j = 0; j < this.m1.getWidth("flippedVertical"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("flippedVertical", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (i) {
          case 0:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 2:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 3:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    
    
    this.m1.addImage("image4", this.i4);
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image4"));
    assertEquals(3, this.m1.getWidth("image4"));
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image4"); i++) {
      for (int j = 0; j < this.m1.getWidth("image4"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image4", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (i) {
          case 0:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          
          case 2:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
    this.m1.flip(true, "image4", "flippedVertical2");
    
    // check pixels
    for (int i = 0; i < this.m1.getHeight("flippedVertical2"); i++) {
      for (int j = 0; j < this.m1.getWidth("flippedVertical2"); j++) {
        Map<PixelComponents, Integer> pInfo =
            this.m1.getPixelInfo("flippedVertical2", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (i) {
          case 0:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(0, blue);
            break;
          case 2:
            assertEquals(255, red);
            assertEquals(255, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIAEFlipNoAddedImages() {
    this.m1.flip(true, "image1", "image1flipped");
  }
  
  // image called has not been Added
  @Test(expected = IllegalArgumentException.class)
  public void testIAEFlipNotInAddedImages() {
    this.m1.addImage("image1", this.i1);
    this.m1.flip(true, "image2", "image2flipped");
  }
  
  // test getHeight method
  @Test
  public void testGetHeight() {
    this.m1.addImage("image1", this.i1);
    this.m1.addImage("image2", this.i2);
    this.m1.addImage("image3", this.i3);
    this.m1.addImage("image4", this.i4);
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(4, this.m1.getHeight("image2"));
    assertEquals(4, this.m1.getHeight("image3"));
    assertEquals(3, this.m1.getHeight("image4"));
  }
  
  // test IAE for getHeight method
  // no added images
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGetHeightNoAddedImages() {
    this.m1.getHeight("image1");
  }
  
  // image called has not been Added
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGetHeightNotInAddedImages() {
    this.m1.addImage("image1", this.i1);
    this.m1.getHeight("image2");
  }
  
  // tests for getWidth method
  @Test
  public void testGetWidth() {
    this.m1.addImage("image1", this.i1);
    this.m1.addImage("image2", this.i2);
    this.m1.addImage("image3", this.i3);
    this.m1.addImage("image4", this.i4);
    assertEquals(2, this.m1.getWidth("image1"));
    assertEquals(3, this.m1.getWidth("image2"));
    assertEquals(3, this.m1.getWidth("image3"));
    assertEquals(3, this.m1.getWidth("image4"));
  }
  
  // tests for IAE in getWidth method
  // no added images
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGetWidthNoAddedImages() {
    this.m1.getWidth("image1");
  }
  
  // image called has not been Added
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGetWidthNotInAddedImages() {
    this.m1.addImage("image1", this.i1);
    this.m1.getWidth("image2");
  }
  
  // tests for getPixelInfo method
  @Test
  public void testGetPixelInfo() {
    this.m1.addImage("image1", this.i1);
    Map<PixelComponents, Integer> test = new HashMap<>();
    test.put(Intensity, 85);
    test.put(Luma, 18);
    test.put(Blue, 255);
    test.put(Green, 0);
    test.put(Red, 0);
    test.put(Value, 255);
    test.put(Max, 255);
    assertEquals(test,
        this.m1.getPixelInfo("image1", 0, 0));
    
    Map<PixelComponents, Integer> test2 = new HashMap<>();
    test2.put(Intensity, 170);
    test2.put(Luma, 72);
    test2.put(Blue, 255);
    test2.put(Green, 0);
    test2.put(Red, 255);
    test2.put(Value, 255);
    test2.put(Max, 255);
    assertEquals(test2,
        this.m1.getPixelInfo("image1", 0, 1));
  }
  
  // tests for IAE in getPixelInfo method
  // no added images
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGetPixelInfoNoAddedImages() {
    this.m1.getPixelInfo("image1", 0, 0);
  }
  
  // image called has not been added
  @Test(expected = IllegalArgumentException.class)
  public void testIAEGetPixelInfoNotInAddedImages() {
    this.m1.addImage("image1", this.i1);
    this.m1.getPixelInfo("image2", 0, 0);
  }
  
  // out of bounds coordinates
  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInfoOutOfBounds() {
    this.m1.addImage("image1", this.i1);
    this.m1.getPixelInfo("image1", -2, -2);
    this.m1.getPixelInfo("image1", 2, -2);
    this.m1.getPixelInfo("image1", 2, 2);
    this.m1.getPixelInfo("image1", 0, 10);
    this.m1.getPixelInfo("image1", 10, 0);
    this.m1.getPixelInfo("image1", 10, 10);
  }
  
  // tests for save method
  // test saving image2 to res
  @Test
  public void testSaveImage2PPM() {
    // check file does not currently exist
    // if this test has been run previously, delete image2.ppm or comment this test out
    //assertFalse(new File("res/image2.ppm").exists());
    // add image to added images
    this.m1.addImage("image2", this.i2);
    // save image to desktop
    this.m1.save("res/image2.ppm", "image2");
    // check file exists
    assertTrue(new File("res/image2.ppm").exists());
  }
  
  // save image1 to res
  // load the just-saved image1 and name it image1check
  // ensure the previously loaded image1 and image1check are the same
  @Test
  public void testSaveImage1LoadImage1CheckPPM() {
    // add image to added images
    this.m1.addImage("image1", this.i1);
    // save image to desktop
    this.m1.save("res/image1.ppm", "image1");
    
    // load image1 and name it image1check
    this.m1.load("res/image1.ppm", "image1check");
    
    // check the images are the same
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        Map<PixelComponents, Integer> i1Pixel = this.m1.getPixelInfo("image1", i, j);
        int maxi1 = i1Pixel.get(Max);
        int redi1 = i1Pixel.get(Red);
        int greeni1 = i1Pixel.get(Green);
        int bluei1 = i1Pixel.get(Blue);
        int valuei1 = i1Pixel.get(Value);
        int intensityi1 = i1Pixel.get(Intensity);
        int lumai1 = i1Pixel.get(Luma);
        
        Map<PixelComponents, Integer> i1checkPixel =
            this.m1.getPixelInfo("image1check", i, j);
        int maxi1check = i1checkPixel.get(Max);
        int redi1check = i1checkPixel.get(Red);
        int greeni1check = i1checkPixel.get(Green);
        int bluei1check = i1checkPixel.get(Blue);
        int valuei1check = i1checkPixel.get(Value);
        int intensityi1check = i1checkPixel.get(Intensity);
        int lumai1check = i1checkPixel.get(Luma);
        
        // check each PixelComponent
        assertEquals(maxi1, maxi1check);
        assertEquals(redi1, redi1check);
        assertEquals(greeni1, greeni1check);
        assertEquals(bluei1, bluei1check);
        assertEquals(valuei1, valuei1check);
        assertEquals(intensityi1, intensityi1check);
        assertEquals(lumai1, lumai1check);
      }
    }
  }
  
  // tests for IAE for save method
  // no added images
  @Test(expected = IllegalArgumentException.class)
  public void testIAESaveNoAddedImages() {
    this.m1.save("res/image1.ppm", "image1");
  }
  
  // image called has not been added
  @Test(expected = IllegalArgumentException.class)
  public void testIAESaveNotInAddedImages() {
    this.m1.addImage("image1", this.i1);
    this.m1.save("res/image2.ppm", "image2");
  }
  
  // path is not valid
  @Test(expected = IllegalArgumentException.class)
  public void testIAESavePathNotValid() {
    this.m1.addImage("image1", this.i1);
    this.m1.save("john/image1.ppm", "image1");
  }
  
  // tests for load method
  // test loading image1dupe duplicate from res
  @Test
  public void testLoadImage1DupePPM() {
    this.m1.load("res/image1dupe.ppm", "image1res");
    
    // test dimensions
    assertEquals(3, this.m1.getHeight("image1res"));
    assertEquals(2, this.m1.getWidth("image1res"));
    
    // test pixels
    // check pixels
    for (int i = 0; i < this.m1.getHeight("image1res"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1res"); j++) {
        Map<PixelComponents, Integer> pInfo = this.m1.getPixelInfo("image1res", i, j);
        int red = pInfo.get(Red);
        int green = pInfo.get(Green);
        int blue = pInfo.get(Blue);
        int max = pInfo.get(Max);
        assertEquals(255, max);
        switch (j) {
          case 0:
            assertEquals(0, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          case 1:
            assertEquals(255, red);
            assertEquals(0, green);
            assertEquals(255, blue);
            break;
          default:
            fail("you failed");
        }
      }
    }
  }
  
  // test loading techsupport.ppm from res
  @Test
  public void testLoadTechsupportFromResPPM() {
    this.m1.load("res/techsupport.ppm", "techsupport");
    
    // test dimensions
    assertEquals(188, this.m1.getHeight("techsupport"));
    assertEquals(250, this.m1.getWidth("techsupport"));
    
    // test max pixel value
    Map<PixelComponents, Integer> thisPixel = this.m1.getPixelInfo("techsupport", 0, 0);
    int actualMax = thisPixel.get(Max);
    assertEquals(255, actualMax);
  }
  
  // tests for IAE for load method
  // illegal path so file not going to be found
  @Test(expected = IllegalArgumentException.class)
  public void testLoadIAEIllegalPath() {
    this.m1.load("not a valid path lol", "not happening");
  }
  
  // file not to be found because it does not exist
  @Test(expected = IllegalArgumentException.class)
  public void testLoadIAEFileDoesNotExist() {
    this.m1.load("res/somethingrandoom.ppm", "not happening");
  }
  
  // invalid ppm file
  @Test(expected = IllegalArgumentException.class)
  public void testLoadIAEInvalidPPM1() {
    this.m1.load("/users/ry.cheung/desktop/legoat.png", "legoat");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testLoadIAEInvalidPPM2() {
    this.m1.load("res/not a valid ppm.ppm", "yikes");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testLoadIAEInvalidPPM3() {
    this.m1.load("res/not a valid ppm.txt", "yikes");
  }
  
  // try to save with no extension
  @Test(expected = IllegalArgumentException.class)
  public void testSaveIAENoExtension() {
    this.m1.addImage("image1", this.i1);
    this.m1.save("res/image1", "image1");
  }
  
  // try to save with invalid extension
  @Test(expected = IllegalArgumentException.class)
  public void testSaveIAEInvalidExtension() {
    this.m1.addImage("image1", this.i1);
    this.m1.save("res/image1.zzz", "image1");
  }
  
  // save png
  @Test
  public void testSavePNG1() {
    this.m1.load("res/techsupport.ppm", "ts");
    this.m1.save("res/techsupport.png", "ts");
    
    // check that file exists
    assertTrue(new File("res/techsupport.png").exists());
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.png", "tspng");
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tspng", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        assertEquals(redPNG, redExpected);
        assertEquals(greenPNG, greenExpected);
        assertEquals(bluePNG, blueExpected);
      }
    }
  }
  
  @Test
  public void testSavePNG2() {
    this.m1.load("res/image1.ppm", "i1");
    this.m1.save("res/image1.png", "i1");
    
    // check that file exists
    assertTrue(new File("res/image1.png").exists());
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/image1.png", "i1png");
    for (int i = 0; i < this.m1.getHeight("i1"); i++) {
      for (int j  = 0; j < this.m1.getWidth("i1"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("i1png", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("i1", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        assertEquals(redPNG, redExpected);
        assertEquals(greenPNG, greenExpected);
        assertEquals(bluePNG, blueExpected);
      }
    }
  }
  
  // save jpeg
  @Test
  public void testSaveJPEG() {
    this.m1.load("res/techsupport.ppm", "ts");
    this.m1.save("res/techsupport.jpeg", "ts");
    
    // check that file exists
    assertTrue(new File("res/techsupport.jpeg").exists());
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.jpeg", "tsjpeg");
    int redSumDif = 0;
    int greenSumDif = 0;
    int blueSumDif = 0;
    int pixelCount = 0;
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tsjpeg", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        redSumDif += Math.abs(redPNG - redExpected);
        greenSumDif += Math.abs(greenPNG - greenExpected);
        blueSumDif += Math.abs(bluePNG - blueExpected);
        pixelCount++;
      }
    }
    // average difference between values is small
    assertTrue(redSumDif / pixelCount < 5);
    assertTrue(greenSumDif / pixelCount < 4);
    assertTrue(blueSumDif / pixelCount < 6);
  }
  
  // save jpg
  @Test
  public void testSaveJPG() {
    this.m1.load("res/techsupport.ppm", "ts");
    this.m1.save("res/techsupport.jpg", "ts");
    
    // check that file exists
    assertTrue(new File("res/techsupport.jpg").exists());
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.jpg", "tsjpg");
    int redSumDif = 0;
    int greenSumDif = 0;
    int blueSumDif = 0;
    int pixelCount = 0;
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tsjpg", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        redSumDif += Math.abs(redPNG - redExpected);
        greenSumDif += Math.abs(greenPNG - greenExpected);
        blueSumDif += Math.abs(bluePNG - blueExpected);
        pixelCount++;
      }
    }
    // average difference between values is small
    assertTrue(redSumDif / pixelCount < 5);
    assertTrue(greenSumDif / pixelCount < 4);
    assertTrue(blueSumDif / pixelCount < 6);
  }
  
  // save bmp
  @Test
  public void testSaveBMP() {
    this.m1.load("res/techsupport.ppm", "ts");
    this.m1.save("res/techsupport.BMP", "ts");
    
    // check that file exists
    assertTrue(new File("res/techsupport.BMP").exists());
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.BMP", "tsBMP");
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tsBMP", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        assertEquals(redPNG, redExpected);
        assertEquals(greenPNG, greenExpected);
        assertEquals(bluePNG, blueExpected);
      }
    }
  }
  
  // try to load with no extension
  @Test(expected = IllegalArgumentException.class)
  public void testLoadIAENoExtension() {
    this.m1.load("res/LeGoat", "nah");
  }
  
  // try to load with invalid extension
  @Test(expected = IllegalArgumentException.class)
  public void testLoadIAEInvalidExtension() {
    this.m1.load("res/techsupport.txt", "nah");
  }
  
  // load png
  @Test
  public void testLoadPNG() {
    this.m1.load("res/techsupport.ppm", "ts");
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.png", "tspng");
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tspng", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        assertEquals(redPNG, redExpected);
        assertEquals(greenPNG, greenExpected);
        assertEquals(bluePNG, blueExpected);
      }
    }
  }
  
  // load jpeg
  @Test
  public void testLoadJPEG() {
    this.m1.load("res/techsupport.ppm", "ts");
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.jpeg", "tsjpeg");
    int redSumDif = 0;
    int greenSumDif = 0;
    int blueSumDif = 0;
    int pixelCount = 0;
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tsjpeg", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        redSumDif += Math.abs(redPNG - redExpected);
        greenSumDif += Math.abs(greenPNG - greenExpected);
        blueSumDif += Math.abs(bluePNG - blueExpected);
        pixelCount++;
      }
    }
    // average difference between values is small
    assertTrue(redSumDif / pixelCount < 5);
    assertTrue(greenSumDif / pixelCount < 4);
    assertTrue(blueSumDif / pixelCount < 6);
  }
  
  // load jpg
  @Test
  public void testLoadJPG() {
    this.m1.load("res/techsupport.ppm", "ts");
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.jpg", "tsjpg");
    int redSumDif = 0;
    int greenSumDif = 0;
    int blueSumDif = 0;
    int pixelCount = 0;
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tsjpg", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        redSumDif += Math.abs(redPNG - redExpected);
        greenSumDif += Math.abs(greenPNG - greenExpected);
        blueSumDif += Math.abs(bluePNG - blueExpected);
        pixelCount++;
      }
    }
    // average difference between values is small
    assertTrue(redSumDif / pixelCount < 5);
    assertTrue(greenSumDif / pixelCount < 4);
    assertTrue(blueSumDif / pixelCount < 6);
  }
  
  // load bmp
  @Test
  public void testLoadBMP() {
    this.m1.load("res/techsupport.ppm", "ts");
    
    // reload it into the model and check the pixels are as expected
    // to ensure it was saved successfully
    this.m1.load("res/techsupport.bmp", "tsbmp");
    for (int i = 0; i < this.m1.getHeight("ts"); i++) {
      for (int j  = 0; j < this.m1.getWidth("ts"); j++) {
        Map<PixelComponents, Integer> thisPixelInfo =
            this.m1.getPixelInfo("tsbmp", i, j);
        int redPNG = thisPixelInfo.get(Red);
        int greenPNG = thisPixelInfo.get(Green);
        int bluePNG = thisPixelInfo.get(Blue);
        
        Map<PixelComponents, Integer> thisPixelInfoEx =
            this.m1.getPixelInfo("ts", i, j);
        int redExpected = thisPixelInfoEx.get(Red);
        int greenExpected = thisPixelInfoEx.get(Green);
        int blueExpected = thisPixelInfoEx.get(Blue);
        
        assertEquals(redPNG, redExpected);
        assertEquals(greenPNG, greenExpected);
        assertEquals(bluePNG, blueExpected);
      }
    }
  }
  
  // filter
  // sharpen
  @Test
  public void testFilterSharpen() {
    double[][] sharpen =
        {{-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.250, 0.250, 0.250, -0.125},
            {-0.125, 0.250, 1.000, 0.250, -0.125},
            {-0.125, 0.250, 0.250, 0.250, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    
    this.m1.addImage("image1", this.i1);
    this.m1.filter(sharpen, "image1", "image1sharpen");
    
    PixelInfo[][] expected = new PixelInfo[][]{
        {new PixelInfo(95, 0, 255, 255),
            new PixelInfo(255, 0, 255, 255)},
        {new PixelInfo(191, 0, 255, 255),
            new PixelInfo(255, 0, 255, 255)},
        {new PixelInfo(95, 0, 255, 255),
            new PixelInfo(255, 0, 255, 255)}};
    
    for (int i =  0; i < this.m1.getHeight("image1sharpen"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1sharpen"); j++) {
        assertEquals(this.m1.getPixelInfo("image1sharpen", i, j),
            expected[i][j].getPixelInfo());
      }
    }
  }
  
  // blur
  @Test
  public void testFilterBlur() {
    double[][] blur =
        {{0.0625, 0.1250, 0.0625},
            {0.1250, 0.2500, 0.1250},
            {0.0625, 0.1250, 0.0625}};
    
    this.m1.addImage("image1", this.i1);
    this.m1.filter(blur, "image1", "image1blur");
    
    PixelInfo[][] expected = new PixelInfo[][]{
        {new PixelInfo(47, 0, 143, 255),
            new PixelInfo(95, 0, 143, 255)},
        {new PixelInfo(63, 0, 191, 255),
            new PixelInfo(127, 0, 191, 255)},
        {new PixelInfo(47, 0, 143, 255),
            new PixelInfo(95, 0, 143, 255)}};
    
    for (int i =  0; i < this.m1.getHeight("image1blur"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1blur"); j++) {
        assertEquals(this.m1.getPixelInfo("image1blur", i, j),
            expected[i][j].getPixelInfo());
      }
    }
  }
  
  // color transformation
  // luma
  @Test
  public void testColorTransformationLuma() {
    double[][] greyscale_luma =
        {{0.216, 0.7152, 0.0722},
            {0.216, 0.7152, 0.0722},
            {0.216, 0.7152, 0.0722}};
  
    this.m1.addImage("image1", this.i1);
    this.m1.colorTransformation(greyscale_luma, "image1", "image1luma");
  
    PixelInfo[][] expected = new PixelInfo[][]{
        {new PixelInfo(18, 18, 18, 255),
            new PixelInfo(73, 73, 73, 255)},
        {new PixelInfo(18, 18, 18, 255),
            new PixelInfo(73, 73, 73, 255)},
        {new PixelInfo(18, 18, 18, 255),
            new PixelInfo(73, 73, 73, 255)}};
  
    for (int i =  0; i < this.m1.getHeight("image1luma"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1luma"); j++) {
        assertEquals(this.m1.getPixelInfo("image1luma", i, j),
            expected[i][j].getPixelInfo());
      }
    }
  }
  
  // sepia
  @Test
  public void testColorTransformationSep() {
    double[][] sepia = {{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
  
    this.m1.addImage("image1", this.i1);
    this.m1.colorTransformation(sepia, "image1", "image1sep");
  
    PixelInfo[][] expected = new PixelInfo[][]{
        {new PixelInfo(48, 42, 33, 255),
            new PixelInfo(148, 131, 102, 255)},
        {new PixelInfo(48, 42, 33, 255),
            new PixelInfo(148, 131, 102, 255)},
        {new PixelInfo(48, 42, 33, 255),
            new PixelInfo(148, 131, 102, 255)}};
  
    for (int i =  0; i < this.m1.getHeight("image1sep"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1sep"); j++) {
        assertEquals(this.m1.getPixelInfo("image1sep", i, j),
            expected[i][j].getPixelInfo());
      }
    }
  }
}