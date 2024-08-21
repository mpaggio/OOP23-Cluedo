package it.unibo.cluedo.model.player.impl;

import java.util.List;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.notebook.api.Notebook;
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
    private Position currentPosition;
    private boolean playerTurn;
    private boolean hasWon;
    private boolean inRoom;
    private final List<Card> playerCards;
    private boolean doubleRollDice;
    private boolean nextTurn;
    //private Notebook notebook;

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
        this.doubleRollDice = false;
        this.nextTurn = true;
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

    /**
     * Sets the current position of the player.
     * @param position the new position of the player
     */
    protected void setCurrentPosition(final Position position) {
        this.currentPosition = position;
    }

    /**
     * Sets whether it is currently this player's turn.
     * @param isTurn true if it is the player's turn, false otherwise
     */
    protected void setPlayerTurn(final boolean isTurn) {
        this.playerTurn = isTurn;
    }

    /**
     * Sets whether the player has won the game.
     * @param hasWon true if the player has won, false otherwise
     */
    protected void setHasWon(final boolean hasWon) {
        this.hasWon = hasWon;
    }

    /**
     * Sets whether the player is currently in a room.
     * @param inRoom true if the player is in a room, false otherwise
     */
    protected void setInRoom(final boolean inRoom) {
        this.inRoom = inRoom;
    }

    /**
     * Sets whether the player can double roll the dice.
     * @param doubleRoll true if the player can double roll the dice, false otherwise
     */
    protected void setDoubleRollDice(final boolean doubleRoll) {
        this.doubleRollDice = doubleRoll;
    }

    /**
     * Sets whether the player can take the next turn.
     * @param nextTurn true if the player can take the next turn, false otherwise
     */
    protected void setNextTurn(final boolean nextTurn) {
        this.nextTurn = nextTurn;
    }

    /**
     * Gets the notebook of the player.
     */
    @Override
    public Notebook getPlayerNotebook() {
        return null;
        //return this.notebook;
    }
}
