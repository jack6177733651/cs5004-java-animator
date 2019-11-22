package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.shape.Color;
import cs5004.animator.model.shape.IColor;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import util.TweenModelBuilder;

/**
 * A class that implements AnimationModelImpl, represents an AnimationModelImpl. An animation
 * consists of individual Frames that when combined create a moving picture. The AnimationModelImpl
 * object is initially empty and must be filled with IShapes that are stored in an Assets object.
 * The Assets object is used to create a Frame which represents the currentFrame, depending on the
 * currentTime. This currentFrame is then modified by a List of FrameModifications, which
 * individually modifies the currentFrame. This resultant currentFrame represents a single Frame of
 * the animation that corresponds to the current time.
 */
public final class AnimationModelImpl implements AnimationModel {
  // an internal counter that starts from 0, represents the current time of the animation
  private int currentTime;
  // the assets of this AnimationModelImpl, stores the IShapes, along with their string name,
  // and their appearance and disappearance times
  private Assets assets;
  // a copy of the original assets, for restarting the animation
  private Assets original;
  // a list of objects that modifies a Frame, depending on the currentTime
  private List<FrameModification> frameMods;
  // the current Frame of the AnimationModelImpl, analogous to a cel of a cartoon
  private Frame currentFrame;
  // the speed in ticks per second
  private double fps;

  /**
   * A constructor that creates an empty AnimationModelImpl. Set the currentTime to 0, initializes
   * the Assets, the list of FrameModifications, and the current Frame.
   */
  public AnimationModelImpl(double fps) {
    if (fps <= 0) {
      throw new IllegalArgumentException("The fps cannot be less or equal to 0");
    }
    this.currentTime = 0;
    assets = new Assets();
    original = new Assets();
    frameMods = new ArrayList<>();
    currentFrame = new Frame(assets, currentTime);
    this.fps = fps;
  }

