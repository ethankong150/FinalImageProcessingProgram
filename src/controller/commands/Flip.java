package controller.commands;

import model.IPModel;

/**
 * This class represents flipping an image in a model.
 */
public class Flip extends AIPCommand {
  
  private final boolean vert;
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param vert            A boolean representing the whether to flip vertically (true) or
   *                        horizontally (false)
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public Flip(boolean vert, String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
    this.vert = vert;
  }
  
  @Override
  public void execute(IPModel model) {
    model.flip(this.vert, this.firstStringArg, this.secondStringArg);
  }
}
