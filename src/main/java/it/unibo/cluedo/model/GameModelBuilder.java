package it.unibo.cluedo.model;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an application of the Builder pattern.
 * It is used to build a new instance of GameModel.
 * Example of usage:
 * <pre>{@code
 * PlayerBuilder builder1 = new PlayerBuilderImpl();
 * GameModelBuilder builder2 = new GameModelBuilder();
 * GameModel model = builder.addPlayer(builder1.username("John").color("Red").buildPlayer())
 *                          .addPlayer(builder1.username("Nick").color("Blue").buildPlayer())
 *                          .setSolution("Candlestick", "Kitchen", "MrGreen")
 *                          .build;
 * }
 */
public interface GameModelBuilder {
        
    /**
     * The maximum number of players that can be added to the game.
     */
    static final int MAX_PLAYERS = 3;

    /**
     * Method to add a player to the game.
     * It is not allowed to add a player with a nickname or color
     * that is already used by another player, or to add more than MAX_PLAYERS players.
     * @param player the {@link Player} instance to add to the game.
     * @return the builder itself.
     * @throws IllegalArgumentException if there is already another player with the same nickname or color,
     * or if the maximum number of players has already been reached.
     */
    GameModelBuilder addPlayer(Player player);

    
}
