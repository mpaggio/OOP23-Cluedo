package it.unibo.cluedo.model.card.api;


/**
 * Represents a card in the Cluedo game.
 */
public interface Card {
    
    /**
     * Enum representing the different types of cards in the game Cluedo.
     */
    enum Type {
        /**
         * Represents a character card.
         */
        CHARACTER,
        /**
         * Represents a weapon card.
         */
        WEAPON,
        /**
         * Represents a room card.
         */
        ROOM
    }

    /**
     * Gets the type of the card.
     * 
     * @return the type of the card
     */
    Type getType();

    /**
     * Gets the name of the card.
     * 
     * @return the name of the card
     */
    String getName();

    /**
     * Gets the image path of the card.
     * 
     * @return the image path of the card
     */
    String getImagePath();
}
