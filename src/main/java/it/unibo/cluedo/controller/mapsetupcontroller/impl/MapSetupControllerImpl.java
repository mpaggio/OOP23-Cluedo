package it.unibo.cluedo.controller.mapsetupcontroller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.mapsetupcontroller.api.MapSetupController;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Controller for setting up the map in the Cluedo game.
 */
public class MapSetupControllerImpl implements MapSetupController{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Position> getTilesPositions() {
        final List<Position> tilesPositions = new ArrayList<>();
        for (final Square square : Cluedo.CONTROLLER.getGameInstance().getMap().getOrderedVisitedSquares()) {
            tilesPositions.add(square.getPosition());
        }
        return tilesPositions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, String> getPlayersPositionsAndColors() {
        final Map<Position, String> playerInfo = new HashMap<>();
        for (final Player player : Cluedo.CONTROLLER.getGameInstance().getPlayers()) {
            if (!Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().equals(player)) {
                playerInfo.put(player.getCurrentPosition(), player.getColor());
            }
        }
        return playerInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Position> getPlayersPositions() {
        final List<Position> playersPositions = new ArrayList<>();
        for (final Player player : Cluedo.CONTROLLER.getGameInstance().getPlayers()) {
            if (!Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().equals(player)) {
                playersPositions.add(player.getCurrentPosition());
            }
        }
        return playersPositions;
    }
}
