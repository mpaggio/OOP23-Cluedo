package it.unibo.cluedo.model.statistics.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.statistics.api.Statistics;

/**
 * This class implements the Statistics interface.
 */

public class StatisticsImpl implements Statistics {

    private final Map<Player, Integer> steps = new HashMap<>();
    private final Map<Player, Integer> rooms = new HashMap<>();
    private final Map<Player, Integer> accusations = new HashMap<>();
    private final Map<Player, Integer> cards = new HashMap<>();

    /**
     * Constructor for this class which sets all the statistics to 0 for
     * each player.
     * @param players the list of the players in the current game.
     */
    public StatisticsImpl(final List<Player> players) {
        players.stream().forEach(player -> {
            steps.put(player, Integer.valueOf(0));
            rooms.put(player, Integer.valueOf(0));
            accusations.put(player, Integer.valueOf(0));
            cards.put(player, Integer.valueOf(0));
        });
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getAccusationsMade() {
        // TODO Auto-generated method stub
        return new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getRoomsVisited() {
        // TODO Auto-generated method stub
        return new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getStepsMade() {
        // TODO Auto-generated method stub
        return new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getViewedCards() {
        // TODO Auto-generated method stub
        return new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementAccusationsMade(final Player player) {
        accusations.replace(player, Integer.valueOf(accusations.get(player).intValue() + 1)); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementRoomsVisited(final Player player) {
        rooms.replace(player, Integer.valueOf(rooms.get(player).intValue() + 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementSteps(final Player player, final int stepsMade) {
        steps.replace(player, Integer.valueOf(steps.get(player).intValue() + stepsMade));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementViewedCards(final Player player) {
        cards.replace(player, Integer.valueOf(cards.get(player).intValue() + 1));
    }
}
