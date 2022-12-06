package controller.commands;

import model.IPModel;
import model.IPModelState.PixelComponents;

/**
 * This class represents greyscaling an image based on a component.
 */
public class Component extends AIPCommand {
  
  private final PixelComponents component;
  
  /**
   * This first constructor takes two strings and initializes them as fields in an IPCommand model.
   *
   * @param component A PixelComponent representing the component to greyscale an image
   * @param firstStringArg  A String representing the first String argument for a command
   * @param secondStringArg A String representing the second String argument for a command
   */
  public Component(PixelComponents component, String firstStringArg, String secondStringArg) {
    super(firstStringArg, secondStringArg);
    this.component = component;
  }
  
  @Override
  public void execute(IPModel model) {
    model.greyscale(this.component, this.firstStringArg, this.secondStringArg);
  }
}
