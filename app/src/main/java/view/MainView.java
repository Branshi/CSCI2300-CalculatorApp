package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ExpressionEvaluator;

public class MainView extends JFrame {
  // GUI components
  private JPanel buttonPanel;           // Panel holding all the buttons
  private JPanel displayPanel;          // Panel showing input/output
  private ArrayList<IOPanel> IoPanels;  // Stores input/output panels
  private JPanel numberPanel;           // Grid panel for calculator buttons
  private String lastAnswer = "";       // Stores last calculated result (used with 'ans')

  public MainView() {
    super("Caltrix"); // Set window title
    this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Close app when window is closed
    Container cp = getContentPane();
    cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS)); // Arrange components vertically

    initDisplayPanel();  // Set up input/output display
    initButtonPanel();   // Set up calculator buttons
    addIoPanel(0);       // Show first IOPanel on launch
    activate(0);         // Highlight it red (active)

    cp.add(displayPanel);
    cp.add(buttonPanel);
    pack();               // Resize window to fit content
    setLocationRelativeTo(null); // Center the window on screen
    this.setVisible(true);       // Show the window
  }

  // Initialize the display panel (holds input/output lines)
  private void initDisplayPanel() {
    displayPanel = new JPanel();
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    IoPanels = new ArrayList<>();
  }

  // Get the index of the active input/output panel (the red one)
  private int getActivePanelIndex() {
    for (int i = 0; i < IoPanels.size(); i++) {
      if (IoPanels.get(i).getBackground() == Color.RED) {
        return i;
      }
    }
    return -1;
  }

  // Append text (like "sin(" or a digit) to the currently active input field
  private void appendToActiveInput(String text) {
    int index = getActivePanelIndex();
    if (index != -1) {
      IOPanel activePanel = IoPanels.get(index);
      InputPane input = activePanel.getInputPane();
      input.setText(input.getText() + text);
    }
  }

  // Set up calculator buttons (numbers, operators, trig functions, equals, etc.)
  private void initButtonPanel() {
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    numberPanel = new JPanel();
    numberPanel.setLayout(new GridLayout(5, 4)); // 5 rows x 4 columns

    // Row 1: 7 8 9 /
    numberPanel.add(new InputButton("7"));
    numberPanel.add(new InputButton("8"));
    numberPanel.add(new InputButton("9"));
    numberPanel.add(new InputButton("/"));

    // Row 2: 4 5 6 x
    numberPanel.add(new InputButton("4"));
    numberPanel.add(new InputButton("5"));
    numberPanel.add(new InputButton("6"));
    numberPanel.add(new InputButton("x"));

    // Row 3: 1 2 3 -
    numberPanel.add(new InputButton("1"));
    numberPanel.add(new InputButton("2"));
    numberPanel.add(new InputButton("3"));
    numberPanel.add(new InputButton("-"));

    // Row 4: 0 . ans +
    InputButton ansButton = new InputButton("ans");
    ansButton.addActionListener(e -> appendToActiveInput(lastAnswer));

    numberPanel.add(new InputButton("0"));
    numberPanel.add(new InputButton("."));
    numberPanel.add(ansButton);
    numberPanel.add(new InputButton("+"));

    // Trig buttons: sin, cos, tan
    InputButton sinButton = new InputButton("sin");
    InputButton cosButton = new InputButton("cos");
    InputButton tanButton = new InputButton("tan");

    sinButton.addActionListener(e -> appendToActiveInput("("));
    cosButton.addActionListener(e -> appendToActiveInput("("));
    tanButton.addActionListener(e -> appendToActiveInput("("));

    numberPanel.add(sinButton);
    numberPanel.add(cosButton);
    numberPanel.add(tanButton);

    // Equals button
    InputButton equalButton = new InputButton("=");
    equalButton.addActionListener(e -> {
      int index = getActivePanelIndex();
      if (index != -1) {
        IOPanel activePanel = IoPanels.get(index);
        String inputExpr = activePanel.getInputPane().getText();
        try {
          ExpressionEvaluator evaluator = new ExpressionEvaluator(true); // degree mode
          double result = evaluator.evaluate(inputExpr);
          String resultStr = String.valueOf(result);
          activePanel.getOutputPane().setText(resultStr);
          lastAnswer = resultStr; // Store for 'ans'
        } catch (Exception ex) {
          activePanel.getOutputPane().setText("Error");
        }
      }
    });
    numberPanel.add(equalButton);

    buttonPanel.add(numberPanel); // Add number panel to button panel
  }

  // Adds a new input/output panel and updates the display
  public IOPanel addIoPanel(int ind) {
    IOPanel pan = new IOPanel();
    IoPanels.add(ind, pan);
    reinitDisplay();
    return pan;
  }

  // Refresh the display area with all current panels
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

  // Set the panel at index `ind` as active (highlight red)
  public void activate(int ind) {
    IOPanel panelActive = IoPanels.get(ind);
    panelActive.setBackground(Color.RED);
  }

  // Deactivate the panel at index `ind` (set back to white)
  public void deactivate(int ind) {
    IOPanel panelInactive = IoPanels.get(ind);
    panelInactive.setBackground(Color.WHITE);
  }

  public ArrayList<IOPanel> getIoPanels() {
    return IoPanels;
  }
}
