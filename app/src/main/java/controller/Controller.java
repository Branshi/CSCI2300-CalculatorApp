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
    initHeaderController();
  }

  public void initInputPaneController() {
    if (model.getBuffers().isEmpty()) {
      Buffer buf = this.model.createBuffer(0);
      buf.setActive(true);
      IOPanel pan = this.view.addIoPanel(0);
      this.view.activate(0);
      new PaneController(buf, pan, model, view);
    } else {
      this.restore();
    }
    view.updateDegreeMode(model.getDegreeMode());
  }

  public void initButtonController() {
    for (Component c : view.getNumberPanel().getComponents()) {
      if (c instanceof InputButton) {
        new ButtonController((InputButton) c, model, view);
      }
    }
  }

  public void initHeaderController() {
    new HeaderController(model, view);
  }

  public void restore() {
    for (int i = 0; i < model.getBuffers().size(); i++) {
      Buffer buf = model.getBuffers().get(i);
      IOPanel pan = view.addIoPanel(i);

      pan.getInputPane().setText(buf.getContent());

      try {
        String out = String.valueOf(Evaluate.eval(buf.getContent()));
        pan.getOutputPane().setText(out);
      } catch (Exception ex) {
        pan.getOutputPane().setText("");
      }

      new PaneController(buf, pan, model, view);

      if (buf.getActive()) {
        view.activate(i);
      } else {
        view.deactivate(i);
      }
    }
  }

  public void reEvalPanels() {
    for (IOPanel panel : view.getIoPanels()) {
      try {
        String out =
            String.valueOf(
                Evaluate.eval(
                    model.getBuffers().get(view.getIoPanels().indexOf(panel)).getContent()));
        panel.getOutputPane().setText(out);
      } catch (IllegalArgumentException ex) {
        panel.getOutputPane().setText(ex.getMessage());
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {}
}
