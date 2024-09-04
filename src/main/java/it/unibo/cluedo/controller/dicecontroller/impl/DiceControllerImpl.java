package it.unibo.cluedo.controller.dicecontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.dicecontroller.api.DiceController;

/**
 * Class that implements the DiceController interface.
 */
public class DiceControllerImpl implements DiceController {

    /**
     * Gets the result of the dice roll.
     * @return the result of the dice roll
     */
    @Override
    public int getResult() {
        Cluedo.CONTROLLER.getGameInstance().rollDice();
        return Cluedo.CONTROLLER.getGameInstance().getDiceResult();
    }

}
