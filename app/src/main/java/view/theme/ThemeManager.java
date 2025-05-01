package view.theme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ThemeManager {

  private static Theme current = TokyoNightTheme.getInstance();
  private static final List<Consumer<Theme>> listeners = new ArrayList<>();

  public static Theme getTheme() {
    return current;
  }

  public static void setTheme(Theme theme) {
    current = theme;
    listeners.forEach(l -> l.accept(theme));
  }

  public static void onChange(Consumer<Theme> listener) {
    listeners.add(listener);
  }
}
