package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Buffer;
import model.State;
import view.IOPanel;
import view.InputButton;
import view.MainView;

public class ButtonController implements ActionListener {
  private final InputButton button;
  private final State model;
  private final MainView view;

  public ButtonController(InputButton b, State model, MainView view) {
    this.button = b;
    this.model = model;
    this.view = view;
    button.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // 1) find the active buffer’s index
    int activeIndex = -1;
    for (int i = 0; i < model.getBuffers().size(); i++) {
      if (model.getBuffers().get(i).getActive()) {
        activeIndex = i;
        break;
      }
    }
    if (activeIndex < 0) return; // no active buffer, bail out

    // 2) grab the corresponding IOPanel
    IOPanel activePanel = view.getIoPanels().get(activeIndex);

    Buffer buf = model.getBuffers().get(activeIndex);
    String seq = button.getSequence();
    String old = activePanel.getInputPane().getText();
    String next = old + seq;

    // 3) push the new content back into your Buffer
    buf.setContent(next);

    // 4) append the button text to the input field
    activePanel.getInputPane().setText(next);
  }
}
