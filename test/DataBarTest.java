import org.junit.Test;

import view.DataBar;

import static org.junit.Assert.assertEquals;


/**
 * This class represents a test class for the DataBar class.
 */
public class DataBarTest {
  DataBar d1 = new DataBar(5, 10);
  DataBar d2 = new DataBar(0, 100);
  DataBar d3 = new DataBar(100, 0);

  // test constructor
  @Test
  public void testConstructor1() {
    assertEquals(5, this.d1.getHeight());
    assertEquals(10, this.d1.getWidth());

    assertEquals(0, this.d2.getHeight());
    assertEquals(100, this.d2.getWidth());

    assertEquals(100, this.d3.getHeight());
    assertEquals(0, this.d3.getWidth());
  }

  // test illegal argument
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1IAE1() {
    new DataBar(-1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1IAE2() {
    new DataBar(5, -5);
  }

  // test getHeight method
  @Test
  public void testGetHeight() {
    assertEquals(5, this.d1.getHeight());
    assertEquals(0, this.d2.getHeight());
    assertEquals(100, this.d3.getHeight());
  }

  // test getWidth method
  @Test
  public void testGetWidth() {
    assertEquals(10, this.d1.getWidth());
    assertEquals(100, this.d2.getWidth());
    assertEquals(0, this.d3.getWidth());
  }
}