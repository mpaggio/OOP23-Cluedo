package it.unibo.cluedo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.deck.api.Deck;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.PlayerBuilder;
import it.unibo.cluedo.model.player.impl.PlayerBuilderImpl;
import java.util.Set;

/**
 * Implementation of the {@link GameModelBuilder} interface.
 * It is used to build a new instance of GameModel.
 */
public class GameModelBuilderImpl implements GameModelBuilder {

    private final List<Player> players = new ArrayList<>();
    private final Deck deck;
    private Set<Card> solution;

    /**
     * Constructor of the GameModelBuilderImpl class.
     * @param deck
     */
    public GameModelBuilderImpl(final Deck deck) {
        this.deck = deck;
        this.solution = new HashSet<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder addPlayer(final String username, final String color) {
        final PlayerBuilder builder = new PlayerBuilderImpl();
        final Player player = builder.username(username).color(color).buildPlayer();
        players.forEach(p -> {
            if (p.getUsername().equals(player.getUsername()) || p.getColor().equals(player.getColor())) {
                throw new IllegalArgumentException("Player with the same username or color already exists");
            }
        });
        if (this.players.size() >= PLAYERS) {
            throw new IllegalArgumentException("Maximum number of players reached");
        }
        this.players.add(player);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder withGameSolution() {
        this.solution = deck.drawSolution();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModel build() {
        if (this.solution.size() != 3) {
            throw new IllegalStateException("The game solution must be set before building the game model");
        }
        if (this.players.isEmpty()) {
            throw new IllegalStateException("At least one player must be added before building the game");
        }
        if (this.players.size() != 3) {
            throw new IllegalStateException("Exactly 3 players must be added before building the game");
        }
        return new GameModelImpl(this.players, this.deck, this.solution);
    }
}
