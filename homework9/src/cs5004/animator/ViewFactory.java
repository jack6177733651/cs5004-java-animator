package cs5004.animator;

import cs5004.animator.view.AnimationView;
import cs5004.animator.view.MyWindow;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;

/**
 * A class that acts a factory that creates different views depends on the input String.
 */
public class ViewFactory {

  /**
   * Create the appropriate view by the passed String.
   *
   * @param viewType the String that represents the view type
   * @return an appropriate AnimationView
   */
  public static AnimationView createView(String viewType) {
    String viewLC = viewType.toLowerCase();
    if (viewLC.equals("text")) {
      return createTextView();
    } else if (viewLC.equals("svg")) {
      return createSVGView();
    } else if (viewLC.equals("visual")) {
      return createVisualView();
    } else {
      throw new IllegalArgumentException("Invalid view type");
    }
  }

  private static AnimationView createTextView() {
    return new TextView();
  }

  private static AnimationView createSVGView() {
    return new SVGView();
  }

  private static AnimationView createVisualView() {
    return new MyWindow();
  }
}
