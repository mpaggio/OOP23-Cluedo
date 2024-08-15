package it.unibo.cluedo.model.eventcard.impl;
import it.unibo.cluedo.model.eventcard.api.EventCard;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Implementation of the EventCard interface.
 */
public class EventCardImpl implements EventCard {

    private final String name;

    /**
     * Constructs a new EventCardImpl.
     *
     * @param name the name of the event card
     */
    public EventCardImpl(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImagePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImagePath'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeAction(final Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeAction'");
    }

}

