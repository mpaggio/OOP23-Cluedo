package it.unibo.cluedo.controller.statisticscontroller.api;

/**
 * Interface of the controller to manage the statistics of the game.
 */
public interface StatisticsController {
    /**
     * Check if the steps leaderboard has a next element.
     * @return true if there is a next element, false otherwise.
     */
    boolean stepsLeaderboardHasNext();
    /**
     * Get the next element of the steps leaderboard.
     * @return the next element of the steps leaderboard.
     */
    String getStepsLeaderboard();
    /**
     * Check if the accusations leaderboard has a next element.
     * @return true if there is a next element, false otherwise.
     */
    boolean accusationsLeaderboardHasNext();
    /**
     * Get the next element of the accusations leaderboard.
     * @return the next element of the accusations leaderboard.
     */
    String getAccusationsLeaderboard();
    /**
     * Check if the rooms leaderboard has a next element.
     * @return true if there is a next element, false otherwise.
     */
    boolean roomsLeaderboardHasNext();
    /**
     * Get the next element of the rooms leaderboard.
     * @return the next element of the rooms leaderboard.
     */
    String getRoomsLeaderboard();
    /**
     * Check if the cards leaderboard has a next element.
     * @return true if there is a next element, false otherwise.
     */
    boolean cardsLeaderboardHasNext();
    /**
     * Get the next element of the cards leaderboard.
     * @return the next element of the cards leaderboard.
     */
    String getCardsLeaderboard();
}
