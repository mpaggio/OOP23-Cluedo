package it.unibo.cluedo.model.room.api;

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
}
