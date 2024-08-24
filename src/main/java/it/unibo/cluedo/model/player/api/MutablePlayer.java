package it.unibo.cluedo.model.player.api;

import java.util.List;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.utilities.Position;

/**
 * Interface that models a mutable player. Extends {@link Player} and add methods
 * that allow modifing the player's state.
 */
public interface MutablePlayer extends Player, Cloneable {

    /**
     * Move the player to a certain position.
     * @param coords - position where to move the player
     */
    void setPosition(Position coords);

    /**
     * Sets whether it is currently this player's turn.
     * @param isTurn a boolean indicating if it is the player's turn or not.
     */
    void setPlayerTurn(boolean isTurn);

    /**
     * Sets the player's victory status.
     * @param hasWon a boolean indicating if the player has won the game or not.
     */
    void setHasWon(boolean hasWon);

    /**
     * Sets whether the player is currently in a room.
     * @param inRoom a boolean indicating if the player is in a room or not.
     */
    void setInRoom(boolean inRoom);

    /**
     * Sets whether the player can double roll the dice.
     * @param doubleRoll a boolean indicating if the player can double roll the dice.
     */
    void setDoubleRollDice(boolean doubleRoll);

    /**
     * Sets wheter the player can take next turn.
     * @param nextTurn a boolean indicating if the player can take next turn.
     */
    void setNextTurn(boolean nextTurn);

    /**
     * Sets the player's cards.
     * @param cards the list of cards to assign to the player.
     */
    void setPlayerCards(List<Card> cards);

    /**
     * Sets the current steps of the player.
     * @param steps the number of steps to set.
     */
    void setCurrentSteps(int steps);
    /**
     * Creates and returns a copy of this player.
     *
     * @return a clone of this instance.
     */
    MutablePlayer clone();
}
