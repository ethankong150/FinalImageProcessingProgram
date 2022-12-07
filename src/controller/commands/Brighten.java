package controller.commands;

import model.IPModel;

/**
 * This class represents brightening an image in a model.
 */
public class Brighten extends AIPCommand {
  
  private final int amount;
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param amount          An integer representing the amount to brighten an image.
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public Brighten(int amount, String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
    this.amount = amount;
  }
  
  @Override
  public void execute(IPModel model) {
    model.brighten(this.amount, this.firstStringArg, this.secondStringArg);
  }
}
