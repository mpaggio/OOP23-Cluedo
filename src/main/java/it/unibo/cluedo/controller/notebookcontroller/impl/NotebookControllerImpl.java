package it.unibo.cluedo.controller.notebookcontroller.impl;

import java.util.List;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;
import it.unibo.cluedo.model.GameModel;

/**
 * Class that implements the NotebookController interface.
 */
public class NotebookControllerImpl  implements NotebookController {

    private final GameModel model;

    /**
     * Constructor for the NotebookControllerImpl class.
     * @param model the GameModel to set
     */
    public NotebookControllerImpl(final GameModel model) {
        this.model = model;
    }

    /**
     * Gets the list of seen suspects.
     * @return the list of seen suspects
     */
    @Override
    public List<String> getSeenSuspect() {
        return model.getNotebook().getSeenSuspects();
    }

    /**
     * Gets the list of seen weapons.
     * @return the list of seen weapons
     */
    @Override
    public List<String> getSeenWeapon() {
        return model.getNotebook().getSeenWeapons();
    }

    /**
     * Gets the list of seen rooms.
     * @return the list of seen rooms
     */
    @Override
    public List<String> getSeenRoom() {
        return model.getNotebook().getSeenRooms();
    }
}
