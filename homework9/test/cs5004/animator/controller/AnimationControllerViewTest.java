package cs5004.animator.controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.io.StringWriter;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shape.Color;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A test class for AnimationController and AnimationView, which act as the controller and view of
 * the animation.
 */
public class AnimationControllerViewTest {
  private AnimationController controller;
  private StringWriter writer;

  /**
   * Set up the model, view, and controller of the animation.
   */
  @Before
  public void setUp() {
    AnimationModel model = new AnimationModelImpl(4.0);
    IShape oval = new Oval(new Point2D.Double(0, 0), new Color(0, 0, 0), 4, 2);
    model.addShape("A", oval, 0, 5);
    IShape rect = new Rectangle(new Point2D.Double(5, 5), new Color(1, 1, 1), 5, 5);
    model.addShape("B", rect, 3, 7);
    model.addMove("A", new Point2D.Double(0, 0), new Point2D.Double(-4, -4), 3, 5);
    model.addMove("B", new Point2D.Double(5, 5), new Point2D.Double(5, 10), 5, 7);
    model.changeDimension("A", 4, 2, 2, 4, 2, 4);
    model.changeColor("B", new Color(1, 1, 1), new Color(0, 1, 0), 3, 7);
    writer = new StringWriter();
    AnimationView view = new TextView();
    view.setSource(writer);
    controller = new AnimationControllerImpl(view, model);
  }

  /**
   * Test whether a null Appendable object causes the view to throw an exception.
   */
  @Test
  public void testNullAppendable() {
    try {
      AnimationView badView = new TextView();
      badView.setInput("hello");
      fail("An exception should have been thrown");
    } catch (NullPointerException e) {
      System.err.append(e.getMessage());
    }
  }

  /**
   * Test whether the controller throws an exception for a null view.
   */
  @Test
  public void testNullView() {
    try {
      AnimationModel goodModel = new AnimationModelImpl(5);
      AnimationController badController = new AnimationControllerImpl(null, goodModel);
      badController.run("text");
      fail("An exception should have been thrown");
    } catch (NullPointerException e) {
      System.err.append(e.getMessage());
    }
  }

  /**
   * Test whether the controller throws an exception for a null controller.
   */
  @Test
  public void testNullModel() {
    try {
      AnimationView goodView = new TextView();
      goodView.setSource(System.out);
      AnimationController badController = new AnimationControllerImpl(goodView, null);
      badController.run("text");
      fail("An exception should have been thrown");
    } catch (NullPointerException e) {
      System.err.append(e.getMessage());
    }
  }

  /**
   * Tests a valid model, view, and controller.
   */
  @Test
  public void testValidController() {
    try {
      AnimationModel goodModel = new AnimationModelImpl(5);
      AnimationView goodView = new TextView();
      goodView.setSource(System.out);
      AnimationController goodController = new AnimationControllerImpl(goodView, goodModel);
      goodController.run("text");
    } catch (NullPointerException e) {
      fail("An exception shouldn't have been thrown");
    }
  }

  /**
   * Tests the text output of the view, which should be a String representation of the animation for
   * human reading.
   */
  @Test
  public void testTextView() {
    controller.run("text");
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
                    "shape A scales from Width: 4.0, Height: 2.0 to Width: 2.0, Height 4.0 " +
                    "from t=0.5s to t=1.0s\n" +
                    "shape B changes color from (1.0,1.0,1.0) to (0.0,1.0,0.0) " +
                    "from t=0.8s to t=1.8s\n",
            writer.toString());
  }

  /**
   * Tests the svg output of the view, which should be a String representation of the animation for
   * html processing.
   */
  @Test
  public void testSVGView() {
    controller.run("svg");
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
            "<rect id=\"B\" x=\"5\" y=\"5\" width=\"5\" height=\"5\" fill=\"rgb(255,255,255)\" " +
            "visibility=\"hidden\" >\n" +
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
            "</svg>", writer.toString());
  }

  /**
   * Tests whether the controller throws an exception for a viewType other than text or svg.
   */
  @Test
  public void testInvalidView() {
    try {
      controller.run("something");
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      System.err.append(e.getMessage());
    }
  }
}