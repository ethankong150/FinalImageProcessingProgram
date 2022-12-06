package controller.commands;

import model.IPModel;

/**
 * This class represents saving an image to a specific place on a computer.
 */
public class Save extends AIPCommand {
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public Save(String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
  }
  
  @Override
  public void execute(IPModel model) {
    model.save(this.firstStringArg, this.secondStringArg);
  }
}
