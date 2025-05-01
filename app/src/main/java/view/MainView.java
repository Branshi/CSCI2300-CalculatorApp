package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import view.theme.ThemeManager;

public class MainView extends JFrame {
  private JPanel mainPanel;
  private JPanel buttonPanel;
  private JPanel displayContainer;
  private JScrollPane displayScroll;
  private ArrayList<IOPanel> IoPanels;
  private JPanel numberPanel;
  private JPanel headerPanel;

  public MainView() {
    super("Caltrix");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container cp = getContentPane();
    cp.setLayout(new BorderLayout());
    cp.setMaximumSize(new Dimension(1800, 1800));
    cp.setPreferredSize(new Dimension(1800, 1800));
    // Initlize App Sections
    mainPanel =
        new JPanel() {
          @Override
          public Dimension getPreferredSize() {
            return new Dimension(800, 700);
          }

          @Override
          public Dimension getMaximumSize() {
            return getPreferredSize();
          }
        };
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    Border b = BorderFactory.createLineBorder(ThemeManager.getTheme().getBackgroundXDark(), 1);
    mainPanel.setBorder(b);
    initDisplayPanel();
    initButtonPanel();
    initButtons();
    // Add Components
    mainPanel.add(displayScroll);
    mainPanel.add(buttonPanel);

    JPanel centerWrapper = new JPanel(new GridBagLayout());
    centerWrapper.add(mainPanel); // this will center mainPanel both axes
    cp.add(centerWrapper, BorderLayout.CENTER);

    initTheme();
    pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  public void initTheme() {
    ThemeManager.getTheme().applyTo(getContentPane());
    ThemeManager.onChange(
        theme ->
            // always repaint on the Swing EDT:
            SwingUtilities.invokeLater(
                () -> {
                  theme.applyTo(getContentPane());
                  revalidate();
                  repaint();
                }));
  }

  private void initDisplayPanel() {
    // Set-Uo displayContainer
    displayContainer = new JPanel();
    displayContainer.setLayout(new BoxLayout(displayContainer, BoxLayout.Y_AXIS));
    // Setting up IoPanels
    IoPanels = new ArrayList<IOPanel>();
    displayContainer.add(Box.createVerticalGlue());
    displayScroll = new JScrollPane(displayContainer);
    displayScroll.setBorder(null); // optional: remove default border
    displayScroll.setPreferredSize(new Dimension(800, 550));
    displayScroll.setMaximumSize(displayScroll.getPreferredSize());
    displayScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
    displayScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    displayContainer.setMaximumSize(
        new Dimension(displayScroll.getPreferredSize().width, Short.MAX_VALUE));
  }

  private void initButtonPanel() {
    // Set-Up buttonPanel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    headerPanel = new JPanel();
    numberPanel = new JPanel();

    int numberPanelRows = 4;
    int numberPanelColumns = 4;
    int gap = 8;
    numberPanel.setLayout(new GridLayout(numberPanelRows, numberPanelColumns, gap, gap));
    numberPanel.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
    for (int i = 1; i <= 16; i++) {
      if ((i / 4 == 0) && (i % 4 != 0)) numberPanel.add(new InputButton(String.valueOf(i + 6)));
      if ((i / 4 == 1) && (i % 4 != 0)) numberPanel.add(new InputButton(String.valueOf(i - 1)));
      if ((i / 4 == 2) && (i % 4 != 0)) numberPanel.add(new InputButton(String.valueOf(i - 8)));
      if (i == 13) numberPanel.add(new InputButton(String.valueOf("0")));
      if (i == 14) numberPanel.add(new InputButton(String.valueOf(".")));
      if (i == 15) numberPanel.add(new InputButton(String.valueOf("ans")));
      if (i == 4) numberPanel.add(new InputButton("/"));
      if (i == 8) numberPanel.add(new InputButton("*"));
      if (i == 12) numberPanel.add(new InputButton("-"));
      if (i == 16) numberPanel.add(new InputButton("+"));
    }

    headerPanel.add(new JButton("clear all"));
    headerPanel.add(new JButton("degree"));
    headerPanel.add(new JButton("radian"));
    headerPanel.add(new JButton("save"));

    buttonPanel.add(headerPanel);
    buttonPanel.add(numberPanel);

    buttonPanel.setName("buttonPanel");
    buttonPanel.setPreferredSize(new Dimension(800, 250));
    buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    headerPanel.setName("headerPanel");
    headerPanel.setPreferredSize(new Dimension(800, 50));
    headerPanel.setMaximumSize(headerPanel.getPreferredSize());

    numberPanel.setName("headerPanel");
    numberPanel.setPreferredSize(new Dimension(800, 200));
    numberPanel.setMaximumSize(headerPanel.getPreferredSize());
  }

  public IOPanel addIoPanel(int ind) {
    IOPanel pan = new IOPanel();
    IoPanels.add(ind, pan);
    reinitDisplay();
    return pan;
  }

  public IOPanel removeIoPanel(int ind) {
    IOPanel pan = IoPanels.remove(ind);
    reinitDisplay();
    return pan;
  }

  public void initButtons() {
    for (Component c : numberPanel.getComponents()) {
      if (c instanceof InputButton) {
        InputButton button = (InputButton) c;
        button.setPreferredSize(new Dimension(20, 30));
        button.setMaximumSize(new Dimension(20, 30));
        Border b = BorderFactory.createLineBorder(ThemeManager.getTheme().getBackgroundXDark(), 1);
        button.setBorder(b);
      }
    }
  }

  public void clearIoPanels() {
    IoPanels.subList(0, IoPanels.size() - 1).clear();
    IoPanels.get(0).getInputPane().setText("");
    IoPanels.get(0).activate();
    reinitDisplay();
  }

  public IOPanel getIoPanel(int ind) {
    return IoPanels.get(ind);
  }

  private void reinitDisplay() {

    displayContainer.removeAll();
    displayContainer.add(Box.createVerticalGlue());
    for (int i = 0; i < IoPanels.size(); ++i) {
      displayContainer.add(IoPanels.get(i));
    }

    displayContainer.revalidate();
    displayContainer.repaint();
    pack();
  }

  public void deactivatePanels() {
    for (IOPanel pan : IoPanels) {
      pan.deactivate();
    }
  }

  public JPanel getNumberPanel() {
    return numberPanel;
  }

  public JPanel getHeaderPanel() {
    return headerPanel;
  }

  public void activate(int ind) {
    IOPanel panelActive = IoPanels.get(ind);
    panelActive.activate();
  }

  public void deactivate(int ind) {
    IOPanel panelInactive = IoPanels.get(ind);
    panelInactive.deactivate();
  }

  public void updateDegreeMode(boolean deg) {
    JButton degBtn = null, radBtn = null;
    for (Component comp : headerPanel.getComponents()) {
      if (!(comp instanceof JButton)) continue;
      JButton b = (JButton) comp;
      if (b.getText().equals("degree")) degBtn = b;
      if (b.getText().equals("radian")) radBtn = b;
    }
    Border def = BorderFactory.createLineBorder(ThemeManager.getTheme().getBackgroundXDark(), 1);
    Border selected = BorderFactory.createLineBorder(ThemeManager.getTheme().getWarning(), 1);

    if (deg) {
      degBtn.setBorder(selected);
      radBtn.setBorder(def);
    } else {
      radBtn.setBorder(selected);
      degBtn.setBorder(def);
    }
    headerPanel.revalidate();
    headerPanel.repaint();
  }

  public ArrayList<IOPanel> getIoPanels() {
    return IoPanels;
  }
}
