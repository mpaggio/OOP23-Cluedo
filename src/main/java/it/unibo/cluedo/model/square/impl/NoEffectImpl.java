package it.unibo.cluedo.model.square.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Effect;

/**
 * Implementation of Effect interface with pattern Strategy.
 * Representing the No Effect effect.
 */
public class NoEffectImpl implements Effect{

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(Player player) {
        // No effect
    }
    
}
