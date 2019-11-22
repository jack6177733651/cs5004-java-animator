package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import cs5004.animator.model.shape.Color;
import cs5004.animator.model.shape.IColor;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A test class for the AnimationModel interface.
 */
public class AnimationModelTest {
  private AnimationModel test;

  /**
   * Setting up the AnimationModel.
   */
  @Before
  public void setUp() {
    test = new AnimationModelImpl(4.0);
    IShape oval = new Oval(new Point2D.Double(0, 0), new Color(0, 0, 0), 4, 2);
    test.addShape("A", oval, 0, 5);
    IShape rect = new Rectangle(new Point2D.Double(5, 5), new Color(1, 1, 1), 5, 5);
    test.addShape("B", rect, 3, 7);
    test.addMove("A", new Point2D.Double(0, 0), new Point2D.Double(-4, -4), 3, 5);
    test.addMove("B", new Point2D.Double(5, 5), new Point2D.Double(5, 10), 5, 7);
    test.changeDimension("A", 4, 2, 2, 4, 2, 4);
    test.changeColor("B", new Color(1, 1, 1), new Color(0, 1, 0), 3, 7);
  }

  @Test
  public void testInvalidModel() {
    try {
      AnimationModel badModel = new AnimationModelImpl(-4);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.append(e.getMessage());
    }
  }

