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
    private boolean hasBeenShown;

    /**
     * Constructor for UnforeseenControllerImpl.
     */
    public UnforeseenControllerImpl() {
        this.unforeseen = null;
        this.hasBeenShown = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEffectType() {
        return this.unforeseen.getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEffectDescription() {
        return this.unforeseen.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showUnforeseen() {
        final UnforeseenView unforeseenView = new UnforeseenView();
        unforeseenView.showEffect();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeController() {
        this.hasBeenShown = false;
        this.unforeseen = Cluedo.CONTROLLER.getGameInstance().drawUnforeseen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasUnforeseenBeenShown() {
        final boolean shown = this.hasBeenShown;
        this.hasBeenShown = true;
        return shown;
    }
}
