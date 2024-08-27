package it.unibo.cluedo.model.square.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.MapComponentVisitor;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.utilities.Position;
import java.util.Optional;

/**
 * Implementation of the Square interface.
 * Represents a square on the Cluedo map, with a specific position and effect.
 */
public class SquareImpl implements Square {
    private final Position position;
    private final Effect effect;
    private Optional<Player> player;

    /**
     * Constructor for the square implementation.
     * 
     * @param position the position of the square
     * @param effect the effect of the square
     */
    public SquareImpl(final Position position, final Effect effect) {
        this.position = position; 
        this.effect = effect;
        this.player = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void landOn(final Player player) {
        setPlayer(player);
        this.effect.apply(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Effect getEffect() {
        return this.effect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(final MapComponentVisitor visitor) {
        visitor.visitSquare(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlayer() {
        this.player = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyOccupied() {
        return this.player.isPresent();
    }

    /**
     * Sets the player moved in the square.
     */
    private void setPlayer(final Player player) {
        this.player = Optional.of(player);
    }
}
