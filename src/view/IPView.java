package view;

import java.io.IOException;

/**
 * Represents an interface for an image processing view.
 */

public interface IPView {
  
  /**
   * Renders the message to the output view.
   *
   * @param msg The desired message to be displayed
   * @throws IOException when message isn't able to be rendered
   */
  void renderMessage(String msg) throws IOException;
}
