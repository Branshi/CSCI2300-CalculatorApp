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

<<<<<<< HEAD
/**
 * Controller for managing I/O panel interactions in the calculator application.
 * Handles both text input changes (via DocumentListener) and Enter key presses (via ActionListener).
 * Coordinates between input panes, output panes, and the application state model.
 */
public class PaneController implements DocumentListener, ActionListener {
    /** The IOPanel being controlled */
    private final IOPanel panel;
    
    /** The buffer associated with this panel */
    private final Buffer buffer;
    
    /** The main application view */
    private final MainView view;
    
    /** The application state model */
    private final State model;
=======
// wherever your eval method lives

public class PaneController implements DocumentListener, ActionListener, FocusListener {
  private final IOPanel panel;
  private final Buffer buffer;
  private final MainView view;
  private final State model;
>>>>>>> e897d5d91c052598a014703145d845342cec5926

    /**
     * Constructs a new PaneController and sets up listeners.
     * 
     * @param buf the Buffer to associate with this panel
     * @param pan the IOPanel to control
     * @param m the application State model
     * @param v the main MainView
     */
    public PaneController(Buffer buf, IOPanel pan, State m, MainView v) {
        this.buffer = buf;
        this.panel = pan;
        this.model = m;
        this.view = v;

        // 1) Listen for any text change
        panel.getInputPane().getDocument().addDocumentListener(this);

<<<<<<< HEAD
        // 2) Listen for "Enter pressed"
        panel.getInputPane().addActionListener(this);
    }

    /**
     * Shared evaluation logic that updates the output based on current input.
     * Evaluates the input expression and displays either the result or error message.
     */
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

    // DocumentListener implementation

    /**
     * Handles text insertion events in the input pane.
     * @param e the DocumentEvent representing the insertion
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateOutput();
    }

    /**
     * Handles text removal events in the input pane.
     * @param e the DocumentEvent representing the removal
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        updateOutput();
    }

    /**
     * Handles attribute changes in the input pane (not typically used).
     * @param e the DocumentEvent representing the change
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        updateOutput();
    }

    // ActionListener implementation

    /**
     * Handles Enter key press events in the input pane.
     * Creates a new active buffer and I/O panel when Enter is pressed.
     * @param e the ActionEvent representing the Enter key press
     */
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

        new PaneController(newBuf, newPan, model, view);
    }
}
=======
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
    model.deactivateBuffers();
    view.deactivatePanels();
    buffer.setActive(true);
    panel.activate();
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
>>>>>>> e897d5d91c052598a014703145d845342cec5926
