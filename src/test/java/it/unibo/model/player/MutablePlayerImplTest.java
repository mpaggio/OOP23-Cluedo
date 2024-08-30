package it.unibo.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;

/**
 * Test class for a {@link MutablePlayerImpl} class.
 */
final class MutablePlayerImplTest {

    private MutablePlayerImpl player;
    private static final String PLAYER_1 = "Player1";
    private static final String COLOR_RED = "Red";
    
    @BeforeEach
    void setUp() {
        player = new MutablePlayerImpl(PLAYER_1, COLOR_RED);
    }

    @Test
    void testInitialValues() {
        assertEquals(PLAYER_1, player.getUsername());
        assertEquals(COLOR_RED, player.getColor());

    }
}
