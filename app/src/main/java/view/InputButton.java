package view;

import javax.swing.JButton;

public class InputButton extends JButton {
  String sequence;

  public InputButton(String text) {
    super(text);
    sequence = text;
  }

  public String getSequence() {
    return sequence;
  }
}
