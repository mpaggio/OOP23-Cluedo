package it.unibo.cluedo.model.player.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.PlayerBuilder;

/**
 * Implementation of the {@link PlayerBuilder} interface.
 * Provides methods to build a new {@link Player} instance.
 */
public class PlayerBuilderImpl implements PlayerBuilder {

    private String username;
    private String color;

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder username(final String username) {
        this.username = username;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder color(final String color) {
        this.color = color;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player buildPlayer() {
        if (this.username == null || this.color == null) {
            throw new IllegalArgumentException("Missing fields: cannot create a new Player");
        }
        return new PlayerImpl(this.username, this.color);
    }
}
