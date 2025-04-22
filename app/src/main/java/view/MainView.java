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

  private void initDisplayPanel() {
    // Set-Uo displayPanel
    displayPanel = new JPanel();
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    // Setting up IoPanels
    IoPanels = new ArrayList<IOPanel>();
  }

  private void initButtonPanel() {
    // Set-Up buttonPanel
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

  public IOPanel addIoPanel(int ind) {
    IOPanel pan = new IOPanel();
    IoPanels.add(ind, pan);
    reinitDisplay();
    return pan;
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
