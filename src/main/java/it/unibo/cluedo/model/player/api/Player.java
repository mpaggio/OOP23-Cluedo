package it.unibo.cluedo.model.player.api;

import it.unibo.cluedo.utilities.Position;

/**
 * Interface wich models a player. Each player has a username, position
 * and a character.
 * The interface provides the methods to get and set information about
 * the player.
 */
public interface Player {

    /**
     * Get the username of a certain player.
     * 
     * @return a string which is the username of the player
     */
    String getUsername();

    /**
     * Get the current position of the player.
     * 
     * @return the position.
     */
    Position getCurrentPosition();

    /**
     * Move the player to a certain position.
     * 
     * @param coords - position where to move the player
     */
    void setPosition(Position coords);

    /**
     * Get the color of the player.
     * 
     * @return a string which is the color of the player
     */
    String getColor();

    /**
     * @return a boolean which indicates if a player is in a room.
     */
    boolean isInRoom();

    /**
     * @return a boolean which indicates if a player has won or not.
     */
    boolean hasWon();

}
