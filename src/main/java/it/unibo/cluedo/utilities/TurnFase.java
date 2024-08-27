package it.unibo.cluedo.utilities;

/**
 * Enum to represent the possible fases of a turn in the Cluedo game.
 */
public enum TurnFase {
    /**
     * The player has to roll the dice.
     */
    ROLL_DICE,
    /**
     * The unforseen cards get distributed.
     */
    DISTRIBUTE_UNFORSEEN,
    /**
     * The player has to choose where to move.
     */
    MOVE_PLAYER,
    /**
     * The player has to make an accusation or the final accusation.
     */
    MAKE_ACCUSATION,
    /**
     * The player has to end his turn.
     */
    END_TURN;
    /**
     * Method to change the fase of the turn.
     * @return the next fase of the turn.
     */
    public TurnFase nextFase() {
        return values()[(ordinal() + 1) % values().length];
    }
}
