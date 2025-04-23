package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Buffer;
import model.State;
import view.IOPanel;
import view.InputButton;
import view.MainView;

/**
 * The main controller class that coordinates between the model and view components.
 * Implements the core application logic and initializes specialized controllers
 * for different UI components (input panes and buttons).
 */
public class Controller implements ActionListener {
    /** The application state model */
    State model;
    
    /** The main application view */
    MainView view;

    /**
     * Constructs the main controller and initializes sub-controllers.
     * 
     * @param m the application state model
     * @param v the main application view
     */
    public Controller(State m, MainView v) {
        this.model = m;
        this.view = v;
        initInputPaneController();
        initButtonController();
    }

    /**
     * Initializes the input pane controller by:
     * 1. Creating and activating a new buffer
     * 2. Adding a corresponding I/O panel to the view
     * 3. Creating a PaneController to manage their interaction
     */
    public void initInputPaneController() {
        Buffer buf = this.model.createBuffer(0);
        buf.setActive(true);
        IOPanel pan = this.view.addIoPanel(0);
        this.view.activate(0);
        new PaneController(buf, pan, model, view);
    }

    /**
     * Initializes controllers for all calculator buttons.
     * Iterates through the number panel components and creates
     * ButtonController instances for each InputButton found.
     */
    public void initButtonController() {
        for (Component c : view.getNumberPanel().getComponents()) {
            if (c instanceof InputButton) {
                new ButtonController((InputButton) c, model, view);
            }
        }
    }

    /**
     * Handles action events (currently unimplemented).
     * 
     * @param e the ActionEvent to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Intentionally left blank - to be implemented if needed
    }
}