package it.unibo.cluedo.controller.accusationcontroller.impl;

import java.util.Optional;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.accusationcontroller.api.AccusationController;
import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.card.api.Card;

/**
 * Implementation of the AccusationController interface.
 */
public class AccusationControllerImpl implements AccusationController {

    private Optional<Card> cardToShow;

    /**
     * Class constructor.
     */
    public AccusationControllerImpl() {
        cardToShow = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeAccusation(final Card suspect, final Card weapon, final Card room) {
        final GameModel gameModel = Cluedo.CONTROLLER.getGameInstance();
        try {
            cardToShow = gameModel.makeAccusation(weapon, room, room, gameModel.getMap()
                .getRoomBySquare(gameModel.getMap().
                getSquareByPosition(gameModel.getCurrentPlayer()
                .getCurrentPosition())).get());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccusationCorrect() {
        return cardToShow.isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card getCardToShow() {
        return cardToShow.get();
    }
}
