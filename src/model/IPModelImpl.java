package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import controller.BufferedImageUtils;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Max;
import static model.IPModelState.PixelComponents.Red;

/**
 * This class represents an object of a model of an image processor.
 */
public class IPModelImpl implements IPModel {

  private final Map<String, PixelInfo[][]> addedImages;
  private PixelInfo[][] maskImage;

  /**
   * This first constructor takes no arguments and calls the abstract constructor to create
   * a model.
   */
  public IPModelImpl() {
    this.addedImages = new HashMap<>();
    this.maskImage = null;
  }

  @Override
  public void addImage(String imgName, PixelInfo[][] imgPixels) throws IllegalArgumentException {
    if (imgName == null || imgName.equals("")) {
      throw new IllegalArgumentException("illegal image name given");
    }
    if (imgPixels == null) {
      throw new IllegalArgumentException("null pixels arraylist given");
    }
    this.addedImages.put(imgName, imgPixels);
  }

  @Override
  public void greyscale(PixelComponents components, String imgName, String rename)
          throws IllegalArgumentException {
    // check to see if imgName exists in addedImages already (throw IAE if not)
    this.imageExists(imgName);

    // create empty [][] with same size as the one attached to the given string
    PixelInfo[][] greyScaledImage = new PixelInfo[this.getHeight(imgName)][this.getWidth(imgName)];

    // for each row
    for (int i = 0; i < this.getHeight(imgName); i++) {
      // for each column
      for (int j = 0; j < this.getWidth(imgName); j++) {

        // is mask image null or is this mask image's pixel black
        if (this.maskImage == null || this.maskImageBlack(i, j)) {
          // get the pixel info of this pixel
          Map<PixelComponents, Integer> pixelInfoGrey = this.getPixelInfo(imgName, i, j);
          // get the desired value based on the component
          int desiredValue = pixelInfoGrey.get(components);
          // create a new pixel with that desired value as R G and B and put it into
          // greyScaledImage[][]
          greyScaledImage[i][j] = new PixelInfo(desiredValue, desiredValue, desiredValue,
                  pixelInfoGrey.get(Max));
        } else {
          Map<PixelComponents, Integer> ogPixelInfo = this.getPixelInfo(imgName, i, j);
          greyScaledImage[i][j] = new PixelInfo(ogPixelInfo.get(Red), ogPixelInfo.get(Green),
                  ogPixelInfo.get(Blue), ogPixelInfo.get(Max));
        }
      }
    }
    this.addImage(rename, greyScaledImage);
    this.resetMaskImage();
  }

  @Override
  public void flip(boolean vert, String imgName, String rename) throws IllegalArgumentException {
    // check to see if imgName exists in addedImages already (throw IAE if not)
    this.imageExists(imgName);

    PixelInfo[][] orig = addedImages.get(imgName);
    PixelInfo[][] flipped = new PixelInfo[this.getHeight(imgName)][this.getWidth(imgName)];

    // if user wants to flip vertically
    if (vert) {
      for (int i = 0; i < this.getWidth(imgName); i++) {
        for (int j = 0; j < this.getHeight(imgName); j++) {
          flipped[j][i] = orig[this.getHeight(imgName) - j - 1][i];
        }
      }
    }
    // if user wants to flip horizontally
    else {
      // for each row
      for (int i = 0; i < this.getHeight(imgName); i++) {
        for (int j = 0; j < this.getWidth(imgName); j++) {
          flipped[i][j] = orig[i][this.getWidth(imgName) - j - 1];
        }
      }
    }
    this.addImage(rename, flipped);
  }

  @Override
  public void brighten(int amount, String imgName, String rename)
          throws IllegalArgumentException {
    // check to see if imgName exists in addedImages already (throw IAE if not)
    this.imageExists(imgName);

    // copy the [][] from the desired image
    PixelInfo[][] brightenedImage = new PixelInfo[this.getHeight(imgName)][this.getWidth(imgName)];

    // for each row
    for (int i = 0; i < this.getHeight(imgName); i++) {
      for (int j = 0; j < this.getWidth(imgName); j++) {
        // get the pixel info of this pixel from the copy
        Map<PixelComponents, Integer> pixelInfoBrightened = this.getPixelInfo(imgName, i, j);
        // change the red, green, and blue values by the given amount if it is between 0 and 255
        // (else round to the nearest bound)
        int redBrightened = rgbInBounds(pixelInfoBrightened.get(Red) + amount,
                pixelInfoBrightened);
        int greenBrightened = rgbInBounds(pixelInfoBrightened.get(Green) + amount,
                pixelInfoBrightened);
        int blueBrightened = rgbInBounds(pixelInfoBrightened.get(Blue) + amount,
                pixelInfoBrightened);
        // create new pixel with new R G B values and put it into brightenedImage[][]
        brightenedImage[i][j] = new PixelInfo(redBrightened, greenBrightened, blueBrightened,
                pixelInfoBrightened.get(Max));
      }
    }
    this.addImage(rename, brightenedImage);
  }

