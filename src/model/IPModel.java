package model;

/**
 * This interface represents the operations offered by the image processing model. One
 * object of the model represents one collection of added images.
 */
public interface IPModel extends IPModelState {
  
  /**
   * This method adds a double indexed array of pixels into the added images with
   * the given String name.
   *
   * @param imgName   A String representing the desired name of the image upon adding it
   *                  to the added images.
   * @param imgPixels A double indexed array representing all the pixels that make up
   *                  the image.
   * @throws IllegalArgumentException if the given image name is null or an empty String,
   *                                  or when the double indexed array of pixels is null.
   */
  void addImage(String imgName, PixelInfo[][] imgPixels) throws IllegalArgumentException;
  
  /**
   * This method finds the desired image from the added images and creates a greyscale
   * version of it based on the given component. The new image is then added under the
   * desired name.
   *
   * @param components A PixelComponents type that indicates the type of greyscale conversion
   *                   that an image should undergo.
   * @param imgName    A String representing the name of the specified image.
   * @param rename     A String representing what the user wants to name the new greyscaled image.
   * @throws IllegalArgumentException when the given imgName is not an image name found
   *                                  in the added images.
   */
  void greyscale(PixelComponents components, String imgName, String rename)
      throws IllegalArgumentException;
  
  /**
   * This method adds the given amount to each of the red, green, and blue values of all
   * pixels in an image to brighten or darken the image accordingly.
   *
   * @param amount  An integer representing the amount to add to the red, green, and blue
   *                values of all pixels in an image.
   * @param imgName A String representing the name of the specified image.
   * @param rename  A String representing what the user wants to name the new brightened
   *                or darkened image.
   * @throws IllegalArgumentException when the given imgName is not an image name found
   *                                  in the added images.
   */
  void brighten(int amount, String imgName, String rename) throws IllegalArgumentException;
  
  /**
   * This method flips the specified image vertically or horizontally.
   *
   * @param vert    A boolean representing if the specified image should be flipped vertically
   *                or horizontally. If boolean is true, flip vertically;
   *                If boolean is false, flip horizontally.
   * @param imgName A String representing the name of the specified image.
   * @param rename  A String representing what the user wants to name the new flipped image.
   * @throws IllegalArgumentException when the given imgName is not an image name found
   *                                  in the added images.
   */
  void flip(boolean vert, String imgName, String rename) throws IllegalArgumentException;
  
  /**
   * This method saves an image to a specific path on the user's computer.
   *
   * @param path    A String representing the path to where the image should be saved.
   * @param imgName A String representing the name of the specified image that needs to be saved.
   * @throws IllegalArgumentException when the given imgName is not an image name found
   *                                  in the added images.
   */
  void save(String path, String imgName) throws IllegalArgumentException;
  
  /**
   * This method loads an image from a specific path into the added images of the model.
   *
   * @param path A String representing the path to where the image should be retrieved from.
   * @param name A String representing the name that the image should be named in the model.
   * @throws IllegalArgumentException when the given path is invalid or the given name is invalid
   */
  void load(String path, String name) throws IllegalArgumentException;
  
  /**
   * This method applies a filter onto an image based on the given kernel double indexed array.
   *
   * @param kernel  A double indexed array representing the values needed to compute new values
   *                for the filter
   * @param imgName A String representing the desired name of the image in the model
   * @param rename  A String representing the name that the new image should be named in the model
   * @throws IllegalArgumentException when the given path is invalid or the given name is invalid
   */
  void filter(double[][] kernel, String imgName, String rename)
      throws IllegalArgumentException;
  
  /**
   * This method applies a color transformation onto an image based on the given kernel double
   * indexed array.
   *
   * @param kernel  A double indexed array representing the values needed to compute new values for
   *                the color transformation
   * @param imgName A String representing the desired name of the image in  the model
   * @param rename  A String representing the name that the new image should be named in the model
   * @throws IllegalArgumentException when the given path is invalid or the given name is invalid
   */
  void colorTransformation(double[][] kernel, String imgName, String rename)
      throws IllegalArgumentException;

  /**
   * Creates a smaller version of desired image with desired dimensions.
   *
   * @param percentageOfHeight percentage of original height
   * @param percentageOfWidth percentage of original width
   * @param imgName name of image saved to the model
   * @param rename new name for image created
   * @throws IllegalArgumentException when percentages given aren't between 0 and 100
   */
  void downsize(int percentageOfHeight, int percentageOfWidth, String imgName, String rename)
          throws IllegalArgumentException;

  /**
   * Checks if the desired image is allowed to be the mask image for the desired image -
   * (checks if all pixels within the image are perfect white 255 255 255 or perfect black 0 0 0
   * and if the dimensions of both the mask image and the actual image are the same).
   *
   * @param maskPath The path to the image that may serve as the mask image
   * @param imgName name of the image that is to be modified
   * @throws IllegalArgumentException if the image and the mask image have different dimensions or
   *                                  if the mask image contains pixels that aren't
   *                                  perfect black or white
   */
  void setMaskImage(String maskPath, String imgName) throws IllegalArgumentException;
}