  @Override
  public void addShape(String name, IShape shape, int startTime, int endTime)
          throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("The passed shape is null");
    }
    if (assets.nameInAssets(name)) {
      return;
    }
    this.assets.addAsset(name, shape, startTime, endTime);
    this.original.addAsset(name, shape.copy(), startTime, endTime);
    this.currentFrame = new Frame(this.assets, this.currentTime);
  }

  @Override
  public void addMove(String name, Point2D startPos, Point2D endPos, int startTime, int endTime)
          throws IllegalArgumentException {
    if (startPos == null || endPos == null) {
      throw new IllegalArgumentException("The passed position is null");
    }
    for (FrameModification f : frameMods) {
      if (f.getName().equals(name) && f.isMove()) {
        if (f.getDuration().withinDuration(startTime) || f.getDuration().withinDuration(endTime)) {
          return;
        }
      }
    }
    FrameModification newMove = new Move(name, startPos, endPos,
            new StartEndTime(startTime, endTime));
    frameMods.add(newMove);
  }

  @Override
  public void changeColor(String name, IColor startColor, IColor endColor,
                          int startTime, int endTime) throws IllegalArgumentException {
    if (startColor == null || endColor == null) {
      throw new IllegalArgumentException("The passed color is null");
    }
    for (FrameModification f : frameMods) {
      if (f.getName().equals(name) && f.isChangeColor()) {
        if (f.getDuration().withinDuration(startTime) || f.getDuration().withinDuration(endTime)) {
          return;
        }
      }
    }
    FrameModification newColorChange = new ChangeColor(name, startColor,
            endColor, new StartEndTime(startTime, endTime));
    frameMods.add(newColorChange);
  }

  @Override
  public void changeDimension(String name, double startX, double startY,
                              double endX, double endY, int startTime, int endTime) {
    for (FrameModification f : frameMods) {
      if (f.getName().equals(name) && f.isScale()) {
        if (f.getDuration().withinDuration(startTime) || f.getDuration().withinDuration(endTime)) {
          return;
        }
      }
    }
    FrameModification newChangeScale = new Scale(name, startX, startY, endX, endY,
            new StartEndTime(startTime, endTime));
    frameMods.add(newChangeScale);
  }

  @Override
  public void advanceFrame() {
    currentTime++;
    Frame frame = new Frame(assets, currentTime);
    for (FrameModification f : frameMods) {
      f.changeFrame(frame, currentTime);
    }
    this.currentFrame = frame;
  }

  @Override
  public String currentFrame() {
    return "CurrentTime: " + currentTime + "\n" + currentFrame.toString();
  }

  @Override
  public List<IShape> shapesInFrame() {
    return currentFrame.getAllShapes();
  }

  @Override
  public String toText() {
    Map<String, IShape> allAssets = assets.allAssets();
    Map<String, StartEndTime> allDurations = assets.allDurations();
    String output = "Shapes:\n";
    for (String k : allAssets.keySet()) {
      output += "Name: " + k + "\n";
      output += allAssets.get(k).toString();
      double startSecond = allDurations.get(k).getStartTime() / this.fps;
      double endSecond = allDurations.get(k).getEndTime() / this.fps;
      output += String.format("Appears at t=%.1fs\n", startSecond);
      output += String.format("Disappears at t=%.1fs\n", endSecond);
      output += "\n";
    }
    for (FrameModification f : frameMods) {
      double startSecond = f.getDuration().getStartTime() / this.fps;
      double endSecond = f.getDuration().getEndTime() / this.fps;
      if (f.isMove()) {
        Move move = (Move) f;
        output += "shape " + move.getName() + " moves from ";
        output += String.format("(%.1f,%.1f) to (%.1f,%.1f) from t=%.1fs to t=%.1fs\n",
                move.getStartPos().getX(), move.getStartPos().getY(), move.getEndPos().getX(),
                move.getEndPos().getY(), startSecond, endSecond);
      } else if (f.isScale()) {
        Scale scale = (Scale) f;
        output += "shape " + scale.getName() + " scales from ";
        output += String.format("Width: %.1f, Height: %.1f to Width: %.1f, Height %.1f ",
                scale.getStartWidth(), scale.getStartHeight(),
                scale.getEndWidth(), scale.getEndHeight());
        output += String.format("from t=%.1fs to t=%.1fs\n", startSecond, endSecond);
      } else if (f.isChangeColor()) {
        ChangeColor changeColor = (ChangeColor) f;
        output += "shape " + changeColor.getName() + " changes color from ";
        output += changeColor.getStartColor().toString() + " to "
                + changeColor.getEndColor().toString();
        output += String.format(" from t=%.1fs to t=%.1fs\n", startSecond, endSecond);
      }
    }
    return output;
  }

  @Override
  public String toSVG() {
    Map<String, IShape> allAssets = assets.allAssets();
    Map<String, StartEndTime> allDurations = assets.allDurations();
    String output = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n";
    output += "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    for (String k : allAssets.keySet()) {
      IShape current = allAssets.get(k);
      boolean isRect = current.isRectangle();
      boolean isOval = current.isOval();
      if (isRect) {
        output += "<rect id=\"" + k + "\" ";
        output += String.format("x=\"%d\" ", (int) current.getPoint().getX());
        output += String.format("y=\"%d\" ", (int) current.getPoint().getY());
        output += String.format("width=\"%d\" ", (int) current.getXDimension());
        output += String.format("height=\"%d\" ", (int) current.getYDimension());
      } else if (isOval) {
        output += "<ellipse id=\"" + k + "\" ";
        output += String.format("cx=\"%d\" ", (int) current.getPoint().getX());
        output += String.format("cy=\"%d\" ", (int) current.getPoint().getY());
        output += String.format("rx=\"%d\" ", (int) current.getXDimension());
        output += String.format("ry=\"%d\" ", (int) current.getYDimension());
      }
      output += "fill=" + "\"" + current.getColor().getSVG() + "\" ";
      output += "visibility=\"hidden\" >\n";
      output += "     <animate attributeType=\"xml\" ";
      double begin = allDurations.get(k).getStartTime() / this.fps * 1000;
      output += String.format("begin=\"%.1fms\" ", begin);
      double duration = allDurations.get(k).getDuration() / this.fps * 1000;
      output += String.format("dur=\"%.1fms\" ", duration);
      output += "attributeName=\"visibility\" to=\"visible\" />\n";
      for (FrameModification f : frameMods) {
        if (f.getName().equals(k)) {
          String header = "     <animate attributeType=\"xml\" ";
          begin = f.getDuration().getStartTime() / this.fps * 1000;
          header += String.format("begin=\"%.1fms\" ", begin);
          duration = f.getDuration().getDuration() / this.fps * 1000;
          header += String.format("dur=\"%.1fms\" ", duration);
          if (f.isMove()) {
            Move currentMove = (Move) f;
            output += header;
            if (isRect) {
              output += "attributeName=\"x\" ";
            } else if (isOval) {
              output += "attributeName=\"cx\" ";
            }
            output += String.format("from=\"%d\" ", (int) currentMove.getStartPos().getX());
            output += String.format("to=\"%d\" fill=\"freeze\" />\n",
                    (int) currentMove.getEndPos().getX());
            output += header;
            if (isRect) {
              output += "attributeName=\"y\" ";
            } else if (isOval) {
              output += "attributeName=\"cy\" ";
            }
            output += String.format("from=\"%d\" ", (int) currentMove.getStartPos().getY());
            output += String.format("to=\"%d\" fill=\"freeze\" />\n",
                    (int) currentMove.getEndPos().getY());
          } else if (f.isScale()) {
            Scale currentScale = (Scale) f;
            output += header;
            if (isRect) {
              output += "attributeName=\"width\" ";
            } else if (isOval) {
              output += "attributeName=\"rx\" ";
            }
            output += String.format("from=\"%d\" ", (int) currentScale.getStartWidth());
            output += String.format("to=\"%d\" fill=\"freeze\" />\n",
                    (int) currentScale.getEndWidth());
            output += header;
            if (isRect) {
              output += "attributeName=\"height\" ";
            } else if (isOval) {
              output += "attributeName=\"ry\" ";
            }
            output += String.format("from=\"%d\" ", (int) currentScale.getStartHeight());
            output += String.format("to=\"%d\" fill=\"freeze\" />\n",
                    (int) currentScale.getEndHeight());
          } else if (f.isChangeColor()) {
            ChangeColor changeColor = (ChangeColor) f;
            output += header;
            output += "attributeName=\"fill\" ";
            output += "from=" + "\"" + changeColor.getStartColor().getSVG() + "\" ";
            output += "to=" + "\"" + changeColor.getEndColor().getSVG() + "\" ";
            output += "fill=\"freeze\" />\n";
          }
        }
      }
      if (isRect) {
        output += "</rect>\n\n";
      } else if (isOval) {
        output += "</ellipse>\n\n";
      }
    }
    output += "</svg>";
    return output;
  }

  @Override
  public double getFPS() {
    return this.fps;
  }

  @Override
  public void reset() {
    this.currentTime = 0;
    // the returned copy must be a deep copy or aliasing will occur when resetting the animation
    this.assets = original.copy();
  }

  /**
   * Get a String representation of this AnimationModelImpl.
   *
   * @return the String representation of this AnimationModelImpl
   */
  @Override
  public String toString() {
    String output = assets.toString();
    for (FrameModification f : frameMods) {
      output += f.toString();
    }
    return output;
  }

  /**
   * A class that implements TweenModelBuilder. It allows the AnimationFileReader to build my
   * specific animation model by acting as an adapter.
   */
  public static final class Builder implements TweenModelBuilder<AnimationModel> {
    private AnimationModel model;

    /**
     * The constructor that creates a new AnimationModel with a specific frames per second.
     *
     * @param fps the frames per second at which the animation runs
     */
    public Builder(double fps) {
      this.model = new AnimationModelImpl(fps);
    }

    @Override
    public TweenModelBuilder<AnimationModel> addOval(String name, float cx, float cy, float xRadius,
                                                     float yRadius, float red, float green,
                                                     float blue, int startOfLife, int endOfLife) {
      this.model.addShape(name, new Oval(new Point2D.Double(cx, cy),
              new Color(red, green, blue), xRadius, yRadius), startOfLife, endOfLife);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addRectangle(String name, float lx, float ly,
                                                          float width, float height, float red,
                                                          float green, float blue, int startOfLife,
                                                          int endOfLife) {
      this.model.addShape(name, new Rectangle(new Point2D.Double(lx, ly),
              new Color(red, green, blue), width, height), startOfLife, endOfLife);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                     float moveToX, float moveToY, int startTime,
                                                     int endTime) {
      this.model.addMove(name, new Point2D.Double(moveFromX, moveFromY),
              new Point2D.Double(moveToX, moveToY), startTime, endTime);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG,
                                                            float oldB, float newR, float newG,
                                                            float newB, int startTime,
                                                            int endTime) {
      this.model.changeColor(name, new Color(oldR, oldG, oldB), new Color(newR, newG, newB),
              startTime, endTime);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx,
                                                              float fromSy, float toSx, float toSy,
                                                              int startTime, int endTime) {
      this.model.changeDimension(name, fromSx, fromSy, toSx, toSy, startTime, endTime);
      return this;
    }

    @Override
    public AnimationModel build() {
      return this.model;
    }
  }
}
