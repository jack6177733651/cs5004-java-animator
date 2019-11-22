package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.util.List;

import cs5004.animator.model.shape.IColor;
import cs5004.animator.model.shape.IShape;

/**
 * An interface for implementing IAnimations, which represent an animation. The animation consists
 * of IShapes which make up frames of the animation. Such frames can be modified by moving, scaling,
 * or changing the color of the IShapes.
 */
public interface AnimationModel {
  /**
   * Add an IShape to to the available assets.
   *
   * @param name      the String name of the IShape
   * @param shape     the IShape
   * @param startTime the int appearance time for this IShape
   * @param endTime   the int disappearance time for this IShape
   */
  void addShape(String name, IShape shape, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Add a Move object to the AnimationModel, which will be used to move an IShape.
   *
   * @param name      the String name of the IShape to be moved
   * @param startPos  the Point2D starting position of the IShape
   * @param endPos    the Point2D ending position of the IShape
   * @param startTime the int starting time for moving the IShape
   * @param endTime   the int ending time for moving the IShape
   */
  void addMove(String name, Point2D startPos, Point2D endPos, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Change the IColor of the IShape associated with the passed String name.
   *
   * @param name       the String name of the IShape to be recolored
   * @param startColor the starting IColor of the IShape
   * @param endColor   the ending IColor of the IShape
   * @param startTime  the int starting time for recoloring the IShape
   * @param endTime    the int ending time for recoloring the IShape
   */
  void changeColor(String name, IColor startColor, IColor endColor, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Scale the dimensions of the IShape associated with the passed String name.
   *
   * @param name      the String name of the IShape to be scaled
   * @param startX    the double x starting dimension of the IShape
   * @param startY    the double y starting dimension of the IShape
   * @param endX      the double x ending dimension of the IShape
   * @param endY      the double y ending dimension of the IShape
   * @param startTime the int starting time for scaling the IShape
   * @param endTIme   the int ending time for scaling the IShape
   */
  void changeDimension(String name, double startX, double startY, double endX, double endY,
                       int startTime, int endTIme);

  /**
   * Advance from this frame to the next frame of this AnimationModel.
   */
  void advanceFrame();

  /**
   * Get the current frame of the AnimationModel as a String.
   *
   * @return the String representation of the current frame of this AnimationModel
   */
  String currentFrame();

  /**
   * Get the shapes in the current frame of the animation as a list of IShapes.
   *
   * @return a List of IShapes that represent the shapes in the current frame of the animation
   */
  List<IShape> shapesInFrame();

  /**
   * Get the text representation of the animation as a String.
   *
   * @return the String of the text representation of the animation
   */
  String toText();

  /**
   * Get the svg representation of the animation as a String.
   *
   * @return the String of the svg representation of the animation
   */
  String toSVG();

  /**
   * Get the frames per second as a double.
   *
   * @return a double that represents the frames-per-second for the animation
   */
  double getFPS();

  /**
   * Reset the animation to its original state at frame 0.
   */
  void reset();
}
