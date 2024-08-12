package it.unibo.cluedo.model.player.impl;

import java.util.List;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;
import java.util.ArrayList;

/**
 * Implementation of the {@link Player} interface.
 * Represents a player in the game with a username and a color.
 */
public class PlayerImpl implements Player {

    private final String username;
    private final String color;
    private final Position currentPosition;
    private final boolean playerTurn;
    private final boolean hasWon;
    private final boolean inRoom;
    private final List<Card> playerCards;

    /**
     * Constructs a new player with the given username and color.
     *
     * @param username the username of the player
     * @param color the color representing the player
     */
    public PlayerImpl(final String username, final String color) {
        this.username = username;
        this.color = color;
        this.currentPosition = new Position(0, 0);
        this.playerTurn = false;
        this.inRoom = false;
        this.hasWon = false;
        this.playerCards = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayerTurn() {
        return this.playerTurn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInRoom() {
        return this.inRoom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasWon() {
        return this.hasWon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getPlayerCards() {
        return new ArrayList<>(this.playerCards);
    }
}
