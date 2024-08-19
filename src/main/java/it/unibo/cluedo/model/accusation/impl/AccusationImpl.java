package it.unibo.cluedo.model.accusation.impl;

import java.lang.StackWalker.Option;
import java.util.Optional;
import java.util.stream.Stream;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Represents the implementation of the accusation in the Cluedo game.
 */

public class AccusationImpl implements Accusation {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> accuse(final Card weapon, final Card room, final Card character, final Player player) {
        return Stream.of(weapon, room, character)
                    .filter(player.getPlayerCards()::contains)
                    .findAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean finalAccuse(final Card weapon, final Card room, final Card character) {
        // TODO Auto-generated method stub
        return false;
    }
}
