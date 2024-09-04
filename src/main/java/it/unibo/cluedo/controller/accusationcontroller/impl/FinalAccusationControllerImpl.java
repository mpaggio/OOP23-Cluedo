package it.unibo.cluedo.controller.accusationcontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.accusationcontroller.api.FinalAccusationController;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.GameModel;

/**
 * Implementation of the FinalAccusationController.
 */
public class FinalAccusationControllerImpl implements FinalAccusationController {
    /**
     * {@inheritDoc}
     */
    @Override
    public void makeFinalAccusation(final Card suspect, final Card weapon, final Card room) {
        final GameModel gameModel = Cluedo.CONTROLLER.getGameInstance();
        try {
            gameModel.makeFinalAccusation(weapon, room, room, gameModel.getMap()
                .getRoomBySquare(gameModel.getMap()
                .getSquareByPosition(gameModel.getCurrentPlayer()
                .getCurrentPosition())).get());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinalAccusationCorrect() {
        return Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().hasWon();
    }
}
