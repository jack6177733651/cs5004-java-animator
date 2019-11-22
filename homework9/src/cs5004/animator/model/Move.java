package cs5004.animator.model;

import java.awt.geom.Point2D;

import cs5004.animator.model.shape.IShape;

/**
 * A class that implements FrameModification, represents a move for a Frame object.
 */
class Move implements FrameModification {
  private String name;
  private Point2D startPos;
  private Point2D endPos;
  private StartEndTime duration;

  /**
   * Initializes this Move object with the name of the IShape to be moved, the starting position,
   * the ending position, and the duration.
   *
   * @param name     the String name of the IShape to be moved
   * @param startPos the starting position
   * @param endPos   the ending position
   * @param duration the duration of the move as a StartEndTime
   */
  Move(String name, Point2D startPos, Point2D endPos, StartEndTime duration) {
    this.name = name;
    this.startPos = startPos;
    this.endPos = endPos;
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
    double deltaX = endPos.getX() - startPos.getX();
    double deltaY = endPos.getY() - startPos.getY();
    double deltaXPerTime = deltaX / duration.getDuration();
    double deltaYPerTime = deltaY / duration.getDuration();
    double xShift = deltaXPerTime * (currentTime - duration.getStartTime());
    double yShift = deltaYPerTime * (currentTime - duration.getStartTime());
    double newX = startPos.getX() + xShift;
    double newY = startPos.getY() + yShift;
    Point2D newPos = new Point2D.Double(newX, newY);
    shape.setPoint(newPos);
  }

  @Override
  public boolean isMove() {
    return true;
  }

  @Override
  public boolean isScale() {
    return false;
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
   * Get the starting position as this Move as a Point2D.
   *
   * @return the starting Point2D of this Move
   */
  Point2D getStartPos() {
    return this.startPos;
  }

  /**
   * Get the ending position of this Move as a Point2D.
   *
   * @return the ending Point2D of this Move
   */
  Point2D getEndPos() {
    return this.endPos;
  }

  /**
   * Get the String representation of this Move.
   *
   * @return a String representation of this Move
   */
  @Override
  public String toString() {
    String output = "shape " + name + " moves from ";
    output += String.format("(%.1f,%.1f) to (%.1f,%.1f) from t=%d to t=%d\n",
            startPos.getX(), startPos.getY(), endPos.getX(), endPos.getY(),
            duration.getStartTime(), duration.getEndTime());
    return output;
  }
}
