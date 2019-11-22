package cs5004.animator.model.shape;

import java.awt.geom.Point2D;

/**
 * A class that extends AbstractShape, represents an Oval. An oval has a center, a color, a x
 * radius, and a y radius.
 */
public class Oval extends AbstractShape {

  /**
   * Constructs this Oval using the passed point, color, and x and y radii.
   *
   * @param origin  the Point2D center of this Oval
   * @param color   the IColor color of this Oval
   * @param xRadius the double x radius of this Oval
   * @param yRadius the double y radius of this Oval
   */
  public Oval(Point2D origin, IColor color, double xRadius, double yRadius) {
    super(origin, color, xRadius, yRadius);
  }

  @Override
  public boolean isRectangle() {
    return false;
  }

  @Override
  public boolean isOval() {
    return true;
  }

  @Override
  public IShape copy() {
    return new Oval((Point2D) origin.clone(), color.copy(), this.xLength, this.xLength);
  }

  /**
   * Get the string representation of this Oval.
   *
   * @return the String representation of this Oval
   */
  @Override
  public String toString() {
    String output = "Type: oval\n";
    output += "Center: (";
    output += String.format("%.1f,%.1f), Width: %.1f, Height: %.1f, ", this.origin.getX(),
            this.origin.getY(), this.xLength, this.yLength);
    output += "Color: " + this.color.toString() + "\n";
    return output;
  }
}
