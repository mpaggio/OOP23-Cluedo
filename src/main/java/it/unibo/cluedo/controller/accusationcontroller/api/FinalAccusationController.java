package it.unibo.cluedo.controller.accusationcontroller.api;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface of the FinalAccusationController.
 */
public interface FinalAccusationController {
    /**
     * Method that allows the {@link Player} to make a final {@link Accusation} by passing as input three
     * {@link String} containing the name of the {@link Card} choosen.
     * @param suspect the suspect card.
     * @param weapon the weapon card.
     * @param room the room card.
     */
    void makeFinalAccusation(String suspect, String weapon, String room);
    /**
     * Method that checks if the final {@link Accusation} made by the {@link Player} is correct.
     * @return true if the accusation is correct, false otherwise.
     */
    boolean isFinalAccusationCorrect();
}
