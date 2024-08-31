package it.unibo.cluedo.model.unforeseen.impl;

import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * This class represents an Unforeseen Card that allows a player to skip their
 * turn.
 */
public class SkipTurnEffect implements UnforeseenEffect {

    /**
     * This method is used to apply the effect of the card to the player.
     * @param player the player that has to apply the effect of the card.
     */
    @Override
    public void applyEffect(final Player player) {
        if (player instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) player;
            mutablePlayer.setNextTurn(false);
        }
    }

    /**
     * This method is used to get the type of the card.
     * @return the type of the card.
     */
    @Override
    public String getType() {
        return "SkipTurn";
    }
}
