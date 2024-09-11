package it.unibo.cluedo.model.dice.api;

/**
 * Interface that rapresents a dice with 6 sides.
 */
public interface Dice {

    /**
     * Method that return a random number between 1 and the number of sides of the dice(6).
     * @return a random number between 1 and the number of sides of the dice(6).
     */
    int rollDice();
}
