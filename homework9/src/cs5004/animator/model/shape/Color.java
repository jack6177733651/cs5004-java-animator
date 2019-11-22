package cs5004.animator.model.shape;

/**
 * A class that implements the IColor interface, represents a RGB color. Uses the system in which
 * the red, green, and blue values are limited between 0 and 1.
 */
public class Color implements IColor {
  private double r;
  private double g;
  private double b;

  /**
   * A constructor that initializes the RGB values of this Color to the passed doubles. If the
   * passed doubles do not adhere to the 0 to 1 constraint then throw an exception.
   *
   * @param r a double that represents the red value
   * @param g a double that represents the green value
   * @param b a double that represents the blue value
   * @throws IllegalArgumentException if any of the rgb arguments are not between 0 and 1
   */
  public Color(double r, double g, double b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("One of the passed rgb value is less than 0");
    }
    if (r > 1 || g > 1 || b > 1) {
      throw new IllegalArgumentException("One of the passed rgb value is greater than 1");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public double getR() {
    return this.r;
  }

  @Override
  public double getG() {
    return this.g;
  }

  @Override
  public double getB() {
    return this.b;
  }

  @Override
  public String getSVG() {
    int r = (int) (this.r * 255);
    int g = (int) (this.g * 255);
    int b = (int) (this.b * 255);
    return String.format("rgb(%d,%d,%d)", r, g, b);
  }

  @Override
  public IColor copy() {
    return new Color(this.r, this.g, this.b);
  }

  /**
   * A string representation of this Color object.
   *
   * @return the String representation of this Color object
   */
  @Override
  public String toString() {
    return String.format("(%.1f,%.1f,%.1f)", this.r, this.g, this.b);
  }
}
