package it.unibo.cluedo.controller.accusationcontroller.impl;

import javax.swing.JOptionPane;

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
    public void makeFinalAccusation(final String suspect, final String weapon, final String room) {
        final GameModel gameModel = Cluedo.CONTROLLER.getGameInstance();
        final Card suspectCard = Cluedo.CONTROLLER.getAccusationController().getCardByName(suspect).get();
        final Card weaponCard = Cluedo.CONTROLLER.getAccusationController().getCardByName(weapon).get();
        final Card roomCard = Cluedo.CONTROLLER.getAccusationController().getCardByName(room).get();
        try {
            gameModel.makeFinalAccusation(weaponCard, roomCard, suspectCard, gameModel.getMap()
                .getRoomBySquare(gameModel.getMap()
                .getSquareByPosition(gameModel.getCurrentPlayer()
                .getCurrentPosition())).get());
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
