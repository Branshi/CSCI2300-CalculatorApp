package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Buffer;
import view.IOPanel;

// wherever your eval method lives

public class PaneController implements DocumentListener, ActionListener {
  private final IOPanel panel;
  private final Buffer buffer;

  public PaneController(Buffer buf, IOPanel pan) {
    this.buffer = buf;
    this.panel = pan;

    // 1) Listen for any text change
    panel.getInputPane().getDocument().addDocumentListener(this);

    // 2) Listen for “Enter pressed”
    panel.getInputPane().addActionListener(this);
  }

  /** Shared evaluation logic */
  private void updateOutput() {
    String text = panel.getInputPane().getText();
    buffer.setContent(text);
    try {
      String out = String.valueOf(Evaluate.eval(buffer.getContent()));
      panel.getOutputPane().setText(out);
    } catch (IllegalArgumentException ex) {
      panel.getOutputPane().setText(ex.getMessage());
    }
  }

  // DocumentListener → fires after insert or remove
  @Override
  public void insertUpdate(DocumentEvent e) {
    updateOutput();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    updateOutput();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // plain‐text fields don’t fire this, but we implement anyway
    updateOutput();
  }

  // ActionListener → fires when user presses Enter
  @Override
  public void actionPerformed(ActionEvent e) {
    // if you want special Enter logic, do it here;
    // otherwise, you can just call updateOutput() too:
    updateOutput();
  }
}
