package it.unibo.model.unforeseen;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.unforeseen.impl.MoveExtraStepEffect;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class tests the MoveExtraStepEffect class.
 */
class MoveExtraStepEffectTest {

    private static final int DEFAULT_STEPS = 5;
    private static final int DEFAULT_POSITIVE_STEPS = 3;
    private static final int DEFAULT_NEGATIVE_STEPS = -2;
    private static final int DEFAULT_OUT_OF_BOUND_STEPS = -10;

    /**
     * This method tests the applyEffect method of the MoveExtraStepEffect class with positive steps.
     */
    @Test
    void testApplyEffectPositiveStep() {
        final Player player = new MutablePlayerImpl("Player1", "color");
        final MutablePlayer mutablePlayer = (MutablePlayer) player;
        mutablePlayer.setCurrentSteps(DEFAULT_STEPS);
        final MoveExtraStepEffect effect = new MoveExtraStepEffect(DEFAULT_POSITIVE_STEPS);
        effect.applyEffect(player);
        assertEquals(8, player.getCurrentSteps());
    }

    /**
     * This method tests the applyEffect method of the MoveExtraStepEffect class with negative steps.
     */
    @Test
    void testApplyEffectNegativeStep() {
        final Player player = new MutablePlayerImpl("Player1", "color");
        final MutablePlayer mutablePlayer = (MutablePlayer) player;
        mutablePlayer.setCurrentSteps(DEFAULT_STEPS);
        final MoveExtraStepEffect effect = new MoveExtraStepEffect(DEFAULT_NEGATIVE_STEPS);
        effect.applyEffect(player);
        assertEquals(3, player.getCurrentSteps());
    }

    /**
     * This method tests the applyEffect method of the MoveExtraStepEffect class with out of bound steps.
     */
    @Test
    void testApplyEffectOutOfBound() {
        final Player player = new MutablePlayerImpl("Player1", "color");
        final MutablePlayer mutablePlayer = (MutablePlayer) player;
        mutablePlayer.setCurrentSteps(DEFAULT_STEPS);
        final MoveExtraStepEffect effect = new MoveExtraStepEffect(DEFAULT_OUT_OF_BOUND_STEPS);
        effect.applyEffect(player);
        assertEquals(0, player.getCurrentSteps());
    }
}
