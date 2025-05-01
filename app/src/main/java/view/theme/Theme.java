package view.theme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.*;
import javax.swing.JButton;
import view.*;

/** A Theme interface defining eight semantic colors. */
public interface Theme {
  Color getBackgroundDark();

  Color getBackgroundMed();

  Color getBackgroundSoft();

  Color getBackgroundXDark();

  Color getForeground();

  Color getPrimary(); // main accent (e.g. buttons, highlights)

  Color getSecondary(); // secondary accent (e.g. info badges)

  Color getError(); // error states

  Color getWarning(); // warning states

  Color getSuccess(); // success states

  Color getInfo(); // informational states

  default void applyTo(Container root) {
    // only panels get the theme background:
    if ("buttonPanel".equals(root.getName()) || "headerPanel".equals(root.getName())) {
      root.setBackground(getBackgroundMed()); // whatever extra color you want
      ((JComponent) root).setOpaque(true);
    } else if (root instanceof IOPanel) {
      root.setBackground(getBackgroundDark());
      ((JComponent) root).setOpaque(true);
    } else if (root instanceof JPanel || root instanceof JScrollPane) {
      root.setBackground(getBackgroundDark());
      ((JComponent) root).setOpaque(true);
    }
    for (Component c : root.getComponents()) {
      if (c instanceof JButton) {
        c.setBackground(getBackgroundSoft());
        c.setForeground(getForeground());
        ((JButton) c)
            .setBorder(BorderFactory.createLineBorder(ThemeManager.getTheme().getBackgroundMed()));
        ((JComponent) c).setOpaque(true);
        // <— do *not* recurse into buttons
      } else if (c instanceof JTextField) {
        c.setBackground(getBackgroundDark());
        ((JTextField) c).setForeground(getForeground());
        ((JComponent) c).setOpaque(true);
      } else if (c instanceof JLabel) {
        c.setBackground(getBackgroundDark());
        ((JLabel) c).setForeground(getForeground());
        ((JComponent) c).setOpaque(true);
      } else if (c instanceof Container) {
        applyTo((Container) c);
      }
    }
  }
}
