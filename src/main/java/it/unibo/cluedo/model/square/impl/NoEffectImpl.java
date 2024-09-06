package it.unibo.cluedo.model.square.impl;

import java.io.Serializable;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Effect;

/**
 * Implementation of Effect interface with pattern Strategy.
 * Representing the No Effect effect.
 */
public class NoEffectImpl implements Effect, Serializable {
    private static final long serialVersionUID = 1L;
    private final EffectType type = EffectType.NONE;

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final Player player) {
        // No effect
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EffectType getType() {
        return this.type;
    } 
}
