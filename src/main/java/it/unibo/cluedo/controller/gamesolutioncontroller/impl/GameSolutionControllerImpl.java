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
    /**
     * {@inheritDoc}
     */
    @Override
    public GameSolutionView showSolution() {
        final List<String> cardInfo = new ArrayList<>();
        final Set<Card> solution = Cluedo.CONTROLLER.getGameInstance().getSolution();
        for (final var card : solution) {
            cardInfo.add(card.getImagePath());
        }
       return new GameSolutionView(cardInfo);
    }
}
