package cs5004.animator.view;

import java.util.List;

/**
 * An interface that implements views of the animation. The role of such classes would be to take in
 * a String representation of the animation and append it to an Appendable output.
 */
public interface AnimationView {

  /**
   * Display the view by rendering it.
   */
  void render();

  /**
   * Set the Appendable output for the view.
   *
   * @param output the Appendable object that acts as the output
   */
  void setSource(Appendable output);

  /**
   * Set the String representation of the animation in the view.
   *
   * @param input the String representation of the animation
   */
  void setInput(String input);

  /**
   * Set the input of the view to the given List of IRenderableShapes.
   *
   * @param data a List of IRenderableShapes that acts as the input for the view
   */
  void setInput(List<IRenderableShape> data);
}
