package model;

import java.util.Map;

/**
 * This interface represents operations that can be used to monitor the state of an image
 * processing model, without changing it.
 */
public interface IPModelState {
  
  /**
   * This enum represents the components of a single pixel in an image. Every pixel
   * has a red, green, blue, value, intensity, and luma component.
   */
  enum PixelComponents { Max, Red, Green, Blue, Value, Intensity, Luma }
  
  /**
   * This method gets the height of a specified image from the added images.
   *
   * @param imgName A String representing the name of the specified image.
   * @return An integer representing the height of an image.
   * @throws IllegalArgumentException when the given String is not an image name
   *                                  found in the added images.
   */
  int getHeight(String imgName) throws IllegalArgumentException;
  
  /**
   * This method gets the width of a specified image from the added images.
   *
   * @param imgName A String representing the name of the specified image.
   * @return An integer representing the width of an image.
   * @throws IllegalArgumentException when the given String is not an image name
   *                                  found in the added images
   */
  int getWidth(String imgName) throws IllegalArgumentException;
  
  /**
   * This method creates a Map containing the components and corresponding
   * values of a pixel.
   *
   * @param imgName A String representing the name of the specified image.
   * @param row     An integer representing the desired pixel's row coordinate.
   * @param col     An integer representing the desired pixel's column coordinate.
   * @return A Map containing the pixel component and corresponding value of that
   *         component for a pixel.
   * @throws IllegalArgumentException when the given String is not an image name
   *                                  found in the added images or when the given
   *                                  coordinates are invalid
   */
  Map<PixelComponents, Integer> getPixelInfo(String imgName, int row, int col)
      throws IllegalArgumentException;
  
  /**
   * This method gets the 2D array of pixels of the image represented by the given String name.
   * @param imgName A String representing the name of the specified image.
   * @return A 2D array of pixels of the image.
   * @throws IllegalArgumentException when the image does not exist in the added Images field.
   */
  public PixelInfo[][] getPixels(String imgName) throws IllegalArgumentException;
}
