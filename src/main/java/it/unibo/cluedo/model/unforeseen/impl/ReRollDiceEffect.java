package it.unibo.cluedo.model.unforeseen.impl;

import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * This class represents an Unforeseen that allows a player to re-roll the dice.
 */
public final class ReRollDiceEffect implements UnforeseenEffect {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect(final Player player) {
        if (player instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) player;
            mutablePlayer.setDoubleRollDice(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "ReRollDice";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "You can re-roll the dice";
    }
}
