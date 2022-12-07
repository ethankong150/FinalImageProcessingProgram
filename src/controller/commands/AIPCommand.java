package controller.commands;

/**
 * This class represents an abstract class for an IPCommand operation. In particular,
 * it contains an abstracted constructor.
 */
public abstract class AIPCommand implements IPCommand {
  protected String firstStringArg;
  protected String secondStringArg;
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public AIPCommand(String firstStringArg, String secondStringArg) {
    this.firstStringArg = firstStringArg;
    this.secondStringArg = secondStringArg;
  }
}
