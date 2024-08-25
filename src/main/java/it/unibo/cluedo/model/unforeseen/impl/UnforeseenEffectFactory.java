package it.unibo.cluedo.model.unforeseen.impl;

import java.util.Random;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * Factory class to create an unforeseen effect.
 */
public final class UnforeseenEffectFactory {

    private static final Random RANDOM = new Random();
    private static final int MOVE_EXTRA_STEP_PROBABILITY = 50;
    private static final int REROLL_DICE_PROBABILITY = MOVE_EXTRA_STEP_PROBABILITY + 20;
    private static final int SWAP_POSITION_PROBABILITY = REROLL_DICE_PROBABILITY + 15;
    private static final int SWAP_CARD_PROBABILITY = SWAP_POSITION_PROBABILITY + 10;
    private static final int TOTAL_STEPS = 3;

    /**
     * Private constructor that hides the implicit public one because it is an utility class.
     */
    private UnforeseenEffectFactory() {
        throw new UnsupportedOperationException("Utility class");
    }
    /**
     * Creates an unforeseen effect.
     *
     * @param otherPlayer the player who triggered the event card
     * @return the unforeseen effect
     */
    public static UnforeseenEffect createUnforeseenEffect(final Player otherPlayer) {
        final int roll = RANDOM.nextInt(100);

        if (roll < MOVE_EXTRA_STEP_PROBABILITY) {
            return new MoveExtraStepEffect(RANDOM.nextInt(TOTAL_STEPS) - 3); /* 50 % di probabilità */
        } else if (roll < REROLL_DICE_PROBABILITY) {
            return new ReRollDiceEffect(); /* 20 % di probabilità */
        } else if (roll < SWAP_POSITION_PROBABILITY) {
            return new SwapPositionEffect(otherPlayer); /* 15 % di probabilità */
        } else if (roll < SWAP_CARD_PROBABILITY) {
            return new SwapCardEffect(otherPlayer); /* 10 % di probabilità */
        } else {
            return new SkipTurnEffect(); /* 5 % di probabilità */
        }
    }
}