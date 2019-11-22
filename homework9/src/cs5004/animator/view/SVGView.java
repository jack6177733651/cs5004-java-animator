package cs5004.animator.view;

import java.io.IOException;
import java.util.List;

/**
 * A class that implements AnimationView, acts as a view for the animation that outputs svg.
 */
public class SVGView implements AnimationView {
  private Appendable output;

  /**
   * A default constructor.
   */
  public SVGView() {
    // the body is empty 
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
      this.output.append(data.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
