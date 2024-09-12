package it.unibo.cluedo.model.accusation.impl;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.Set;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.card.api.Card;

/**
 * Represents the implementation of the accusation in the Cluedo game.
 */
public class AccusationImpl implements Accusation {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> accuse(final Card weapon, final Card room, final Card character, final Set<Card> playerCards) {
        return Stream.of(weapon, room, character)
                    .filter(playerCards::contains)
                    .findAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean finalAccuse(final Card weapon, final Card room, final Card character, final Set<Card> solution) {
        return solution.containsAll(Set.of(weapon, room, character));
    }
}
