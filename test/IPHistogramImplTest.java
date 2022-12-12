import org.junit.Test;

import model.IPModel;
import model.IPModelImpl;
import view.IPHistogramImpl;

import static org.junit.Assert.fail;

/**
 * This class represents a test class for the IPHistogramImpl class.
 */
public class IPHistogramImplTest {
  IPModel m1 = new IPModelImpl();
  IPHistogramImpl h1 = new IPHistogramImpl(1, 1);

  // test illegal argument in first constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1IAE1() {
    new IPHistogramImpl(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1IAE2() {
    new IPHistogramImpl(-1, 1);
  }

  // test createHistgramData does not fail with valid environment
  @Test
  public void testCreateHistogramData() {
    this.m1.load("res/techsupport.png", "ts");
    try {
      this.h1.createHistogramData(this.m1.getPixels("ts"));
    } catch (Exception e) {
      fail("you failed");
    }
  }
  
  // test illegal argument in createHistogramData class
  @Test(expected = IllegalArgumentException.class)
  public void testCreateHistogramDataIAE() {
    this.h1.createHistogramData(this.m1.getPixels("ts"));;
  }

}