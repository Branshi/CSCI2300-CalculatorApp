package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!(e.getSource() instanceof JButton)) return;
    JButton button = (JButton) e.getSource();
    switch (button.getText()) {
      case "clear all":
        handleClearAll();
        break;
      case "clear":
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
      case "save":
        handleSave();
        break;
    }
  }

  private void handleSave() {
    String savePath = JOptionPane.showInputDialog(view, "Save state to:");
    if (savePath != null && !savePath.trim().isEmpty()) {
      try {
        model.saveTo(savePath.trim());
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(
            view, "Failed to save: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void handleClearAll() {
    int index = model.getActiveBufIndex();
    Buffer buf = model.getBuffers().get(index);

    if (model.getBuffers().size() <= 1 || !buf.getContent().isEmpty()) return;

    model.clearBuffers();
    view.clearIoPanels();
  }

  private void handleClear() {
    int index = model.getActiveBufIndex();
    Buffer buf = model.getBuffers().get(index);
    InputPane tf = view.getIoPanel(index).getInputPane();
    tf.setText("");
    buf.setContent("");
  }

  private void handleDegree() {
    model.setDegreeMode(true);
  }

  private void handleRadian() {
    model.setDegreeMode(false);
  }

  private void handleRedo() {}

  private void handleUndo() {}
}
