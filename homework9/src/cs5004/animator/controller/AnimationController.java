package cs5004.animator.controller;

/**
 * An interface for implementing controllers for the animation. Its role will be to execute the
 * animation given a String viewType, which should be either text or svg.
 */
public interface AnimationController {
  /**
   * Run the animation, the output will depend on the passed String viewType, which should be either
   * text or svg.
   *
   * @param viewType the type of the view output, should be either text or svg
   */
  void run(String viewType);
}
