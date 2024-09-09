package it.unibo.cluedo.model.player.api;

/**
 * The {@code SimplePlayerFactory} interface defines the methods to create a new player.
 * It applies the creational pattern Factory
 */
public interface SimplePlayerFactory {
    
    /**
     * This method is used to creat a new instance of a Player.
     * @param username the String representing the username of the player
     * @param color the String representing the color of the player
     * @return a new instance of a player 
     */
    Player createPlayer(String username, String color);
}
