package view;

import javax.swing.JButton;

/**
 * A custom JButton that stores an input sequence along with its display text.
 * This button can be used in interfaces where the displayed text and the
 * associated input sequence might differ.
 */
public class InputButton extends JButton {
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