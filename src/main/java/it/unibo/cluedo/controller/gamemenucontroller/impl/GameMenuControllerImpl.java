package it.unibo.cluedo.controller.gamemenucontroller.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;
import it.unibo.cluedo.controller.gamesavecontroller.impl.GameSaveControllerImpl;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.PlayerImpl;
import java.awt.Window;

/**
 * This class is used to manage the game menu.
 */
public class GameMenuControllerImpl implements GameMenuController {

    private static final int NUMBER_OF_PLAYERS = 3;
    private static final int LIMIT = 20;
    private final List<Player> players;
    private final List<String> availableColors;

    /**
     * This constructor is used to create a new GameMenuControllerImpl.
     */
    public GameMenuControllerImpl() {
        this.players = new ArrayList<>();
        this.availableColors = new ArrayList<>();
    }

    /**
     * This method is used to start the game.
     *
     * @param playerUsernames the list of the player usernames
     * @param playerColors    the list of the player colors
     * @return true if the game is started, false otherwise
     */
    @Override
    public boolean startGame(final List<String> playerUsernames, final List<String> playerColors) {
        if (playerUsernames == null || playerUsernames.size() != NUMBER_OF_PLAYERS || playerColors == null
                || playerColors.size() != NUMBER_OF_PLAYERS) {
            return false;
        }
        for (final String username : playerUsernames) {
            if (username.length() > LIMIT) {
                return false;
            }
        }
        final Set<String> uniqueNames = playerUsernames.stream()
                .filter(name -> name != null && !name.trim().isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        final Set<String> uniqueColors = new HashSet<>(playerColors);
        return uniqueNames.size() == NUMBER_OF_PLAYERS && uniqueColors.size() == NUMBER_OF_PLAYERS;
    }

    /**
     * This method is used to set the players.
     *
     * @param playerUsernames the list of the player usernames
     * @param playerColors    the list of the player colors
     */
    @Override
    public void setPlayer(final List<String> playerUsernames, final List<String> playerColors) {
        if (playerUsernames != null && playerColors != null && playerUsernames.size() == NUMBER_OF_PLAYERS
                && playerColors.size() == NUMBER_OF_PLAYERS) {
            this.players.clear();
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (playerUsernames.get(i).length() > LIMIT) {
                    throw new IllegalArgumentException("The username is too long");
                }
                final Player player = new PlayerImpl(playerUsernames.get(i), playerColors.get(i));
                this.players.add(player);
            }
        }
    }

    /**
     * This method is used to get the players.
     *
     * @return the list of the players
     */
    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(this.players);
    }

    /**
     * This method is used to get the available colors.
     *
     * @return the list of the available colors
     */
    @Override
    public List<String> getAvailableColors() {
        return new ArrayList<>(this.availableColors);
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

    /**
     * This method is used to view the saved games.
     *
     */
    @Override
    public void viewSavedGames() {
        final Optional<GameSaveControllerImpl.GameState> savedGame = Cluedo.CONTROLLER.getGameSaveController()
                .loadGame();
        if (savedGame.isPresent()) {
            final GameSaveControllerImpl.GameState gameState = savedGame.get();
            final List<String> playerNames = gameState.getPlayers().stream()
                    .map(Player::getUsername).collect(Collectors.toList());
            final List<String> playerColors = gameState.getPlayers().stream().map(Player::getColor)
                    .collect(Collectors.toList());
            System.out.println(playerNames.isEmpty());
            System.out.println(playerColors.isEmpty());
            Cluedo.CONTROLLER.initializeSavedGameModel(gameState.getPlayers(), gameState.getSolution(),
                    gameState.getTurnManager(), gameState.getStatistics(), gameState.getMap(), gameState.getAllCards(), gameState.getTurnFase());
            Cluedo.CONTROLLER.displayMainFrame();
        }
    }
}
