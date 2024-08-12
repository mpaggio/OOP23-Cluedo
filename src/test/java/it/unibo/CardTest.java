package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.impl.CardFactory;

/**
 * Test class for CardImpl and CardFactory
 */
final class CardTest {
    private static final String CHARACTER_NAME = "Professor Plum";
    private static final String WEAPON_NAME = "Professor Plum";
    private static final String ROOM_NAME = "Professor Plum";

    /**
     * Test the creation of a character card using CardFactory
     */
    @Test
    void testCreateCharacterCard() {
        final String imagePath = Paths.get(
            "src",
            "main",
            "resources", 
            CHARACTER_NAME.replace(" ", "") + ".PNG"
        ).toString();
        final Card characterCard = CardFactory.createCharacterCard(CHARACTER_NAME, imagePath);
        assertEquals(Card.Type.CHARACTER, characterCard.getType(), "Card type should be CHARACTER");
        assertEquals(CHARACTER_NAME, characterCard.getName(), "Card name should match");
        assertTrue(Files.exists(Paths.get(imagePath)), "Card image path should take to a file");
    }

    /**
     * Test the creation of a weapon card using CardFactory
     */
    @Test
    void testCreateWeaponCard() {
        final String imagePath = Paths.get(
            "src",
            "main",
            "resources", 
            WEAPON_NAME.replace(" ", "") + ".PNG"
        ).toString();
        final Card weaponCard = CardFactory.createWeaponCard(WEAPON_NAME, imagePath);
        assertEquals(Card.Type.WEAPON, weaponCard.getType(), "Card type should be WEAPON");
        assertEquals(WEAPON_NAME, weaponCard.getName(), "Card name should match");
        assertTrue(Files.exists(Paths.get(imagePath)), "Card image path should take to a file");
    }

    /**
     * Test the creation of a room card using CardFactory
     */
    @Test
    void testCreateRoomCard() {
        final String imagePath = Paths.get(
            "src",
            "main",
            "resources", 
            ROOM_NAME.replace(" ", "") + ".PNG"
        ).toString();
        final Card roomCard = CardFactory.createRoomCard(ROOM_NAME, imagePath);
        assertEquals(Card.Type.ROOM, roomCard.getType(), "Card type should be ROOM");
        assertEquals(ROOM_NAME, roomCard.getName(), "Card name should match");
        assertTrue(Files.exists(Paths.get(imagePath)), "Card image path should take to a file");
    }
}
