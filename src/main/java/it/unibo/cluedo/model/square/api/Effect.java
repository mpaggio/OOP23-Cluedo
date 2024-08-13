package it.unibo.cluedo.model.square.api;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an effect applyable to a player.
 */
public interface Effect {
    /**
     * Enum representing the type of effect.
     */
    enum EffectType {
        /**
         * No effect.
         */
        NONE("No effect", "No effect"),

        /**
         * Bonus effect. 
         */
        BONUS("Bonus effect", "One more roll of the dice"),

        /**
         * Malus effect.
         */
        MALUS("Malus effect", "Loss of the next turn");

        private final String title;
        private final String description;

        /**
         * Constructor for EffectType.
         * 
         * @param title the title of the effect type
         * @param description the description of the effect type
         */
        EffectType(final String title, final String description) {
            this.title = title;
            this.description = description;
        }

        /**
         * Returns the title of the effect type.
         * 
         * @return the title of the effect type
         */
        public String getTitle() {
            return this.title;
        }

        /**
         * Returns the description of the effect type.
         * 
         * @return the description of the effect type
         */
        public String getDescription() {
            return this.description;
        }
    }

    /**
     * Applies the effect to the specified player.
     * 
     * @param player the player to whom the effect is applied
     */
    void apply(Player player);
}
