package it.unibo.cluedo.controller.mapsetupcontroller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Controller for setting up the map in the Cluedo game.
 */
public class MapSetupController {
    private static final Board MAP = new BoardImpl();

    /**
     * Retrieves the positions of all tiles on the map.
     * 
     * @return a list of positions of all tiles on the map
     */
    public List<Position> getTilesPositions() {
        final List<Position> tilesPositions = new ArrayList<>();
        for (final Square square : MAP.getOrderedVisitedSquares()) {
            tilesPositions.add(square.getPosition());
        }
        return tilesPositions;
    }

    public List<Position> getPlayersPositions() {
        final List<Position> playersPositions = new ArrayList<>();
        for (final Player player : Cluedo.CONTROLLER.getGameInstance().getPlayers()) {
            playersPositions.add(player.getCurrentPosition());
        }
        return playersPositions;
    }
}
