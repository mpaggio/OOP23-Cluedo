package it.unibo.model.square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.room.api.MapComponentVisitor;
import it.unibo.cluedo.model.room.impl.MapComponentVisitorImpl;
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
    private MapComponentVisitor visitor;
 
    /**
     * Initialization before every test.
     */
    @BeforeEach
    void setUp() {
        this.position = new Position(0, 0);
        this.player = new MutablePlayerImpl("Player", "Purple");
        this.visitor = new MapComponentVisitorImpl();
    }

    /**
     * Test the effect of the bonus square.
     */
    @Test
    void testBonusSquare() {
        final LinkedList<Square> visitedSquare = new LinkedList<>();
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
        this.square.accept(visitor);
        visitedSquare.addAll(visitor.getVisitedSquare());
        assertEquals(visitedSquare.getLast(), this.square);
    }

    /**
     * Test the effect of the malus square.
     */
    @Test
    void testMalusSquare() {
        final LinkedList<Square> visitedSquare = new LinkedList<>();
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
        this.square.accept(visitor);
        visitedSquare.addAll(visitor.getVisitedSquare());
        assertEquals(visitedSquare.getLast(), this.square);
    }

    /**
     * Test the effect of the malus square.
     */
    @Test
    void testNormalSquare() {
        final LinkedList<Square> visitedSquare = new LinkedList<>();
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
        this.square.accept(visitor);
        visitedSquare.addAll(visitor.getVisitedSquare());
        assertEquals(visitedSquare.getLast(), this.square);
    }
}
