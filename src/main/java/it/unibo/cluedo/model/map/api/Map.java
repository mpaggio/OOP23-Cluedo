package it.unibo.cluedo.model.map.api;

import java.util.List;

import it.unibo.cluedo.model.room.api.MapComponent;
import it.unibo.cluedo.model.room.api.MapComponentVisitor;

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

    /**
     * Gets the map component visitor.
     * 
     * @return the map component visitor
     */
    MapComponentVisitor getVisitor();
}
