package it.unibo.cluedo.controller.mapsetupcontroller.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Controller for setting up the map in the Cluedo game.
 */
public class MapSetupController {
    private static final Board MAP = new BoardImpl();
    private static final double TILE_SIZE = 23;
    private static final double OFFSET_X = 41.7;
    private static final double OFFSET_Y = 23.3;

    /**
     * Retrieves the positions of all tiles on the map.
     * 
     * @return a list of positions of all tiles on the map
     */
    public List<Position> getTilesPositions() {
        final List<Position> tilesPositions = new LinkedList<>();
        for (final Square square : MAP.getOrderedVisitedSquares()) {
            tilesPositions.add(square.getPosition());
        }
        return tilesPositions;
    }

    /**
     * Retrieves the size of a single tile.
     * 
     * @return the size of a single tile
     */
    public static double getTileSize() {
        return TILE_SIZE;
    }

    /**
     * Retrieves the offset for x-coordinates.
     * 
     * @return the offset for x-coordinates
     */
    public static double getOffsetX() {
        return OFFSET_X;
    }

    /**
     * Retrieves the offset for y-coordinates.
     * 
     * @return the offset for y-coordinates
     */
    public static double getOffsetY() {
        return OFFSET_Y;
    }
}
