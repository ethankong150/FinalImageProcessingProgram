import org.junit.Test;

import controller.IPControllerGUIImpl;
import model.IPModel;
import model.IPModelImpl;
import view.IPViewGUI;
import view.IPViewGUIImpl;

import static org.junit.Assert.fail;


/**
 * This class represents tests for the IPViewGUIImpl class.
 */
public class IPViewGUIImplTest {

  IPModel model = new IPModelImpl();
  IPViewGUI viewGUI = new IPViewGUIImpl(this.model);

  // check IAE in first constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1IAE() {
    new IPViewGUIImpl(null);
  }

  // check that controllerGUI does not fail with a valid controller
  @Test
  public void testControllerGUI() {
    try {
      this.viewGUI.setControllerGUI(new IPControllerGUIImpl(this.model, this.viewGUI));
    } catch (Exception e) {
      fail("you failed");
    }
  }

  // check IAE in controllerGUI
  @Test(expected = IllegalArgumentException.class)
  public void testControllerGUIIAE() {
    this.viewGUI.setControllerGUI(null);
  }

  // check that draw image does not fail with valid environment
  @Test
  public void testDrawImage() {
    try {
      this.model.load("res/techsupport.ppm", "ts");
      this.viewGUI.drawImage("ts");
    } catch (Exception e) {
      fail("you failed");
    }
  }

  // check draw image IAE
  @Test(expected = IllegalArgumentException.class)
  public void testDrawImageIAE() {
    this.viewGUI.drawImage("ts");
  }

  // check that draw histogram does not fail with valid environment
  @Test
  public void testDrawHistogram() {
    try {
      this.model.load("res/techsupport.ppm", "ts");
      this.viewGUI.drawHistogram("ts");
    } catch (Exception e) {
      fail("you failed");
    }
  }

  // check draw histogram IAE
  @Test(expected = IllegalArgumentException.class)
  public void testDrawHistogramIAE() {
    this.viewGUI.drawHistogram("ts");
  }
}