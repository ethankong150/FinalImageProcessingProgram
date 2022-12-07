package controller.commands;

import model.IPModel;

public class Partial extends AIPCommand {
  
  private final String maskPath;
  private final AIPCommand command;
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   * @throws IllegalArgumentException if the given command class object is null
   */
  public Partial(String maskPath, AIPCommand command, String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
    this.maskPath = maskPath;
    if (command == null) {
      throw new IllegalArgumentException("given command class object null");
    }
    this.command = command;
  }
  
  @Override
  public void execute(IPModel model) {
    model.setMaskImage(this.maskPath, this.firstStringArg);
    this.command.execute(model);
  }
}
