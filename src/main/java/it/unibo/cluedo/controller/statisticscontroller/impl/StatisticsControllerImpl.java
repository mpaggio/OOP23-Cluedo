package it.unibo.cluedo.controller.statisticscontroller.impl;

import java.util.List;
import java.util.Iterator;

import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.player.api.Player;
/**
 * Controller to manage the statistics of the game.
 */
public class StatisticsControllerImpl implements StatisticsController {

    private static final String PLAYER = "player: ";
    private static final String STEPS = " steps: ";
    private static final String ACCUSATIONS = " accusations: ";
    private static final String ROOMS = " rooms: ";

    private final Iterator<Player> stepsLeaderboardIterator;
    private final Iterator<Player> accusationsLeaderboardIterator;
    private final Iterator<Player> roomsLeaderboardIterator;
    private final Iterator<Player> cardsLeaderboardIterator;
    private final Iterator<Integer> stepsIterator;
    private final Iterator<Integer> accusationIterator;
    private final Iterator<Integer> roomsIterator;
    private final Iterator<Integer> cardsIterator;

    /**
     * Constructor for the StatisticsController class.
     * @param statistics the statistics of the game.
     */
    public StatisticsControllerImpl(final Statistics statistics) {
        final List<Player> stepsLeaderboard;
        final List<Player> accusationsLeaderboard;
        final List<Player> roomsLeaderboard;
        final List<Player> cardsLeaderboard;
        final List<Integer> stepsValues;
        final List<Integer> accusationsValues;
        final List<Integer> roomsValues;
        final List<Integer> cardsValues;
        stepsLeaderboard = statistics.getStepsMade().getFirst();
        accusationsLeaderboard = statistics.getAccusationsMade().getFirst();
        roomsLeaderboard = statistics.getRoomsVisited().getFirst();
        cardsLeaderboard = statistics.getViewedCards().getFirst();
        stepsValues = statistics.getAccusationsMade().getSecond();
        accusationsValues = statistics.getAccusationsMade().getSecond();
        roomsValues = statistics.getRoomsVisited().getSecond();
        cardsValues = statistics.getViewedCards().getSecond();
        //inizializzo ora gli iteratori
        this.stepsLeaderboardIterator = stepsLeaderboard.iterator();
        this.accusationsLeaderboardIterator = accusationsLeaderboard.iterator();
        this.roomsLeaderboardIterator = roomsLeaderboard.iterator();
        this.cardsLeaderboardIterator = cardsLeaderboard.iterator();
        this.stepsIterator = stepsValues.iterator();
        this.accusationIterator = accusationsValues.iterator();
        this.roomsIterator = roomsValues.iterator();
        this.cardsIterator = cardsValues.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean stepsLeaderboardHasNext() {
        return stepsLeaderboardIterator.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accusationsLeaderboardHasNext() {
        return accusationsLeaderboardIterator.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean roomsLeaderboardHasNext() {
        return roomsLeaderboardIterator.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cardsLeaderboardHasNext() {
        return cardsLeaderboardIterator.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStepsLeaderboard() {
        final String playerName = stepsLeaderboardIterator.next().getUsername();
        final int steps = stepsIterator.next();
        return PLAYER + playerName + STEPS + steps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAccusationsLeaderboard() {
        final String playerName = accusationsLeaderboardIterator.next().getUsername();
        final int accusations = accusationIterator.next();
        return PLAYER + playerName + ACCUSATIONS + accusations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCardsLeaderboard() {
        final String playerName = accusationsLeaderboardIterator.next().getUsername();
        final int cards = cardsIterator.next();
        return PLAYER + playerName + ACCUSATIONS + cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRoomsLeaderboard() {
        final String playerName = roomsLeaderboardIterator.next().getUsername();
        final int rooms = roomsIterator.next();
        return PLAYER + playerName + ROOMS + rooms;
    }
}
