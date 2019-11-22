package cs5004.animator.view;

import java.awt.Graphics;

import cs5004.animator.model.shape.IShape;

/**
 * An implementation of IRenderableShape, represents an abstract class that contains the similiar
 * elements for all IRenderableShapes.
 */
public abstract class AbstractRenderableShape implements IRenderableShape {
  protected IShape shape;

  /**
   * A constructor that takes in an IShape and delegates all shape-processing tasks to it.
   *
   * @param shape the IShape delegate
   */
  public AbstractRenderableShape(IShape shape) {
    this.shape = shape;
  }

  @Override
  public double getWidth() {
    return shape.getXDimension();
  }

  @Override
  public double getHeight() {
    return shape.getYDimension();
  }

  @Override
  public int getX() {
    return (int) shape.getPoint().getX();
  }

  @Override
  public int getY() {
    return (int) shape.getPoint().getY();
  }

  @Override
  public abstract void drawYourSelf(Graphics g);

  @Override
  public abstract RenderableShapeType getType();
}
