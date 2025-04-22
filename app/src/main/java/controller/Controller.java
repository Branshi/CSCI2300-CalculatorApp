package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Buffer;
import model.State;
import view.IOPanel;
import view.InputButton;
import view.MainView;

public class Controller implements ActionListener {
  State model;
  MainView view;

  public Controller(State m, MainView v) {
    this.model = m;
    this.view = v;
    initInputPaneController();
    initButtonController();
  }

  public void initInputPaneController() {
    Buffer buf = this.model.createBuffer(0);
    buf.toggleActive();
    IOPanel pan = this.view.addIoPanel(0);
    this.view.activate(0);
    new PaneController(buf, pan);
  }

  public void initButtonController() {
    for (Component c : view.getNumberPanel().getComponents()) {
      if (c instanceof InputButton) {
        new ButtonController((InputButton) c, model, view);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {}
}
