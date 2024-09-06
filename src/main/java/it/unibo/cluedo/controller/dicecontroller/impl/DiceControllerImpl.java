package it.unibo.cluedo.controller.dicecontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.dicecontroller.api.DiceController;
import it.unibo.cluedo.utilities.TurnFase;

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
        if (Cluedo.CONTROLLER.getGameInstance().getTurnFase().equals(TurnFase.DRAW_UNFORESEEN)) {
            Cluedo.CONTROLLER.getUnforeseenController().initializeController();
        }
        return Cluedo.CONTROLLER.getGameInstance().getDiceResult();
    }

}
