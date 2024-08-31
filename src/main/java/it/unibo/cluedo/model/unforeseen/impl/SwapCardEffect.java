package it.unibo.cluedo.model.unforeseen.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.api.Card.Type;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;

/**
 * This class represents an Unforeseen Card that allows a player to swap
 * on of their card with another player's card of the same type.
 */
public final class SwapCardEffect implements UnforeseenEffect {

    private final Player otherPlayer;
    private final Random random = new Random();

    /**
     * Constructor of the class.
     * @param otherPlayer the player with whom the current player will swap the card
     */
    public SwapCardEffect(final Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    @Override
    public void applyEffect(final Player player) {
        final List<Card> playerCards = player.getPlayerCards();
        final List<Card> otherPlayerCards = otherPlayer.getPlayerCards();

        if (!playerCards.isEmpty() && !otherPlayerCards.isEmpty()) {
            final Card playerCard = playerCards.get(random.nextInt(playerCards.size()));
            final Type cardType = playerCard.getType();

            final List<Card> matchingCards = otherPlayerCards.stream()
                    .filter(card -> card.getType() == cardType)
                    .collect(Collectors.toList());

            if (!matchingCards.isEmpty()) {
                final Card otherPlayerCard = matchingCards.get(random.nextInt(matchingCards.size()));
                playerCards.remove(playerCard);
                playerCards.add(otherPlayerCard);

                otherPlayerCards.remove(otherPlayerCard);
                otherPlayerCards.add(playerCard);

                if (player instanceof MutablePlayer) {
                    ((MutablePlayer) player).setPlayerCards(playerCards);
                }
                if (otherPlayer instanceof MutablePlayer) {
                    ((MutablePlayer) otherPlayer).setPlayerCards(otherPlayerCards);
                }
            }

        }

    }

    @Override
    public String getType() {
        return "SwapCard";
    }
}
