package it.unibo.cluedo.model.component.api;

import java.util.List;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface for all the component of the map.
 * It can be a room, a trap door or a square (normal, bonus or malus).
 */
public interface MapComponent {

    /**
     * Accept a map component visitor.
     * 
     * @param visitor the visitor of the component
     */
    void accept(MapComponentVisitor visitor);

    /**
     * Tells if the MapComponent has been visited by the visitor.
     * 
     * @return true if it has been visited
     */
    boolean hasBeenVisited();

    /**
     * Returns the list of players located in the map component.
     * 
     * @return the list of players located in the map component
     */
    List<Player> getPlayersIn();
}
