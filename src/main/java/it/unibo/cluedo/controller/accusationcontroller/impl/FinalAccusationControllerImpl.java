package it.unibo.cluedo.controller.accusationcontroller.impl;

import java.util.Set;

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
        final Set<Card> allCards = Set.copyOf(gameModel.getAllCards());
        final Card suspectCard = allCards.stream().filter(card -> card.getName().equals(suspect)).findFirst().get();
        final Card weaponCard = allCards.stream().filter(card -> card.getName().equals(weapon)).findFirst().get();
        final Card roomCard = allCards.stream().filter(card -> card.getName().equals(room)).findFirst().get();
        try {
            gameModel.makeFinalAccusation(weaponCard, roomCard, suspectCard, gameModel.getMap()
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
