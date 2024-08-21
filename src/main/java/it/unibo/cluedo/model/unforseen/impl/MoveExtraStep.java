package it.unibo.cluedo.model.unforseen.impl;
import it.unibo.cluedo.model.player.api.Player;

/**
 * This class represents an Event Card that allows a player to move extra steps
 * beyond what they would normally move based on the dice roll.
 */
public class MoveExtraStep extends AbstractUnforseen {

    //private final int extraSteps;

    /**
     * Constructor of the class.
     *
     * @param name the name of the event card
     *
     */
    public MoveExtraStep(final String name /*,final int extraSteps*/) {
        super(name);
        //this.extraSteps = extraSteps;
    }

    /**
     * Returns the number of extra steps the player can move.
     *
     *
     */
    @Override
    protected void doApplyEffect(final Player player) {
        //player.move(extraSteps);
    }

    /**
     * Returns the type of the event card.
     *
     *
     */
    @Override
    public void applyEffetct(final Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffetct'");
    }

}
