package it.unibo.cluedo.model.square.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.api.Effect.EffectType;
import it.unibo.cluedo.utilities.Position;

/**
 * Implementation of the Square interface.
 * Represents a square on the Cluedo map, with a specific position and effect.
 */
public class SquareImpl implements Square {
    private final Position position;
    private final EffectType effect;

    /**
     * Constructor for the square implementation.
     * 
     * @param position the position of the square
     * @param effect the effect of the square
     */
    public SquareImpl(final Position position, final EffectType effect) {
        this.position = position; 
        this.effect = effect;
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
        switch (this.effect) {
            case BONUS:
                // add a way to set the player double dice roll                
                break;

            case MALUS:
                // add a way to unable the player next turn
                break;
        
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EffectType getEffect() {
        return this.effect;
    }
}
