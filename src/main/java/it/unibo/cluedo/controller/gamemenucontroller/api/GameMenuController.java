package it.unibo.cluedo.controller.gamemenucontroller.api;

/**
 * This interface is used to manage the game menu.
 */
public interface GameMenuController {

    /**
     * Initialize the game menu.
     */
    void initialize();

    /**
     * Set the number of players.
     * @param numberOfPlayers the number of players.
     */
    void setNumberOfPlayers(int numberOfPlayers);

    /**
     * Add a player to the game.
     * @param playerName the name of the player.
     */
    void addPlayer(String playerName);

    /**
     * Start the game.
     */
    void startGame();
}
