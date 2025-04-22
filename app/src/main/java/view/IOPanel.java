package view;

import java.awt.*;
import javax.swing.*;

public class IOPanel extends JPanel {
  private InputPane input;
  private OutputPane output;

  public IOPanel() {
    super();

    this.setPreferredSize(new Dimension(800, 100));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    input = new InputPane();
    output = new OutputPane();

    this.add(input);
    this.add(output);
  }

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
