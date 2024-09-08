package it.unibo.cluedo.controller.accusationcontroller.impl;

import java.util.Optional;

import javax.swing.JOptionPane;

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
    public String getRoomName() {
        final GameModel gameModel = Cluedo.CONTROLLER.getGameInstance();
        return gameModel.getMap().getRoomBySquare(gameModel.getMap()
            .getSquareByPosition(gameModel
            .getCurrentPlayer().getCurrentPosition()))
            .isPresent() ? 
            gameModel.getMap()
            .getRoomBySquare(gameModel.getMap()
            .getSquareByPosition(gameModel
            .getCurrentPlayer().getCurrentPosition()))
            .get().getName() : 
            "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeAccusation(final String suspect, final String weapon, final String room) {
        final GameModel gameModel = Cluedo.CONTROLLER.getGameInstance();
        final Card suspectCard = getCardByName(suspect).get();
        final Card weaponCard = getCardByName(weapon).get();
        final Card roomCard = getCardByName(getRoomName()).get();
        try {
            cardToShow = gameModel.makeAccusation(weaponCard, roomCard, suspectCard, gameModel.getMap()
                .getRoomBySquare(gameModel.getMap().
                getSquareByPosition(gameModel.getCurrentPlayer()
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> getCardByName(final String name) {
        final GameModel gameModel = Cluedo.CONTROLLER.getGameInstance();
        return Optional.of(gameModel.getAllCards().stream().filter(card -> card.getName().equals(name)).findFirst().get());
    }
}
