package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Buffer;
import view.InputPane;
import view.OutputPane;
// wherever your eval method lives

public class PaneController implements DocumentListener, ActionListener {
  private final InputPane inField;
  private final OutputPane outField;
  private final Buffer buffer;

  public PaneController(Buffer buf, InputPane in, OutputPane out) {
    this.buffer = buf;
    this.inField = in;
    this.outField = out;

    // 1) Listen for any text change
    inField.getDocument().addDocumentListener(this);

    // 2) Listen for “Enter pressed”
    inField.addActionListener(this);
  }

  /** Shared evaluation logic */
  private void updateOutput() {
    String text = inField.getText();
    buffer.setContent(text);
    try {
      String out = String.valueOf(Evaluate.eval(buffer.getContent()));
      outField.setText(out);
    } catch (IllegalArgumentException ex) {
      outField.setText(ex.getMessage());
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
