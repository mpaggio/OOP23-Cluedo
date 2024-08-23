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
     * Constructor of the class.
     */
    @Override
    public void applyEffetct(final Player player) {
        if (player instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) player;
            mutablePlayer.setPlayerTurn(false);
        }
    }
}
