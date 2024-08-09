package it.unibo.cluedo.model.deck.impl;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.impl.CardFactory;
import it.unibo.cluedo.model.deck.api.Deck;

/**
 * Represents the implementation of the deck of cards in the Cluedo game.
 */
public class DeckImpl implements Deck {
    private final Set<Card> cards = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDeck() {
        this.cards.clear();

        this.cards.add(CardFactory.createCharacterCard("Colonel Mustard", ""));
        this.cards.add(CardFactory.createCharacterCard("Miss Scarlet", ""));
        this.cards.add(CardFactory.createCharacterCard("Mr. Green", ""));
        this.cards.add(CardFactory.createCharacterCard("Mrs. Peacock", ""));
        this.cards.add(CardFactory.createCharacterCard("Mrs. White", ""));
        this.cards.add(CardFactory.createCharacterCard("Professor Plum", ""));

        this.cards.add(CardFactory.createWeaponCard("Candlestick", ""));
        this.cards.add(CardFactory.createWeaponCard("Knife", ""));
        this.cards.add(CardFactory.createWeaponCard("Lead pipe", ""));
        this.cards.add(CardFactory.createWeaponCard("Revolver", ""));
        this.cards.add(CardFactory.createWeaponCard("Rope", ""));
        this.cards.add(CardFactory.createWeaponCard("Wrench", ""));

        this.cards.add(CardFactory.createRoomCard("Kitchen", ""));
        this.cards.add(CardFactory.createRoomCard("Ball room", ""));
        this.cards.add(CardFactory.createRoomCard("Conservatory", ""));
        this.cards.add(CardFactory.createRoomCard("Billiard room", ""));
        this.cards.add(CardFactory.createRoomCard("Library", ""));
        this.cards.add(CardFactory.createRoomCard("Study", ""));
        this.cards.add(CardFactory.createRoomCard("Hall", ""));
        this.cards.add(CardFactory.createRoomCard("Lounge", ""));
        this.cards.add(CardFactory.createRoomCard("Dining room", ""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Card> drawSolution() {
        final Card solutionCharacter = this.cards.stream()
            .filter(card -> card.getType() == Card.Type.CHARACTER)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("No character card available"));
        final Card solutionWeapon = this.cards.stream()
            .filter(card -> card.getType() == Card.Type.WEAPON)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("No weapon card available"));
        final Card solutionRoom = this.cards.stream()
            .filter(card -> card.getType() == Card.Type.ROOM)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("No room card available"));
        this.cards.removeIf(card -> card.equals(solutionCharacter) || card.equals(solutionWeapon) || card.equals(solutionRoom));
        return Set.of(solutionCharacter, solutionWeapon, solutionRoom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Set<Card>> distributedCards(final int numberOfPlayers) {
        Set<Set<Card>> distributedSet = new HashSet<>();
        Iterator<Card> cardIterator = this.cards.iterator();
        int numberOfCardsPerPlayer = this.cards.size() / numberOfPlayers;
        for (int i = 0; i < numberOfPlayers; i++) {
            Set<Card> playerCards = new HashSet<>();
            for (int j = 0; j < numberOfCardsPerPlayer; j++) {
                if (cardIterator.hasNext()) {
                    playerCards.add(cardIterator.next());
                }
            }
            distributedSet.add(playerCards);
        }
        return distributedSet;
    }
}
