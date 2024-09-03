package it.unibo.cluedo.controller.gamesolutioncontroller.api;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.room.api.Room;

/**
 * Interface for the controller class that handles the game solution logic.
 */
public interface GameSolutionController {
    /**
     * Handles the final accusation made by the player.
     * @param weapon the weapon card in the accusation
     * @param room the room card in the accusation
     * @param character the character card in the accusation
     * @param roomPosition the room were the player can make the final accusation
     */
    void handleFinalAccusation(Card weapon, Card room, Card character, Room roomPosition);
}
