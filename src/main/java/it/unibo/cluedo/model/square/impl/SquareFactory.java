package it.unibo.cluedo.model.square.impl;

import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Utility class for creating squares in the Cluedo game.
 */
public final class SquareFactory {
    /**
     * Private constructor to prevent instantiation.
     */
    private SquareFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Create a normal square (only for movement) with the specified position and effect.
     * 
     * @param position the position of the square
     * @return a new Square with no effect
     */
    public static Square createNormalSquare(final Position position) {
        return new SquareImpl(position, Effect.EffectType.NONE);
    }

    /**
     * Create a bonus square with the specified position and effect.
     * 
     * @param position the position of the square
     * @return a new Square with bonus effect
     */
    public static Square createBonusSquare(final Position position) {
        return new SquareImpl(position, Effect.EffectType.BONUS);
    }

    /**
     * Create a malus square with the specified position and effect.
     * 
     * @param position the position of the square
     * @return a new Square with malus effect
     */
    public static Square createMalusSquare(final Position position) {
        return new SquareImpl(position, Effect.EffectType.MALUS);
    }
}
