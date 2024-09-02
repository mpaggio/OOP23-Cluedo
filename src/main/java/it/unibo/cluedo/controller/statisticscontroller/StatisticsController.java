package it.unibo.cluedo.controller.statisticscontroller;

import java.util.List;
import java.util.Iterator;

import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.player.api.Player;
/**
 * Controller to manage the statistics of the game.
 */
public class StatisticsController {

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
    public StatisticsController(final Statistics statistics) {
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
     * Getter for the next player in the steps leaderboard.
     * @return the next player in the leaderboard.
     */
    public Player getStepsLeaderboardNext() {
        return stepsLeaderboardIterator.hasNext() ? stepsLeaderboardIterator.next() : null;
    }

    /**
     * Getter for the next player in the accusations leaderboard.
     * @return the next player in the leaderboard.
     */
    public Player getAccusationsLeaderboardNext() {
        return accusationsLeaderboardIterator.hasNext() ? accusationsLeaderboardIterator.next() : null;
    }

    /**
     * Getter for the next player in the rooms leaderboard.
     * @return the next player in the leaderboard.
     */
    public Player getRoomsLeaderboardNext() {
        return roomsLeaderboardIterator.hasNext() ? roomsLeaderboardIterator.next() : null;
    }

    /**
     * Getter for the next player in the cards leaderboard.
     * @return the next player in the leaderboard.
     */
    public Player getCardsLeaderboardNext() {
        return cardsLeaderboardIterator.hasNext() ? cardsLeaderboardIterator.next() : null;
    }

    /**
     * Getter for the next value in the steps leaderboard.
     * @return the next value in the leaderboard.
     */
    public Integer getStepsValuesNext() {
        return stepsIterator.hasNext() ? stepsIterator.next() : null;
    }

    /**
     * Getter for the next value in the accusations leaderboard.
     * @return the next value in the leaderboard.
     */
    public Integer getAccusationsValuesNext() {
        return accusationIterator.hasNext() ? accusationIterator.next() : null;
    }

    /**
     * Getter for the next value in the rooms leaderboard.
     * @return the next value in the leaderboard.
     */
    public Integer getRoomsValuesNext() {
        return roomsIterator.hasNext() ? roomsIterator.next() : null;
    }

    /**
     * Getter for the next value in the cards leaderboard.
     * @return the next value in the leaderboard.
     */
    public Integer getCardsValuesNext() {
        return cardsIterator.hasNext() ? cardsIterator.next() : null;
    }
}
