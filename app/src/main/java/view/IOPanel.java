package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import view.theme.ThemeManager;

public class IOPanel extends JPanel {
  private InputPane input;
  private OutputPane output;

  public IOPanel() {
    super();

    // lock the height but let width float to container:
    Dimension pref = new Dimension(800, 100);
    setPreferredSize(pref);
    setMaximumSize(new Dimension(pref.width, pref.height));

    // THIS is key — align to the left so BoxLayout gives you the full width:
    setAlignmentX(Component.LEFT_ALIGNMENT);

    // your existing layout + children…
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    input = new InputPane();
    output = new OutputPane();
    add(input);
    add(output);

    ThemeManager.getTheme().applyTo(this);
  }

  public InputPane getInputPane() {
    return input;
  }

  public OutputPane getOutputPane() {
    return output;
  }

  public void activate() {
    Border b = BorderFactory.createLineBorder(ThemeManager.getTheme().getPrimary(), 2);
    setBorder(b);
    setMaximumSize(
        new Dimension(
            getPreferredSize().width - b.getBorderInsets(this).right / 2,
            getPreferredSize().height));
  }

  public void deactivate() {
    Color c = ThemeManager.getTheme().getBackgroundXDark();
    // top, left, bottom, right
    Border topOnly = BorderFactory.createMatteBorder(1, 0, 0, 0, c);
    setBorder(topOnly);

    // lock height but allow width to fill
    Dimension pref = getPreferredSize();
    setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));
  }
}
