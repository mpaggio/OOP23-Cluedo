package it.unibo.model.unforeseen;

import java.util.List;
import java.util.Objects;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.unforeseen.impl.SwapCardEffect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * This class represents a test class for the SwapCardEffect class.
 */
class SwapCardEffectTest {

    /**
     * This method tests the applyEffect method of the SwapCardEffect class.
     */
    @Test
    void testApplyEffect() {
        final Card weaponCard1 = new TestCard("Revolver", Card.Type.WEAPON);
        final Card weaponCard2 = new TestCard("Candlestick", Card.Type.WEAPON);

        final Player player1 = new MutablePlayerImpl("Player1", "Red");
        final Player player2 = new MutablePlayerImpl("Player2", "Blue");
        final MutablePlayer mutablePlayer1 = (MutablePlayer) player1;
        final MutablePlayer mutablePlayer2 = (MutablePlayer) player2;

        mutablePlayer1.setPlayerCards(List.of(weaponCard1));
        mutablePlayer2.setPlayerCards(List.of(weaponCard2));

        final SwapCardEffect effect = new SwapCardEffect(player2);
        effect.applyEffect(player1);

        assertEquals(List.of(weaponCard2), player1.getPlayerCards());
        assertEquals(List.of(weaponCard1), player2.getPlayerCards());
    }

    /**
     * This class represents a test card.
     */
    private static class TestCard implements Card {
        private final String name;
        private final Type type;

        TestCard(final String name, final Type type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Type getType() {
            return type;
        }

        @Override
        public String getImagePath() {
            return null;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            } else if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final TestCard testCard = (TestCard) o;
            return Objects.equals(name, testCard.name) && type == testCard.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type);
        }
    }
}
