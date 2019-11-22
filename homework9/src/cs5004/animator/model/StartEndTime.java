package cs5004.animator.model;

/**
 * This class represents a duration, with a starting and ending time.
 */
class StartEndTime {
  private int startTime;
  private int endTime;

  /**
   * Initializes this StartEndTime object with the int startTime and endTime. Follows the
   * constraints that neither time can be negative and the endTime must be bigger than the
   * StartTime.
   *
   * @param startTime the starting time
   * @param endTime   the ending time
   * @throws IllegalArgumentException to be thrown when the above infractions occur
   */
  StartEndTime(int startTime, int endTime) throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0 || startTime >= endTime) {
      throw new IllegalArgumentException("Invalid start or end times");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Get the starting time.
   *
   * @return the starting time
   */
  int getStartTime() {
    return startTime;
  }

  /**
   * Get the ending time.
   *
   * @return the ending time
   */
  int getEndTime() {
    return endTime;
  }

  /**
   * Get the duration, or the time between the start and end times.
   *
   * @return the duration of this StartEndTime
   */
  int getDuration() {
    return endTime - startTime;
  }

  /**
   * Is the given time within the duration of this StartEndTime.
   *
   * @param time the time to be checked whether it's within the duration
   * @return whether the given time is within the duration of this StartEndTime
   */
  boolean withinDuration(int time) {
    return time >= startTime && time <= endTime;
  }
}
