package it.unibo.cluedo.model.player.api;

/**
 * The {@code SimplePlayerFactory} interface defines the method for creating a new player instances.
 * It follows the Factory Design Pattern, which helps in encapsulating the instantiation logic of players.
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
