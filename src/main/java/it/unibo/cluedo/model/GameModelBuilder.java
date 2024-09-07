package it.unibo.cluedo.model;

import java.util.Set;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.utilities.TurnFase;

/**
 * Interface that represents an application of the Builder pattern.
 * It is used to build a new instance of GameModel.
 * Example of usage:
 * <pre>{@code
 * //Example of creating a new game:
 * GameModelBuilder builder = new GameModelBuilderImpl();
 * GameModel model = builder.addPlayer("Alice", "Red")
 *                          .addPlayer("Bob", "Green")
 *                          .addPlayer("Charlie", "Blue")
 *                          .withGameSolution()
 *                          .build();
 * 
 * //Example of restoring a saved game:
 * GameModelBuilder savedGameModelBuilder = new GameModelBuilderImpl();
 * GameModel savedModel = savedGameModelBuilder.
 * }</pre>
 */
public interface GameModelBuilder {
    /**
     * The number of player to add.
     */
    int PLAYERS = 3;
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
     * Method to set te turn manager of the saved game.
     * @param turnManager the turn manage of the saved game
     * @return the builder itself.
     */
    GameModelBuilder withTurnManager(TurnManager turnManager);

    /**
     * Method to set the statistics of the saved game.
     * @param statistics the statistics of the saved match
     * @return the builder itself
     */
    GameModelBuilder withStatistics(Statistics statistics);

    /**
     * Method to set the map of the saved game.
     * @param map the board of the saved match
     * @return the builder itself
     */
    GameModelBuilder withMap(Board map);

    /**
     * Method to set the cards of the saved game.
     * @param cards the set containing all the cards of the game
     * @return the builder itseld
     */
    GameModelBuilder withAllCards(Set<Card> cards);

    /**
     * Method to set the fase of the saved game.
     * @param fase the current fase
     * @return the builder itself
     */
    GameModelBuilder withTurnFase(TurnFase fase);

    /**
     * Method to set the player of the saved game.
     * @param player the player that played the saved game
     * @return the Builder itself
     */
    GameModelBuilder withPlayer(Player player);

    /**
     * Method to set the saved solution of the game.
     * @param solution the saved game solutoin
     * @return the Builder itself
     */
    GameModelBuilder withSavedSolution(Set<Card> solution);

    /**
     * Method to build and return the GameModel.
     * This method should be called after all players and the game solution have been set
     * @return an istance of GameModel
     * @throws IllegalStateException if the required parameters have not been set
     */
    GameModel build();

    /**
     * Method to build and return a saved GameModel with additional properties.
     * The additional properties are TurnManager,Statistics and Board, whose are set
     * and not null
     * @return an istance of GameModel for a saved game
     * @throws IllegalStateException if the parameters have not been set
     */
    GameModel buildsaved();
}
