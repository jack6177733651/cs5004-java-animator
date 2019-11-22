package cs5004.animator.model;

/**
 * An interface for implementing classes that are designed to modify a Frame. These modifications
 * will be based on the current time.
 */
interface FrameModification {
  /**
   * Change the passed Frame, depending on the passed currentTime.
   *
   * @param frame       the Frame to be modified
   * @param currentTime the current time of the animation
   */
  void changeFrame(Frame frame, int currentTime);

  /**
   * Is the FrameModification a Move.
   *
   * @return whether the FrameModification is a Move
   */
  boolean isMove();

  /**
   * Is the FrameModification a Scale.
   *
   * @return whether the FrameModification is a Scale
   */
  boolean isScale();

  /**
   * Is the FrameModification a ChangeColor.
   *
   * @return whether the FrameModification is a ChangeColor
   */
  boolean isChangeColor();

  /**
   * Get the name of the IShape associated with this FrameModification.
   *
   * @return the name of the IShape associated with this FrameModification
   */
  String getName();

  /**
   * Get the duration of this FrameModification as a StartEndTime.
   *
   * @return the duration of the IShape associated as a StartEndTime
   */
  StartEndTime getDuration();
}
