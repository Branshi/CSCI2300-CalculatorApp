package controller;

import model.State;
import view.MainView;

public class App {

  public static void main(String[] args) {
    // Bootstrap
    MainView appView = new MainView();
    State appState = new State();
    new Controller(appState, appView);
  }
}
