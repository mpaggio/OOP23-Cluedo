package it.unibo.cluedo.model.accusation.impl;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Represents the implementation of the accusation in the Cluedo game.
 */

public class AccusationImpl implements Accusation {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accuse(final Card weapon, final Card room, final Card character, final Player player) {
        return player.getPlayerCards().contains(weapon) || player.getPlayerCards().contains(room) 
            || player.getPlayerCards().contains(character);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean finalAccuse(final Card weapon, final Card room, final Card character) {
        // TODO Auto-generated method stub
        return false;
    }
}
