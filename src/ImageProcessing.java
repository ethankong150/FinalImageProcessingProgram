import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import controller.IPControllerGUIImpl;
import controller.IPControllerImpl;
import model.IPModel;
import model.IPModelImpl;
import view.IPView;
import view.IPViewGUIImpl;
import view.IPViewImpl;

/**
 * This class contains the main method for running the Image Processing program.
 */
public class ImageProcessing {
  
  /**
   * This method is the main method that runs the program on a controller.
   *
   * @param args An array list of Strings representing the user's inputs into the command line.
   */
  public static void main(String[] args) {
    IPModel model = new IPModelImpl();
    if (args.length == 0) {
      new IPControllerGUIImpl(model, new IPViewGUIImpl()).startIPGUI();
    } else {
      IPView view = new IPViewImpl();
      Readable in = null;
      if (args[0].equalsIgnoreCase("-file")) {
        in = new StringReader(read(args[1]));
      } else if (args[0].equalsIgnoreCase("-text")) {
        in = new InputStreamReader(System.in);
      } else {
        System.out.println("invalid command line arguments given: program quitting");
        System.exit(0);
      }
      new IPControllerImpl(model, view, in).startIP();
    }
  }
  
  /**
   * This method reads a text file and copies it over to a string that can be read by the
   * image processing program.
   * @param path A String representing the path to the script file
   * @return A String containing the contents of the script file.
   * @throws IllegalArgumentException when the path does not lead to a readable file
   */
  public static String read(String path) throws IllegalArgumentException {
    Scanner sc;
    
    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found from path");
    }
    
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      builder.append(s + " ");
    }
    
    return builder.append("q").toString();
    
  }
}
