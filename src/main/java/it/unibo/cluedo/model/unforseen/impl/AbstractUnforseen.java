package it.unibo.cluedo.model.unforseen.impl;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforseen.api.Unforseen;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Implementation of the EventCard interface.
 */
public abstract class AbstractUnforseen implements Unforseen {

    private static final Logger LOGGER = Logger.getLogger(AbstractUnforseen.class.getName());
    private final String name;

    /**
     * Constructor of the class.
     *
     * @param name the name of the event card
     */
    public AbstractUnforseen(final String name) {
        this.name = name;
    }

    /**
     * Returns the name of the event card.
     *
     * @return the name of the event card
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Executes the action of the event card.
     *
     * @param player the player who triggered the event card
     */
    public void applyEffect(final Player player) {
        LOGGER.log(Level.INFO, "Applying effect of the event card {0} to player {1}",
                new Object[]{name, player.getUsername()});
        doApplyEffect(player);

    }

    /**
     * Each specific event card will implement its own effetct.
     *
     * @param player the player who receives the event card
     */
    protected abstract void doApplyEffect(Player player);



}

