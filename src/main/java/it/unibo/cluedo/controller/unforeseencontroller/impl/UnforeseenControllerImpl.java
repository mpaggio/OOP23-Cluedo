package it.unibo.cluedo.controller.unforeseencontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.unforeseencontroller.api.UnforeseenController;
import it.unibo.cluedo.view.unforeseen.UnforeseenView;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * Class that implements the UnforeseenController interface.
 */
public class UnforeseenControllerImpl implements UnforeseenController {

    private UnforeseenEffect unforeseen;

    /**
     * Gets the type of the unforeseen.
     * 
     * @return the type of the unforeseen
     */
    @Override
    public String getEffectType() {
        return this.unforeseen.getType();
    }

    /**
     * Gets the description of the unforeseen.
     * 
     * @return the description of the unforeseen
     */
    @Override
    public String getEffectDescription() {
        return this.unforeseen.getDescription();
    }

    /**
     * Shows the unforeseen.
     */
    @Override
    public void showUnforeseen() {
        final UnforeseenView unforeseenView = new UnforeseenView();
        unforeseenView.showEffect();
        Cluedo.CONTROLLER.updateInformations();
    }

    /**
     * Initializes the controller.
     */
    @Override
    public void initializeController() {
        this.unforeseen = Cluedo.CONTROLLER.getGameInstance().drawUnforeseen();
    }

}
