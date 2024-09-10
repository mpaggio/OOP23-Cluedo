package it.unibo.cluedo.model;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;
import it.unibo.cluedo.utilities.TurnFase;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.board.api.Board;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Interface that represents the model of the Cluedo game.
 */
public interface GameModel {
    /**
     * Method to use a trapdoor if the player is in a room with one in it.
     * It moves the player to the room connected to room and sets the turn
     * fase to {@code TurnFase.MAKE_ACCUSATION}.
     * @param room the room to use the trapdoor in.
     */
    void useTrapdoor(Room room);
    /**
     * Method to draw an {@link UnforeseenEffect}. This method is called at the beginning of
     * every turn after rolling the dice. After drawing the card, the effect is
     * applied to the player and the {@link TurnFase} is set depending on the effect.
     * @return the unforseen card drawn.
     */
    UnforeseenEffect drawUnforeseen();
    /**
     * Method to roll the {@link Dice}. This method is called at the beginning of every turn
     * or in case something has happened that requires the player to roll the dice
     * another time.
     * @return the result of the dice roll or 0 in case the dice can't be rolled.
     */
    int rollDice();
    /**
     * Move the {@link Player} to the {@link Position} he wants to move in.
     * This method also checks if the player has entered the room or has finished
     * the steps he had. In this cases the {@link TurnFase} will be setted to the next fase
     * dependending on the situation. Also if the player steps on a {@link Square} with an effect,
     * the effect will be applied.
     * @param direction the direction to move the player in.
     */
    void movePlayer(MovementStrategy.Direction direction);
    /**
     * Method to make an {@link Accusation} hypothesis. This method can be called only if
     * the player is in a {@link Room}. After the accusation is made, the {@link TurnFase}
     * and the {@link Player}'s {@link Notebook} will be updated.
     * @param weapon the weapon of the accusation.
     * @param room the room of the accusation.
     * @param character the character of the accusation.
     * @param roomPosition the room the player is in.
     * @return an {@link Optional} containing the {@link Card} found,
     * empty otherwise.
     */
    Optional<Card> makeAccusation(Card weapon, Card room, Card character, Room roomPosition);
    /**
     * Method to make the final {@link Accusation}. This method can be called only if
     * the {@link Player} is in the Cluedo {@link Room}. After the accusation is made, if it's correct
     * the player has won the game, otherwise he has lost and he will be removed from the game.
     * @param weapon the weapon of the accusation.
     * @param room the room of the accusation.
     * @param character the character of the accusation.
     * @param roomPosition the room the player is in.
     * @return true if the accusation is correct, false otherwise.
     */
    boolean makeFinalAccusation(Card weapon, Card room, Card character, Room roomPosition);
    /**
     * Method to get the {@link Notebook} of the current {@link Player}.
     * @return the notebook of the current player.
     */
    Notebook getNotebook();
    /**
     * Method to get the {@link Statistics} of the game.
     * @return the statistics of the game.
     */
    Statistics getStatistics();
    /**
     * Method to get the current {@link TurnFase}.
     * @return the current turn fase.
     */
    TurnFase getTurnFase();
    /**
     * Method to get the result of the {@link Dice} roll.
     * @return the result of the dice roll.
     */
    int getDiceResult();
    /**
     * Method to end the turn of the current {@link Player}.
     * @return the next player.
     */
    Player endTurn();
    /**
     * Method to establish if the game is over.
     * @return true if the game is over, false otherwise.
     */
    boolean isOver();
    /**
     * Method to get the {@link Player} {@link List} of the game.
     * @return the list of players of the game.
     */
    List<Player> getPlayers();
    /**
     * Method to get the current {@link Player}.
     * @return the current player.
     */
    Player getCurrentPlayer();
    /**
     * Method to get the {@link Effect} of the {@link Square} at the given 
     * {@link Position}.
     * @param position the position of the square.
     * @return the effect of the square.
     */
    Effect getSquareEffects(Square position);
    /**
     * Method to get the solution of the game.
     * @return a {@link Card} {@link Set} containing the solution of the game.
     */
    Set<Card> getSolution();
    /**
     * Method to get the {@link Board} of the game.
     * @return the map of the game.
     */
    Board getMap();
    /**
     * Method to get the a {@link Card} {@link Set} of the game.
     * @return the cards of the game.
     */
    Set<Card> getAllCards();
    /**
     * Method to get the {@link Square} where {@link Player} is on.
     * @return the square of the player.
     */
    Square getSquare();
    /**
     * Method to get the {@link TurnManager} of the game.
     * @return the turn manager of the game.
     */
    TurnManager getTurnManager();
}
