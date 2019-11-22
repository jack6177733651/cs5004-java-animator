package cs5004.animator.model;

import cs5004.animator.model.shape.IShape;

/**
 * A class that implements FrameModification, represents a scaling operation on a Frame.
 */
class Scale implements FrameModification {
  private String name;
  private double startWidth;
  private double startHeight;
  private double endWidth;
  private double endHeight;
  private StartEndTime duration;

  /**
   * Initializes this Scale object with the String name of the IShape to be scaled, the starting
   * width, the starting height, the ending width, the ending height, and the duration.
   *
   * @param name        String the name of the IShape to be scaled
   * @param startWidth  the starting width
   * @param startHeight the starting height
   * @param endWidth    the ending width
   * @param endHeight   the ending height
   * @param duration    the duration of the scaling operation as a StartEndTime
   * @throws IllegalArgumentException to be thrown when any of the passed dimensions is negative
   */
  Scale(String name, double startWidth, double startHeight, double endWidth,
        double endHeight, StartEndTime duration) throws IllegalArgumentException {
    if (startWidth < 0 || startHeight < 0 || endWidth < 0 || endHeight < 0) {
      throw new IllegalArgumentException("Invalid dimensions for scaling");
    }
    this.name = name;
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.duration = duration;
  }

  @Override
  public void changeFrame(Frame frame, int currentTime) {
    if (currentTime < duration.getStartTime() || currentTime > duration.getEndTime()) {
      return;
    }
    IShape shape = frame.getShape(this.name);
    if (shape == null) {
      return;
    }
    double deltaWidth = endWidth - startWidth;
    double deltaHeight = endHeight - startHeight;
    double deltaWidthPerTime = deltaWidth / duration.getDuration();
    double deltaHeightPerTime = deltaHeight / duration.getDuration();
    double widthShift = deltaWidthPerTime * (currentTime - duration.getStartTime());
    double heightShift = deltaHeightPerTime * (currentTime - duration.getStartTime());
    double newWidth = startWidth + widthShift;
    double newHeight = startHeight + heightShift;
    shape.setXDimension(newWidth);
    shape.setYDimension(newHeight);
  }

  @Override
  public boolean isMove() {
    return false;
  }

  @Override
  public boolean isScale() {
    return true;
  }

  @Override
  public boolean isChangeColor() {
    return false;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public StartEndTime getDuration() {
    return this.duration;
  }

  /**
   * Get the starting width of this Scale as a double.
   *
   * @return the starting width as a double
   */
  double getStartWidth() {
    return this.startWidth;
  }

  /**
   * Get the starting height of this Scale as a double.
   *
   * @return the starting height as a double
   */
  double getStartHeight() {
    return this.startHeight;
  }

  /**
   * Get the ending width of this Scale as a double.
   *
   * @return the ending width as a double
   */
  double getEndWidth() {
    return this.endWidth;
  }

  /**
   * Get the ending height of this Scale as a double.
   *
   * @return the ending height as a double
   */
  double getEndHeight() {
    return this.endHeight;
  }

  /**
   * Get the String representation of this Scale object.
   *
   * @return the String representation of this Scale object
   */
  @Override
  public String toString() {
    String output = "shape " + this.name + " scales from ";
    output += String.format("Width: %.1f, Height: %.1f to Width: %.1f, Height %.1f ",
            startWidth, startHeight, endWidth, endHeight);
    output += String.format("from t=%d to t=%d\n", duration.getStartTime(), duration.getEndTime());
    return output;
  }
}
