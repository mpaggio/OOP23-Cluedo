package it.unibo.cluedo.application;

import it.unibo.cluedo.controller.maincontroller.impl.MainControllerImpl;

/**
 * The main class for the Cluedo application.
 */
public final class Cluedo {
    public static final MainControllerImpl CONTROLLER = new MainControllerImpl();
    /**
     * Private constructor to prevent instantiation.
     */
    private Cluedo() {
    }

    /**
     * The main method to start the Cluedo application.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(final String[] args) {
        CONTROLLER.startView();
    }
}
