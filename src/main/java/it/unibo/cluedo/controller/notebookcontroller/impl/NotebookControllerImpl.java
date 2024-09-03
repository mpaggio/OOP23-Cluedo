package it.unibo.cluedo.controller.notebookcontroller.impl;

import java.util.List;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;

/**
 * Class that implements the NotebookController interface.
 */
public class NotebookControllerImpl  implements NotebookController {

    /**
     * Constructor for the NotebookControllerImpl class.
     * @param model the GameModel to set
     */
    public NotebookControllerImpl() {
    }

    /**
     * Gets the list of seen suspects.
     * @return the list of seen suspects
     */
    @Override
    public List<String> getSeenSuspect() {
        return Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getPlayerNotebook().getSeenSuspects();
    }

    /**
     * Gets the list of seen weapons.
     * @return the list of seen weapons
     */
    @Override
    public List<String> getSeenWeapon() {
        return Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getPlayerNotebook().getSeenWeapons();
    }

    /**
     * Gets the list of seen rooms.
     * @return the list of seen rooms
     */
    @Override
    public List<String> getSeenRoom() {
        return Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getPlayerNotebook().getSeenRooms();
    }
}
