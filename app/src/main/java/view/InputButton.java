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

/**
 * A custom JButton that stores an input sequence along with its display text.
 * This button can be used in interfaces where the displayed text and the
 * associated input sequence might differ.
 */
public class InputButton extends JButton {
<<<<<<< HEAD
    /**
     * The sequence associated with this button. This might be the same as
     * the button's text or different, depending on use case.
     */
    String sequence;

    /**
     * Constructs an InputButton with the specified text. Both the button's
     * display text and its sequence will be set to the provided text.
     *
     * @param text the text to display on the button and to use as the sequence
     */
    public InputButton(String text) {
        super(text);
        sequence = text;
    }

    /**
     * Returns the sequence associated with this button.
     *
     * @return the sequence string associated with this button
     */
    public String getSequence() {
        return sequence;
    }
}
=======
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

>>>>>>> e897d5d91c052598a014703145d845342cec5926
