package cs5004.animator.view;

import java.awt.Graphics;
import java.awt.Color;

import cs5004.animator.model.shape.IShape;

/**
 * A class that implements IRenderableShape, acts as a renderable rectangle for the view.
 */
public class RenderableRectangle extends AbstractRenderableShape {

  /**
   * A constructor that initializes this RenderableRectangle.
   *
   * @param rect the given IShape
   */
  public RenderableRectangle(IShape rect) {
    super(rect);
  }

  @Override
  public void drawYourSelf(Graphics g) {
    int width = (int) shape.getXDimension();
    int height = (int) shape.getYDimension();
    int x = (int) shape.getPoint().getX();
    int y = (int) shape.getPoint().getY();
    int red = (int) (shape.getColor().getR() * 255);
    int green = (int) (shape.getColor().getG() * 255);
    int blue = (int) (shape.getColor().getB() * 255);
    g.setColor(new Color(red, green, blue));
    g.fillRect(x, y, width, height);
  }

  @Override
  public RenderableShapeType getType() {
    return RenderableShapeType.Rectangle;
  }
}
