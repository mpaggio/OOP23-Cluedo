package it.unibo.cluedo.controller.gamemenucontroller.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Set;

import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.PlayerImpl;
import java.awt.Window;

/**
 * This class is used to manage the game menu.
 */
public class GameMenuControllerImpl implements GameMenuController {

    private static final int NUMBER_OF_PLAYERS = 3;
    private final List<Player> players;

    /**
     * This constructor is used to create a new GameMenuControllerImpl.
     */
    public GameMenuControllerImpl() {
        this.players = new ArrayList<>();
    }

    /**
     * This method is used to start the game.
     * @param playerNames the list of the player names
     * @param playerColors the list of the player colors
     * @return true if the game is started, false otherwise
     */
    @Override
    public boolean startGame(final List<String> playerNames, final List<String> playerColors) {
        if (playerNames == null || playerNames.size() != NUMBER_OF_PLAYERS || playerColors == null
            || playerColors.size() != NUMBER_OF_PLAYERS) {
                return false;
        }
        final Set<String> uniqueNames = playerNames.stream()
                          .filter(name -> name != null && !name.trim().isEmpty())
                          .map(String::toLowerCase)
                          .collect(Collectors.toSet());
        if (uniqueNames.size() != NUMBER_OF_PLAYERS) {
            return false;
        }
        return uniqueNames.size() == playerNames.size();
    }

    /**
     * This method is used to set the players.
     * @param playerNames the list of the player names
     * @param playerColors the list of the player colors
     */
    @Override
    public void setPlayer(final List<String> playerNames, final List<String> playerColors) {
        if (playerNames != null && playerColors != null && playerNames.size() == NUMBER_OF_PLAYERS
            && playerColors.size() == NUMBER_OF_PLAYERS) {
            this.players.clear();
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                final Player player = new PlayerImpl(playerNames.get(i), playerColors.get(i));
                this.players.add(player);
            }
        }
    }

    /**
     * This method is used to get the players.
     * @return the list of the players
     */
    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(this.players);
    }

    /**
     * This method is used to quit the game.
     */
    @Override
    public void quitGame() {
        final Window[] windows = Window.getWindows();
        for (final Window window : windows) {
            window.dispose();
        }
    }

}
