package it.unibo.cluedo.controller.accusationcontroller.api;

import java.util.Optional;

import it.unibo.cluedo.model.card.api.Card;

/**
 * Interface for the AccusationController.
 */
public interface AccusationController {

    /**
     * Method that returns the name of the room the pleyer is in.
     * @return the name of the room the player is in.
     */
    String getRoomName();
    /**
     * Method that allows the player to make an accusation.
     * @param suspect the suspect card.
     * @param weapon the weapon card.
     * @param room the room card.
     */
    void makeAccusation(String suspect, String weapon, String room);
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
    /**
     * Method that returns the card with the given name.
     * @param name
     * @return the card with the given name.
     */
    Optional<Card> getCardByName(String name);
}
