package it.unibo.cluedo.model.room.api;

import java.util.List;
import java.util.Optional;

import it.unibo.cluedo.model.component.api.MapComponent;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;

/**
 * Represents a room in the Cluedo game.
 * A room contains squares and has at least one entrance.
 * A room can contain a trap door.
 */
public interface Room extends MapComponent {
    /**
     * Adds a square to the room.
     * 
     * @param square the square to be added to the room
     */
    void addSquare(Square square);

    /**
     * Adds an entrance to the room.
     * 
     * @param entrance the square representing the entrance to the room
     */
    void addEntrance(Square entrance);

    /**
     * Checks if a given square is an entrance to the room.
     * 
     * @param square the square to be checked
     * @return true if the square is an entrance, false otherwise
     */
    boolean isEntrance(Square square);

    /**
     * Sets a trap door in the room.
     * 
     * @param trapDoor the trap door to set
     */
    void setTrapDoor(Optional<TrapDoor> trapDoor);

    /**
     * Checks if there is a trapdoor in the room.
     * 
     * @return true if there is a trapdoor, false otherwise
     */
    boolean hasTrapDoor();

    /**
     * Gets the name of the room.
     * 
     * @return the name of the room
     */
    String getName();

    /**
     * Gets the list of squares of the room.
     * 
     * @return the list of squares of the room
     */
    List<Square> getSquares();

    /**
     * Gets the list of entrances of the room.
     * 
     * @return the list of entrances of the room
     */
    List<Square> getEntrances();

    /**
     * Gets the trapdoor of the room.
     * 
     * @return the trapdoor of the room
     */
    Optional<TrapDoor> getTrapDoor();

    /**
     * Add a player in the room.
     * 
     * @param player the player to add
     */
    void addPlayerInRoom(Player player);

    /**
     * Remove the specified player from the list.
     * 
     * @param player the player to remove from the list
     */
    void removePlayerFromRoom(Player player);

    /**
     * Gets the list of players located in the room.
     * 
     * @return the list of players located in the room
     */
    List<Player> getPlayersInRoom();
}
