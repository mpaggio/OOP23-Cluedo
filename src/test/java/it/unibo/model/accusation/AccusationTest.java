package it.unibo.model.accusation;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.accusation.impl.AccusationImpl;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.impl.CardFactory;

import java.util.Set;

final class AccusationTest {
    private static final String SRC = "src";
    private static final String MAIN = "main";
    private static final String RESOURCES = "resources";
    private static final String PNG = ".PNG";
    private static final String CHARACTER = "Miss Scarlett";
    private static final String WEAPON = "Candlestick";
    private static final String ROOM = "Kitchen";

    private final Accusation accusation = new AccusationImpl();

    @Test
    void testFinalAccusation() {
        final String charactePath = Paths.get(
            SRC,
            MAIN,
            RESOURCES, 
            CHARACTER.replace(" ", "") + PNG
        ).toString();
        final String weaponPath = Paths.get(
            SRC,
            MAIN,
            RESOURCES,
            WEAPON + PNG
        ).toString();
        final String roomPath  = Paths.get(
            SRC,
            MAIN,
            RESOURCES, 
            ROOM + ".PNG"
        ).toString();
        final Set<Card> solution = Set.of(CardFactory.createCharacterCard(CHARACTER, charactePath),
                            CardFactory.createWeaponCard(WEAPON, weaponPath),
                            CardFactory.createRoomCard(ROOM, roomPath));
        final String characterAccused = "Professor Plum";
        final String characterAccPath = Paths.get(
            SRC,
            MAIN,
            RESOURCES, 
            characterAccused.replace(" ", "") + PNG
        ).toString();
        assertFalse(accusation.finalAccuse(CardFactory.createCharacterCard(characterAccused, characterAccPath),
                                        CardFactory.createWeaponCard(WEAPON, weaponPath),
                                        CardFactory.createRoomCard(ROOM, roomPath),
                                        solution));
    }
}
