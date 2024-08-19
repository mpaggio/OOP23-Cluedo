package it.unibo.cluedo.model.statistics.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.utilities.Pair;

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

    private Pair<List<Player>, List<Integer>> statSort(final Map<Player, Integer> map) {
        final List<Map.Entry<Player, Integer>> list = new ArrayList()<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Player, Integer>>() {
            @Override
            public int compare(final Map.Entry<Player, Integer> o1, final Map.Entry<Player, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        final Pair<List<Player>, List<Integer>> sorted = 
            new Pair<>(new LinkedList<>(), new LinkedList<>());
        for (final Map.Entry<Player, Integer> entry : list) {
            sorted.getFirst().add(entry.getKey());
            sorted.getSecond().add(entry.getValue());
        }
        return sorted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<List<Player>, List<Integer>> getAccusationsMade() {
        return statSort(accusations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<List<Player>, List<Integer>> getRoomsVisited() {
        return statSort(rooms);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<List<Player>, List<Integer>> getStepsMade() {
        return statSort(steps);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<List<Player>, List<Integer>> getViewedCards() {
        return statSort(cards);
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
