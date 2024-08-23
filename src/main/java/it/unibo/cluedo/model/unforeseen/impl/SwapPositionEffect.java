package it.unibo.cluedo.model.unforeseen.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;
import it.unibo.cluedo.utilities.Position;

/**
 * This class represents an Unforeseen Card that allows a player to swap position with another player.
 */
public final class SwapPositionEffect implements UnforeseenEffect {

    private final Player otherPlayer;

    /**
     * Constructor of the class.
     *
     * @param otherPlayer the player with whom the current player will swap position
     */
    public SwapPositionEffect(final Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    @Override
    public void applyEffetct(final Player player) {
        if (player instanceof MutablePlayer && otherPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) player;
            final MutablePlayer mutableOtherPlayer = (MutablePlayer) otherPlayer;
            final Position tempPosition = mutablePlayer.getCurrentPosition();
            mutablePlayer.setPosition(otherPlayer.getCurrentPosition());
            mutableOtherPlayer.setPosition(tempPosition);
        }
    }

}
