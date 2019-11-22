package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

/**
 * A class that implements AnimationView, acts as the view for the animation. It also extends JFrame
 * and acts as the frame that holds the drawing panel and functions of the animation.
 */
public class MyWindow extends JFrame implements AnimationView {
  MyDrawingPanel drawingPanel = new MyDrawingPanel();
  JPanel buttons;
  JButton play;
  JButton pause;
  JButton restart;
  JButton slow;
  JButton fast;
  JButton save;

  /**
   * A constructor that initializes the this JFrame, the JPanels, and the JButtons.
   */
  public MyWindow() {
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    buttons = new JPanel();
    play = new JButton("Play");
    buttons.add(play);
    pause = new JButton("Pause");
    buttons.add(pause);
    restart = new JButton("Restart");
    buttons.add(restart);
    slow = new JButton("Slow down");
    buttons.add(slow);
    fast = new JButton("Speed up");
    buttons.add(fast);
    save = new JButton("Save");
    buttons.add(save);
    add(buttons, BorderLayout.SOUTH);
    add(drawingPanel, BorderLayout.CENTER);
    setVisible(true);
  }

  /**
   * Add an ActionListener for the add JButton.
   *
   * @param listener an ActionListener that gives the JButton functionality
   */
  public void addPlayListener(ActionListener listener) {
    play.addActionListener(listener);
  }

  /**
   * Add an ActionListener for the stop JButton.
   *
   * @param listener an ActionListener that gives the JButton functionality
   */
  public void addStopListener(ActionListener listener) {
    pause.addActionListener(listener);
  }

  /**
   * Add an ActionListener for the restart JButton.
   *
   * @param listener an ActionListener that gives the JButton functionality
   */
  public void addRestartListener(ActionListener listener) {
    restart.addActionListener(listener);
  }

  /**
   * Add an ActionListener for the slow JButton.
   *
   * @param listener an ActionListener that gives the JButton functionality
   */
  public void addSlowListener(ActionListener listener) {
    slow.addActionListener(listener);
  }

  /**
   * Add an ActionListener for the fast JButton.
   *
   * @param listener an ActionListener that gives the JButton functionality
   */
  public void addFastListener(ActionListener listener) {
    fast.addActionListener(listener);
  }

  /**
   * Add an ActionListener for the save JButton.
   *
   * @param listener an ActionListener that gives the JButton functionality
   */
  public void addSaveListener(ActionListener listener) {
    save.addActionListener(listener);
  }


  @Override
  public void render() {
    drawingPanel.repaint();
  }

  @Override
  public void setSource(Appendable output) {
    int i = 0;
  }

  @Override
  public void setInput(String input) {
    int i = 0;
  }

  @Override
  public void setInput(List<IRenderableShape> data) {
    drawingPanel.updateShapes(data);
  }
}
