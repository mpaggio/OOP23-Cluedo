package it.unibo.cluedo.controller.statisticscontroller.impl;

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
        this.stepsLeaderboardIterator = statistics.getStepsMade().getFirst().iterator();
        this.accusationsLeaderboardIterator = statistics.getAccusationsMade().getFirst().iterator();
        this.roomsLeaderboardIterator = statistics.getRoomsVisited().getFirst().iterator();
        this.cardsLeaderboardIterator = statistics.getViewedCards().getFirst().iterator();
        this.stepsIterator = statistics.getAccusationsMade().getSecond().iterator();
        this.accusationIterator = statistics.getAccusationsMade().getSecond().iterator();
        this.roomsIterator = statistics.getRoomsVisited().getSecond().iterator();
        this.cardsIterator = statistics.getViewedCards().getSecond().iterator();
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
