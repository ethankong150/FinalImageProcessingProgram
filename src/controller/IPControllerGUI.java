package controller;

/**
 * Represents the operations offered by the image processing GUI controller.
 */
public interface IPControllerGUI {
  
  /**
   * This method establishes a connection with the ViewGUI and begins the program with the GUI.
   */
  void startIPGUI();
  
  /**
   * This method receives a desired command represented by a String and an extra argument
   * and executes the appropriate command if possible through the model.
   *
   * @param method          A String representing the desired command to be executed.
   * @param specialArgument A String representing a potential argument that may be needed from the
   *                        view, like a number for brightening or darkening an image, or a path
   *                        to load or save an image.
   */
  void commandHandler(String method, String specialArgument);
}
