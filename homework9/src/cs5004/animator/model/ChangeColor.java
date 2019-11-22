package cs5004.animator.model;

import cs5004.animator.model.shape.Color;
import cs5004.animator.model.shape.IColor;
import cs5004.animator.model.shape.IShape;

/**
 * A class that implements FrameModification, represents a color change for a Frame object.
 */
class ChangeColor implements FrameModification {
  private String name;
  private IColor startColor;
  private IColor endColor;
  private StartEndTime duration;

  /**
   * A constructor that initializes this ChangeColor with the name of the IShape to be changed, the
   * starting IColor, the ending IColor, and the duration.
   *
   * @param name       the String name of the IShape to be changed
   * @param startColor the starting IColor
   * @param endColor   the ending IColor
   * @param duration   the StartEndTime that represents the duration
   */
  ChangeColor(String name, IColor startColor, IColor endColor, StartEndTime duration) {
    this.name = name;
    this.startColor = startColor;
    this.endColor = endColor;
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
    double deltaR = endColor.getR() - startColor.getR();
    double deltaG = endColor.getG() - startColor.getG();
    double deltaB = endColor.getB() - startColor.getB();
    double deltaRPerTime = deltaR / duration.getDuration();
    double deltaGPerTime = deltaG / duration.getDuration();
    double deltaBPerTime = deltaB / duration.getDuration();
    double rShift = deltaRPerTime * (currentTime - duration.getStartTime());
    double gShift = deltaGPerTime * (currentTime - duration.getStartTime());
    double bShift = deltaBPerTime * (currentTime - duration.getStartTime());
    double newR = startColor.getR() + rShift;
    double newG = startColor.getG() + gShift;
    double newB = startColor.getB() + bShift;
    IColor newColor = new Color(newR, newG, newB);
    shape.setColor(newColor);
  }

  @Override
  public boolean isMove() {
    return false;
  }

  @Override
  public boolean isScale() {
    return false;
  }

  @Override
  public boolean isChangeColor() {
    return true;
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
   * Get the starting color of this ChangeColor as an IColor.
   *
   * @return the starting IColor of this ChangeColor
   */
  IColor getStartColor() {
    return this.startColor;
  }

  /**
   * Get the ending color of this Changecolor as an IColor.
   *
   * @return the ending IColor of this ChangeColor
   */
  IColor getEndColor() {
    return this.endColor;
  }

  /**
   * Get the String representation of this ChangeColor.
   *
   * @return the String representation of this ChangeColor
   */
  @Override
  public String toString() {
    String output = "shape " + this.name + " changes color from ";
    output += startColor.toString() + " to " + endColor.toString();
    output += String.format(" from t=%d to t=%d\n", duration.getStartTime(), duration.getEndTime());
    return output;
  }
}
