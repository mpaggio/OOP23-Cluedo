package it.unibo.cluedo.controller.notebookcontroller.api;

import java.util.List;

/**
 * Interface that handles the notebook logic.
 */
public interface NotebookController {

    /**
     * Gets the list of seen suspects.
     * @return the list of seen suspects
     */
    List<String> getSeenSuspect();

    /**
     * Gets the list of seen weapons.
     * @return the list of seen weapons
     */
    List<String> getSeenWeapon();

    /**
     * Gets the list of seen rooms.
     * @return the list of seen rooms
     */
    List<String> getSeenRoom();
}
