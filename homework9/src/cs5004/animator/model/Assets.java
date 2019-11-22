package cs5004.animator.model;

import java.util.LinkedHashMap;
import java.util.Map;

import cs5004.animator.model.shape.IShape;

/**
 * A package private class that stores the IShapes that the AnimationModelImpl cs5004.animator.model
 * takes in. This class presents an collection of the original assets/IShapes that can be used to
 * create a Frame.
 */
class Assets {
  // a Map that links a String name to an IShape
  private Map<String, IShape> assets;
  // a Map that links a String name to a StartEndTime
  private Map<String, StartEndTime> durations;

  /**
   * A constructor that initializes an empty Asset object.
   */
  Assets() {
    this.assets = new LinkedHashMap<>();
    this.durations = new LinkedHashMap<>();
  }

  /**
   * Add an IShape to this Assets object.
   *
   * @param name      the String name of the IShape object to be added
   * @param shape     the IShape to be added
   * @param startTime the appearance time of the IShape
   * @param endTime   the disappearance time of the IShape
   */
  void addAsset(String name, IShape shape, int startTime, int endTime) {
    assets.put(name, shape);
    durations.put(name, new StartEndTime(startTime, endTime));
  }

  /**
   * Return the available assets as a Map that links String names and IShapes.
   *
   * @param currentTIme the current time of the Animation
   * @return a Map that maps String to IShape, represents the available assets/IShape
   */
  Map<String, IShape> availableAssets(int currentTIme) {
    Map<String, IShape> availableAssets = new LinkedHashMap<>();
    for (String key : assets.keySet()) {
      if (currentTIme >= durations.get(key).getStartTime()
              && currentTIme <= durations.get(key).getEndTime()) {
        availableAssets.put(key, assets.get(key));
      }
    }
    return availableAssets;
  }

  /**
   * Is the name of an IShape in the Assets.
   *
   * @param name the String name to be checked whether not it exists in the Assets
   * @return boolean whether the name exists in this Assets object
   */
  boolean nameInAssets(String name) {
    return assets.containsKey(name);
  }

  /**
   * Return all contained assets as a Map that maps between String and IShape.
   *
   * @return a Map that maps String and IShape
   */
  Map<String, IShape> allAssets() {
    return this.assets;
  }

  /**
   * Return the durations of all contained assets as a Map that maps between String and
   * StartEndTime.
   *
   * @return a Map that maps String and StartEndTime
   */
  Map<String, StartEndTime> allDurations() {
    return this.durations;
  }

  /**
   * Return a copy of this Assets.
   *
   * @return a copy of this Assets
   */
  Assets copy() {
    Assets copy = new Assets();
    for (String name : assets.keySet()) {
      copy.addAsset(name, assets.get(name).copy(), durations.get(name).getStartTime(),
              durations.get(name).getEndTime());
    }
    return copy;
  }

  /**
   * Return the String representation this Assets object.
   *
   * @return the String representation of this Assets object
   */
  @Override
  public String toString() {
    String output = "Shapes:\n";
    for (String k : assets.keySet()) {
      output += String.format("Name: %s\n", k);
      output += assets.get(k).toString();
      output += String.format("Appears at t=%d\n", durations.get(k).getStartTime());
      output += String.format("Disappears at t=%d\n", durations.get(k).getEndTime());
      output += "\n";
    }
    return output;
  }
}
