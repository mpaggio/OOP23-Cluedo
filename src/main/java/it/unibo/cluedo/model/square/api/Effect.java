package it.unibo.cluedo.model.square.api;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an effect applyable to a player.
 */
public interface Effect {
    /**
     * Enum representing the type of effect.
     */
    public enum EffectType {
        NONE ("No effect", "No effect"),
        BONUS ("Bonus effect", "One more roll of the dice"),
        MALUS ("Malus effect", "Loss of the next turn");

        private final String title;
        private final String description;

        EffectType(final String title, final String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return this.title;
        }

        public String getDescription() {
            return this.description;
        }
    };  

    /**
     * Applies the effect to the specified player.
     * 
     * @param player the player to whom the effect is applied
     */
    void apply(Player player);
}
