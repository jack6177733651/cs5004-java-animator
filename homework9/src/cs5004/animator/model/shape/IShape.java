package cs5004.animator.model.shape;

import java.awt.geom.Point2D;

/**
 * An interface for implementing IShapes, which represent 2D shapes. These shapes all have a single
 * Point2D point that fixes it location, an IColor that represents a RGB color value, and two values
 * that represent its dimensions on the x and y axes.
 */
public interface IShape {
  /**
   * Set the point of this IShape to the given Point2D.
   *
   * @param point the Point2D to be set as the point of this IShape
   */
  void setPoint(Point2D point);

  /**
   * Set the color of this IShape to the given IColor.
   *
   * @param color the IColor to be set as the color of this IShape
   */
  void setColor(IColor color);

  /**
   * Set the x dimension of this IShape to the given double.
   *
   * @param x the double to be set as the x dimension of this IShape
   */
  void setXDimension(double x);

  /**
   * Set the y dimension of this IShape to the given double.
   *
   * @param y the double to be set as the y dimension of this IShape
   */
  void setYDimension(double y);

  /**
   * Get the origin point of this IShape as a Point2D.
   *
   * @return the origin Point2D of this IShape
   */
  Point2D getPoint();

  /**
   * Get the color fo this IShape as an IColor.
   *
   * @return the IColor fo this IShape
   */
  IColor getColor();

  /**
   * Get the x dimension of this IShape as a double.
   *
   * @return the x dimension of this IShape
   */
  double getXDimension();

  /**
   * Get the y dimension of this IShape as a double.
   *
   * @return the y dimension of this IShape
   */
  double getYDimension();

  /**
   * Is this IShape a Rectangle.
   *
   * @return a boolean of whether or not this IShape is a Rectangle
   */
  boolean isRectangle();

  /**
   * Is this IShape an Oval.
   *
   * @return a boolean of whether or not this IShape is an Oval
   */
  boolean isOval();

  /**
   * Create a copy of this IShape.
   *
   * @return a copy of this IShape
   */
  IShape copy();
}
