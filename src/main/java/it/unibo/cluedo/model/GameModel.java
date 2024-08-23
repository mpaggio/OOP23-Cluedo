package it.unibo.cluedo.model;

import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Pair;

import java.util.List;

/**
 * Interface to represent the model of the Cluedo game.
 */
public interface GameModel {
    /**
     * Method to roll the dice.
     * @return the result of the dice roll.
     */
    double rollDice();
    /**
     * Move the player to the given position.
     * @param position the position to move the player to.
     */
    void movePlayer(Square position);
    /**
     * Methof to apply the effect of the square the player landed on.
     */
    void applyEffect();
    /**
     * method to make an accusation.
     * @return true if the accusation is correct, false otherwise.
     */
    boolean makeAccusation();
    /**
     * Method to get the notebook of the current player.
     * @return the notebook of the current player.
     */
    Notebook getNotebook();
    /**
     * Method to end the turn of the current player.
     */
    void endTurn();
    /**
     * Method to establish if the game is over.
     * @return true if the game is over, false otherwise.
     */
    boolean isOver();
    /**
     * Method to get the players of the game.
     * @return the list of players of the game.
     */
    List<Player> getPlayers();
    /**
     * Method to get the current player.
     * @return the current player.
     */
    Player getCurrentPlayer();
    /**
     * Method to get the effects of the square at the given position.
     * @param position the position of the square.
     * @return the effects of the square.
     */
    Pair<Square, Effect> getSquareEffects(Square position);

}
