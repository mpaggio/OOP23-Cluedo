package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.PlayerBuilderImpl;

/**
 * Test class for a {@link PlayerBuilderImpl} class.
 */
final class PlayerBuilderImplTest {

    /**
     * Check if the player is created correctly.
     */
    @Test
    void testBuildPlayer() {
        final Player player = new PlayerBuilderImpl().username("username").color("color").buildPlayer();
        assertNotNull(player);
        assertEquals("username", player.getUsername());
        assertEquals("color", player.getColor());
    }

    /**
     * Check that building a player without not enough information throws an exception or fails.
     */
    @Test
    void testBuildPlayerWithoutEnoughInformation() {
        final String username = "username";
        final PlayerBuilderImpl playerBuilder = new PlayerBuilderImpl();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    playerBuilder.username(username)
                            .buildPlayer();
                });
    }

}
