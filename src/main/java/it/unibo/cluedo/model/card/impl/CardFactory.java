package it.unibo.cluedo.model.card.impl;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.api.Card.Type;

/**
 * Utility class for creating cards in the Cluedo game.
 */
public final class CardFactory {
    /**
     * Private constructor to prevent instantiation.
     */
    private CardFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Create a character card.
     * 
     * @param name the name of the character
     * @param imagePath the image path of the character
     * @return the created character card
     */
    public static Card createCharacterCard(final String name, final String imagePath) {
        return new CardImpl(Type.CHARACTER, name, imagePath);
    }

    /**
     * Create a weapon card.
     * 
     * @param name the name of the weapon
     * @param imagePath the image path of the weapon
     * @return the created weapon card
     */
    public static Card createWeaponCard(final String name, final String imagePath) {
        return new CardImpl(Type.WEAPON, name, imagePath);
    }

    /**
     * Create a room card.
     * 
     * @param name the name of the room
     * @param imagePath the image path of the room
     * @return the create room card
     */
    public static Card createRoomCard(final String name, final String imagePath) {
        return new CardImpl(Type.ROOM, name, imagePath);
    }
}
