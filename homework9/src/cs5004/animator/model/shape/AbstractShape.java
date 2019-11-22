package cs5004.animator.model.shape;

import java.awt.geom.Point2D;

/**
 * An abstract class that implements the IShape interface, represents a abstract shape that shares
 * the common aspects of all shapes. This class contains a Point2D origin point, an IColor rgb
 * color, a double x length, and a double y length.
 */
public abstract class AbstractShape implements IShape {
  protected Point2D origin;
  protected IColor color;
  protected double xLength;
  protected double yLength;

  /**
   * A constructor that initializes the point, color, x length, and y length of this AbstractShape
   * to the given arguments. Throws an exception if the passed lengths are negative.
   *
   * @param origin  the Point2D point that fixes this AbstractShape to the Cartesian plane
   * @param color   the IColor color of this AbstractShape
   * @param xLength the double x length of this AbstractShape
   * @param yLength the double y length of this AbstractShape
   * @throws IllegalArgumentException thrown when the passed x or y length are negative
   * @throws IllegalArgumentException thrown when the passed Point2D or IColor are null
   */
  public AbstractShape(Point2D origin, IColor color, double xLength, double yLength)
          throws IllegalArgumentException {
    if (xLength < 0 || yLength < 0) {
      throw new IllegalArgumentException("Invalid lengths for a shape");
    }
    if (origin == null || color == null) {
      throw new IllegalArgumentException("The passed Point2D or IColor are null");
    }
    this.origin = origin;
    this.color = color;
    this.xLength = xLength;
    this.yLength = yLength;
  }

  @Override
  public void setPoint(Point2D point) {
    this.origin = point;
  }

  @Override
  public void setColor(IColor color) {
    this.color = color;
  }

  @Override
  public void setXDimension(double x) {
    this.xLength = x;
  }

  @Override
  public void setYDimension(double y) {
    this.yLength = y;
  }

  @Override
  public Point2D getPoint() {
    return this.origin;
  }

  @Override
  public IColor getColor() {
    return this.color;
  }

  @Override
  public double getXDimension() {
    return this.xLength;
  }

  @Override
  public double getYDimension() {
    return this.yLength;
  }
}
