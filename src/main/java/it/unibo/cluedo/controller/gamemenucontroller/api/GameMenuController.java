package it.unibo.cluedo.controller.gamemenucontroller.api;

import java.util.List;
import java.awt.Color;
import it.unibo.cluedo.model.player.api.Player;

/**
 * This interface is used to manage the game menu.
 */
public interface GameMenuController {

    /**
     * This method is used to start the game.
     * @param playerNames the list of the player names
     * @param playerColors the list of the player colors
     * @return true if the game is started, false otherwise
     */
    boolean startGame(List<String> playerNames, List<Color> playerColors);

    /**
     * This method is used to set the players.
     * @param playerNames the list of the player names
     * @param playerColors the list of the player colors
     */
    void setPlayer(List<String> playerNames, List<Color> playerColors);

    /**
     * This method is used to get the players.
     * @return the list of the players
     */
    List<Player> getPlayers();

    /**
     * This method is used to get the available colors.
     * @return the list of the available colors
     */
    List<Color> getAvailableColors();

    /**
     * This method is used to quit the game.
     */
    void quitGame();
}
