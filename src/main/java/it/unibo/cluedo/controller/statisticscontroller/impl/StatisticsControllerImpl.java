package it.unibo.cluedo.controller.statisticscontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;

/**
 * Controller to manage the statistics of the game.
 */
public class StatisticsControllerImpl implements StatisticsController {

    private static final String PLAYER = "player: ";
    private static final String STEPS = " steps: ";
    private static final String ACCUSATIONS = " accusations: ";
    private static final String ROOMS = " rooms: ";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean stepsLeaderboardHasNext() {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getStepsMade()
            .getFirst()
            .iterator()
            .hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accusationsLeaderboardHasNext() {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getRoomsVisited()
            .getFirst()
            .iterator()
            .hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean roomsLeaderboardHasNext() {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getAccusationsMade()
            .getFirst()
            .iterator()
            .hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cardsLeaderboardHasNext() {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getViewedCards()
            .getFirst()
            .iterator()
            .hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStepsLeaderboard() {
        final String playerName = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getStepsMade()
            .getFirst()
            .iterator()
            .next()
            .getUsername();
        final int steps = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getAccusationsMade()
            .getSecond()
            .iterator()
            .next();
        return PLAYER + playerName + STEPS + steps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAccusationsLeaderboard() {
        final String playerName = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getAccusationsMade()
            .getFirst()
            .iterator()
            .next()
            .getUsername();
        final int accusations = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getAccusationsMade()
            .getSecond()
            .iterator()
            .next();
        return PLAYER + playerName + ACCUSATIONS + accusations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCardsLeaderboard() {
        final String playerName = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getViewedCards()
            .getFirst()
            .iterator()
            .next()
            .getUsername();
        final int cards = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getViewedCards()
            .getSecond()
            .iterator()
            .next();
        return PLAYER + playerName + ACCUSATIONS + cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRoomsLeaderboard() {
        final String playerName = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getRoomsVisited()
            .getFirst()
            .iterator()
            .next()
            .getUsername();
        final int rooms = Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getRoomsVisited()
            .getSecond()
            .iterator()
            .next();
        return PLAYER + playerName + ROOMS + rooms;
    }
}
