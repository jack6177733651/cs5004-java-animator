package cs5004.animator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.AnimationView;
import util.AnimationFileReader;

/**
 * The main class that runs the animation program. Takes in arguments in the form of <-if
 * name-of-animation-file> <-iv type-of-view> <-o where-output-show-go> <-speed
 * integer-ticks-per-second> as command line arguments to specify the name of the input file,
 * whether the output is raw text or svg text, specify the Appendable output, and set the speed of
 * the animation.
 */
public final class EasyAnimator {
  /**
   * The main method and entry point to this animation program.
   *
   * @param args An array of command line arguments as Strings
   */
  public static void main(String[] args) {
    String fileName = "";
    String viewType = "";
    String output = "out";
    String fps = "1";
    for (int i = 0; i < args.length; i++) {
      if (i % 2 == 0) {
        if (args[i].equals("-if")) {
          fileName = args[i + 1];
        } else if (args[i].equals("-iv")) {
          viewType = args[i + 1];
        } else if (args[i].equals("-o")) {
          output = args[i + 1];
        } else if (args[i].equals("-speed")) {
          fps = args[i + 1];
        } else {
          JOptionPane.showMessageDialog(null,
                  "The argument identifier is incorrect",
                  "Argument error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    double framesPerSecond = Double.parseDouble(fps);
    AnimationModelImpl.Builder builder = new AnimationModelImpl.Builder(framesPerSecond);
    AnimationFileReader reader = new AnimationFileReader();
    AnimationModel model = null;
    try {
      model = reader.readFile(fileName, builder);
    } catch (Exception e) {
      System.err.append(e.getMessage());
    }
    AnimationView view = ViewFactory.createView(viewType);
    BufferedWriter writer = null;
    boolean fileOutput = false;
    if (output.equals("out")) {
      view.setSource(System.out);
    } else {
      fileOutput = true;
      try {
        writer = new BufferedWriter(new FileWriter(output, true));
        view.setSource(writer);
      } catch (IOException e) {
        System.err.append(e.getMessage());
      }
    }
    AnimationController controller = new AnimationControllerImpl(view, model);
    controller.run(viewType);
    if (fileOutput) {
      try {
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
