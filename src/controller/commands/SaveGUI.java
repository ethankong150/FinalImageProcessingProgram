package controller.commands;

import model.IPModel;

public class SaveGUI extends AIPCommand {

  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public SaveGUI(String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
  }

  /**
   *
   * @param model An IPModel representing the model that needs to be executed upon
   * @throws IllegalArgumentException when the first String argument is empty, indicating cancel
   *                                  was pressed and the argument is an un-needed input and is therefore illegal.
   */
  @Override
  public void execute(IPModel model) throws IllegalArgumentException {
    if (this.firstStringArg.isEmpty()) {
      throw new IllegalArgumentException("command was canceled");
    }
    model.save(this.firstStringArg, this.secondStringArg);
  }
}
