package model;

import java.util.HashMap;
import java.util.Map;

import static model.IPModelState.PixelComponents;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Luma;
import static model.IPModelState.PixelComponents.Max;
import static model.IPModelState.PixelComponents.Red;
import static model.IPModelState.PixelComponents.Value;

/**
 * This class represents a pixel's information (red, green, blue, value, intensity, and luma).
 */
public class PixelInfo {
  private final int max;
  private final int red;
  private final int green;
  private final int blue;
  private final int value;
  private final int intensity;
  private final int luma;
  
  /**
   * This first constructor takes in a red, green, and blue value as integers and initializes a
   * pixel based on this information.
   *
   * @param red   An integer representing the red value.
   * @param green An integer representing the green value.
   * @param blue  An integer representing the blue value.
   * @param max   An integer representing the maximum value supported for red,
   *              green, and blue values.
   * @throws IllegalArgumentException when any of the given integers representing red, green, or
   *                                  blue are under 0 or above 255.
   */
  public PixelInfo(int red, int green, int blue, int max)
      throws IllegalArgumentException {
    if (max < 0) {
      throw new IllegalArgumentException("invalid max value");
    }
    this.max = max;
    
    if (red < 0 || red > this.max) {
      throw new IllegalArgumentException("invalid red value");
    }
    this.red = red;
    
    if (green < 0 || green > this.max) {
      throw new IllegalArgumentException("invalid green value");
    }
    this.green = green;
    
    if (blue < 0 || blue > this.max) {
      throw new IllegalArgumentException("invalid blue value");
    }
    this.blue = blue;
    
    this.value = Math.max(Math.max(this.red, this.green), this.blue);
    
    this.intensity = ((this.red + this.green + this.blue) / 3);
    
    this.luma = (int) (0.2126 * this.red + 0.7152 * this.green + 0.0722 * this.blue);
  }
  
  /**
   * This method creates a Map with the fields of a PixelInfo object.
   *
   * @return A Map where the key represents the component and the value is an integer representing
   *         the corresponding value.
   */
  public Map<PixelComponents, Integer> getPixelInfo() {
    Map<PixelComponents, Integer> pixelInfoIntegerMap = new HashMap<>();
    
    pixelInfoIntegerMap.put(Max, this.max);
    pixelInfoIntegerMap.put(Red, this.red);
    pixelInfoIntegerMap.put(Green, this.green);
    pixelInfoIntegerMap.put(Blue, this.blue);
    pixelInfoIntegerMap.put(Value, this.value);
    pixelInfoIntegerMap.put(Intensity, this.intensity);
    pixelInfoIntegerMap.put(Luma, this.luma);
    
    return pixelInfoIntegerMap;
  }
}
