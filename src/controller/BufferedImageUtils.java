package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import model.IPModel;
import model.IPModelState;
import model.PixelInfo;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Red;

/**
 * This class represents an utilities class containing methods for converting to and creating
 * Buffered Images.
 */
public class BufferedImageUtils {

  /**
   * Convert an image from a specified path to a buffered image.
   *
   * @param path A String representing the relative path to retrieve the desired file.
   * @return A buffered image of the same image that was retrieved from the given path.
   * @throws IllegalArgumentException when the path is unable to be read or does not exist.
   */
  public static BufferedImage turnIntoBI(String path) throws IllegalArgumentException {
    BufferedImage img;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      throw new IllegalArgumentException("Error reading");
    }
    return img;
  }

  /**
   * Create a buffered image from an image in the given model.
   *
   * @param width   An integer representing the width of the image.
   * @param height  An integer representing the height of the image.
   * @param pixels A 2D array representing the pixels of the desired image.
   * @return A buffered image of the desired image from the model.
   * @throws IllegalArgumentException if the given imgName does not exist in the given model.
   */
  public static BufferedImage createBI(int width, int height, PixelInfo[][] pixels)
          throws IllegalArgumentException {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Map<IPModelState.PixelComponents, Integer> thisPixelInfo = pixels[i][j].getPixelInfo();
        int red = thisPixelInfo.get(Red);
        int green = thisPixelInfo.get(Green);
        int blue = thisPixelInfo.get(Blue);

        int rgb = (red << 16) | (green << 8) | (blue);

        img.setRGB(j, i, rgb);
      }
    }
    return img;
  }
}
