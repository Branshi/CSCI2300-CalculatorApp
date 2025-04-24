// package view;

// import javax.swing.JButton;



// public class InputButton extends JButton {
//   String sequence;

//   public InputButton(String text) {
//     super(text);
//     sequence = text;
//   }




//   public String getSequence() {
//     return sequence;
//   }
// }
package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class InputButton extends JButton {
  private final String sequence;

  public InputButton(String text) {
    super(text);
    this.sequence = text;

    // Styling
    setFont(new Font("Serif", Font.BOLD, 20));
    setFocusPainted(false);
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    setOpaque(true);

  }

  public String getSequence() {
    return sequence;
  }
}

