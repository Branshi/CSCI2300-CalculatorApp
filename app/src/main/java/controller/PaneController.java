package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Buffer;
import model.State;
import view.IOPanel;
import view.InputPane;
import view.MainView;

// wherever your eval method lives

public class PaneController implements DocumentListener, ActionListener, FocusListener {
  private final IOPanel panel;
  private final Buffer buffer;
  private final MainView view;
  private final State model;

  public PaneController(Buffer buf, IOPanel pan, State m, MainView v) {
    this.buffer = buf;
    this.panel = pan;
    this.model = m;
    this.view = v;

    // 1) Listen for any text change
    panel.getInputPane().getDocument().addDocumentListener(this);

    // 2) Listen for “Enter pressed”
    panel.getInputPane().addActionListener(this);
    panel.getInputPane().addFocusListener(this);
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
    updateOutput();
  }

  // ActionListener → fires when user presses Enter
  @Override
  public void actionPerformed(ActionEvent e) {
    int ind = model.getActiveBufIndex();
    if (ind >= 0) {
      model.deactivateBuffer(ind);
      view.deactivate(ind);
    }

    int newIdx = (ind < 0 ? 0 : ind + 1);
    Buffer newBuf = model.createBuffer(newIdx);
    model.activateBuffer(newIdx);
    IOPanel newPan = view.addIoPanel(newIdx);
    view.activate(newIdx);
    SwingUtilities.invokeLater(
        () -> {
          newPan.getInputPane().requestFocusInWindow();
        });
    new PaneController(newBuf, newPan, model, view);
  }

  @Override
  public void focusGained(FocusEvent e) {
    // activate focused buffer and panel
    panel.activate();
    buffer.setActive(true);
  }

  @Override
  public void focusLost(FocusEvent e) {
    // deactivate unfocused buffer and panel
    Component gained = e.getOppositeComponent();
    if (gained instanceof InputPane) {
      panel.deactivate();
      buffer.setActive(false);
    }
  }
}
