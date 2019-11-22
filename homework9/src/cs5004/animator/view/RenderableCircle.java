package cs5004.animator.view;

import java.awt.Graphics;
import java.awt.Color;

import cs5004.animator.model.shape.IShape;

/**
 * A class that implements IRenderableShape, acts as a renderable circle for the view.
 */
public class RenderableCircle extends AbstractRenderableShape {

  /**
   * A constructor that initializes this RenderableCircle.
   *
   * @param circle the given IShape
   */
  public RenderableCircle(IShape circle) {
    super(circle);
  }

  @Override
  public void drawYourSelf(Graphics g) {
    int x = (int) shape.getPoint().getX();
    int y = (int) shape.getPoint().getY();
    int width = (int) shape.getXDimension();
    int height = (int) shape.getYDimension();
    int red = (int) (shape.getColor().getR() * 255);
    int green = (int) (shape.getColor().getG() * 255);
    int blue = (int) (shape.getColor().getB() * 255);
    g.setColor(new Color(red, green, blue));
    g.fillOval(x, y, width, height);
  }

  @Override
  public RenderableShapeType getType() {
    return RenderableShapeType.Circle;
  }
}
