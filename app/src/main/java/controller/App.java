package controller;

import model.State;
import view.MainView;

/**
 * The main application entry point for the Caltrix calculator.
 * Bootstraps and initializes the Model-View-Controller components:
 * - Creates the main application view ({@link MainView})
 * - Initializes the application state ({@link State})
 * - Establishes the controller ({@link Controller}) to coordinate between them
 */
public class App {

    /**
     * The main entry point for the application.
     * Initializes the MVC architecture components and starts the application.
     * 
     * @param args command-line arguments (not currently used)
     */
    public static void main(String[] args) {
        // Bootstrap and initialize application components
        MainView appView = new MainView();
        State appState = new State();
        new Controller(appState, appView);
    }
}