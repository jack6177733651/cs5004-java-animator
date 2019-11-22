package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JFileChooser;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.IRenderableShape;
import cs5004.animator.view.MyWindow;
import cs5004.animator.view.RenderableCircle;
import cs5004.animator.view.RenderableRectangle;

/**
 * An implementation of AnimationController, acts as the controller of the animation. Mediates
 * between the model and the view by passing the appropriate text representation of the animation
 * from the model to the view.
 */
public class AnimationControllerImpl implements AnimationController {
  private AnimationView view;
  private AnimationModel model;
  private Timer timer;

  /**
   * A constructor that takes in an AnimationView and AnimationModel, which represent the view and
   * model of the animation respectively.
   *
   * @param view  the view of the animation
   * @param model the model of the animation
   */
  public AnimationControllerImpl(AnimationView view, AnimationModel model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void run(String viewType) {
    String viewLC = viewType.toLowerCase();
    if (viewLC.equals("text")) {
      view.setInput(model.toText());
    } else if (viewLC.equals("svg")) {
      view.setInput(model.toSVG());
    } else if (viewLC.equals("visual")) {
      runJFrame();
    } else {
      throw new IllegalArgumentException("The view type String is invalid");
    }
  }

  private void runJFrame() {
    this.timer = new Timer((int) (1000 / model.getFPS()), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        List<IShape> shapes = model.shapesInFrame();
        List<IRenderableShape> renderableShapes = new ArrayList<>();
        for (IShape shape : shapes) {
          if (shape.isRectangle()) {
            IRenderableShape rect = new RenderableRectangle(shape);
            renderableShapes.add(rect);
          } else if (shape.isOval()) {
            IRenderableShape circle = new RenderableCircle(shape);
            renderableShapes.add(circle);
          }
        }
        view.setInput(renderableShapes);
        view.render();
        model.advanceFrame();
      }
    });
    ((MyWindow) view).addPlayListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.start();
      }
    });
    ((MyWindow) view).addStopListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.stop();
      }
    });
    ((MyWindow) view).addRestartListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!timer.isRunning()) {
          timer.start();
        }
        model.reset();
      }
    });
    ((MyWindow) view).addSlowListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(timer.getDelay() * 2);
      }
    });
    ((MyWindow) view).addFastListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(timer.getDelay() / 2);
      }
    });
    ((MyWindow) view).addSaveListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser save = new JFileChooser();
        save.setDialogTitle("Save your svg file");
        int savePossible = save.showSaveDialog(save);
        if (savePossible == JFileChooser.APPROVE_OPTION) {
          File file = save.getSelectedFile();
          BufferedWriter writer = null;
          try {
            writer = new BufferedWriter(new FileWriter(file.getName()));
          } catch (IOException e1) {
            e1.printStackTrace();
          }
          try {
            writer.append(model.toSVG());
          } catch (IOException e1) {
            e1.printStackTrace();
          }
          try {
            writer.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      }
    });
  }
}
