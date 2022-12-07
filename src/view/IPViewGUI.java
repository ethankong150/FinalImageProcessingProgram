package view;

import controller.IPControllerGUI;

/**
 * Represents an interface for a view of the IP program's GUI.
 */
public interface IPViewGUI {

  /**
   * This method sets the ViewGUI's controller field to link a controller with the view
   * appropriately.
   *
   * @param controller A IPControllerGUIImpl that represents the controller that
   *                   should be linked to the view.
   * @throws IllegalArgumentException when the given controller is null.
   */
  void setControllerGUI(IPControllerGUI controller) throws IllegalArgumentException;

  /**
   * This method allows the GUI to be seen by setting it to be visible with a
   * JFrame method.
   */
  void seeGUI();

  /**
   * This method allows a customized pop-up message to be delivered through the GUI.
   *
   * @param body  A String representing the body of the pop-up message.
   * @param title A String representing the title of the pop-up message.
   * @param type  An integer representing the type of pop-up message (i.e. informative,
   *              error, etc)
   */
  void renderPopUpMessage(String body, String title, int type);

  /**
   * This method retrieves an image from the view's model based on the given String and draws it
   * onto the GUI for the user to see.
   *
   * @param imgName A String representing the name of the desired image to draw onto the GUI.
   * @throws IllegalArgumentException when the given String name cannot be found in
   *                                  the view's model.
   */
  void drawImage(String imgName) throws IllegalArgumentException;

  /**
   * This method retrieves an image from the view's model based on the given String and draws a
   * histogram of the red, green, blue, and intensity values onto the GUI for the user to see.
   *
   * @param imgName A String representing the name of the desired image to draw onto the GUI.
   * @throws IllegalArgumentException when the given String name cannot be found in
   *                                  the view's model.
   */
  void drawHistogram(String imgName) throws IllegalArgumentException;
}