  /**
   * Test whether an invalid x dimension will cause IShape to throw an exception.
   */
  @Test
  public void testInvalidShape() {
    try {
      IShape testOval = new Oval(new Point2D.Double(4, 7), new Color(0, 0, 0),
              -5, 7);
      test.addShape("A", testOval, 0, 10);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether an invalid y dimension will cause IShape to throw an exception.
   */
  @Test
  public void testInvalidShape2() {
    try {
      IShape testRect = new Rectangle(new Point2D.Double(0, 0), new Color(1, 1, 1),
              20, -7);
      test.addShape("B", testRect, 0, 10);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a null Point2D will cause IShape to throw an exception.
   */
  @Test
  public void testInvalidShape3() {
    try {
      IShape testOval = new Oval(null, new Color(0, 0, 0), 20, 20);
      test.addShape("A", testOval, 0, 10);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a null IColor will cause IShape to throw an exception.
   */
  @Test
  public void testInvalidShape4() {
    try {
      IShape testOval = new Oval(new Point2D.Double(0, 0), null, 20, 20);
      test.addShape("A", testOval, 0, 10);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a negative RGB value will cause IColor to throw an exception.
   */
  @Test
  public void testInvalidColor() {
    try {
      IColor invalidColor = new Color(-1, 0, 0);
      IShape testOval = new Oval(new Point2D.Double(0, 0), invalidColor, 5, 5);
      test.addShape("A", testOval, 0, 10);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a RGB value greater than 1 will cause IColor to throw an exception.
   */
  @Test
  public void testInvalidColor2() {
    try {
      IColor invalidColor = new Color(0, 0, 2);
      IShape testRect = new Rectangle(new Point2D.Double(0, 0), invalidColor, 5, 5);
      test.addShape("B", testRect, 0, 10);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a negative start time will cause the AnimationModel to throw an exception.
   */
  @Test
  public void testInvalidStartEndTime() {
    try {
      test.addMove("R", new Point2D.Double(0, 0), new Point2D.Double(5, 5),
              -2, 5);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a negative end time will cause the AnimationModel to throw an exception.
   */
  @Test
  public void testInvalidStartEndTime2() {
    try {
      test.changeColor("T", new Color(0, 0, 0), new Color(1, 1, 1),
              2, -7);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether an end time greater than the start time will cause the AnimationModel to throw
   * an exception.
   */
  @Test
  public void testInvalidStartEndTime3() {
    try {
      test.changeDimension("S", 5, 5, 10, 10, 7, 2);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a null IShape will cause the addShape method to throw an exception.
   */
  @Test
  public void testNullShape() {
    try {
      test.addShape("", null, 0, 5);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a null Point2D will cause the addMove method to throw an exception.
   */
  @Test
  public void testNullPoint() {
    try {
      test.addMove("", null, null, 0, 5);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing whether a null IColor will cause the changeColor method to throw an exception.
   */
  @Test
  public void testNullColor() {
    try {
      test.changeColor("", null, null, 0, 5);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Testing the toString method of the AnimationModelImpl.
   */
  @Test
  public void testToString() {
    assertEquals("Shapes:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n" +
                    "Appears at t=0\n" +
                    "Disappears at t=5\n" +
                    "\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=3\n" +
                    "Disappears at t=7\n" +
                    "\n" +
                    "shape A moves from (0.0,0.0) to (-4.0,-4.0) from t=3 to t=5\n" +
                    "shape B moves from (5.0,5.0) to (5.0,10.0) from t=5 to t=7\n" +
                    "shape A scales from Width: 4.0, Height: 2.0 to Width: 2.0, " +
                    "Height 4.0 from t=2 to t=4\n" +
                    "shape B changes color from (1.0,1.0,1.0) to (0.0,1.0,0.0) from t=3 to t=7\n",
            test.toString());
  }

  /**
   * Testing the currentFrame method of AnimationModel. The test checks a total of 8 frames.
   */
  @Test
  public void testFrameByFrame() {
    // frame 0
    assertEquals("CurrentTime: 0\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 1
    test.advanceFrame();
    assertEquals("CurrentTime: 1\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 2
    test.advanceFrame();
    assertEquals("CurrentTime: 2\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 3
    test.advanceFrame();
    assertEquals("CurrentTime: 3\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 3.0, Height: 3.0, Color: (0.0,0.0,0.0)\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (1.0,1.0,1.0)\n",
            test.currentFrame());

    // frame 4
    test.advanceFrame();
    assertEquals("CurrentTime: 4\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (-2.0,-2.0), Width: 2.0, Height: 4.0, Color: (0.0,0.0,0.0)\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (0.8,1.0,0.8)\n",
            test.currentFrame());

    // frame 5
    test.advanceFrame();
    assertEquals("CurrentTime: 5\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (-4.0,-4.0), Width: 2.0, Height: 4.0, Color: (0.0,0.0,0.0)\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (0.5,1.0,0.5)\n",
            test.currentFrame());

    // frame 6
    test.advanceFrame();
    assertEquals("CurrentTime: 6\n" +
                    "Current Frame:\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,7.5), Width: 5.0, Height: 5.0, Color: (0.3,1.0,0.3)\n",
            test.currentFrame());

    // frame 7
    test.advanceFrame();
    assertEquals("CurrentTime: 7\n" +
                    "Current Frame:\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,10.0), Width: 5.0, Height: 5.0, Color: (0.0,1.0,0.0)\n",
            test.currentFrame());

    // frame 8
    test.advanceFrame();
    assertEquals("CurrentTime: 8\n" + "Current Frame:\n", test.currentFrame());
  }

  /**
   * Test the effect of adding a command for an IShape that has yet to be added.
   */
  @Test
  public void testAddEmptyCommand() {
    test.addMove("Random String", new Point2D.Double(0, 0),
            new Point2D.Double(10, 10), 0, 2);
    // the command shows up in the toString
    assertEquals("Shapes:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n" +
                    "Appears at t=0\n" +
                    "Disappears at t=5\n" +
                    "\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=3\n" +
                    "Disappears at t=7\n" +
                    "\n" +
                    "shape A moves from (0.0,0.0) to (-4.0,-4.0) from t=3 to t=5\n" +
                    "shape B moves from (5.0,5.0) to (5.0,10.0) from t=5 to t=7\n" +
                    "shape A scales from Width: 4.0, Height: 2.0 to Width: 2.0, " +
                    "Height 4.0 from t=2 to t=4\n" +
                    "shape B changes color from (1.0,1.0,1.0) to (0.0,1.0,0.0) from t=3 to t=7\n" +
                    "shape Random String moves from (0.0,0.0) to (10.0,10.0) from t=0 to t=2\n",
            test.toString());

    // But the command has no effect on the frame-to-frame output
    // frame 0
    assertEquals("CurrentTime: 0\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 1
    test.advanceFrame();
    assertEquals("CurrentTime: 1\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 2
    test.advanceFrame();
    assertEquals("CurrentTime: 2\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());
  }

  /**
   * Testing the effect of having the same type of command for the same IShape in the same time
   * frame. The command should not be added.
   */
  @Test
  public void testAddOverlappingCommand() {
    test.addMove("A", new Point2D.Double(0, 0), new Point2D.Double(4, 4), 3, 5);
    assertEquals("Shapes:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n" +
                    "Appears at t=0\n" +
                    "Disappears at t=5\n" +
                    "\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=3\n" +
                    "Disappears at t=7\n" +
                    "\n" +
                    "shape A moves from (0.0,0.0) to (-4.0,-4.0) from t=3 to t=5\n" +
                    "shape B moves from (5.0,5.0) to (5.0,10.0) from t=5 to t=7\n" +
                    "shape A scales from Width: 4.0, Height: 2.0 to Width: 2.0, " +
                    "Height 4.0 from t=2 to t=4\n" +
                    "shape B changes color from (1.0,1.0,1.0) to (0.0,1.0,0.0) from t=3 to t=7\n",
            test.toString());
  }

  /**
   * Testing the effect of having a command whose duration is outside of the appear-disappear
   * duration of the IShape it's supposed to work on. Nothing should happen.
   */
  @Test
  public void testCommandNotInShapeDuration() {
    test.changeDimension("B", 5, 5, 7, 7, 0, 2);

    // frame 0
    assertEquals("CurrentTime: 0\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 1
    test.advanceFrame();
    assertEquals("CurrentTime: 1\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 2
    test.advanceFrame();
    assertEquals("CurrentTime: 2\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n",
            test.currentFrame());

    // frame 3
    test.advanceFrame();
    assertEquals("CurrentTime: 3\n" +
                    "Current Frame:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 3.0, Height: 3.0, Color: (0.0,0.0,0.0)\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (1.0,1.0,1.0)\n",
            test.currentFrame());
  }

  /**
   * Testing the effect of adding an IShape with a name that's identical to an IShape already in the
   * animation. The first IShape should stay in the animation while the attempt to add the second
   * should be ignored.
   */
  @Test
  public void testAddingExistingName() {
    IShape anotherRect = new Rectangle(new Point2D.Double(-4, -4), new Color(0, 0, 0),
            4, 10);
    test.addShape("A", anotherRect, 0, 10);
    assertEquals("Shapes:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n" +
                    "Appears at t=0\n" +
                    "Disappears at t=5\n" +
                    "\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=3\n" +
                    "Disappears at t=7\n" +
                    "\n" +
                    "shape A moves from (0.0,0.0) to (-4.0,-4.0) from t=3 to t=5\n" +
                    "shape B moves from (5.0,5.0) to (5.0,10.0) from t=5 to t=7\n" +
                    "shape A scales from Width: 4.0, Height: 2.0 to Width: 2.0, " +
                    "Height 4.0 from t=2 to t=4\n" +
                    "shape B changes color from (1.0,1.0,1.0) to (0.0,1.0,0.0) from t=3 to t=7\n",
            test.toString());
  }

  /**
   * Test the toText method that should produce a readable text presentation of the animation for
   * humans.
   */
  @Test
  public void testTextOutput() {
    assertEquals("Shapes:\n" +
                    "Name: A\n" +
                    "Type: oval\n" +
                    "Center: (0.0,0.0), Width: 4.0, Height: 2.0, Color: (0.0,0.0,0.0)\n" +
                    "Appears at t=0.0s\n" +
                    "Disappears at t=1.3s\n" +
                    "\n" +
                    "Name: B\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 5.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0.8s\n" +
                    "Disappears at t=1.8s\n" +
                    "\n" +
                    "shape A moves from (0.0,0.0) to (-4.0,-4.0) from t=0.8s to t=1.3s\n" +
                    "shape B moves from (5.0,5.0) to (5.0,10.0) from t=1.3s to t=1.8s\n" +
                    "shape A scales from Width: 4.0, Height: 2.0 to Width: 2.0, Height 4.0 from " +
                    "t=0.5s to t=1.0s\n" +
                    "shape B changes color from (1.0,1.0,1.0) to (0.0,1.0,0.0) " +
                    "from t=0.8s to t=1.8s\n",
            test.toText());
  }

  /**
   * Test the toSVG method which should produce a svg compliant text for html animation.
   */
  @Test
  public void testSVGOutput() {
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
                    "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "\n" +
                    "<ellipse id=\"A\" cx=\"0\" cy=\"0\" rx=\"4\" ry=\"2\" fill=\"rgb(0,0,0)\" " +
                    "visibility=\"hidden\" >\n" +
                    "     <animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1250.0ms\" " +
                    "attributeName=\"visibility\" to=\"visible\" />\n" +
                    "     <animate attributeType=\"xml\" begin=\"750.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"cx\" from=\"0\" to=\"-4\" fill=\"freeze\" />\n" +
                    "     <animate attributeType=\"xml\" begin=\"750.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"cy\" from=\"0\" to=\"-4\" fill=\"freeze\" />\n" +
                    "     <animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"rx\" from=\"4\" to=\"2\" fill=\"freeze\" />\n" +
                    "     <animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"ry\" from=\"2\" to=\"4\" fill=\"freeze\" />\n" +
                    "</ellipse>\n" +
                    "\n" +
                    "<rect id=\"B\" x=\"5\" y=\"5\" width=\"5\" height=\"5\" " +
                    "fill=\"rgb(255,255,255)\" visibility=\"hidden\" >\n" +
                    "     <animate attributeType=\"xml\" begin=\"750.0ms\" dur=\"1000.0ms\" " +
                    "attributeName=\"visibility\" to=\"visible\" />\n" +
                    "     <animate attributeType=\"xml\" begin=\"1250.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"x\" from=\"5\" to=\"5\" fill=\"freeze\" />\n" +
                    "     <animate attributeType=\"xml\" begin=\"1250.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"y\" from=\"5\" to=\"10\" fill=\"freeze\" />\n" +
                    "     <animate attributeType=\"xml\" begin=\"750.0ms\" dur=\"1000.0ms\" " +
                    "attributeName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(0,255,0)\" " +
                    "fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "\n" +
                    "</svg>",
            test.toSVG());
  }
}