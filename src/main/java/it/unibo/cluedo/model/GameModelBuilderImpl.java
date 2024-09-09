package it.unibo.cluedo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.deck.api.Deck;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.SimplePlayerFactory;
import it.unibo.cluedo.model.player.impl.SimplePlayerFactoryImpl;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.utilities.TurnFase;
import java.util.Set;

/**
 * Implementation of the {@link GameModelBuilder} interface.
 * It is used to build a new instance of GameModel.
 */
public class GameModelBuilderImpl implements GameModelBuilder {

    private final List<Player> players = new ArrayList<>();
    private final Deck deck;
    private Set<Card> solution;
    private TurnManager turnManager;
    private Statistics statistics;
    private Board map;
    private Set<Card> allCards;
    private TurnFase fase;
    private final SimplePlayerFactory playerFactory;

    /**
     * Constructor of the GameModelBuilderImpl class.
     */
    public GameModelBuilderImpl() {
        this.deck = new DeckImpl();
        this.solution = new HashSet<>();
        this.playerFactory = new SimplePlayerFactoryImpl();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder addPlayer(final String username, final String color) {
        if (this.players.size() >= PLAYERS) {
            throw new IllegalArgumentException("Maximum number of players reached");
        }
        this.players.add(this.playerFactory.createPlayer(username, color));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder withTurnManager(final TurnManager turnManager) {
        this.turnManager = turnManager;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder withStatistics(final Statistics statistics) {
       this.statistics = statistics;
       return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder withMap(final Board map) {
        this.map = map;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder withAllCards(final Set<Card> cards) {
        this.allCards = cards;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder withTurnFase(final TurnFase fase) {
        this.fase = fase;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder withPlayer(final Player player) {
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
    public GameModelBuilder withSavedSolution(final Set<Card> solution) {
        this.solution = solution;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModel buildsaved() {
        if (this.turnManager == null) {
            throw new IllegalStateException("TurnManager must be set before building the saved game model");
        }
        if (this.statistics == null) {
            throw new IllegalStateException("Statistic must be set before building the saved game model");
        }
        if (this.map == null) {
            throw new IllegalStateException("Map must be set before building the saved game model");
        }
        if (this.players.size() != 3) {
            throw new IllegalStateException("Exactly 3 players must be set before building the saved game model");
        }
        if (this.solution == null || this.solution.size() != 3) {
            throw new IllegalStateException("A valid solution must be set before building the saved game model");
        }
        if (this.allCards == null) {
            throw new IllegalStateException("The set of all cards must be set before building the saved game model");
        }
        if (this.fase == null) {
            throw new IllegalStateException("The turn fase must be set before building the saved game model");
        }
        return new GameModelImpl(players, solution, turnManager, statistics, map, allCards, fase);
    }
}
