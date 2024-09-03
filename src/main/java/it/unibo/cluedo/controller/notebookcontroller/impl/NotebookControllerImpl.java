package it.unibo.cluedo.controller.notebookcontroller.impl;

import java.util.List;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.maincontroller.impl.MainControllerImpl;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;

/**
 * Class that implements the NotebookController interface.
 */
public class NotebookControllerImpl  implements NotebookController {

    private final MainControllerImpl mainController;

    /**
     * Constructor for the NotebookControllerImpl class.
     */
    public NotebookControllerImpl() {
        this.mainController = Cluedo.CONTROLLER;
    }

    /**
     * Gets the list of seen suspects.
     * @return the list of seen suspects
     */
    @Override
    public List<String> getSeenSuspect() {
        return mainController.getGameInstance().getNotebook().getSeenSuspects();
    }

    /**
     * Gets the list of seen weapons.
     * @return the list of seen weapons
     */
    @Override
    public List<String> getSeenWeapon() {
        return mainController.getGameInstance().getNotebook().getSeenWeapons();
    }

    /**
     * Gets the list of seen rooms.
     * @return the list of seen rooms
     */
    @Override
    public List<String> getSeenRoom() {
        return mainController.getGameInstance().getNotebook().getSeenRooms();
    }
}
