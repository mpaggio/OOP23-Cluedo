package it.unibo.cluedo.controller.gamesolutioncontroller.api;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.view.gamesolution.GameSolutionView;

/**
 * Interface for the controller class that handles the game solution logic.
 */
public interface GameSolutionController {
    /**
     * Sets the view for this controller.
     * @param solutionView the view to set
     */
    void setView(GameSolutionView solutionView);

    /**
     * Handles the final accusation made by the player.
     * @param weapon the weapon card in the accusation
     * @param room the room card in the accusation
     * @param character the character card in the accusation
     * @param roomPosition the room were the player can make the final accusation
     */
    void handleFinalAccusation(Card weapon, Card room, Card character, Room roomPosition);

    /**
     * Displays the solution cards in the view when the "Game solution" button is clicked.
     */
    void showSolution();
}
