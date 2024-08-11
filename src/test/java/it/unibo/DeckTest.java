package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.deck.impl.DeckImpl;

final class DeckTest {
    private static final int TOT_NUM_CARDS = 21;
    private static final int TOT_NUM_CHARACTERS = 6;
    private static final int TOT_NUM_WEAPONS = 6;
    private static final int TOT_NUM_ROOMS = 9; 
    private DeckImpl deck;

    /**
     * Initialization of the deck before every test.
     */
    @BeforeEach
    void setUp() {
        this.deck = new DeckImpl();
        deck.initializeDeck();
    }

    /**
     * Test that the initialization method creates all the right cards and that
     * assignes the rigth paths.
     */
    @Test
    void testInitialization() {
        assertEquals(
            TOT_NUM_CARDS, 
            deck.getAllCards().size(), 
            "The deck should contain 21 cards after initialization"
        );
        assertEquals(
            TOT_NUM_CHARACTERS, 
            deck.getAllCards().stream()
                .filter(card -> card.getType().equals(Card.Type.CHARACTER))
                .count(),
            "Characters card should be 6 after initialization"
        );
        assertEquals(
            TOT_NUM_WEAPONS, 
            deck.getAllCards().stream()
                .filter(card -> card.getType().equals(Card.Type.WEAPON))
                .count(),
            "Weapons card should be 6 after initialization"
        );
        assertEquals(
            TOT_NUM_ROOMS, 
            deck.getAllCards().stream()
                .filter(card -> card.getType().equals(Card.Type.ROOM))
                .count(),
            "Rooms card should be 6 after initialization"
        );
        deck.getAllCards().forEach(card -> {
            assertTrue(
                Files.exists(Paths.get(card.getImagePath())), 
                "Image file should exist for card: " + card.getName()
            );
        });
    } 

    /**
     * Test that all cards name are different.
     */
    @Test
    void testUniqueCardNames() {
        final Set<String> cardNames = deck.getAllCards().stream()
            .map(Card :: getName)
            .collect(Collectors.toSet());
        assertEquals(
            TOT_NUM_CARDS, 
            cardNames.size(), 
            "All cards should be unique"
        );
    }
}
