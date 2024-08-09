package it.unibo.cluedo.model.turnmanager.impl;

import java.util.ArrayList;
import java.util.List;
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
        // players.get(currentPlayerIndex).setPosition(false);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        // players.get(currentPlayerIndex).setTurn(true);
        System.out.println("It's " + players.get(currentPlayerIndex).getUsername() + "'s turn");
    }

}
