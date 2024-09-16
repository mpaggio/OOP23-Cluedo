package it.unibo.cluedo.controller.statisticscontroller.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;

/**
 * Controller to manage the statistics of the game.
 */
public class StatisticsControllerImpl implements StatisticsController {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFullStepsLeaderboard() {
        return new ArrayList<>(Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getStepsMade()
            .getFirst()
            .stream()
            .map(Player::getUsername)
            .collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFullAccusationsLeaderboard() {
        return new ArrayList<>(Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getAccusationsMade()
            .getFirst()
            .stream()
            .map(Player::getUsername)
            .collect(Collectors.toList())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFullRoomsLeaderboard() {
        return new ArrayList<>(Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getRoomsVisited()
            .getFirst()
            .stream()
            .map(Player::getUsername)
            .collect(Collectors.toList())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFullCardsLeaderboard() {
        return new ArrayList<>(Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getViewedCards()
            .getFirst()
            .stream()
            .map(Player::getUsername)
            .collect(Collectors.toList())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getStepsLeaderboard(final String playerName) {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getStepsMade()
            .getSecond()
            .get(getFullStepsLeaderboard().indexOf(playerName)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getAccusationsLeaderboard(final String playerName) {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getAccusationsMade()
            .getSecond()
            .get(getFullAccusationsLeaderboard().indexOf(playerName)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCardsLeaderboard(final String playerName) {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getViewedCards()
            .getSecond()
            .get(getFullCardsLeaderboard().indexOf(playerName)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getRoomLeaderboard(final String playerName) {
        return Cluedo.CONTROLLER.getGameInstance()
            .getStatistics()
            .getRoomsVisited()
            .getSecond()
            .get(getFullRoomsLeaderboard().indexOf(playerName)
        );
    }
}
