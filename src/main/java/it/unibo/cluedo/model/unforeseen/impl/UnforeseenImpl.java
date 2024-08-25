package it.unibo.cluedo.model.unforeseen.impl;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.Unforeseen;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;


/**
 * Implementation of the EventCard interface.
 */
public abstract class UnforeseenImpl implements Unforeseen {

    private final UnforeseenEffect effect;
    private final String description;

    /**
     * Constructor of the class.
     *
     * @param effect the effect of the unforseen card
     * @param description the description of the unforseen card
     */
    public UnforeseenImpl(final UnforeseenEffect effect, final String description) {
        this.effect = effect;
        this.description = description;
    }

    /**
     * Executes the action of the event card.
     *
     * @param player the player who triggered the event card
     */
    @Override
    public void apply(final Player player) {
        effect.applyEffect(player);
    }

    /**
     * Returns the name of the event card.
     *
     * @return the name of the event card
     */
    @Override
    public String getDescription() {
        return description;
    }






}

