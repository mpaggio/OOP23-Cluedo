package it.unibo.cluedo.model;

/**
 * Interface that represents an application of the Builder pattern.
 * It is used to build a new instance of GameModel.
 * Example of usage:
 * <pre>{@code
 * GameModelBuilder builder = new GameModelBuilderImpl(Deck deck);
 * GameModel model = builder.addPlayer("Alice", "Red")
 *                          .addPlayer("Bob", "Green")
 *                          .addPlayer("Charlie", "Blue")
 *                          .withGameSolution()
 *                          .build();
 * }</pre>
 */
public interface GameModelBuilder {
    /**
     * The maximum number of players that can be added to the game.
     */
    int MAX_PLAYERS = 3;
    /**
     * Method to add a player to the game.
     * It is not allowed to add a player with a nickname or color
     * that is already used by another player, or to add more than MAX_PLAYERS players.
     * 
     * @param username the nickname of the player.
     * @param color the color of the player.
     * @return the builder itself.
     * @throws IllegalArgumentException if there is already another player with the same nickname or color,
     * or if the maximum number of players has already been reached.
     */
    GameModelBuilder addPlayer(String username, String color);

    /**
     * Method to set the solution of the game.
     * @return the builder itself.
     */
    GameModelBuilder withGameSolution();

    /**
     * Method to build and return the GameModel.
     * This method should be called after all players and the game solution have been set
     * @return an istance of GameModel.
     * @throws IllegalStateException if the required parameters have not been set.
     */
    GameModel build(); 
}
