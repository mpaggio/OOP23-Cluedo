package it.unibo.cluedo.model.square.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Effect;

/**
 * Implementation of Effect interface with pattern Strategy.
 * Representing the Malus Effect effect.
 */
public class MalusEffectImpl implements Effect{
    private EffectType type = EffectType.MALUS;

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(Player player) {
        // player.cancelNextTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EffectType getType() {
        return this.type;
    }
    
}
