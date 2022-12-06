package controller.commands;

import model.IPModel;

/**
 * This class represents applying a filter to an image.
 */
public class Filter extends AIPCommand {
  
  private final double[][] matrix;
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param matrix          A double indexed array containing doubles that are required to
   *                        apply a filter onto an image
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public Filter(double[][] matrix, String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
    this.matrix = matrix;
  }
  
  @Override
  public void execute(IPModel model) {
    model.filter(this.matrix, this.firstStringArg, this.secondStringArg);
  }
}
