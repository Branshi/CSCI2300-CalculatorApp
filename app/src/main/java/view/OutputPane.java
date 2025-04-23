package view;

import javax.swing.JLabel;

/**
 * A custom output display component that extends JLabel.
 * Designed to display calculation results or other output text in the calculator interface.
 * Provides a simple, read-only text display area with label semantics.
 */
public class OutputPane extends JLabel {

    /**
     * Constructs a new OutputPane with default settings.
     * Creates an empty label with default alignment and no initial text.
     * The component is suitable for displaying calculator results or other output.
     */
    public OutputPane() {
        super();
    }
}