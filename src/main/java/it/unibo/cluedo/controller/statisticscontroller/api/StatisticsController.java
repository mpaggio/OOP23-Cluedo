package it.unibo.cluedo.controller.statisticscontroller.api;

import java.util.List;

/**
 * Interface of the controller to manage the statistics of the game.
 */
public interface StatisticsController {
    /**
     * Gets the full steps leaderboard.
     * @return the full steps leaderboard.
     */
    List<String> getFullStepsLeaderboard();
    /**
     * Get the statistic of steps of the player passed as input.
     * @param playerName the player name.
     * @return the value of the statistic.
     */
    Integer getStepsLeaderboard(String playerName);
    /**
     * Gets the full accusations leaderboard.
     * @return the full accusations leaderboard.
     */
    List<String> getFullAccusationsLeaderboard();
    /**
     * Get the statistic of accusations of the player passed as input.
     * @param playerName the player name.
     * @return the value of the statistic.
     */
    Integer getAccusationsLeaderboard(String playerName);
    /**
     * Gets the full rooms leaderboard.
     * @return the full rooms leaderboard.
     */
    List<String> getFullRoomsLeaderboard();
    /**
     * Get the statistic of rooms of the player passed as input.
     * @param playerName the player name.
     * @return the value of the statistic.
     */
    Integer getRoomLeaderboard(String playerName);
    /**
     * Gets the full cards leaderboard.
     * @return the full cards leaderboard.
     */
    List<String> getFullCardsLeaderboard();
    /**
     * Get the statistic of cards of the player passed as input.
     * @param playerName the player name.
     * @return the value of the statistic.
     */
    Integer getCardsLeaderboard(String playerName);
}
