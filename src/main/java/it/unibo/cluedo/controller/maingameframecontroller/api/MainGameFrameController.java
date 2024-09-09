package it.unibo.cluedo.controller.maingameframecontroller.api;

import java.util.List;

/**
 * Interface for the MainGameFrameControllerImpl.
 */
public interface MainGameFrameController {
    /**
     * Returns a list of the current player's cards path.
     *
     * @return a list of the current player's cards path
     */
    List<String> getCurrentPlayerCardsPaths();

    /**
     * Returns a boolean representing if the player can use a trapdoor. 
     * 
     * @return true if the player can use a trapdoor, false otherwise
     */
    boolean canPlayerUseTrapDoor();

    /**
     * Returns a boolean representing if the player has zero steps or not.
     * 
     * @return true if player has zero steps, false otherwise
     */
    boolean areStepsZero();
}
