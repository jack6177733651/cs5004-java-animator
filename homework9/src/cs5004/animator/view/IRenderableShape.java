package cs5004.animator.view;

import java.awt.Graphics;

/**
 * An interface for implementing classes that represent shapes that can be rendered by the view of
 * the animation.
 */
public interface IRenderableShape {

  /**
   * Get the width of the IRenderableShape.
   *
   * @return the width of the IRenderableShape
   */
  double getWidth();

  /**
   * Get the height of the IRenderableShape.
   *
   * @return the height of the IRenderableShape
   */
  double getHeight();

  /**
   * Get the x dimension of the IRenderableShape.
   *
   * @return the x dimension of the IRenderableShape
   */
  int getX();

  /**
   * Get the y dimension of the IRenderableShape.
   *
   * @return the y dimension of the IRenderableShape
   */
  int getY();

  /**
   * Asks for the IRenderableShape to draw itself.
   *
   * @param g the Graphics on which the IRenderableShape is going to draw itself.
   */
  void drawYourSelf(Graphics g);

  /**
   * Get the type of the IRenderableShape as a RenderableShapeType enum.
   *
   * @return a RenderableShapeType that represents the type of shape this IRenderableShape is
   */
  RenderableShapeType getType();
}
