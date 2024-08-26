package it.unibo.cluedo.model;

import java.io.IOException;

/**
 * Interface used to save and load the game.
 */
public interface GameSaveManager {

    /**
     * Save the current game state in a file.
     * @param fileName the name of the file where the game state will be saved.
     * @throws IOException if an I/O error occurs.
     */
    void saveGame(String fileName) throws IOException;

    /**
     * Load a game state from a file.
     * @param fileName the name of the file where the game state is saved.
     * @throws IOException if an I/O error occurs.
     */
    void loadGame(String fileName) throws IOException;
}
