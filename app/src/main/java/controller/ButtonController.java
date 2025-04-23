package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Buffer;
import model.State;
import view.IOPanel;
import view.InputButton;
import view.MainView;
import view.OutputPane;

/**
 * Controller class that handles button press events in the calculator application.
 * Implements ActionListener to respond to button clicks and updates both the model
 * and view accordingly. Manages the interaction between InputButtons, the application
 * state, and the I/O panels.
 */
public class ButtonController implements ActionListener {
    /** The button instance this controller manages */
    private final InputButton button;
    
    /** The application state model */
    private final State model;
    
    /** The main application view */
    private final MainView view;

    /**
     * Constructs a ButtonController for the specified button.
     * 
     * @param b the InputButton to control
     * @param model the application state model
     * @param view the main application view
     */
    public ButtonController(InputButton b, State model, MainView view) {
        this.button = b;
        this.model = model;
        this.view = view;
        button.addActionListener(this);
    }

    /**
     * Handles button press events by updating both model and view.
     * Performs the following actions:
     * 1. Finds the currently active buffer
     * 2. Gets the corresponding I/O panel
     * 3. Updates the buffer content with the new input
     * 4. Updates the input field display
     * 
     * @param e the ActionEvent triggered by the button press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 1) Find the active buffer's index
        int activeIndex = -1;
        for (int i = 0; i < model.getBuffers().size(); i++) {
            if (model.getBuffers().get(i).getActive()) {
                activeIndex = i;
                break;
            }
        }
        if (activeIndex < 0) return; // no active buffer, bail out

<<<<<<< HEAD
        // 2) Get the corresponding IOPanel
        IOPanel activePanel = view.getIoPanels().get(activeIndex);

        // Update buffer and view with new input
        Buffer buf = model.getBuffers().get(activeIndex);
        String seq = button.getSequence();
        String old = activePanel.getInputPane().getText();
        String next = old + seq;

        // 3) Update the buffer content
        buf.setContent(next);

        // 4) Update the input field display
        activePanel.getInputPane().setText(next);
    }
}
=======
    Buffer buf = model.getBuffers().get(activeIndex);
    // String seq = button.getSequence();
    String seq;
switch (button.getSequence()) {
  case "π":
    seq = "3.14159";
    break;
  case "√":
    seq = "sqrt(";
    break;
  case "ⁿ√":
    seq = "root(";
    break;
  case "x²":
    seq = "^2";
    break;
  case "xʸ":
    seq = "^";
    break;
  case "|x|":
    seq = "abs(";
    break;
  default:
    seq = button.getSequence();
}

    String old = activePanel.getInputPane().getText();
    String next;
    if (seq.equals("ans")) {
      next = old + handleAnswer();
    } else {
      next = old + seq;
    }
    // 3) push the new content back into your Buffer
    buf.setContent(next);

    // 4) append the button text to the input field
    activePanel.getInputPane().setText(next);
  }

  private String handleAnswer() {
    int currentInd = model.getActiveBufIndex();
    int previousInd = currentInd - 1;
    try {
      model.getBuffers().get(previousInd);
      OutputPane out = view.getIoPanel(previousInd).getOutputPane();
      return out.getText();
    } catch (IndexOutOfBoundsException e) {
      return "";
    }
  }
}
>>>>>>> e897d5d91c052598a014703145d845342cec5926