  /**
   * This method ensures that the given value is within the bounds of accepted RGB values. If not,
   * the value should be rounded to the nearest bound.
   *
   * @param i An integer representing the value that should be checked.
   * @return An integer representing a valid RGB value. If the value is less than 0, return 0. If
   * the value is greater than 255, return 255.
   */
  private int rgbInBounds(int i, Map<PixelComponents, Integer> p) {
    if (i < 0) {
      return 0;
    } else if (i > p.get(Max)) {
      return p.get(Max);
    } else {
      return i;
    }
  }

  @Override
  public void save(String path, String imgName) throws IllegalArgumentException {
    // check to see if imgName exists in addedImages already (throw IAE if not)
    this.imageExists(imgName);

    int height = this.getHeight(imgName);
    int width = this.getWidth(imgName);

    String extension = checkExtension(path);

    if (extension.equalsIgnoreCase(".ppm")) {
      this.savePPM(path, imgName, height, width);
    } else {
      this.saveElse(path, imgName, height, width);
    }
  }

  /**
   * This method serves as a helper to the save method and specifically saves PPM files.
   *
   * @param path    A String representing the path on the computer to save the image
   * @param imgName A String representing the desired image's name in the model
   * @param height  An integer representing the height of the desired image
   * @param width   An integer representing the width of the desired image
   * @throws IllegalArgumentException when the method is unable to write to the path or when an
   *                                  error occurs during the method
   */
  private void savePPM(String path, String imgName, int height, int width)
          throws IllegalArgumentException {
    // create file
    try {
      // create file at path location
      BufferedWriter bw = new BufferedWriter(new FileWriter(path));

      Map<PixelComponents, Integer> thisPixel = this.getPixelInfo(imgName, 0, 0);

      // add the formatting of PPM (i.e. P3, width x height, max value, etc)
      bw.write("P3\n");
      bw.write("#Created by RC & EK\n");
      bw.write(width + " " + height + "\n");
      bw.write(thisPixel.get(Max) + "\n");

      // write the RGB values
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Map<PixelComponents, Integer> thisPixelInfo = this.getPixelInfo(imgName, i, j);
          bw.write(thisPixelInfo.get(Red) + "\n");
          bw.write(thisPixelInfo.get(Green) + "\n");
          bw.write(thisPixelInfo.get(Blue) + "\n");
        }
      }
      bw.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("error occurred");
    }
  }

  /**
   * This method serves as a helper to the save method and specifically saves PNG, JPG, JPEG, or
   * BMP files.
   *
   * @param path    A String representing the path on the computer to save the image
   * @param imgName A String representing the desired image's name in the model
   * @param height  An integer representing the height of the desired image
   * @param width   An integer representing the width of the desired image
   * @throws IllegalArgumentException when the method is unable to write to the path or when an
   *                                  error occurs during the method
   */
  private void saveElse(String path, String imgName, int height, int width)
          throws IllegalArgumentException {

    BufferedImage img = BufferedImageUtils.createBI(width, height, this, imgName);

    try {
      FileOutputStream f = new FileOutputStream(path);
      ImageIO.write(img, path.substring(path.lastIndexOf(".") + 1), f);
    } catch (IOException e) {
      throw new IllegalArgumentException("error occurred");
    }
  }

  @Override
  public void load(String path, String name) throws IllegalArgumentException {
    String extension = checkExtension(path);

    if (extension.equalsIgnoreCase(".ppm")) {
      this.loadPPM(path, name);
    } else {
      this.loadElse(path, name);
    }
  }

  /**
   * This method loads a PPM image from the computer into the model's image collection.
   *
   * @param path A String representing the location of the desired image to be loaded
   * @param name A String representing the name that the loaded image should go by in the model
   * @throws IllegalArgumentException when the method is unable to write to the path or when an
   *                                  error occurs during the method
   */
  private void loadPPM(String path, String name) throws IllegalArgumentException {
    this.addImage(name, ImageUtil.readPPM(path));
  }

  /**
   * This method loads a PNG, JPG, JPEG, or BMP image from the computer into the model's image
   * collection.
   *
   * @param path A String representing the location of the desired image to be loaded
   * @param name A String representing the name that the loaded image should go by in the model
   * @throws IllegalArgumentException when the method is unable to write to the path or when an
   *                                  error occurs during the method
   */
  private void loadElse(String path, String name) throws IllegalArgumentException {
    this.addImage(name, bufferedImgTo2DArray(BufferedImageUtils.turnIntoBI(path)));
  }

  private PixelInfo[][] bufferedImgTo2DArray(BufferedImage img) {
    int height = img.getHeight();
    int width = img.getWidth();

    PixelInfo[][] loadedPixels = new PixelInfo[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = new Color(img.getRGB(j, i));
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        loadedPixels[i][j] = new PixelInfo(red, green, blue, 255);
      }
    }
    return loadedPixels;
  }

  /**
   * This method checks if a path's extension is a valid one that the program can handle.
   *
   * @param path A String representing the path to the desired image
   * @return A String representing the extension of the image after being determined to be valid
   * @throws IllegalArgumentException when an extension is unusable or non-existing
   */
  private String checkExtension(String path) throws IllegalArgumentException {
    String extension;
    if (path.lastIndexOf(".") > 0) {
      extension = path.substring(path.lastIndexOf("."));
    } else {
      throw new IllegalArgumentException("Unusable extension");
    }

    if (!(extension.equalsIgnoreCase(".ppm")
            || extension.equalsIgnoreCase(".jpg")
            || extension.equalsIgnoreCase(".jpeg")
            || extension.equalsIgnoreCase(".png")
            || extension.equalsIgnoreCase(".bmp"))) {
      throw new IllegalArgumentException("Unusable extension");
    }
    return extension;
  }

  @Override
  public void filter(double[][] kernel, String imgName, String rename)
          throws IllegalArgumentException {
    imageExists(imgName);
    if (kernel.length % 2 == 0 || kernel[0].length % 2 == 0) {
      throw new IllegalArgumentException("Input a valid kernel!");
    }

    PixelInfo[][] filteredImage = new PixelInfo[this.getHeight(imgName)][this.getWidth(imgName)];

    for (int i = 0; i < this.getHeight(imgName); i++) {
      for (int j = 0; j < this.getWidth(imgName); j++) {

        int filteredMax = this.getPixelInfo(imgName, i, j).get(Max);
        int filteredRed = filterPixel(Red, kernel, i, j, filteredMax, imgName);
        int filteredGreen = filterPixel(Green, kernel, i, j, filteredMax, imgName);
        int filteredBlue = filterPixel(Blue, kernel, i, j, filteredMax, imgName);

        filteredImage[i][j] = new PixelInfo(filteredRed, filteredGreen, filteredBlue, filteredMax);
      }
    }
    this.addImage(rename, filteredImage);
  }

  //performs the matrix calculation on a pixel for a given pixel component
  private int filterPixel(PixelComponents component, double[][] kernel,
                          int pRow, int pCol, int filteredMax, String imgName) {

    double newValue = 0.00000;

    int halfKernel = (kernel.length / 2);

    for (int i = pRow - halfKernel; i <= pRow + halfKernel; i++) {
      for (int j = pCol - halfKernel; j <= pCol + halfKernel; j++) {

        if (checkPixelInBounds(i, j, imgName)) {
          Map<PixelComponents, Integer> pixelInfoFiltered = this.getPixelInfo(imgName, i, j);
          int pixelValue = pixelInfoFiltered.get(component);

          newValue += kernel[i - pRow + halfKernel][j - pCol + halfKernel] * pixelValue;
        }
      }
    }
    return numInBounds(newValue);
  }

  //checks if a given coordinate is within bounds on an image
  private boolean checkPixelInBounds(int pRow, int pCol, String imgName) {
    int height = this.getHeight(imgName);
    int width = this.getWidth(imgName);
    return pRow >= 0 && pRow < height && pCol >= 0 && pCol < width;
  }

  @Override
  public void colorTransformation(double[][] kernel, String imgName, String rename)
          throws IllegalArgumentException {
    imageExists(imgName);

    PixelInfo[][] transformedImage = new PixelInfo[getHeight(imgName)][getWidth(imgName)];

    for (int i = 0; i < transformedImage.length; i++) {
      for (int j = 0; j < transformedImage[0].length; j++) {

        int transformedMax = this.getPixelInfo(imgName, i, j).get(Max);

        int currRedVal = this.getPixelInfo(imgName, i, j).get(Red);
        int currGreenVal = this.getPixelInfo(imgName, i, j).get(Green);
        int currBlueVal = this.getPixelInfo(imgName, i, j).get(Blue);

        int[] currRGB = {currRedVal, currGreenVal, currBlueVal};

        int newRed = valueTransform(kernel[0], currRGB);
        int newGreen = valueTransform(kernel[1], currRGB);
        int newBlue = valueTransform(kernel[2], currRGB);

        transformedImage[i][j] = new PixelInfo(newRed, newGreen, newBlue, transformedMax);
      }
    }
    this.addImage(rename, transformedImage);
  }

  //performs the change in value for each component of a PixelInfo
  private int valueTransform(double[] partOfKernel, int[] rgb) {

    double newValue = 0;

    for (int i = 0; i < partOfKernel.length; i++) {
      newValue += partOfKernel[i] * rgb[i];
    }

    return numInBounds(newValue);
  }

  private int numInBounds(double num) {

    int intNewValue = (int) num;

    if (intNewValue > 255) {
      intNewValue = 255;
    } else if (intNewValue < 0) {
      intNewValue = 0;
    }
    return intNewValue;
  }


  @Override
  public int getHeight(String imgName) throws IllegalArgumentException {
    // check to see if imgName exists in addedImages already (throw IAE if not)
    this.imageExists(imgName);
    return this.addedImages.get(imgName).length;
  }

  @Override
  public int getWidth(String imgName) throws IllegalArgumentException {
    // check to see if imgName exists in addedImages already (throw IAE if not)
    this.imageExists(imgName);
    return this.addedImages.get(imgName)[0].length;
  }

  @Override
  public Map<PixelComponents, Integer> getPixelInfo(String imgName, int row, int col)
          throws IllegalArgumentException {
    // check to see if imgName exists in addedImages already (throw IAE if not)
    this.imageExists(imgName);

    // check to see if row and col are valid coordinate for the specified picture
    int maxRow = this.getHeight(imgName);
    int maxCol = this.getWidth(imgName);
    if (row < 0 || col < 0 || row > maxRow || col > maxCol) {
      throw new IllegalArgumentException("invalid coordinates given");
    }

    return this.addedImages.get(imgName)[row][col].getPixelInfo();
  }

  /**
   * This method checks if a specified image is not a part of the addedImages.
   *
   * @param imgName A String representing the specified image.
   * @throws IllegalArgumentException when the String name is not found in addedImages.
   */
  private void imageExists(String imgName) throws IllegalArgumentException {
    if (!this.addedImages.containsKey(imgName)) {
      throw new IllegalArgumentException(imgName + " doesn't exist");
    }
  }

  public void downsize(int percentageOfHeight, int percentageOfWidth, String imgName, String rename) throws IllegalArgumentException {
    imageExists(imgName);
    if (percentageOfHeight >= 100 || percentageOfWidth >= 100 || percentageOfHeight <= 0 || percentageOfWidth <= 0) {
      throw new IllegalArgumentException("Input numbers between 0 and 100!");
    }
    int height = this.getHeight(imgName);
    int width = this.getWidth(imgName);
    int newHeight = (int) ((double) (height * percentageOfHeight) / 100.00);
    int newWidth = (int) ((double) (width * percentageOfWidth) / 100.00);

    PixelInfo[][] newImage = new PixelInfo[newHeight][newWidth];

    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {

        double currX = i * (100.0 / percentageOfHeight);
        double currY = j * (100.0 / percentageOfWidth);

        if (currX == (int) currX || currY == (int) currY) {
          newImage[i][j] = new PixelInfo(this.getPixelInfo(imgName, (int) currX, (int) currY).get(Red),
                  this.getPixelInfo(imgName, (int) currX, (int) currY).get(Green),
                  this.getPixelInfo(imgName, (int) currX, (int) currY).get(Blue),
                  this.getPixelInfo(imgName, (int) currX, (int) currY).get(Max));
        } else {

          PixelInfo pixelA = new PixelInfo(
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.floor(currY)).get(Red),
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.floor(currY)).get(Blue),
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.floor(currY)).get(Green),
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.floor(currY)).get(Max));
          PixelInfo pixelB = new PixelInfo(
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.floor(currY)).get(Red),
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.floor(currY)).get(Blue),
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.floor(currY)).get(Green),
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.floor(currY)).get(Max));
          PixelInfo pixelC = new PixelInfo(
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.ceil(currY)).get(Red),
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.ceil(currY)).get(Blue),
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.ceil(currY)).get(Green),
                  this.getPixelInfo(imgName, (int) Math.floor(currX), (int) Math.ceil(currY)).get(Max));
          PixelInfo pixelD = new PixelInfo(
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.ceil(currY)).get(Red),
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.ceil(currY)).get(Blue),
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.ceil(currY)).get(Green),
                  this.getPixelInfo(imgName, (int) Math.ceil(currX), (int) Math.ceil(currY)).get(Max));

          //ca
          int pAr = pixelA.getPixelInfo().get(Red);
          int pAg = pixelA.getPixelInfo().get(Green);
          int pAb = pixelA.getPixelInfo().get(Blue);

          //cb
          int pBr = pixelB.getPixelInfo().get(Red);
          int pBg = pixelB.getPixelInfo().get(Green);
          int pBb = pixelB.getPixelInfo().get(Blue);

          //cc
          int pCr = pixelC.getPixelInfo().get(Red);
          int pCg = pixelC.getPixelInfo().get(Green);
          int pCb = pixelC.getPixelInfo().get(Blue);

          //cd
          int pDr = pixelD.getPixelInfo().get(Red);
          int pDg = pixelD.getPixelInfo().get(Green);
          int pDb = pixelD.getPixelInfo().get(Blue);


          double mRed = ((pBr * (currX - Math.floor(currX))) + (pAr * (Math.ceil(currX) - currX)));
          double nRed = ((pDr * (currX - Math.floor(currX))) + (pCr * (Math.ceil(currX) - currX)));
          double newRed = ((nRed * (currY - Math.floor(currY))) + (mRed * (Math.ceil(currY) - currY)));

          double mGreen = ((pBg * (currX - Math.floor(currX))) + (pAg * (Math.ceil(currX) - currX)));
          double nGreen = ((pDg * (currX - Math.floor(currX))) + (pCg * (Math.ceil(currX) - currX)));
          double newGreen = ((nGreen * (currY - Math.floor(currY))) + (mGreen * (Math.ceil(currY) - currY)));

          double mBlue = ((pBb * (currX - Math.floor(currX))) + (pAb * (Math.ceil(currX) - currX)));
          double nBlue = ((pDb * (currX - Math.floor(currX))) + (pCb * (Math.ceil(currX) - currX)));
          double newBlue = ((nBlue * (currY - Math.floor(currY))) + (mBlue * (Math.ceil(currY) - currY)));

          newImage[i][j] = new PixelInfo((int) newRed, (int) newGreen, (int) newBlue, 255);
        }
      }
    }
    this.addedImages.put(rename, newImage);
  }

  // main method for partial image manipulation --
  public void setMaskImage(String path, String imgName) throws IllegalArgumentException {
    // check to see if imgName exists
    this.imageExists(imgName);

    // upload image and turn it into 2d array
    PixelInfo[][] potentialMask;
    String extension = checkExtension(path);
    if (extension.equalsIgnoreCase(".ppm")) {
      potentialMask = ImageUtil.readPPM(path);
    } else {
      potentialMask = bufferedImgTo2DArray(BufferedImageUtils.turnIntoBI(path));
    }

    // check that height and width of mask image are same as height and width of imgName
    int maskHeight = potentialMask.length;
    int maskWidth = potentialMask[0].length;
    if (this.getHeight(imgName) != maskHeight || this.getWidth(imgName) != maskWidth) {
      throw new IllegalArgumentException("mask image given is not the same size as the " +
              "desired image to modify");
    }

    // check that the mask image's pixels are either white or black
    for (int i = 0; i < maskHeight; i++) {
      for (int j = 0; j < maskWidth; j++) {
        Map<PixelComponents, Integer> thisMaskPixel = potentialMask[i][j].getPixelInfo();
        int thisMaskPixelRed = thisMaskPixel.get(Red);
        int thisMaskPixelGreen = thisMaskPixel.get(Green);
        int thisMaskPixelBlue = thisMaskPixel.get(Blue);

        if (!(thisMaskPixelRed == 0 && thisMaskPixelGreen == 0 && thisMaskPixelBlue == 0)
                || !(thisMaskPixelRed == 255 && thisMaskPixelGreen == 255 && thisMaskPixelBlue == 255)) {
          throw new IllegalArgumentException("mask image given is not black and white");
        }
      }
    }
    // set this.maskImage to the potentialMask
    this.maskImage = potentialMask;
  }

  private boolean maskImageBlack(int row, int col) {
    Map<PixelComponents, Integer> thisPixel = this.maskImage[row][col].getPixelInfo();
    int thisMaskPixelRed = thisPixel.get(Red);
    int thisMaskPixelGreen = thisPixel.get(Green);
    int thisMaskPixelBlue = thisPixel.get(Blue);
    return (thisMaskPixelRed == 0 && thisMaskPixelGreen == 0 && thisMaskPixelBlue == 0);
  }

  private void resetMaskImage() {
    // we need to reset the mask image to null after a partial image manipulation command is finished
    this.maskImage = null;
  }
}