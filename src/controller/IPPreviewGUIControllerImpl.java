package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.*;

import controller.commands.Brighten;
import controller.commands.ColorTransformation;
import controller.commands.Component;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.IPCommand;
import controller.commands.Matrices;
import model.IPModel;
import view.IPViewPreviewGUIImpl;

import static model.IPModelState.PixelComponents.Blue;
import static model.IPModelState.PixelComponents.Green;
import static model.IPModelState.PixelComponents.Intensity;
import static model.IPModelState.PixelComponents.Luma;
import static model.IPModelState.PixelComponents.Red;
import static model.IPModelState.PixelComponents.Value;

public class IPPreviewGUIControllerImpl {
  
  private final IPModel model;
  private final IPViewPreviewGUIImpl previewView;
  private final Map<String, Function<String, IPCommand>> commands;
  private final String thisImage;
  private boolean previewImageExists;
  private Function<String, IPCommand> command;
  private String specialArgument;
  
  public IPPreviewGUIControllerImpl(IPModel model, IPViewPreviewGUIImpl previewView)
      throws IllegalArgumentException {
    if (model == null || previewView == null) {
      throw new IllegalArgumentException("null parameters given");
    }
    this.model = model;
    this.previewView = previewView;
    this.commands = new HashMap<>();
    this.loadCommands();
    this.thisImage = "previewImage";
    this.previewImageExists = false;
    this.previewView.connect(this);
    this.command = null;
    this.specialArgument = "";
  }
  
  private void createBaselinePreviewImage() {
    if (!this.previewImageExists) {
      try {
        this.model.addImage(this.thisImage, this.model.getPixels("image"));
        this.previewImageExists = true;
      } catch (IllegalArgumentException e) {
        this.renderPopUpMessage("There was a problem: no image loaded yet", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  public void updatePreviewImage(ArrayList<Integer> barInfo) {
    this.model.previewMaskImage(barInfo.get(0), barInfo.get(1), this.model.getPixels("image").length,
        this.model.getPixels("image")[0].length);
    
    System.out.println("preview gui " + barInfo.get(0) + " " +  barInfo.get(1) + " "
        + barInfo.get(2) + " " + barInfo.get(3));
    
    IPCommand c = this.command.apply(this.specialArgument);
    c.execute(this.model);
    this.previewView.drawImage(this.model.getPixels(this.thisImage));
  }
  
  public void commandHandler(String method, String specialArgument) {
    IPCommand command;
    
    this.createBaselinePreviewImage();
    
    this.model.previewMaskImage(0, 0,
        this.model.getPixels("image").length,
        this.model.getPixels("image")[0].length);
    
    Function<String, IPCommand> cmd =
        this.commands.getOrDefault(method, null);
    
    // if cmd is null
    if (cmd == null) {
      this.renderPopUpMessage("Invalid command given: " + method, "Error",
          JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        command = cmd.apply(specialArgument);
        command.execute(this.model);
        
        this.command = cmd;
        this.specialArgument = specialArgument;
        
        // because command successfully went through, an image exists for sure at this stage
        
        // draw the image
        this.previewView.drawImage(this.model.getPixels(this.thisImage));
  
        // show preview gui
        this.previewView.seeGUI();
        
      } catch (IllegalArgumentException e) {
        if (!e.getMessage().equals("command was canceled")) {
          this.renderPopUpMessage("There was a problem: " + e.getMessage(), "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
  
  /**
   * This method allows a customized pop-up message to be delivered through the view GUI.
   *
   * @param body  A String representing the body of the pop-up message.
   * @param title A String representing the title of the pop-up message.
   * @param type  An integer representing the type of pop-up message (i.e. informative,
   *              error, etc)
   */
  private void renderPopUpMessage(String body, String title, int type) {
    this.previewView.renderPopUpMessage(body, title, type);
  }
  
  /**
   * This method gets the next integer from the given scanner if possible.
   *
   * @param s A Scanner representing the user's inputs
   * @return An integer representing the next integer the user inputted into the scanner
   * @throws IllegalArgumentException If the readable is out of arguments, if the next input
   *                                  is not an integer, or if the next input is empty (indicating
   *                                  the command was canceled).
   */
  private int parseInt(String s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException("command was canceled");
    }
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("invalid integer given");
    }
  }
  
  private void loadCommands() {
    this.commands.put("brighten", str ->
        new Brighten(parseInt(str), this.thisImage, this.thisImage));
    this.commands.put("darken", str ->
        new Brighten(parseInt(str) * -1, this.thisImage, this.thisImage));
    this.commands.put("red-component", str ->
        new Component(Red, this.thisImage, this.thisImage));
    this.commands.put("green-component", str ->
        new Component(Green, this.thisImage, this.thisImage));
    this.commands.put("blue-component", str ->
        new Component(Blue, this.thisImage, this.thisImage));
    this.commands.put("value-component", str ->
        new Component(Value, this.thisImage, this.thisImage));
    this.commands.put("intensity-component", str ->
        new Component(Intensity, this.thisImage, this.thisImage));
    this.commands.put("luma-component", str ->
        new Component(Luma, this.thisImage, this.thisImage));
    this.commands.put("blur", str ->
        new Filter(Matrices.blur, this.thisImage, this.thisImage));
    this.commands.put("sharpen", str ->
        new Filter(Matrices.sharpen, this.thisImage, this.thisImage));
    this.commands.put("greyscale-luma", str ->
        new ColorTransformation(Matrices.greyscaleluma, this.thisImage, this.thisImage));
    this.commands.put("sepia", str ->
        new ColorTransformation(Matrices.sepia, this.thisImage, this.thisImage));
  }
}
