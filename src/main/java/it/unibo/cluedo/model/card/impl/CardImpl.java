package it.unibo.cluedo.model.card.impl;

import it.unibo.cluedo.model.card.api.Card;

public class CardImpl implements Card {
    private Type type;
    private String name;
    private String imagePath;

    public CardImpl(final Type type, final String name, final String imagePath){
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }
    
}
