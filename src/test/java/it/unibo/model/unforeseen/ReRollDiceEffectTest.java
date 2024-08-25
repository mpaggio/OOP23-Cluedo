package it.unibo.model.unforeseen;

import org.junit.jupiter.api.Test;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.unforeseen.impl.ReRollDiceEffect;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the ReRollDiceEffect class.
 */
class ReRollDiceEffectTest {

    /**
     * Test the applyEffect method.
     */
    @Test
    void testApplyEffect() {
        final Player player = new MutablePlayerImpl("Player1", "color");
        final MutablePlayer mutablePlayer = (MutablePlayer) player;
        mutablePlayer.setDoubleRollDice(true);
        final ReRollDiceEffect effect = new ReRollDiceEffect();
        effect.applyEffect(player);
        assertTrue(player.canDoubleRollDice());
    }
}
