package it.unibo.cluedo.model.dice.impl;

import it.unibo.cluedo.model.dice.api.Dice;
import java.util.Random;

/**
 * Class that implements the Dice interface.
 */
public class DiceImpl implements Dice {

    private static final int SIDES = 6;
    private final Random random;

    /**
     * Constructor of the class.
    */
    public DiceImpl() {
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
        return random.nextInt(SIDES) + 1;
    }
}
