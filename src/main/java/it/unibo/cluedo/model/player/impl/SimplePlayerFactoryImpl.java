package it.unibo.cluedo.model.player.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.SimplePlayerFactory;
/**
 * Implementation of the {@link SimplePlayerFactory} interface.
 * This class implements the method to create a new {@link Player} instance
 */
public class SimplePlayerFactoryImpl implements SimplePlayerFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Player createPlayer(final String username, final String color) {
        return new MutablePlayerImpl(username, color);
    }
}
