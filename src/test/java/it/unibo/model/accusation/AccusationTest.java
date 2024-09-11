package it.unibo.model.accusation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.accusation.impl.AccusationImpl;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.impl.CardFactory;

import java.util.Set;
import java.util.Optional;

/**
 * Test class for the Accusation interface.
 */
final class AccusationTest {
    private static final String SRC = "src";
    private static final String MAIN = "main";
    private static final String RESOURCES = "resources";
    private static final String PNG = ".png";
    private static final String CHARACTER = "Miss Scarlett";
    private static final String WEAPON = "Candlestick";
    private static final String ROOM = "Kitchen";

    private final String characterPath = Paths.get(
        SRC,
        MAIN,
        RESOURCES, 
        CHARACTER.replace(" ", "") + PNG
    ).toString();
    private final String weaponPath = Paths.get(
        SRC,
        MAIN,
        RESOURCES,
        WEAPON + PNG
    ).toString();
    private final String roomPath = Paths.get(
        SRC,
        MAIN,
        RESOURCES, 
        ROOM + ".PNG"
    ).toString();

    private Card characterCard;
    private Card weaponCard;
    private Card roomCard;
    private final Accusation accusation = new AccusationImpl();

    /**
     * Sets up some cards used in the methods.
     */
    @BeforeEach
    void setUp() {
        characterCard = CardFactory.createCharacterCard(CHARACTER, characterPath);
        weaponCard = CardFactory.createWeaponCard(WEAPON, weaponPath);
        roomCard = CardFactory.createRoomCard(ROOM, roomPath);
    }

    /**
     * Tests that the final accuse method works correctly.
     */
    @Test
    void testFinalAccuse() {
        final String characterAccused = "Professor Plum";
        final String characterAccPath = Paths.get(
            SRC,
            MAIN,
            RESOURCES, 
            characterAccused.replace(" ", "") + PNG
        ).toString();
        final Card characterAccusedCard = CardFactory.createCharacterCard(characterAccused, characterAccPath);
        final Set<Card> solution = Set.of(characterCard, weaponCard, roomCard);
        assertFalse(accusation.finalAccuse(characterAccusedCard, weaponCard, roomCard, solution));
        assertTrue(accusation.finalAccuse(characterCard, weaponCard, roomCard, solution));
    }

    /**
     * Tests that the accuse method returns the right card or Optional.empty().
     */
    @Test
    void testAccuse() {
        final Set<Card> playerCards = Set.of(characterCard, weaponCard, roomCard);
        final String weaponAccusedName = "Revolver";
        final String weaponAccPath = Paths.get(
            SRC,
            MAIN,
            RESOURCES,
            weaponAccusedName + PNG
        ).toString();
        final String roomAccusedName = "BathRoom";
        final String roomAccPath = Paths.get(
            SRC,
            MAIN,
            RESOURCES,
            roomAccusedName + PNG
        ).toString();
        final Card weaponAccused = CardFactory.createWeaponCard(weaponAccusedName, weaponAccPath);
        final Card roomAccused = CardFactory.createRoomCard(roomAccusedName, roomAccPath);
        assertEquals(accusation.accuse(characterCard, weaponAccused, roomAccused, playerCards), Optional.of(characterCard));
        final String characterAccusedName = "Colonel Mustard";
        final String characterAccPath = Paths.get(
            SRC,
            MAIN,
            RESOURCES,
            characterAccusedName.replace(" ", "") + PNG
        ).toString();
        final Card characterAccused = CardFactory.createCharacterCard(characterAccusedName, characterAccPath);
        assertEquals(accusation.accuse(characterAccused, weaponAccused, roomAccused, playerCards), Optional.empty());
    }
}
