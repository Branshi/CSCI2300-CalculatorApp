

package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The main application window for Caltrix calculator.
 * Manages the display panel for I/O and the button panel for user input.
 * Provides functionality to manage multiple I/O panels and control their activation state.
 */
public class MainView extends JFrame {
    private JPanel buttonPanel;
    private JPanel displayPanel;
    private ArrayList<IOPanel> IoPanels;
    private JPanel numberPanel;

    /**
     * Constructs the main application window.
     * Initializes the display and button panels with default settings.
     * Sets up the window properties including:
     * - Title ("Caltrix")
     * - Close operation (EXIT_ON_CLOSE)
     * - Layout (BoxLayout with Y_AXIS orientation)
     * - Visibility and positioning (centered on screen)
     */
    public MainView() {
        super("Caltrix");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        
        // Initialize App Sections
        initDisplayPanel();
        initButtonPanel();
        
        // Add Components
        cp.add(displayPanel);
        cp.add(buttonPanel);
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Initializes the display panel that will contain I/O panels.
     * Sets up:
     * - Vertical BoxLayout
     * - Empty ArrayList for IOPanels
     */
    private void initDisplayPanel() {
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        IoPanels = new ArrayList<IOPanel>();
    }

    /**
     * Initializes the button panel with calculator buttons.
     * Creates a 4x4 grid layout for number/operation buttons including:
     * - Numeric buttons (0-9)
     * - Decimal point
     * - "ans" button
     * - Basic operations (/, x, -, +)
     */
    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        numberPanel = new JPanel();
        int numberPanelRows = 4;
        int numberPanelColumns = 4;
        numberPanel.setLayout(new GridLayout(numberPanelRows, numberPanelColumns));
        
        for (int i = 1; i <= 16; i++) {
            if ((i / 4 == 0) && (i % 4 != 0)) numberPanel.add(new InputButton(String.valueOf(i + 6)));
            if ((i / 4 == 1) && (i % 4 != 0)) numberPanel.add(new InputButton(String.valueOf(i - 1)));
            if ((i / 4 == 2) && (i % 4 != 0)) numberPanel.add(new InputButton(String.valueOf(i - 8)));
            if (i == 13) numberPanel.add(new InputButton(String.valueOf("0")));
            if (i == 14) numberPanel.add(new InputButton(String.valueOf(".")));
            if (i == 15) numberPanel.add(new InputButton(String.valueOf("ans")));
            if (i == 4) numberPanel.add(new InputButton("/"));
            if (i == 8) numberPanel.add(new InputButton("x"));
            if (i == 12) numberPanel.add(new InputButton("-"));
            if (i == 16) numberPanel.add(new InputButton("+"));
        }
        buttonPanel.add(numberPanel);
    }

    /**
     * Adds a new I/O panel at the specified index.
     * 
     * @param ind the index at which to add the new panel
     * @return the newly created IOPanel instance
     */
    public IOPanel addIoPanel(int ind) {
        IOPanel pan = new IOPanel();
        IoPanels.add(ind, pan);
        reinitDisplay();
        return pan;
    }

    /**
     * Reinitializes the display panel by refreshing all I/O panels.
     * Removes all components and re-adds them to maintain proper ordering.
     */
    private void reinitDisplay() {
        displayPanel.removeAll();
        for (int i = 0; i < IoPanels.size(); ++i) {
            displayPanel.add(IoPanels.get(i));
        }
        displayPanel.revalidate();
        displayPanel.repaint();
        pack();
    }

    /**
     * Returns the number panel containing calculator buttons.
     * 
     * @return the JPanel containing number/operation buttons
     */
    public JPanel getNumberPanel() {
        return numberPanel;
    }

    /**
     * Highlights the specified I/O panel to indicate active status.
     * 
     * @param ind the index of the panel to activate
     */
    public void activate(int ind) {
        IOPanel panelActive = IoPanels.get(ind);
        panelActive.setBackground(Color.RED);
    }

    /**
     * Resets the specified I/O panel to inactive state.
     * 
     * @param ind the index of the panel to deactivate
     */
    public void deactivate(int ind) {
        IOPanel panelInactive = IoPanels.get(ind);
        panelInactive.setBackground(Color.WHITE);
    }

    /**
     * Returns the list of all I/O panels in the display.
     * 
     * @return ArrayList containing all IOPanel instances
     */
    public ArrayList<IOPanel> getIoPanels() {
        return IoPanels;
    }
}