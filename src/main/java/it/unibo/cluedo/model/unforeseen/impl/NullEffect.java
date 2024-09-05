package it.unibo.cluedo.model.unforeseen.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * Class that represents the null effect.
 */
public final class NullEffect  implements UnforeseenEffect {

    /**
     * Constructor for the NullEffect class.
     */
    @Override
    public void applyEffect(final Player player) {
    }

    @Override
    public String getType() {
        return "NullEffect";
    }

    @Override
    public String getDescription() {
       return "You have no effect";
    }


}
