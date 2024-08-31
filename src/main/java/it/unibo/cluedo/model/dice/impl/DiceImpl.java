package it.unibo.cluedo.model.dice.impl;

import it.unibo.cluedo.model.dice.api.Dice;
import java.util.Random;

/**
 * Class that implements the Dice interface, it has a constructor that takes the
 * number of sides of the dice as parameter.
 */
public class DiceImpl implements Dice {

    private final int sides;
    private static final int MAX_SIDES = 6;
    private final Random random;

    /**
     * Constructor that takes the number of sides of the dice as parameter.
     *
     * @param sides the number of sides of the dice.
     * @throws IllegalArgumentException if the number of sides of the dice is less
     *                                  than 1 or greater than 6.
     */
    public DiceImpl(final int sides) {
        if (sides < 1 || sides > MAX_SIDES) {
            throw new IllegalArgumentException("The number of sides of the dice must be between 1 and 6");
        }
        this.sides = sides;
        this.random = new Random();
    }

    /**
     * Method that return a random number between 1 and the number of sides of the
     * dice(6).
     *
     * @return a random number between 1 and the number of sides of the dice(6).
     */
    @Override
    public int rollDice() {
        return random.nextInt(sides) + 1;
    }
}
