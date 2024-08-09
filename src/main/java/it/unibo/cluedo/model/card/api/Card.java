package it.unibo.cluedo.model.card.api;

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

    Type getType();

    String getName();

    String getImagePath();
}
