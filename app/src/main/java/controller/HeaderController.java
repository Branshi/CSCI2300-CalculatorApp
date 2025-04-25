package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.Buffer;
import model.State;
import view.InputPane;
import view.MainView;

public class HeaderController implements ActionListener {
  private final MainView view;
  private final State model;

  public HeaderController(State m, MainView v) {
    this.model = m;
    this.view = v;

    // Listen for click on all buttons in header panel
    for (Component e : view.getHeaderPanel().getComponents()) {
      if (e instanceof JButton) {
        JButton button = (JButton) e;
        button.addActionListener(this);
      }
    }
  }

  // ActionListener → fires when user presses Enter
  @Override
  public void actionPerformed(ActionEvent e) {
    if (!(e.getSource() instanceof JButton)) return;
    JButton button = (JButton) e.getSource();
    switch (button.getText()) {
      case "clear":
      case "clear all":
        handleClear();
        break;
      case "deg":
        handleDegree();
        break;
      case "rad":
        handleRadian();
        break;
      case "undo":
        handleUndo();
        break;
      case "redo":
        handleRedo();
        break;
    }
  }

  private void handleClear() {
    int index = model.getActiveBufIndex();
    Buffer buf = model.getBuffers().get(index);
    InputPane tf = view.getIoPanel(index).getInputPane();
    tf.setText("");
    buf.setContent("");
  }

  private void handleDegree() {}

  private void handleRadian() {}

  private void handleRedo() {}

  private void handleUndo() {}
}
