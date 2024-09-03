package it.unibo.cluedo.controller.accusationcontroller.api;

import it.unibo.cluedo.model.card.api.Card;

/**
 * Interface of the FinalAccusationController.
 */
public interface FinalAccusationController {
    /**
     * Method that allows the player to make a final accusation.
     * @param suspect the suspect card.
     * @param weapon the weapon card.
     * @param room the room card.
     */
    void makeFinalAccusation(Card suspect, Card weapon, Card room);
    /**
     * Method that checks if the final accusation made by the player is correct.
     * @return true if the accusation is correct, false otherwise.
     */
    boolean isFinalAccusationCorrect();
}
