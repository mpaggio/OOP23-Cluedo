package it.unibo.cluedo.controller.gamesolutioncontroller.impl;

import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.view.gamesolution.GameSolutionView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Controller class for handling the game solution logic in the Cluedo game.
 */
public class GameSolutionControllerImpl implements GameSolutionController {
    private final GameModel model;
    private final GameSolutionView solutionView;
    
    /**
     * Contructor for the GameSolutionController class.
     * @param model the GameModel representing the game state and logic
     * @param solutionView the view representing the game solution
     */
    public GameSolutionControllerImpl(final GameModel model, final GameSolutionView solutionView) {
        this.model = model;
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
        if (this.model.getCurrentPlayer().hasWon()) {
            showSolution();
        } else if (!accusationCorrect) {
            this.solutionView.showFailureMessage("Final accusation incorrect. You have lost the game");
        }
    }

    /**
     * Displays the solution cards in the view when the "Game solution" button is clicked.
     * Only works if the player has won the game
     */
    private void showSolution() {
        final List<String> cardInfo = new ArrayList<>();
        if (this.model.getCurrentPlayer().hasWon()) {
            final Set<Card> solution = this.model.getSolution();
            for (final var card : solution) {
                cardInfo.add(card.getImagePath());
            }
            this.solutionView.displaySolution(cardInfo);
        } else {
            this.solutionView.showFailureMessage("You cannot view the solution becaus you didn't win the game");
        }
    }
}
