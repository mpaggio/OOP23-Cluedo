package it.unibo.cluedo.model;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.movement.api.MovementStrategy;

import java.util.List;
import java.util.Optional;

/**
 * Interface to represent the model of the Cluedo game.
 */
public interface GameModel {
    /**
     * Method to use the trapdoor.
     */
    void useTrapdoor();
    /**
     * Method to draw an unforseen cards.
     */
    void drawUnforeseen();
    /**
     * Method to roll the dice.
     * @return the result of the dice roll.
     */
    int rollDice();
    /**
     * Move the player to the given position.
     * @param position the position to move the player to.
     * @param direction the direction to move the player in.
     */
    void movePlayer(Square position, MovementStrategy.Direction direction);
    /**
     * method to make an accusation.
     * @param weapon the weapon of the accusation.
     * @param room the room of the accusation.
     * @param character the character of the accusation.
     * @param roomPosition the room the player is in.
     * @return true if the accusation is correct, false otherwise.
     */
    Optional<Card> makeAccusation(Card weapon, Card room, Card character, Room roomPosition);
    /**
     * Method to make the final accusation.
     * @param weapon the weapon of the accusation.
     * @param room the room of the accusation.
     * @param character the character of the accusation.
     * @param roomPosition the room the player is in.
     * @return true if the accusation is correct, false otherwise.
     */
    boolean makeFinalAccusation(Card weapon, Card room, Card character, Room roomPosition);
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
    Effect getSquareEffects(Square position);

}
