
<<<<<<< HEAD

=======
>>>>>>> e897d5d91c052598a014703145d845342cec5926
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

<<<<<<< HEAD
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
=======
  public MainView() {
    super("Caltrix");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container cp = getContentPane();
    cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

    // Initialize App Sections
    initDisplayPanel();
    initButtonPanel();

    // Add components to content pane
    cp.add(displayPanel);
    cp.add(buttonPanel);

    pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  private void initDisplayPanel() {
    displayPanel = new JPanel();
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    IoPanels = new ArrayList<IOPanel>();
  }

  private void initButtonPanel() {
    // Outer container for all button sections
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
  
    // 🌙 Dark Mode Toggle Button
    JToggleButton darkModeToggle = new JToggleButton("🌙 Switch Color Mode");
    darkModeToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
    darkModeToggle.addActionListener(e -> toggleDarkMode(darkModeToggle.isSelected()));
    buttonPanel.add(darkModeToggle);
  
    // 🔢 Function Panel (Desmos-style emoji/math buttons)
    JPanel funcPanel = new JPanel(new GridLayout(4, 3, 5, 5));
    String[] funcButtons = {
      "x²", "xʸ", "|x|",
      "√", "ⁿ√", "π",
      "sin", "cos", "tan",
      "(", ")", ","
    };
    for (String label : funcButtons) {
      funcPanel.add(new InputButton(label));
    }
  
    // 🔢 Number Panel
    numberPanel = new JPanel();
    numberPanel.setLayout(new GridLayout(4, 4, 5, 5));
    String[] labels = {
      "7", "8", "9", "/",
      "4", "5", "6", "x",
      "1", "2", "3", "-",
      "0", ".", "ans", "+"
    };
    for (String label : labels) {
      numberPanel.add(new InputButton(label));
    }
  
    // Combine funcPanel and numberPanel horizontally
    JPanel combinedPanel = new JPanel();
    combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.X_AXIS));
    combinedPanel.add(funcPanel);
    combinedPanel.add(Box.createRigidArea(new Dimension(10, 0))); // spacing
    combinedPanel.add(numberPanel);
  
    buttonPanel.add(combinedPanel);
  }
  

  public IOPanel addIoPanel(int ind) {
    IOPanel pan = new IOPanel();
    IoPanels.add(ind, pan);
    reinitDisplay();
    return pan;
  }

  public IOPanel getIoPanel(int ind) {
    return IoPanels.get(ind);
  }

  private void reinitDisplay() {
    displayPanel.removeAll();
    for (int i = 0; i < IoPanels.size(); ++i) {
      displayPanel.add(IoPanels.get(i));
    }
    displayPanel.revalidate();
    displayPanel.repaint();
    pack();
  }

<<<<<<< HEAD
  public void toggleDarkMode(boolean enabled) {
    Color bg = enabled ? new Color(34, 34, 34) : Color.WHITE;
    Color fg = enabled ? Color.WHITE : Color.BLACK;

    getContentPane().setBackground(bg);
    displayPanel.setBackground(bg);
    buttonPanel.setBackground(bg);
    numberPanel.setBackground(bg);

    for (Component c : numberPanel.getComponents()) {
      if (c instanceof JButton) {
        c.setBackground(enabled ? new Color(60, 60, 60) : Color.WHITE);
        c.setForeground(fg);
      }
    }

    for (IOPanel panel : IoPanels) {
      panel.setBackground(bg);
      panel.getInputPane().setBackground(enabled ? new Color(50, 50, 50) : Color.WHITE);
      panel.getInputPane().setForeground(fg);
      panel.getOutputPane().setForeground(fg);
    }

    repaint();
=======
  public void deactivatePanels() {
    for (IOPanel pan : IoPanels) {
      pan.deactivate();
    }
>>>>>>> 990db877ad62c4b2774266532dc954bdd48eb28c
  }

  public JPanel getNumberPanel() {
    return numberPanel;
  }

  public void activate(int ind) {
    IOPanel panelActive = IoPanels.get(ind);
    panelActive.activate();
  }

  public void deactivate(int ind) {
    IOPanel panelInactive = IoPanels.get(ind);
    panelInactive.deactivate();
  }
>>>>>>> e897d5d91c052598a014703145d845342cec5926

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