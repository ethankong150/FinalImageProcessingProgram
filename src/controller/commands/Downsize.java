package controller.commands;

import model.IPModel;

public class Downsize extends AIPCommand{

  private final int newHeight;
  private final int newWidth;
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public Downsize(int newHeight, int newWidth, String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
    this.newHeight = newHeight;
    this.newWidth = newWidth;
  }

  /**
   * This method executes a command that needs to be executed onto the given model.
   *
   * @param model An IPModel representing the model that needs to be executed upon
   */
  @Override
  public void execute(IPModel model) {
    model.downsize(this.newHeight, this.newWidth, this.firstStringArg, this.secondStringArg);
  }
}
