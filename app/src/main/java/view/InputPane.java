package view;

import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;

public class InputPane extends JTextField {
  public InputPane() {
    super();
    Caret blank =
        new DefaultCaret() {

          @Override
          public void paint(Graphics g) {}

          @Override
          public boolean isVisible() {
            return false;
          }

          @Override
          public boolean isSelectionVisible() {
            return false;
          }
        };
    this.setCaretPosition(0);
    this.setCaret(blank);
  }
}
