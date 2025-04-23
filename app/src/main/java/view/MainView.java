
package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainView extends JFrame {
  private JPanel buttonPanel;
  private JPanel displayPanel;
  private ArrayList<IOPanel> IoPanels;
  private JPanel numberPanel;

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

  public ArrayList<IOPanel> getIoPanels() {
    return IoPanels;
  }
}
