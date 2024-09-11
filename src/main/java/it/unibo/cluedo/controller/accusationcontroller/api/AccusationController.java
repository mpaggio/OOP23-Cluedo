package it.unibo.cluedo.controller.accusationcontroller.api;

import java.util.Optional;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;

/**
 * Interface for the AccusationController.
 */
public interface AccusationController {

    /**
     * Method that returns the name of the {@link Room} the {@link Player} is in.
     * @return the name ({@link Player}) of the room the player is in.
     */
    String getRoomName();
    /**
     * Method that allows the player to make an {@link Accusation} by passing as input three {@link String}
     * containing the name of the {@link Card} choosen.
     * @param suspect the suspect card.
     * @param weapon the weapon card.
     * @param room the room card.
     */
    void makeAccusation(String suspect, String weapon, String room);
    /**
     * Method that checks if the {@link Accusation} made by the {@link Player} is correct.
     * @return true if the accusation is correct, false otherwise.
     */
    boolean isAccusationCorrect();
    /**
     * Method that returns the {@link Card} to show to the player.
     * @return the card to show to the player.
     */
    Card getCardToShow();
    /**
     * Method that returns the {@link Card} with the given name.
     * @param name
     * @return an {@link Optional} of the card with the given name.
     */
    Optional<Card> getCardByName(String name);
}
