package it.unibo.cluedo.model.player.impl;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.utilities.Position;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of the {@link MutablePlayer} interface.
 * Represents a mutable player in the game with a username and a color,
 * and allows modification of the player's state.
 */
public class MutablePlayerImpl extends PlayerImpl implements MutablePlayer {

    private final String username;
    private final String color;
    private Position currentPosition;
    private boolean playerTurn;
    private boolean hasWon;
    private boolean inRoom;
    private final List<Card> playerCards;
    private boolean doubleRollDice;
    private boolean nextTurn;

    /**
     * Construct a new mutable player with the given username and color.
     *
     * @param username
     * @param color
     */
    public MutablePlayerImpl(final String username, final String color) {
        super(username, color);
        this.username = username;
        this.color = color;
        this.currentPosition = new Position(0, 0);
        this.playerTurn = false;
        this.inRoom = false;
        this.hasWon = false;
        this.playerCards = new ArrayList<>();
        this.doubleRollDice = false;
        this.nextTurn = false;
    }

    /**
     * Move the player to the given position.
     * @param coords the new position of the player
     */
    @Override
    public void setPosition(final Position coords) {
       this.currentPosition = coords;
    }

    /**
     * Set the player's turn.
     * @param isTurn true if it's the player's turn, false otherwise
     */
    @Override
    public void setPlayerTurn(final boolean isTurn) {
        this.playerTurn = isTurn;
    }

    /**
     * Set if the player has won the game.
     * @param hasWon true if the player has won, false otherwise
     */
    @Override
    public void setHasWon(final boolean hasWon) {
        this.hasWon = hasWon;
    }

    /**
     * Set if the player is in a room.
     * @param inRoom true if the player is in a room, false otherwise
     */
    @Override
    public void setInRoom(final boolean inRoom) {
        this.inRoom = inRoom;
    }

    /**
     * Set if the player can roll the dice again.
     * @param doubleRoll true if the player can roll the dice again, false otherwise
     */
    @Override
    public void setDoubleRollDice(final boolean doubleRoll) {
        this.doubleRollDice = doubleRoll;
    }

    /**
     * Set if the player can take next turn.
     * @param nextTurn true if the player can take next turn, false otherwise
     */
    @Override
    public void setNextTurn(final boolean nextTurn) {
        this.nextTurn = nextTurn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutablePlayerImpl clone() {
        try {
            return (MutablePlayerImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDoubleRollDice() {
        return this.doubleRollDice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canNextTurn() {
        return this.nextTurn;
    }

}
