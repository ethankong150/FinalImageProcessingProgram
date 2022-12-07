package model;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {
  
  /**
   * Read an image file in the PPM format and create and return a double indexed
   * array that represents the pixel info of the pixels in the file.
   *
   * @param path the path of the file.
   * @throws IllegalArgumentException if the given path is not valid
   */
  public static PixelInfo[][] readPPM(String path) throws IllegalArgumentException {
    Scanner sc;
    
    // check to see if the path to the file exists
    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found from path");
    }
    
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string
    // throw away comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      try {
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      } catch (StringIndexOutOfBoundsException e) {
        throw new IllegalArgumentException("Cannot read: must be not be valid PPM");
      }
    }
    
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    
    PixelInfo[][] loadedPixels = new PixelInfo[height][width];
    
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        
        // create pixel
        PixelInfo thisPixel = new PixelInfo(r, g, b, maxValue);
        
        // assign to loadedPixels
        loadedPixels[i][j] = thisPixel;
      }
    }
    
    return loadedPixels;
  }
  
  //demo main
  
  /**
   * This method represents a demo main method that calls readPPM.
   * @param args An array representing the user's inputs.
   */
  public static void main(String[] args) {
    String filename;
    
    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "Koala.ppm";
    }
    
    ImageUtil.readPPM(filename);
  }
}

