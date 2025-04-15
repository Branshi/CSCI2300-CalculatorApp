package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.State;
import view.MainView;

public class Controller implements ActionListener {
  State model;
  MainView view;

  public Controller(State m, MainView v) {
    this.model = m;
    this.view = v;
  }

  @Override
  public void actionPerformed(ActionEvent e) {}
}
