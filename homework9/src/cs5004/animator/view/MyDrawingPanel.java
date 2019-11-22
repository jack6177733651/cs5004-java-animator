package cs5004.animator.view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * A class that extends JPanel, represents the panel on which all animation shows.
 */
public class MyDrawingPanel extends JPanel {
  List<IRenderableShape> shapes;

  /**
   * A constructor that initialize the List of IRenderableShapes.
   */
  public MyDrawingPanel() {
    shapes = new ArrayList<>();
  }

  /**
   * Update the List of IRenderableShapes to the given List of shapes.
   *
   * @param data a List of IrenderableShapes
   */
  public void updateShapes(List<IRenderableShape> data) {
    shapes = data;
  }

  /**
   * Paint the List of IRenderableShapes upon the panel.
   *
   * @param g the Graphics object on which the drawing occurs
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IRenderableShape shape : shapes) {
      shape.drawYourSelf(g);
    }
  }
}
