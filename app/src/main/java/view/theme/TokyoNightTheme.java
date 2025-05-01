package view.theme;

import java.awt.Color;

/** The “Tokyo Night Light” color palette. Based on the official Tokyo Night Light theme colors. */
public class TokyoNightTheme implements Theme {
  // Background variants
  private static final Color BACKGROUND_DARK = Color.decode("#e6e7ed");
  private static final Color BACKGROUND_MED = Color.decode("#cfd0d5");
  private static final Color BACKGROUND_SOFT = Color.decode("#a1a2a6");
  private static final Color BACKGROUND_XDARK = Color.decode("#737477");

  // Editor Foreground
  private static final Color FOREGROUND = Color.decode("#343b58");

  // Semantic accents
  private static final Color PRIMARY = Color.decode("#2959aa");
  private static final Color SECONDARY = Color.decode("#5a3e8e");
  private static final Color ERROR = Color.decode("#8c4351");
  private static final Color WARNING = Color.decode("#8f5e15");
  private static final Color SUCCESS = Color.decode("#385f0d");
  private static final Color INFO = Color.decode("#33635c");

  // Singleton instance
  private static final TokyoNightTheme INSTANCE = new TokyoNightTheme();

  public static TokyoNightTheme getInstance() {
    return INSTANCE;
  }

  private TokyoNightTheme() {}

  @Override
  public Color getBackgroundDark() {
    return BACKGROUND_DARK;
  }

  @Override
  public Color getBackgroundMed() {
    return BACKGROUND_MED;
  }

  @Override
  public Color getBackgroundSoft() {
    return BACKGROUND_SOFT;
  }

  @Override
  public Color getBackgroundXDark() {
    return BACKGROUND_XDARK;
  }

  @Override
  public Color getForeground() {
    return FOREGROUND;
  }

  @Override
  public Color getPrimary() {
    return PRIMARY;
  }

  @Override
  public Color getSecondary() {
    return SECONDARY;
  }

  @Override
  public Color getError() {
    return ERROR;
  }

  @Override
  public Color getWarning() {
    return WARNING;
  }

  @Override
  public Color getSuccess() {
    return SUCCESS;
  }

  @Override
  public Color getInfo() {
    return INFO;
  }
}
