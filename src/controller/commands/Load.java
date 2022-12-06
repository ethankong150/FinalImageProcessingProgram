package controller.commands;

import model.IPModel;

/**
 * This class represents the operation of loading an image into a model.
 */
public class Load extends AIPCommand {
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public Load(String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
  }
  
  @Override
  public void execute(IPModel model) {
    model.load(this.firstStringArg, this.secondStringArg);
  }
}
