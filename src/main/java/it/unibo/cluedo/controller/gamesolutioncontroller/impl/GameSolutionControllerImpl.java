package it.unibo.cluedo.controller.gamesolutioncontroller.impl;

import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.view.gamesolution.GameSolutionView;
import java.util.Set;
/**
 * Controller class for handling the game solution logic in the Cluedo game.
 */
public class GameSolutionControllerImpl implements GameSolutionController {
    private final GameModel model;
    private GameSolutionView solutionView;

    /**
     * Contructor for the GameSolutionController class.
     * @param model the GameModel representing the game state and logic
     */
    public GameSolutionControllerImpl(final GameModel model) {
        this.model = model;
    }

    /**
     * Sets the view for this controller.
     * @param solutionView the GameSolutionView to set
     */
    @Override
    public void setView(final GameSolutionView solutionView) {
        this.solutionView = solutionView;
    }

    /**
     * Handles the player's final accusation.
     * If the accusation is correct, the player's status is updated and
     * the "Game solution" button in the view is enabled.
     * @param weapon the Card representing the weapon in the accusation
     * @param room the Card representing the room in the accusation 
     * @param character the Card representing thee character in the accusation
     * @param roomPosition the Room where the player can make the final accusation
     */
    @Override
    public void handleFinalAccusation(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        final boolean accusationCorrect = this.model.makeFinalAccusation(weapon, room, character, roomPosition);
        if (model.getCurrentPlayer().hasWon()) {
            this.solutionView.enableGameSolutionButton();
        } else if (!accusationCorrect) {
            this.solutionView.showFailureMessage("Final accusation incorrect. You have lost the game");
        }
    }

    /**
     * Displays the solution cards in the view when the "Game solution" button is clicked.
     * Only works if the player has won the game
     */
    @Override
    public void showSolution() {
        if (this.model.getCurrentPlayer().hasWon()) {
            final Set<Card> solution = this.model.getSolution();
            this.solutionView.displaySolution(solution);
        } else {
            this.solutionView.showFailureMessage("You cannot view the solution becaus you didn't win the game");
        }
    }
}
