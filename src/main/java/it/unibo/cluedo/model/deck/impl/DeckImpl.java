package it.unibo.cluedo.model.deck.impl;

import java.util.Set;
import java.util.HashSet;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.impl.CardFactory;
import it.unibo.cluedo.model.deck.api.Deck;

public class DeckImpl implements Deck{
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawSolution'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Set<Card>> distributedCards(int numberOfPlayers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'distributedCards'");
    }
}
