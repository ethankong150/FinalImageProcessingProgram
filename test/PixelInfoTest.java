import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import model.PixelInfo;

import static model.IPModelState.PixelComponents.Max;

import static model.IPModelState.PixelComponents;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Luma;
import static model.IPModelState.PixelComponents.Red;
import static model.IPModelState.PixelComponents.Value;
import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for the PixelInfo class.
 */
public class PixelInfoTest {
  
  PixelInfo p1 = new PixelInfo(47, 247, 54, 255);
  
  // tests for first constructor
  @Test
  public void testFirstConstructor() {
    Map<PixelComponents, Integer> result = this.p1.getPixelInfo();
    Map<PixelComponents, Integer> expected = new HashMap<PixelComponents, Integer>();
    expected.put(Max, 255);
    expected.put(Red, 47);
    expected.put(Green, 247);
    expected.put(Blue, 54);
    expected.put(Value, 247);
    expected.put(Intensity, 116);
    expected.put(Luma, 190);
  
    for (PixelComponents c : result.keySet()) {
      assertEquals(result.get(c), expected.get(c));
    }
  }
  
  // tests for IAE for first constructor
  // negative red
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAENegativeRed() {
    new PixelInfo(-5, 100, 100, 255);
  }
  
  // over 255 red
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAEOver255Red() {
    new PixelInfo(256, 100, 100, 255);
  }
  
  // negative green
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAENegativeGreen() {
    new PixelInfo(100, -1, 100, 255);
  }
  
  // over 255 green
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAEOver255Green() {
    new PixelInfo(100, 260, 100, 255);
  }
  
  // negative blue
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAENegativeBlue() {
    new PixelInfo(100, 100, -1, 255);
  }
  
  // over 255 blue
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAEOver255Blue() {
    new PixelInfo(100, 100, 256, 255);
  }
  
  // negative max
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAENegMax() {
    new PixelInfo(100, 100, 256, -1);
  }
  
  // red value over max
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAERedOverMax() {
    new PixelInfo(100, 99, 5, 99);
  }
  
  // green value over max
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAEGreenOverMax() {
    new PixelInfo(99, 100, 5, 99);
  }
  
  // Blue value over max
  @Test(expected = IllegalArgumentException.class)
  public void testFirstConstructorIAEBlueOverMax() {
    new PixelInfo(5, 99, 100, 99);
  }
  
  // tests for getPixelInfo method
  @Test
  public void testGetPixelInfo() {
    Map<PixelComponents, Integer> result = this.p1.getPixelInfo();
    Map<PixelComponents, Integer> expected = new HashMap<PixelComponents, Integer>();
    expected.put(Max, 255);
    expected.put(Red, 47);
    expected.put(Green, 247);
    expected.put(Blue, 54);
    expected.put(Value, 247);
    expected.put(Intensity, 116);
    expected.put(Luma, 190);
    
    for (PixelComponents c : result.keySet()) {
      assertEquals(result.get(c), expected.get(c));
    }
  }
}