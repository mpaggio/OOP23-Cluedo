package it.unibo.cluedo.model.unforeseen.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * Class that represents the null effect.
 * This effect does nothing.
 */
public final class NullEffect  implements UnforeseenEffect {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect(final Player player) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "NullEffect";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
       return "You have no effect";
    }
}
