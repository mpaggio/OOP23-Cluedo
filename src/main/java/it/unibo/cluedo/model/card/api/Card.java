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
        CHARACTER("Character"),
        /**
         * Represents a weapon card.
         */
        WEAPON("Weapon"),
        /**
         * Represents a room card.
         */
        ROOM("Room");

        private final String displayName;

        /**
         * Constructs a Type enum with the specified display name.
         * 
         * @param displayName the display name of the type
         */
        Type(final String displayName) {
            this.displayName = displayName;
        }

        /**
         * Returns the display name of the type.
         * 
         * @return the display name of the type
         */
        @Override
        public String toString() {
            return this.displayName;
        }
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
