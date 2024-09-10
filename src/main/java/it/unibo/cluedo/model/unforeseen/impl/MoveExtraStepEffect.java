package it.unibo.cluedo.model.unforeseen.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * This class represents an Unforeseen that allows a player
 * to move extra steps (+ or -) beyond what they would normally move
 * based on the dice roll.
 */
public final class MoveExtraStepEffect implements UnforeseenEffect {

    private final int extraSteps;

    /**
     * Constructor of the class.
     * @param extraSteps the number of extra steps the player can move
     */
    public MoveExtraStepEffect(final int extraSteps) {
        this.extraSteps = extraSteps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect(final Player player) {
        if (player instanceof MutablePlayer) {
            final int currentSteps = player.getCurrentSteps();
            final int newSteps = Math.max(0, currentSteps + extraSteps);
            ((MutablePlayer) player).setCurrentSteps(newSteps);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "MoveExtraStep";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "You can move " + extraSteps + " extra steps";
    }
}
