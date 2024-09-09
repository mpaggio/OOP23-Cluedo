package it.unibo.cluedo.controller.maingameframecontroller.impl;

import java.util.List;
import java.util.ArrayList;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.maingameframecontroller.api.MainGameFrameController;
import it.unibo.cluedo.model.card.api.Card;

/**
 * MainGameFrameControllerImpl is responsible for getting model
 * informations into the MainGameFrame view.
 */
public class MainGameFrameControllerImpl implements MainGameFrameController {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getCurrentPlayerCardsPaths() {
        final List<String> cardsPaths = new ArrayList<>();
        for (final Card card : Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getPlayerCards()) {
            cardsPaths.add(card.getImagePath());
        }
        return cardsPaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPlayerUseTrapDoor() {
        final var room = Cluedo.CONTROLLER.getGameInstance().getMap().getRoomBySquare(
            Cluedo.CONTROLLER.getGameInstance().getMap().getSquareByPosition(
                Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentPosition()
            )
        );
        if (room.isPresent()) {
            return room.get().hasTrapDoor();
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areStepsZero() {
        final var player = Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer();
        return player.getCurrentSteps() == 0;
    }
}
