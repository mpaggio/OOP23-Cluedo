package it.unibo.cluedo.model.card.impl;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.api.Card.Type;

/**
 * Utility class for creating cards in the Cluedo game.
 */
public class CardFactory {
    /**
     * Create a character card.
     * 
     * @param name the name of the character
     * @param imagePath the image path of the character
     * @return the created character card
     */
    public static Card createCharacterCard(String name, String imagePath){
        return new CardImpl(Type.CHARACTER, name, imagePath);
    }

    /**
     * Create a weapon card
     * 
     * @param name the name of the weapon
     * @param imagePath the image path of the weapon
     * @return the created weapon card
     */
    public static Card createWeaponCard(String name, String imagePath){
        return new CardImpl(Type.WEAPON, name, imagePath);
    }

    /**
     * Create a room card
     * 
     * @param name the name of the room
     * @param imagePath the image path of the room
     * @return the create room card
     */
    public static Card createRoomCard(String name, String imagePath){
        return new CardImpl(Type.ROOM, name, imagePath);
    }
}
