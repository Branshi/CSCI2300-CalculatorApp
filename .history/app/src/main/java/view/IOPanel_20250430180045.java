package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A panel container that holds both input and output components.
 * Provides horizontal layout for an InputPane and OutputPane side by side.
 * The panel has a fixed preferred size of 800x100 pixels.
 */
public class IOPanel extends JPanel {
    private InputPane input;
    private OutputPane output;

    /**
     * Constructs a new IOPanel with default input and output panes.
     * Initializes the panel with:
     * - BoxLayout in horizontal (X_AXIS) orientation
     * - Preferred size of 800x100 pixels
     * - Default InputPane and OutputPane instances
     */
    public IOPanel() {
        super();
        
        this.setPreferredSize(new Dimension(800, 100));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        input = new InputPane();
        output = new OutputPane();

        this.add(input);
        this.add(output);
    }

    /**
     * Returns the input pane component of this panel.
     * 
     * @return the InputPane instance used for user input
     */
    public InputPane getInputPane() {
        return input;
    }


  public OutputPane getOutputPane() {
    return output;
  }

  public void activate() {
    this.setBackground(Color.RED);
  }

  public void deactivate() {
    this.setBackground(Color.WHITE);
  }
}
