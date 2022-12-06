package controller.commands;

/**
 * This interface represents matrices that are used for filters and transformations.
 */
public interface Matrices {
  
  /**
   * This double indexed array represents the values required to blur an image.
   */
  double[][] blur =
      {{0.0625, 0.1250, 0.0625},
          {0.1250, 0.2500, 0.1250},
          {0.0625, 0.1250, 0.0625}};
  
  /**
   * This double indexed array represents the values required to sharpen an image.
   */
  double[][] sharpen =
      {{-0.125, -0.125, -0.125, -0.125, -0.125},
          {-0.125, 0.250, 0.250, 0.250, -0.125},
          {-0.125, 0.250, 1.000, 0.250, -0.125},
          {-0.125, 0.250, 0.250, 0.250, -0.125},
          {-0.125, -0.125, -0.125, -0.125, -0.125}};
  
  /**
   * This double indexed array represents the values required to transform an image using luma.
   */
  double[][] greyscaleluma =
      {{0.216, 0.7152, 0.0722},
          {0.216, 0.7152, 0.0722},
          {0.216, 0.7152, 0.0722}};
  
  /**
   * This double indexed array represents the values required apply a sepia transformation.
   */
  double[][] sepia = {{0.393, 0.769, 0.189},
      {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
}
