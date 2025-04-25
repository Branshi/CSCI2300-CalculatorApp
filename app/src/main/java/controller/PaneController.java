package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
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
  private JButton clearButton;

  public PaneController(Buffer buf, IOPanel pan, State m, MainView v) {
    this.buffer = buf;
    this.panel = pan;
    this.model = m;
    this.view = v;

    // Cache clear button
    for (Component comp : view.getHeaderPanel().getComponents()) {
      if (comp instanceof JButton) {
        JButton button = (JButton) comp;
        if (button.getText().equals("clear all") || button.getText().equals("clear")) {
          this.clearButton = button;
        }
      }
    }

    // 1) Listen for any text change
    panel.getInputPane().getDocument().addDocumentListener(this);

    // 2) Listen for “Enter pressed”
    panel.getInputPane().addActionListener(this);
    panel.getInputPane().addFocusListener(this);

    panel
        .getInputPane()
        .addKeyListener(
            new KeyAdapter() {
              @Override
              public void keyPressed(KeyEvent e) {
                // only when the field is empty
                if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE
                        || e.getKeyCode() == KeyEvent.VK_DELETE)
                    && panel.getInputPane().getText().isEmpty()) {
                  deleteThisLine();
                  e.consume();
                }
              }
            });
  }

  private void deleteThisLine() {
    int idx = model.getActiveBufIndex();
    if (idx <= 0) return;

    // 1) remove model + view
    model.removeBuffer(idx);
    view.removeIoPanel(idx);

    // 2) choose a new active index
    int newIdx = Math.min(idx, model.getBuffers().size() - 1);
    if (newIdx >= 0) {
      model.activateBuffer(newIdx);
      view.activate(newIdx);
      // 3) refocus
      SwingUtilities.invokeLater(
          () -> view.getIoPanel(newIdx).getInputPane().requestFocusInWindow());
    }
  }

  private void updateClearButton(String text) {
    if (text == null || text.isEmpty()) {
      clearButton.setText("clear all");
    } else {
      clearButton.setText("clear");
    }
  }

  /** Shared evaluation logic */
  private void updateOutput() {
    String text = panel.getInputPane().getText();
    buffer.setContent(text);
    this.updateClearButton(buffer.getContent());
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
    if (e.getSource() instanceof InputPane) {
      InputPane ip = (InputPane) e.getSource();
      if (ip.getText().isEmpty()) {
        return;
      }
    }
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
    model.deactivateBuffers();
    view.deactivatePanels();
    buffer.setActive(true);
    panel.activate();
    this.updateClearButton(buffer.getContent());
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
