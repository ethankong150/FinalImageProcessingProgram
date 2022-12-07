package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Brighten;
import controller.commands.ColorTransformation;
import controller.commands.Component;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.IPCommand;
import controller.commands.Load;
import controller.commands.Matrices;
import controller.commands.Save;
import model.IPModel;
import view.IPView;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Luma;
import static model.IPModelState.PixelComponents.Red;
import static model.IPModelState.PixelComponents.Value;

/**
 * Represents the controller of the Image Processing program. This class handles inputs and
 * communicates with the model/view.
 */
public class IPControllerImpl implements IPController {
  
  private final IPModel model;
  private final IPView view;
  private final Readable input;
  private boolean programRunning;
  
  private final Map<String, Function<Scanner, IPCommand>> commands;
  
  /**
   * This first constructor takes a model, view, and readable to create a controller
   * that operates with all three objects in cohesion and appropriately.
   *
   * @param model an image processing model
   * @param view  an image processing textview
   * @param input object that allows the constructor to read
   * @throws IllegalArgumentException when given a null object
   */
  public IPControllerImpl(IPModel model, IPView view, Readable input)
      throws IllegalArgumentException {
    
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("You can't input null!");
    }
    
    this.model = model;
    this.view = view;
    this.input = input;
    this.programRunning = true;
    
    this.commands = new HashMap<>();
    this.loadCommands();
  }
  
  /**
   * Starts the Image Processing program.
   */
  @Override
  public void startIP() throws IllegalStateException {
    Scanner sc = new Scanner(this.input);
    IPCommand command;
    
    // while the program should be running
    while (programRunning) {
      
      // render the instructional message
      this.renderMessage("What would you like to do?\n");
      
      // assume the first string entered to be the desired command to be executed
      String commandEntered = getStringInput(sc);
      
      // if it is "q", quit the program
      if (commandEntered.equals("q")) {
        programRunning = false;
        this.renderMessage("IP quit!");
        break;
      }
      
      // find the command in the Map and set it equal to cmd, if not there, set cmd to null
      Function<Scanner, IPCommand> cmd =
          this.commands.getOrDefault(commandEntered, null);
      
      // if cmd is null
      if (cmd == null) {
        this.renderMessage("Invalid command given: " + commandEntered + "\n");
      } else {
        try {
          command = cmd.apply(sc);
          command.execute(this.model);
          this.renderMessage(commandEntered + " success!\n");
        } catch (IllegalArgumentException e) {
          this.renderMessage("Error: " + e.getMessage() + "\n");
        }
      }
    }
  }
  
  /**
   * This method appends a message to the view's appendable.
   *
   * @param s A String representing the desired message to be appended.
   * @throws IllegalStateException when the message is unable to be appended to
   *                               the view's appendable.
   */
  private void renderMessage(String s) throws IllegalStateException {
    try {
      this.view.renderMessage(s);
    } catch (IOException e) {
      throw new IllegalStateException("Error rendering");
    }
  }
  
  /**
   * This method gets the next String from the given scanner if possible.
   *
   * @param sc A Scanner representing the user's inputs.
   * @return A String representing the next thing the user inputted into the scanner.
   * @throws IllegalStateException If the readable is out of arguments.
   */
  private String getStringInput(Scanner sc) throws IllegalStateException {
    String s;
    try {
      return sc.next().toLowerCase();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Readable out of arguments");
    }
  }
  
  /**
   * This method gets the next integer from the given scanner if possible.
   *
   * @param sc A Scanner representing the user's inputs
   * @return An integer representing the next integer the user inputted into the scanner
   * @throws IllegalArgumentException If the readable is out of arguments or if the next input
   *                                  is not an integer
   */
  private int getIntInput(Scanner sc) throws IllegalArgumentException {
    try {
      return Integer.parseInt(getStringInput(sc));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("invalid command arguments given");
    }
  }
  
  /**
   * This method loads all valid commands into the commands Map for this controller.
   */
  private void loadCommands() {
    this.commands.put("load", sc ->
        new Load(getStringInput(sc), getStringInput(sc)));
    this.commands.put("brighten", sc ->
        new Brighten(getIntInput(sc), getStringInput(sc), getStringInput(sc)));
    this.commands.put("vertical-flip", sc ->
        new Flip(true, getStringInput(sc), getStringInput(sc)));
    this.commands.put("horizontal-flip", sc ->
        new Flip(false, getStringInput(sc), getStringInput(sc)));
    this.commands.put("red-component", sc ->
        new Component(Red, getStringInput(sc), getStringInput(sc)));
    this.commands.put("green-component", sc ->
        new Component(Green, getStringInput(sc), getStringInput(sc)));
    this.commands.put("blue-component", sc ->
        new Component(Blue, getStringInput(sc), getStringInput(sc)));
    this.commands.put("value-component", sc ->
        new Component(Value, getStringInput(sc), getStringInput(sc)));
    this.commands.put("intensity-component", sc ->
        new Component(Intensity, getStringInput(sc), getStringInput(sc)));
    this.commands.put("luma-component", sc ->
        new Component(Luma, getStringInput(sc), getStringInput(sc)));
    this.commands.put("save", sc ->
        new Save(getStringInput(sc), getStringInput(sc)));
    this.commands.put("blur", sc ->
        new Filter(Matrices.blur, getStringInput(sc), getStringInput(sc)));
    this.commands.put("sharpen", sc ->
        new Filter(Matrices.sharpen, getStringInput(sc), getStringInput(sc)));
    this.commands.put("greyscale-luma", sc ->
        new ColorTransformation(Matrices.greyscaleluma, getStringInput(sc), getStringInput(sc)));
    this.commands.put("sepia", sc ->
        new ColorTransformation(Matrices.sepia, getStringInput(sc), getStringInput(sc)));
  }
}
