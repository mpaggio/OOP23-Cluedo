package it.unibo.cluedo.controller.unforeseencontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.unforeseencontroller.api.UnforeseenController;

/**
 * Class that implements the UnforeseenController interface.
 */
public class UnforeseenControllerImpl implements UnforeseenController {

    /**
     * Gets the type of the unforeseen.
     * @return the type of the unforeseen
     */
    @Override
    public String getEffectType() {
        return Cluedo.CONTROLLER.getGameInstance().drawUnforeseen().getType();
    }

    /**
     * Gets the description of the unforeseen.
     * @return the description of the unforeseen
     */
    @Override
    public String getEffectDescription() {
        return Cluedo.CONTROLLER.getGameInstance().drawUnforeseen().getDescription();
    }

}
