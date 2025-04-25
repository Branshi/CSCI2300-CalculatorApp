package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Buffer;
import model.State;
import view.IOPanel;
import view.InputButton;
import view.InputPane;
import view.MainView;
import view.OutputPane;

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
    int activeIndex = -1;
    for (int i = 0; i < model.getBuffers().size(); i++) {
      if (model.getBuffers().get(i).getActive()) {
        activeIndex = i;
        break;
      }
    }
    if (activeIndex < 0) return;

    IOPanel activePanel = view.getIoPanels().get(activeIndex);

    Buffer buf = model.getBuffers().get(activeIndex);
    String seq = button.getSequence();
    InputPane tf = activePanel.getInputPane();
    if (seq.equals("ans")) {
      seq = handleAnswer();
    }

    int pos = tf.getCaretPosition();
    String old = tf.getText();

    String next = old.substring(0, pos) + seq + old.substring(pos);

    tf.setText(next);
    buf.setContent(next);

    tf.setCaretPosition(pos + seq.length());
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
