package cs5004.animator.model.shape;

/**
 * An interface for implementing IColors, which represent a RGB color value. These colors all have a
 * red, green, and blue value.
 */
public interface IColor {
  /**
   * Get the red value from this IColor.
   *
   * @return the red value of this IColor as a double
   */
  double getR();

  /**
   * Get the green value from this IColor.
   *
   * @return the green value of this IColor as a double
   */
  double getG();

  /**
   * Get the blue value from this IColor.
   *
   * @return the blue value of this IColor as a double
   */
  double getB();

  /**
   * Get the svg representation of this IColor as a String.
   *
   * @return a String that is the svg representationof this IColor
   */
  String getSVG();

  /**
   * Create a copy of this color.
   *
   * @return an IColor copy of this color
   */
  IColor copy();
}
