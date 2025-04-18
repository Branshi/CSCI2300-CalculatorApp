package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainView extends JFrame {
  private static JPanel buttonPanel;
  private static JPanel displayPanel;
  private static ArrayList<IOPanel> IoPanels;

  public MainView() {
    super("Caltrix");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container cp = getContentPane();
    cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
    // Initlize App Sections
    initDisplayPanel();
    initButtonPanel();
    // Add Components
    cp.add(displayPanel);
    cp.add(buttonPanel);
    pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  private static void initDisplayPanel() {
    // Set-Uo displayPanel
    displayPanel = new JPanel();
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    // Setting up IoPanels
    IoPanels = new ArrayList<IOPanel>();
    IoPanels.add(new IOPanel());
    for (int i = 0; i < IoPanels.size(); ++i) {
      displayPanel.add(IoPanels.get(i));
    }
  }

  private static void initButtonPanel() {
    // Set-Up buttonPanel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    JPanel numberPanel = new JPanel();
    int numberPanelRows = 4;
    int numberPanelColumns = 4;
    numberPanel.setLayout(new GridLayout(numberPanelRows, numberPanelColumns));
    for (int i = 0; i < 16; i++) {
      numberPanel.add(new InputButton(String.valueOf(i)));
    }
    buttonPanel.add(numberPanel);
  }

  public ArrayList<IOPanel> getIoPanels() {
    return IoPanels;
  }
}
