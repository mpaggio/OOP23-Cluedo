package it.unibo.cluedo.controller.notebookcontroller.impl;

import java.util.List;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;

/**
 * Class that implements the NotebookController interface.
 */
public class NotebookControllerImpl  implements NotebookController {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getSeenSuspect() {
        return Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getPlayerNotebook().getSeenSuspects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getSeenWeapon() {
        return Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getPlayerNotebook().getSeenWeapons();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getSeenRoom() {
        return Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getPlayerNotebook().getSeenRooms();
    }
}
