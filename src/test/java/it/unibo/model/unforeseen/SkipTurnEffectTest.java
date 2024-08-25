package it.unibo.model.unforeseen;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.unforeseen.impl.SkipTurnEffect;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * This class tests the SkipTurnEffect class.
 */
class SkipTurnEffectTest {

    /**
     * This method tests the applyEffect method of the SkipTurnEffect class.
     */
    @Test
    void testApplyEffect() {
        final Player player = new MutablePlayerImpl("Player1", "Red");
        final SkipTurnEffect effect = new SkipTurnEffect();
        effect.applyEffect(player);
        assertFalse(player.canNextTurn());
    }
}
