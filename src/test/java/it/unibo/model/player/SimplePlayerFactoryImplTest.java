package it.unibo.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.SimplePlayerFactory;
import it.unibo.cluedo.model.player.impl.SimplePlayerFactoryImpl;

/**
 * Test class for {@link SimplePlayerFactoryImpl}.
 */
final class SimplePlayerFactoryImplTest {
    private SimplePlayerFactory simplePlayerFactory;

    /**
     * This is done before each test method.
     */
    @BeforeEach
    void setUp() {
        this.simplePlayerFactory = new SimplePlayerFactoryImpl();
    }

    /**
     * Tests that a Player instance is correctly created with the specified username and color.
     */
    @Test
    void testCreatePlayer() {
        final String username = "John";
        final String color = "Red";
        final Player player = simplePlayerFactory.createPlayer(username, color);
        assertEquals(username, player.getUsername());
        assertEquals(color, player.getColor());
    }
}
