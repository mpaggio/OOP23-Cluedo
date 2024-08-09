package it.unibo.cluedo.model.player.api;

/**
 * The {@code PlayerBuilder} interface defines the methods to create a new player.
 * It applies the creation pattern Builder
 */
public interface PlayerBuilder {
    /**
     * Sets the username of the player.
     * @param username of the player
     * @return current builder
     */
    PlayerBuilder username(String username);

    /**
     * Sets the color of the player.
     * @param color chosen by the player
     * @return current builder
     */
    PlayerBuilder color(String color);

    /**
     * Create the player instance with the specified parameters.
     * @return a new {@link Player} instance
     * @throws IllegalStateException if there are not enough information
     */
    Player buildPlayer();

}
