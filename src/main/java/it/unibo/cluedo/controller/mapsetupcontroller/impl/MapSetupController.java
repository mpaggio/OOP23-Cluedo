package it.unibo.cluedo.controller.mapsetupcontroller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Controller for setting up the map in the Cluedo game.
 */
public class MapSetupController {
    /**
     * Retrieves the positions of all tiles on the map.
     * 
     * @return a list of positions of all tiles on the map
     */
    public List<Position> getTilesPositions() {
        final List<Position> tilesPositions = new ArrayList<>();
        for (final Square square : Cluedo.CONTROLLER.getGameInstance().getMap().getOrderedVisitedSquares()) {
            tilesPositions.add(square.getPosition());
        }
        return tilesPositions;
    }

    /**
     * Retrieves a map containing linked color and position of every player in the game.
     * 
     * @return a map containing linked color and position of every player in the game
     */
    public Map<Position, String> getPlayersPositionsAndColors() {
        final Map<Position, String> playerInfo = new HashMap<>();
        for (final Player player : Cluedo.CONTROLLER.getGameInstance().getPlayers()) {
            playerInfo.put(player.getCurrentPosition(), player.getColor());
        }
        return playerInfo;
    }

    /**
     * Retrieves the positions of all players on the map.
     * 
     * @return a list of positions of all players on the map
     */
    public List<Position> getPlayersPositions() {
        final List<Position> playersPositions = new ArrayList<>();
        for (final Player player : Cluedo.CONTROLLER.getGameInstance().getPlayers()) {
            playersPositions.add(player.getCurrentPosition());
        }
        return playersPositions;
    }
}
