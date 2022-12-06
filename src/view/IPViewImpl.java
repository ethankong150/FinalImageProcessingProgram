package view;


import java.io.IOException;

/**
 * Class represents the implementation of the interface that creates the text view.
 */

public class IPViewImpl implements IPView {
  
  private final Appendable output;
  
  /**
   * This first constructor takes nothing and uses System.out as the default appendable.
   */
  public IPViewImpl() {
    this(System.out);
  }
  
  /**
   * Represents the constructor that takes an Appendable and initializes it.
   * @param output An Appendable that things should be appended to.
   * @throws IllegalArgumentException if the given appendable is null.
   */
  public IPViewImpl(Appendable output) throws IllegalArgumentException {
    if (output == null) {
      throw new IllegalArgumentException("null output");
    }
    this.output = output;
  }
  
  /**
   * Renders the message to the output view.
   *
   * @param msg The desired message to be displayed
   * @throws IOException when message isn't able to be rendered
   */
  @Override
  public void renderMessage(String msg) throws IOException {
    this.output.append(msg);
  }
}