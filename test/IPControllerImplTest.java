import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import view.IPView;

import static model.IPModelState.PixelComponents;
import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Luma;
import static model.IPModelState.PixelComponents.Max;
import static model.IPModelState.PixelComponents.Red;
import static model.IPModelState.PixelComponents.Value;

import controller.IPController;
import controller.IPControllerImpl;
import model.IPModelImpl;
import model.PixelInfo;
import view.IPViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents the testing of how the controller works with the rest of the
 * image processing program.
 */
public class IPControllerImplTest {
  
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
  
  Appendable a1 = new StringBuilder();
  IPView v1 = new IPViewImpl(this.a1);
  
  private IPController makeController(String input) {
    return new IPControllerImpl(this.m1, this.v1, new StringReader(input));
  }
  
  
  @Test
  public void testConstructorAtStart1NoQ() {
    try {
      makeController("").startIP();
      fail("you fail");
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\n", this.a1.toString());
    }
  }
  
  @Test
  public void testConstructorAtStart2WithQ() {
    makeController("q").startIP();
    assertEquals("What would you like to do?\nIP quit!", this.a1.toString());
  }
  
  @Test
  public void testConstructorAfterCommandNoQ() {
    try {
      makeController("load res/techsupport.ppm ts").startIP();
      fail("you fail");
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nload success!\n" +
          "What would you like to do?\n", this.a1.toString());
      assertEquals(188, this.m1.getHeight("ts"));
      assertEquals(250, this.m1.getWidth("ts"));
    }
  }
  
  @Test
  public void testConstructorAfterCommandWithQ() {
    makeController("load res/image1.ppm image1 q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
        "What would you like to do?\nIP quit!", this.a1.toString());
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions1() {
    m1.addImage("image1", i1);
    StringBuilder s = new StringBuilder();
    IPView testView = new IPViewImpl(s);
    StringReader s2 = new StringReader("");
    
    
    new IPControllerImpl(null, null, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void ConstructorExceptions2() {
    m1.addImage("image1", i1);
    StringBuilder s = new StringBuilder();
    IPView testView = new IPViewImpl(s);
    StringReader s2 = new StringReader("");
    new IPControllerImpl(m1, testView, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void ConstructorExceptions3() {
    m1.addImage("image1", i1);
    StringBuilder s = new StringBuilder();
    IPView testView = new IPViewImpl(s);
    StringReader s2 = new StringReader("");
    new IPControllerImpl(m1, null, s2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void ConstructorExceptions4() {
    m1.addImage("image1", i1);
    StringBuilder s = new StringBuilder();
    IPView testView = new IPViewImpl(s);
    StringReader s2 = new StringReader("");
    new IPControllerImpl(null, testView, s2);
  }
  
  @Test
  public void testQuit1() {
    makeController("q").startIP();
    assertEquals("What would you like to do?\nIP quit!", this.a1.toString());
  }
  
  @Test
  public void testQuit2() {
    makeController("load res/image1.ppm image1 q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
        "What would you like to do?\nIP quit!", this.a1.toString());
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
  }
  
  @Test
  public void testQuitAfterOneCommand() {
    makeController("load res/image1.ppm image1 q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
        "What would you like to do?\nIP quit!", this.a1.toString());
    assertEquals(3, this.m1.getHeight("image1"));
    assertEquals(2, this.m1.getWidth("image1"));
  }
  
  @Test
  public void testQuitAfterTwoCommands() {
    makeController("load res/techsupport.ppm ts red-component ts ts-red q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
        "What would you like to do?\nred-component success!\n" +
        "What would you like to do?\nIP quit!", this.a1.toString());
    assertEquals(188, this.m1.getHeight("ts"));
    assertEquals(250, this.m1.getWidth("ts"));
    assertEquals(188, this.m1.getHeight("ts-red"));
    assertEquals(250, this.m1.getWidth("ts-red"));
    Map<PixelComponents, Integer> tsCheck = new HashMap<>();
    tsCheck.put(Max, 255);
    tsCheck.put(Red, 45);
    tsCheck.put(Green, 39);
    tsCheck.put(Blue, 36);
    tsCheck.put(Value, 45);
    tsCheck.put(Intensity, 40);
    tsCheck.put(Luma, 40);
    assertEquals(tsCheck, this.m1.getPixelInfo("ts", 0, 0));
    Map<PixelComponents, Integer> tsRedCheck = new HashMap<>();
    tsRedCheck.put(Max, 255);
    tsRedCheck.put(Red, 45);
    tsRedCheck.put(Green, 45);
    tsRedCheck.put(Blue, 45);
    tsRedCheck.put(Value, 45);
    tsRedCheck.put(Intensity, 45);
    tsRedCheck.put(Luma, 45);
    assertEquals(tsRedCheck, this.m1.getPixelInfo("ts-red", 0, 0));
  }
  
  @Test
  public void testInvalidCommandNoQ1() {
    try {
      makeController("notacommand").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\n" +
          "Invalid command given: notacommand\nWhat would you like to do?\n", this.a1.toString());
    }
  }
  
  @Test
  public void testInvalidCommandNoQ2() {
    try {
      makeController("notacommand alsonotacommand").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\n" +
              "Invalid command given: notacommand\nWhat would you like to do?\n" +
              "Invalid command given: alsonotacommand\nWhat would you like to do?\n",
          this.a1.toString());
    }
  }
  
  @Test
  public void testCommandHandlerInvalidCommandArgumentsGiven() {
    makeController("load res/techsupport.ppm ts brighten oops q").startIP();
    assertEquals("What would you like to do?\nload success!\nWhat would you like to do?\n" +
            "Error: invalid command arguments given\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
  }
  
  @Test
  public void testLoad() {
    try {
      makeController("load res/techsupport.ppm ts").startIP();
      fail("you fail");
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nload success!\n" +
          "What would you like to do?\n", this.a1.toString());
      assertEquals(188, this.m1.getHeight("ts"));
      assertEquals(250, this.m1.getWidth("ts"));
    }
  }
  
  @Test
  public void testBrighten() {
    try {
      makeController("load res/techsupport.ppm ts brighten 10 ts ts-b").startIP();
      fail("you fail");
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nload success!\n" +
              "What would you like to do?\nbrighten success!\nWhat would you like to do?\n",
          this.a1.toString());
      assertEquals(188, this.m1.getHeight("ts"));
      assertEquals(250, this.m1.getWidth("ts"));
      assertEquals(188, this.m1.getHeight("ts-b"));
      assertEquals(250, this.m1.getWidth("ts-b"));
      Map<PixelComponents, Integer> tsCheck = new HashMap<>();
      tsCheck.put(Max, 255);
      tsCheck.put(Red, 45);
      tsCheck.put(Green, 39);
      tsCheck.put(Blue, 36);
      tsCheck.put(Value, 45);
      tsCheck.put(Intensity, 40);
      tsCheck.put(Luma, 40);
      assertEquals(tsCheck, this.m1.getPixelInfo("ts", 0, 0));
      Map<PixelComponents, Integer> tsBCheck = new HashMap<>();
      tsBCheck.put(Max, 255);
      tsBCheck.put(Red, 55);
      tsBCheck.put(Green, 49);
      tsBCheck.put(Blue, 46);
      tsBCheck.put(Value, 55);
      tsBCheck.put(Intensity, 50);
      tsBCheck.put(Luma, 50);
      assertEquals(tsBCheck, this.m1.getPixelInfo("ts-b", 0, 0));
    }
  }
  
  @Test
  public void testVerticalFlip() {
    this.m1.addImage("image3", this.i3);
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
    
    try {
      makeController("vertical-flip image3 flippedVertical").startIP();
      fail("you fail");
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nvertical-flip success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      assertEquals(4, this.m1.getHeight("image3"));
      assertEquals(3, this.m1.getWidth("image3"));
      assertEquals(4, this.m1.getHeight("flippedvertical"));
      assertEquals(3, this.m1.getWidth("flippedvertical"));
      
      for (int i = 0; i < this.m1.getHeight("flippedvertical"); i++) {
        for (int j = 0; j < this.m1.getWidth("flippedvertical"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("flippedvertical", i, j);
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
    }
  }
  
  @Test
  public void testHorizontalFlip() {
    this.m1.addImage("image1", this.i1);
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
    
    try {
      makeController("horizontal-flip image1 flippedHorizontal").startIP();
      fail("you fail");
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nhorizontal-flip success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      assertEquals(3, this.m1.getHeight("image1"));
      assertEquals(2, this.m1.getWidth("image1"));
      assertEquals(3, this.m1.getHeight("flippedhorizontal"));
      assertEquals(2, this.m1.getWidth("flippedhorizontal"));
      
      for (int i = 0; i < this.m1.getHeight("flippedhorizontal"); i++) {
        for (int j = 0; j < this.m1.getWidth("flippedhorizontal"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("flippedhorizontal", i, j);
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
    }
  }
  
  @Test
  public void testRedGreyScale() {
    this.m1.addImage("image1", this.i1);
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
    
    try {
      makeController("red-component image1 image1red").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nred-component success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      
      assertEquals(3, this.m1.getHeight("image1"));
      assertEquals(2, this.m1.getWidth("image1"));
      assertEquals(3, this.m1.getHeight("image1red"));
      assertEquals(2, this.m1.getWidth("image1red"));
      
      for (int i = 0; i < this.m1.getHeight("image1red"); i++) {
        for (int j = 0; j < this.m1.getWidth("image1red"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("image1red", i, j);
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
  }
  
  @Test
  public void testGreenGreyScale() {
    this.m1.addImage("image1", this.i1);
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
    
    try {
      makeController("green-component image1 image1green").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\ngreen-component success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      
      assertEquals(3, this.m1.getHeight("image1"));
      assertEquals(2, this.m1.getWidth("image1"));
      assertEquals(3, this.m1.getHeight("image1green"));
      assertEquals(2, this.m1.getWidth("image1green"));
      
      for (int i = 0; i < this.m1.getHeight("image1green"); i++) {
        for (int j = 0; j < this.m1.getWidth("image1green"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("image1green", i, j);
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
  }
  
  @Test
  public void testBlueGreyScale() {
    this.m1.addImage("image1", this.i1);
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
    
    try {
      makeController("blue-component image1 image1blue").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nblue-component success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      
      assertEquals(3, this.m1.getHeight("image1"));
      assertEquals(2, this.m1.getWidth("image1"));
      assertEquals(3, this.m1.getHeight("image1blue"));
      assertEquals(2, this.m1.getWidth("image1blue"));
      
      for (int i = 0; i < this.m1.getHeight("image1blue"); i++) {
        for (int j = 0; j < this.m1.getWidth("image1blue"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("image1blue", i, j);
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
  }
  
  @Test
  public void testGreyScaleByValue() {
    this.m1.addImage("image1", this.i1);
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
    
    try {
      makeController("value-component image1 image1value").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nvalue-component success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      
      assertEquals(3, this.m1.getHeight("image1"));
      assertEquals(2, this.m1.getWidth("image1"));
      assertEquals(3, this.m1.getHeight("image1value"));
      assertEquals(2, this.m1.getWidth("image1value"));
      
      for (int i = 0; i < this.m1.getHeight("image1value"); i++) {
        for (int j = 0; j < this.m1.getWidth("image1value"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("image1value", i, j);
          int value = pInfo.get(Value);
          assertEquals(255, value);
          assertEquals(255, value);
          assertEquals(255, value);
        }
      }
    }
  }
  
  @Test
  public void testGreyScaleByIntensity() {
    this.m1.addImage("image1", this.i1);
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
    
    try {
      makeController("intensity-component image1 image1intensity").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nintensity-component success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      
      assertEquals(3, this.m1.getHeight("image1"));
      assertEquals(2, this.m1.getWidth("image1"));
      assertEquals(3, this.m1.getHeight("image1intensity"));
      assertEquals(2, this.m1.getWidth("image1intensity"));
      
      for (int i = 0; i < this.m1.getHeight("image1intensity"); i++) {
        for (int j = 0; j < this.m1.getWidth("image1intensity"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("image1intensity", i, j);
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
  }
  
  @Test
  public void testGreyScaleByLuma() {
    this.m1.addImage("image1", this.i1);
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
    
    try {
      makeController("luma-component image1 image1luma").startIP();
    } catch (IllegalStateException e) {
      assertEquals("What would you like to do?\nluma-component success!" +
          "\nWhat would you like to do?\n", this.a1.toString());
      
      assertEquals(3, this.m1.getHeight("image1"));
      assertEquals(2, this.m1.getWidth("image1"));
      assertEquals(3, this.m1.getHeight("image1luma"));
      assertEquals(2, this.m1.getWidth("image1luma"));
      
      for (int i = 0; i < this.m1.getHeight("image1luma"); i++) {
        for (int j = 0; j < this.m1.getWidth("image1luma"); j++) {
          Map<PixelComponents, Integer> pInfo =
              this.m1.getPixelInfo("image1luma", i, j);
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
  }
  
  @Test
  public void testSave1() {
    makeController("load res/techsupport.ppm ts green-component ts tsgreen " +
        "save res/techsupportgreen.ppm tsgreen q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\ngreen-component success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupportgreen.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupportgreen.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tsgreen"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tsgreen"));
    int max = this.m1.getPixelInfo("tsgreen", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testSave2() {
    makeController("load res/techsupport.ppm ts blue-component ts tsblue " +
        "save res/techsupportblue.ppm tsblue q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\nblue-component success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupportblue.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupportblue.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tsblue"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tsblue"));
    int max = this.m1.getPixelInfo("tsblue", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testSave3() {
    makeController("load res/techsupport.ppm ts value-component ts tsvalue " +
        "save res/techsupportvalue.ppm tsvalue q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\nvalue-component success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupportvalue.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupportvalue.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tsvalue"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tsvalue"));
    int max = this.m1.getPixelInfo("tsvalue", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testSave4() {
    makeController("load res/techsupport.ppm ts intensity-component ts tsintensity " +
        "save res/techsupportintensity.ppm tsintensity q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\nintensity-component success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupportintensity.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupportintensity.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tsintensity"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tsintensity"));
    int max = this.m1.getPixelInfo("tsintensity", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testSave5() {
    makeController("load res/techsupport.ppm ts luma-component ts tsluma " +
        "save res/techsupportluma.ppm tsluma q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\nluma-component success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupportluma.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupportluma.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tsluma"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tsluma"));
    int max = this.m1.getPixelInfo("tsluma", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testSave6() {
    makeController("load res/techsupport.ppm ts brighten -100 ts tsdarken " +
        "save res/techsupportdarken.ppm tsdarken q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\nbrighten success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupportdarken.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupportdarken.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tsdarken"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tsdarken"));
    int max = this.m1.getPixelInfo("tsdarken", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testSave7() {
    makeController("load res/techsupport.ppm ts vertical-flip ts tsvert " +
        "save res/techsupportvertical.ppm tsvert q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\nvertical-flip success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupportvertical.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupportvertical.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tsvert"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tsvert"));
    int max = this.m1.getPixelInfo("tsvert", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testSave8() {
    makeController("load res/techsupport.ppm ts horizontal-flip ts tshori " +
        "save res/techsupporthorizontal.ppm tshori q").startIP();
    assertEquals("What would you like to do?\nload success!\n" +
            "What would you like to do?\nhorizontal-flip success!\n" +
            "What would you like to do?\nsave success!\nWhat would you like to do?\nIP quit!",
        this.a1.toString());
    assertTrue(new File("res/techsupporthorizontal.ppm").exists());
    
    Scanner sc = null;
    
    try {
      sc = new Scanner(new FileInputStream("res/techsupporthorizontal.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    assertEquals("P3", token);
    assertEquals(sc.nextInt(), this.m1.getWidth("tshori"));
    assertEquals(sc.nextInt(), this.m1.getHeight("tshori"));
    int max = this.m1.getPixelInfo("tshori", 0, 0).get(Max);
    assertEquals(sc.nextInt(), max);
  }
  
  @Test
  public void testBlur() {
    this.m1.load("res/image1.png", "image1");
    
    makeController("blur image1 image1blur q").startIP();
    
    PixelInfo[][] expected = new PixelInfo[][]{
        {new PixelInfo(47, 0, 143, 255),
            new PixelInfo(95, 0, 143, 255)},
        {new PixelInfo(63, 0, 191, 255),
            new PixelInfo(127, 0, 191, 255)},
        {new PixelInfo(47, 0, 143, 255),
            new PixelInfo(95, 0, 143, 255)}};
    
    for (int i = 0; i < this.m1.getHeight("image1blur"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1blur"); j++) {
        assertEquals(this.m1.getPixelInfo("image1blur", i, j),
            expected[i][j].getPixelInfo());
      }
    }
  }
  
  @Test
  public void testSharpen() {
    this.m1.load("res/image1.png", "image1");
  
    makeController("sharpen image1 image1sharpen q").startIP();
  
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
  
  @Test
  public void testGreyScaleLuma() {
    this.m1.load("res/image1.png", "image1");
  
    makeController("greyscale-luma image1 image1luma q").startIP();
  
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
  
  @Test
  public void testSepia() {
    this.m1.load("res/image1.png", "image1");
  
    makeController("sepia image1 image1sep q").startIP();
  
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
  
  @Test
  public void testDealingWithBadInputs() {
    makeController("wow this is nothing and should be ignored but i have decided" +
        " to load res/image1.ppm image1 q").startIP();
    assertEquals("What would you like to do?\n" +
        "Invalid command given: wow\n" +
        "What would you like to do?\n" +
        "Invalid command given: this\n" +
        "What would you like to do?\n" +
        "Invalid command given: is\n" +
        "What would you like to do?\n" +
        "Invalid command given: nothing\n" +
        "What would you like to do?\n" +
        "Invalid command given: and\n" +
        "What would you like to do?\n" +
        "Invalid command given: should\n" +
        "What would you like to do?\n" +
        "Invalid command given: be\n" +
        "What would you like to do?\n" +
        "Invalid command given: ignored\n" +
        "What would you like to do?\n" +
        "Invalid command given: but\n" +
        "What would you like to do?\n" +
        "Invalid command given: i\n" +
        "What would you like to do?\n" +
        "Invalid command given: have\n" +
        "What would you like to do?\n" +
        "Invalid command given: decided\n" +
        "What would you like to do?\n" +
        "Invalid command given: to\n" +
        "What would you like to do?\n" +
        "load success!\n" +
        "What would you like to do?\n" +
        "IP quit!", this.a1.toString());
    for (int i = 0; i < this.m1.getHeight("image1"); i++) {
      for (int j = 0; j < this.m1.getWidth("image1"); j++) {
        assertEquals(this.i1[i][j].getPixelInfo(),
            this.m1.getPixelInfo("image1", i, j));
      }
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testRenderMessageIAE() {
    IPView view = new IPViewImpl(new CorruptedAppendables());
    IPControllerImpl controller = new IPControllerImpl(this.m1, view, new StringReader("q"));
    controller.startIP();
  }
  
  
}
