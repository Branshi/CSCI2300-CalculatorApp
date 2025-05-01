package controller;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.State;
import view.MainView;

public class App {

  public static void main(String[] args) {
    // Bootstrap
    String path =
        JOptionPane.showInputDialog(
            null, "Enter path to load calculator state, or leave blank for a fresh start:");
    State appState;
    if (path != null && !path.trim().isEmpty()) {
      try {
        appState = State.loadFrom(path.trim());
      } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
            null,
            "Failed to load state; starting fresh.",
            "Load Error",
            JOptionPane.WARNING_MESSAGE);
        appState = new State();
      }
    } else {
      appState = new State();
    }

    Font uiFont = new Font("JetBrains Mono", Font.PLAIN, 16);

    UIManager.put("Button.font", uiFont);
    UIManager.put("ToggleButton.font", uiFont);
    UIManager.put("Label.font", uiFont);
    UIManager.put("TextField.font", uiFont);
    UIManager.put("TextPane.font", uiFont);
    UIManager.put("OptionPane.messageFont", uiFont);
    UIManager.put("TabbedPane.font", uiFont);

    MainView appView = new MainView();
    new Controller(appState, appView);
  }
}
