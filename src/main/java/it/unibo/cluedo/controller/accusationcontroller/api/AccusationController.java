package it.unibo.cluedo.controller.accusationcontroller.api;

import it.unibo.cluedo.model.card.api.Card;

/**
 * Interface for the AccusationController.
 */
public interface AccusationController {
    /**
     * Method that allows the player to make an accusation.
     */
    void makeAccusation();
    /**
     * Method that checks if the accusation made by the player is correct.
     * @return true if the accusation is correct, false otherwise.
     */
    boolean isAccusationCorrect();
    /**
     * Method that returns the card to show to the player.
     * @return the card to show to the player.
     */
    Card getCardToShow();
}
