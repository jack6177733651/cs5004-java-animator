package cs5004.animator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.shape.IShape;

/**
 * A package private class that represents a frame of an animation. A frame contains all of the
 * shapes that are visible at a given time.
 */
class Frame {
  private Map<String, IShape> currentFrame;

  /**
   * A constructor that initializes this Frame with the available assets at a given time.
   *
   * @param assets      an Assets object that decides which IShapes are available at any given time
   * @param currentTime the current time of the animation
   */
  Frame(Assets assets, int currentTime) {
    this.currentFrame = assets.availableAssets(currentTime);
  }

  /**
   * Get the IShape associated with a given String name.
   *
   * @param name the String name used to search for an IShape
   * @return the IShape associated with a name
   */
  IShape getShape(String name) {
    return currentFrame.get(name);
  }

  List<IShape> getAllShapes() {
    return new ArrayList<>(currentFrame.values());
  }

  /**
   * Get the String representation of this Frame.
   *
   * @return the String representation of this Frame
   */
  @Override
  public String toString() {
    String output = "Current Frame:\n";
    for (String key : currentFrame.keySet()) {
      output += "Name: " + key + "\n";
      output += currentFrame.get(key).toString();
    }
    return output;
  }
}
