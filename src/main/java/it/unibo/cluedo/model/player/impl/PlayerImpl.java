package it.unibo.cluedo.model.player.impl;

import java.util.List;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.dice.api.Dice;
import it.unibo.cluedo.model.dice.impl.DiceImpl;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.notebook.impl.NotebookImpl;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;
import java.util.ArrayList;

/**
 * Implementation of the {@link Player} interface.
 * Represents a player in the game with a username and a color.
 */
public class PlayerImpl implements Player {

    private static final int DICE_SIDES = 6;
    private final String username;
    private final String color;
    private Position currentPosition;
    private boolean playerTurn;
    private boolean hasWon;
    private boolean inRoom;
    private List<Card> playerCards;
    private boolean doubleRollDice;
    private boolean nextTurn;
    private final Notebook notebook;
    private final Dice dice;
    private int currentSteps;
    private boolean hasLost;
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
        this.notebook = new NotebookImpl();
        this.dice = new DiceImpl(DICE_SIDES);
        this.currentSteps = 0;
        this.hasLost = false;
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
     * Set the current position of the player.
     * @param position the new position of the player
     */
    protected void setCurrentPosition(final Position position) {
        this.currentPosition = position;
    }

    /**
     * Set whether it is currently this player's turn.
     * @param isTurn true if it is the player's turn, false otherwise
     */
    protected void setPlayerTurn(final boolean isTurn) {
        this.playerTurn = isTurn;
    }

    /**
     * Set whether the player has won the game.
     * @param hasWon true if the player has won, false otherwise
     */
    protected void setHasWon(final boolean hasWon) {
        this.hasWon = hasWon;
    }

    /**
     * Set whether the player is currently in a room.
     * @param inRoom true if the player is in a room, false otherwise
     */
    protected void setInRoom(final boolean inRoom) {
        this.inRoom = inRoom;
    }

    /**
     * Set whether the player can double roll the dice.
     * @param doubleRoll true if the player can double roll the dice, false otherwise
     */
    protected void setDoubleRollDice(final boolean doubleRoll) {
        this.doubleRollDice = doubleRoll;
    }

    /**
     * Set whether the player can take the next turn.
     * @param nextTurn true if the player can take the next turn, false otherwise
     */
    protected void setNextTurn(final boolean nextTurn) {
        this.nextTurn = nextTurn;
    }

    /**
     * Set the cards of the player.
     * @param cards the list of the cards to assign to the player
     */
    protected void setPlayerCards(final List<Card> cards) {
        this.playerCards = new ArrayList<>(cards);
        for (final Card card : this.playerCards) {
          this.notebook.logSeenCards(card.getName());
        }
    }

    /**
     * Set the current steps of the player.
     * @param steps the number of steps to set
     */
    protected void setCurrentSteps(final int steps) {
        this.currentSteps = steps;
    }

    /**
     * Set whether the player has lost.
     * @param hasLost true if the player lost, false otherwise
     */
    protected void setHasLost(final boolean hasLost) {
        this.hasLost = hasLost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Notebook getPlayerNotebook() {
        return this.notebook;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSteps() {
        int steps = this.dice.rollDice();
        if (this.doubleRollDice) {
            steps += this.dice.rollDice();
            this.doubleRollDice = false;
        }
        return steps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentSteps() {
        return this.currentSteps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasLost() {
        return this.hasLost;
    }
}
