package it.unibo.model.player;

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

    /**
     * Check that building a player without a username throws an exception or fails.
     */
    @Test
    void testBuildPlayerWithoutUsername() {
        final String color = "color";
        final PlayerBuilderImpl playerBuilder = new PlayerBuilderImpl();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    playerBuilder.color(color)
                            .buildPlayer();
                });
    }

    /**
     * Check that building a player without a color throws an exception or fails.
     */
    @Test
    void testBuildPlayerWithoutColor() {
        final String username = "user";
        final PlayerBuilderImpl playerBuilder = new PlayerBuilderImpl();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    playerBuilder.username(username)
                            .buildPlayer();
                });
    }

    /**
     * Check that modifying a builder works correctly.
     */
    @Test
    void testModifyBuilder() {
        final Player player = new PlayerBuilderImpl()
                .username("oldUsername")
                .color("oldColor")
                .username("newUsername")
                .color("newColor")
                .buildPlayer();
        assertNotNull(player);
        assertEquals("newUsername", player.getUsername());
        assertEquals("newColor", player.getColor());
    }

}
