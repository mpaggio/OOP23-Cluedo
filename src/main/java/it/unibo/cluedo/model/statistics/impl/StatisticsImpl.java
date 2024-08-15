package it.unibo.cluedo.model.statistics.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

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
    private Map<Player, Integer> mapSort(final Map<Player, Integer> map) {
        final List<Map.Entry<Player, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Player, Integer>>() {
            @Override
            public int compare(final Map.Entry<Player, Integer> o1, final Map.Entry<Player, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        final Map<Player, Integer> sortedMap = new LinkedHashMap<>();
        for (final Map.Entry<Player, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getAccusationsMade() {
        return mapSort(accusations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getRoomsVisited() {
        return mapSort(rooms);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getStepsMade() {
        return mapSort(steps);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Integer> getViewedCards() {
        return mapSort(cards);
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
