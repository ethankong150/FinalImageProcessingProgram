package controller.commands;

import model.IPModel;

/**
 * This interface represents commands that need to be executed onto a model.
 */
public interface IPCommand {
  
  /**
   * This method executes a command that needs to be executed onto the given model.
   * @param model An IPModel representing the model that needs to be executed upon
   */
  void execute(IPModel model);
}
