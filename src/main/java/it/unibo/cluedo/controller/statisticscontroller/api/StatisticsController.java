package it.unibo.cluedo.controller.statisticscontroller.api;

import java.util.List;

/**
 * Interface of the controller to manage the statistics of the game.
 */
public interface StatisticsController {
    /**
     * Gets the full steps leaderboard containing the names ({@link String}) of the players.
     * @return a {@link List} containing the full steps leaderboard.
     */
    List<String> getFullStepsLeaderboard();
    /**
     * Get the statistic of steps of the {@link Player} passed as input.
     * @param playerName the player name.
     * @return an {@link Integer} value of the statistic.
     */
    Integer getStepsLeaderboard(String playerName);
    /**
     * Gets the full accusation leaderboard containing the names ({@link String}) of the players.
     * @return a {@link List} containing the full accusations leaderboard.
     */
    List<String> getFullAccusationsLeaderboard();
    /**
     * Get the statistic of accusations of the {@link Player} passed as input.
     * @param playerName the player name.
     * @return the value of the statistic.
     */
    Integer getAccusationsLeaderboard(String playerName);
    /**
     * Gets the full rooms leaderboard containing the names ({@link String}) of the players.
     * @return a {@link List} containing the full rooms leaderboard.
     */
    List<String> getFullRoomsLeaderboard();
    /**
     * Get the statistic of rooms of the player passed as input.
     * @param playerName the player name.
     * @return the value of the statistic.
     */
    Integer getRoomLeaderboard(String playerName);
    /**
     * Gets the full cards leaderboard containing the names ({@link String}) of the players.
     * @return the full cards leaderboard.
     */
    List<String> getFullCardsLeaderboard();
    /**
     * Get the statistic of cards of the player passed as input.
     * @param playerName the player name.
     * @return a {@link List} containing the value of the statistic.
     */
    Integer getCardsLeaderboard(String playerName);
}
