package it.unibo.cluedo.model.turnmanager.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;

/**
 * Class that implements the TurnManager interface, it has a constructor that
 * takes the
 * list of players as parameter.
 */

public class TurnManagerImpl implements TurnManager {
    private final List<Player> players;
    private int currentPlayerIndex;
    private boolean gameFinished;

    /**
     * Constructor of the class.
     *
     * @param players
     */
    public TurnManagerImpl(final List<Player> players) {
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = 0;
    }

    /**
     * Get the current player.
     *
     * @return the current player.
     */
    @Override
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Advances the turn to the next player.
     */
    @Override
    public void switchTurn() {
        if (gameFinished) {
            return;
        }
        MutablePlayer currentPlayer = (MutablePlayer) players.get(currentPlayerIndex);
        currentPlayer.setPlayerTurn(false);
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } while (players.get(currentPlayerIndex).hasWon());
        currentPlayer = (MutablePlayer) players.get(currentPlayerIndex);
        currentPlayer.setPlayerTurn(true);
        if (checkGameEndCondition()) {
            gameFinished = true;
        }
    }

    /**
     * Check if the game has ended.
     *
     * @return a boolean indicating if the game has ended or not.
     */
    private boolean checkGameEndCondition() {
        for (final Player player : players) {
            if (!player.hasWon()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the game has ended.
     * 
     * @return a boolean indicating if the game has ended or not.
     */
    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Get the current notebook.
     * 
     * @return the current notebook.
     */
    @Override
    public Notebook getCurrentNotebook() {
        return null;
        // return players.get(currentPlayerIndex).getNotebook();
    }
}
