package it.unibo.cluedo.model.card.impl;

import it.unibo.cluedo.model.card.api.Card;


/**
 * Implementation of the Card interface.
 */
public class CardImpl implements Card {
    private final Type type;
    private final String name;
    private final String imagePath;

    /**
     * Constructs a new CardImpl.
     * 
     * @param type the type of the card
     * @param name the name of the card
     * @param imagePath the image path of the card
     */
    public CardImpl(final Type type, final String name, final String imagePath) {
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImagePath() {
        return this.imagePath;
    }
}
