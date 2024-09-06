package it.unibo.cluedo.controller.gamesavecontroller.impl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.io.File;
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.board.api.Board;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;


/**
 * Class that implements the GameSaveManager interface.
 */
public final class GameSaveControllerImpl implements GameSaveController {

    private static final String SAVE_FILE_PATH = "saved_game.ser";
    private List<Player> players = new ArrayList<>();
    private Board map = null;
    private int currentPlayerIndex;

    /**
     * Save the current game state in a file.
     *
     */
    @Override
    public void saveGame(final GameState gameState) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_PATH))) {
            out.writeObject(gameState);
        } catch (IOException e) {
            Logger.getLogger(GameSaveControllerImpl.class.getName()).log(Level.SEVERE, "Error while saving the game", e);
        }
    }

    /**
     * Get the output of the saved games.
     *
     * @return an optional containing the string representing the saved games.
     */
    @Override
    public Optional<String> getOutputSavedGames() {
        final File saveFile = new File(SAVE_FILE_PATH);
        if (saveFile.exists()) {
            return Optional.of("A saved game is available");
        }
        return Optional.empty();
    }

    /**
     * Load the game from the file.
     *
     * @return an optional containing the game state.
     */
    @Override
    public Optional<GameState> loadGame() {
        final File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.exists()) {
            Logger.getLogger(GameSaveControllerImpl.class.getName()).log(Level.INFO, "No saved games found");
            return Optional.empty();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE_PATH))) {
            final GameState gameState = (GameState) in.readObject();
            return Optional.of(gameState);
        } catch (ClassNotFoundException | IOException e) {
            Logger.getLogger(GameSaveControllerImpl.class.getName()).log(Level.SEVERE, "Error while loading the game", e);
            return Optional.empty();
        }
    }

    /**
     * Get the current game state.
     *
     * @return the current game state.
     */
    @Override
    public GameState getCurrentGameState() {
        return new GameState(players, map, currentPlayerIndex);
    }

    /**
     * Class representing the game state.
     */
    public static class GameState implements Serializable {
        private final List<Player> players;
        private final Board map;
        private final int currentPlayerIndex;
        private static final long serialVersionUID = 1L;

        /**
         * Constructs a new GameState object.
         *
         * @param players the list of players.
         * @param map the map of the game.
         * @param currentPlayerIndex the index of the current player.
         */
        public GameState(final List<Player> players, final Board map, final int currentPlayerIndex) {
            this.players = players != null ? players : new ArrayList<>();
            this.map = map;
            this.currentPlayerIndex = currentPlayerIndex;
        }

        /**
         * Get the list of players.
         * @return the list of players.
         */
        public List<Player> getPlayers() {
            return new ArrayList<>(players);
        }

        /**
         * Get the map of the game.
         * @return the map of the game.
         */
        public Board getMap() {
            return map;
        }

        /**
         * Get the index of the current player.
         * @return the index of the current player.
         */
        public int getCurrentPlayerIndex() {
            return currentPlayerIndex;
        }
    }
}
