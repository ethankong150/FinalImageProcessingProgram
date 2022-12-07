import org.junit.Test;

import controller.IPControllerGUI;
import controller.IPControllerGUIImpl;
import model.IPModel;
import model.IPModelImpl;
import view.IPViewGUI;
import view.IPViewGUIImpl;

import static org.junit.Assert.assertEquals;


/**
 * This class represents test classes for IPControllerGUIImpl class.
 */
public class IPControllerGUIImplTest {
  IPModel model = new IPModelImpl();
  IPViewGUI view = new IPViewGUIImpl(this.model);
  IPControllerGUI controller = new IPControllerGUIImpl(this.model, this.view);

  MockIPViewGUIImpl mockView = new MockIPViewGUIImpl();
  IPControllerGUI mockController = new IPControllerGUIImpl(this.model, this.mockView);

  // test first constructor IAE
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1IAE1() {
    new IPControllerGUIImpl(null, this.view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1IAE2() {
    new IPControllerGUIImpl(this.model, null);
  }

  // test that startIPGUI calls correct methods in the view
  @Test
  public void testStartIPGUI() {
    this.mockController.startIPGUI();
    assertEquals("setControllerGUI called" +
            "seeGUI called", this.mockView.getLog());
    assertEquals("", this.mockView.getHistogramLog());
  }

  // test that the commandHandler with a good method and specialArgument
  // call the correct methods in the view
  @Test
  public void testCommandHandlerGoodArgumentsLoad() {
    this.mockController.commandHandler("load", "res/techsupport.ppm");
    assertEquals("drawImage called with this imgName: image" +
            "drawHistogram called with this imgName: image" +
            "renderPopUpMessage called with this body, title, and type: " +
            "load success!, Success, 1", this.mockView.getLog());
    assertEquals("createHistogramData with this imgName: image",
            this.mockView.getHistogramLog());
  }

  @Test
  public void testCommandHandlerGoodArgumentsBrighten() {
    this.model.load("res/techsupport.ppm", "image");
    this.mockController.commandHandler("brighten", "50");
    assertEquals("drawImage called with this imgName: image" +
            "drawHistogram called with this imgName: image" +
            "renderPopUpMessage called with this body, title, and type: " +
            "brighten success!, Success, 1", this.mockView.getLog());
    assertEquals("createHistogramData with this imgName: image",
            this.mockView.getHistogramLog());
  }

  @Test
  public void testCommandHandlerGoodArgumentsSepia1() {
    this.model.load("res/techsupport.ppm", "image");
    this.mockController.commandHandler("sepia", "");
    assertEquals("drawImage called with this imgName: image" +
            "drawHistogram called with this imgName: image" +
            "renderPopUpMessage called with this body, title, and type: " +
            "sepia success!, Success, 1", this.mockView.getLog());
    assertEquals("createHistogramData with this imgName: image",
            this.mockView.getHistogramLog());
  }

  @Test
  public void testCommandHandlerGoodArgumentsSepia2() {
    this.model.load("res/techsupport.ppm", "image");
    this.mockController.commandHandler("sepia", "doesn't matter");
    assertEquals("drawImage called with this imgName: image" +
            "drawHistogram called with this imgName: image" +
            "renderPopUpMessage called with this body, title, and type: " +
            "sepia success!, Success, 1", this.mockView.getLog());
    assertEquals("createHistogramData with this imgName: image",
            this.mockView.getHistogramLog());
  }

  // test bad command (should not crash, just throw error message)
  @Test
  public void testCommandHandlerBadCommand() {
    this.model.load("res/techsupport.ppm", "image");
    this.mockController.commandHandler("not a command", "");
    assertEquals("renderPopUpMessage called with this body, title, and type: " +
            "Invalid command given: not a command, Error, 0", this.mockView.getLog());
    assertEquals("", this.mockView.getHistogramLog());
  }

  // test bad specialArgument
  @Test
  public void testCommandHandlerBadSpecialArgument() {
    this.model.load("res/techsupport.ppm", "image");
    this.mockController.commandHandler("brighten", "bruh");
    assertEquals("renderPopUpMessage called with this body, title, and type: " +
            "There was a problem: invalid integer given, Error, 0", this.mockView.getLog());
    assertEquals("",
            this.mockView.getHistogramLog());
  }

  // running commandhandler when no image exists
  @Test
  public void testCommandHandlerPreInitLoad() {
    this.mockController.commandHandler("sepia", "");
    assertEquals("renderPopUpMessage called with this body, title, and type: " +
            "There was a problem: image doesn't exist, Error, 0", this.mockView.getLog());
    assertEquals("", this.mockView.getHistogramLog());
  }
}