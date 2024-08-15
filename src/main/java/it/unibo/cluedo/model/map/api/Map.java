package it.unibo.cluedo.model.map.api;

import java.util.List;

import it.unibo.cluedo.model.room.api.MapComponent;

/**
 * Represents the map of the game of Cluedo.
 */
public interface Map {
    /**
     * Get tne elements list of the map.
     * 
     * @return the elements list of the map
     */
    List<MapComponent> getMap();
}
