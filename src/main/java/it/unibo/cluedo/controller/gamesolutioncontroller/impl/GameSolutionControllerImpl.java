package it.unibo.cluedo.controller.gamesolutioncontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.view.gamesolution.GameSolutionView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Controller class for handling the game solution logic in the Cluedo game.
 */
public class GameSolutionControllerImpl implements GameSolutionController {
    private GameSolutionView solutionView;

    /**
     * Contructor for the GameSolutionController class.
     */
    public GameSolutionControllerImpl() {
        this.solutionView = null;
    }

    /**
     * Displays the solution cards in the view when the "Game solution" button is clicked.
     * Only works if the player has won the game
     * @return 
     */
    public GameSolutionView showSolution() {
        final List<String> cardInfo = new ArrayList<>();
        final Set<Card> solution = Cluedo.CONTROLLER.getGameInstance().getSolution();
        for (final var card : solution) {
            cardInfo.add(card.getImagePath());
        }
       this.solutionView = new GameSolutionView(cardInfo);
       return this.solutionView;
    }
}
