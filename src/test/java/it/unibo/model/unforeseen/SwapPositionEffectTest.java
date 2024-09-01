package it.unibo.model.unforeseen;

import it.unibo.cluedo.utilities.Position;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.unforeseen.impl.SwapPositionEffect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * This class tests the SwapPositionEffect class.
 */
class SwapPositionEffectTest {

    private static final int DEFAULT_COORD = 5;

    /**
     * This method tests the applyEffect method of the SwapPositionEffect class.
     */
    @Test
    void testApplyEffect() throws Exception {
        final Player player1 = new MutablePlayerImpl("Player1", "Red");
        final Player player2 = new MutablePlayerImpl("Player2", "Blue");
        final MutablePlayer mutablePlayer1 = (MutablePlayer) player1;
        final MutablePlayer mutablePlayer2 = (MutablePlayer) player2;

        mutablePlayer1.setPosition(new Position(0, 0));
        mutablePlayer2.setPosition(new Position(DEFAULT_COORD, DEFAULT_COORD));
        final SwapPositionEffect effect = new SwapPositionEffect();
        effect.setSwapPositionEffect(player2);
        effect.applyEffect(player1);
        assertEquals(new Position(DEFAULT_COORD, DEFAULT_COORD), player1.getCurrentPosition());
        assertEquals(new Position(0, 0), player2.getCurrentPosition());
    }
}
