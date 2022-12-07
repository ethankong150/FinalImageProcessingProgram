import org.junit.Test;

import java.io.IOException;

import model.IPModelImpl;
import model.PixelInfo;
import view.IPView;
import view.IPViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents the tests class for the image processing view class.
 */
public class IPViewImplTest {
  
  IPModelImpl m1 = new IPModelImpl();
  
  // 3 x 2 (height x width) image
  PixelInfo[][] i1 = new PixelInfo[][]{
      {new PixelInfo(0, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)},
      {new PixelInfo(0, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)},
      {new PixelInfo(0, 0, 255, 255),
          new PixelInfo(255, 0, 255, 255)}};
  
  // test IAE for first constructor
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAE() {
    new IPViewImpl(null);
  }
  
  @Test
  public void testSecondConstructor() {
    this.m1.addImage("img1", i1);
    StringBuilder s = new StringBuilder();
    IPView testMessageViewException = new IPViewImpl(s);
    try {
      testMessageViewException.renderMessage("Hi");
    } catch (IOException e) {
      fail("you bum");
    }
    assertEquals("Hi", s.toString());
  }
  
  // test IAE for second constructor
  
  // null appendable
  @Test(expected = IllegalArgumentException.class)
  public void testSecondConstructorIAENullAppendable() {
    new IPViewImpl(null);
  }
  
  @Test
  public void testRenderMessage() {
    this.m1.addImage("img1", i1);
    StringBuilder s = new StringBuilder();
    IPView testMessageViewException = new IPViewImpl(s);
    try {
      testMessageViewException.renderMessage("Hi");
    } catch (IOException e) {
      fail("you bum");
    }
    assertEquals("Hi", s.toString());
  }
  
  @Test
  public void testRenderMessageException() {
    CorruptedAppendables ca1 = new CorruptedAppendables();
    
    this.m1.addImage("img1", i1);
    IPView testMessageViewException2 = new IPViewImpl(ca1);
    try {
      testMessageViewException2.renderMessage("Hi!");
      fail();
    } catch (IOException e) {
      //do nothing
    }
  }
}
