package it.unibo.cluedo.controller.mapsetupcontroller.api;

import java.util.List;
import java.util.Map;

import it.unibo.cluedo.utilities.Position;

/**
 * MapSetupController is the main responsible for setting up the board view.
 */
public interface MapSetupController {
    /**
     * Retrieves the positions of all tiles on the map.
     * 
     * @return a list of positions of all tiles on the map
     */
    List<Position> getTilesPositions();

    /**
     * Retrieves a map containing linked color and position of every player in the game
     * except for the current player.
     * 
     * @return a map containing linked color and position of every player in the game
     * except for the current player
     */
    Map<Position, String> getPlayersPositionsAndColors();

    /**
     * Retrieves the positions of all players on the map, except for the current player.
     * 
     * @return a list of positions of all players on the map, except for the current player
     */
    List<Position> getPlayersPositions();
}
