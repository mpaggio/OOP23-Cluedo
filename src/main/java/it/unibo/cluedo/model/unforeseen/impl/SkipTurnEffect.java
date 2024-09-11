package it.unibo.cluedo.model.unforeseen.impl;

import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * This class represents an Unforeseen Card that allows a player to skip their
 * turn.
 */
public final class SkipTurnEffect implements UnforeseenEffect {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect(final Player player) {
        if (player instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) player;
            mutablePlayer.setNextTurn(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "SkipTurn";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "You will skip your next turn.";
    }
}
