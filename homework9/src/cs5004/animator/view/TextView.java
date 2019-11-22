package cs5004.animator.view;

import java.io.IOException;
import java.util.List;

/**
 * An implementation of AnimationView that stores an Appendable object that acts as the output. The
 * view consists of a String that stores all information about the animation. This String can either
 * be a raw text for human readers or a svg text for html output.
 */
public class TextView implements AnimationView {
  private Appendable output;

  /**
   * A default constructor.
   */
  public TextView() {
    // the constructor is empty
  }

  @Override
  public void render() {
    System.out.println(output.toString());
  }

  @Override
  public void setSource(Appendable output) {
    this.output = output;
  }

  @Override
  public void setInput(String input) {
    try {
      this.output.append(input);
    } catch (IOException e) {
      System.err.append(e.getMessage());
    }
  }

  @Override
  public void setInput(List<IRenderableShape> data) {
    try {
      output.append(data.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
