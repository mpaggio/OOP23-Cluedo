package it.unibo.cluedo.model.square.impl;

import java.io.Serializable;

import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Effect;

/**
 * Implementation of Effect interface with pattern Strategy.
 * Representing the Bonus Effect effect.
 */
public class BonusEffectImpl implements Effect, Serializable {
    private static final long serialVersionUID = 1L;
    private final EffectType type = EffectType.BONUS;

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final Player player) {
        if (player instanceof MutablePlayer) {
            final MutablePlayer currentPlayer = (MutablePlayer) player;
            currentPlayer.setDoubleRollDice(true);
        } else {
            throw new IllegalArgumentException("Player is not an instance of MutablePlayer");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EffectType getType() {
        return this.type;
    }
}
