package cs5004.animator.model.shape;

import java.awt.geom.Point2D;

/**
 * A class that extends AbstractShape, represents a Rectangle. A rectangle has its lower-left
 * vertex, its color, its width, and its height.
 */
public class Rectangle extends AbstractShape {

  /**
   * A constructor that initializes this Rectangle with the given point, color, width, and height.
   *
   * @param origin the Point2D point that represents the Rectangle's lower-left vertex
   * @param color  the IColor color of this Rectangle
   * @param width  the double width of this Rectangle
   * @param height the double height of this Rectangle
   */
  public Rectangle(Point2D origin, IColor color, double width, double height) {
    super(origin, color, width, height);
  }

  @Override
  public boolean isRectangle() {
    return true;
  }

  @Override
  public boolean isOval() {
    return false;
  }

  @Override
  public IShape copy() {
    return new Rectangle((Point2D) origin.clone(), color.copy(), this.xLength, this.yLength);
  }

  /**
   * Get the string representation of this Rectangle.
   *
   * @return the String representation of this Rectangle
   */
  @Override
  public String toString() {
    String output = "Type: rectangle\n";
    output += "Min corner: (";
    output += String.format("%.1f,%.1f), Width: %.1f, Height: %.1f, ", this.origin.getX(),
            this.origin.getY(), this.xLength, this.yLength);
    output += "Color: " + this.color.toString() + "\n";
    return output;
  }
}
