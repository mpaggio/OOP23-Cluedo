package it.unibo.model.square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.square.impl.SquareFactory;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Test class for SquareImpl, SquareFactory and Effect implementations.
 */
final class SquareTest {
    private Player player;
    private Position position;
    private Square square;
 
    /**
     * Initialization before every test.
     */
    @BeforeEach
    void setUp() {
        this.position = new Position(0, 0);
        this.player = new MutablePlayerImpl("Player", "Purple");
    }

    /**
     * Test the effect of the bonus square.
     */
    @Test
    void testBonusSquare() {
        this.square = SquareFactory.createBonusSquare(this.position);
        assertEquals(this.square.getPosition(), this.position);
        assertFalse(this.player.canDoubleRollDice());
        assertTrue(this.player.canNextTurn());
        this.square.landOn(this.player);
        assertTrue(this.player.canDoubleRollDice());
        assertTrue(this.player.canNextTurn());
        this.square.landOn(this.player);
        assertTrue(this.player.canDoubleRollDice());
        assertTrue(this.player.canNextTurn());
    }

    /**
     * Test the effect of the malus square.
     */
    @Test
    void testMalusSquare() {
        this.square = SquareFactory.createMalusSquare(this.position);
        assertEquals(this.square.getPosition(), this.position);
        assertFalse(this.player.canDoubleRollDice());
        assertTrue(this.player.canNextTurn());
        this.square.landOn(this.player);
        assertFalse(this.player.canDoubleRollDice());
        assertFalse(this.player.canNextTurn());
        this.square.landOn(this.player);
        assertFalse(this.player.canDoubleRollDice());
        assertFalse(this.player.canNextTurn());
    }

    /**
     * Test the effect of the malus square.
     */
    @Test
    void testNormalSquare() {
        this.square = SquareFactory.createNormalSquare(this.position);
        assertEquals(this.square.getPosition(), this.position);
        assertFalse(this.player.canDoubleRollDice());
        assertTrue(this.player.canNextTurn());
        this.square.landOn(this.player);
        assertFalse(this.player.canDoubleRollDice());
        assertTrue(this.player.canNextTurn());
        this.square.landOn(this.player);
        assertFalse(this.player.canDoubleRollDice());
        assertTrue(this.player.canNextTurn());
    }

    @Test
    void testPlayerPresence() {
        this.square = SquareFactory.createNormalSquare(position);
        assertFalse(this.square.isAlreadyOccupied());
        this.square.setPlayer(player);
        this.square.landOn(player);
        assertTrue(this.square.isAlreadyOccupied());
        this.square.removePlayer();
        assertFalse(this.square.isAlreadyOccupied());
    }
}
