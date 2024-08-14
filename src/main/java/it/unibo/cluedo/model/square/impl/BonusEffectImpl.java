package it.unibo.cluedo.model.square.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Effect;

/**
 * Implementation of Effect interface with pattern Strategy.
 * Representing the Bonus Effect effect.
 */
public class BonusEffectImpl implements Effect{

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(Player player) {
        // player.unableDoubleDiceRoll();
    }
    
}
