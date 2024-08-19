package it.unibo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.PlayerImpl;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.statistics.impl.StatisticsImpl;

import java.util.List;
import java.util.ArrayList;

/**
 * This class contains the tests for the Statistics class.
 */
final class StatisticsTest {

    private static final int STEPS_FOR_PLAYER1_1 = 5;
    private static final int STEPS_FOR_PLAYER1_2 = 2;
    private static final int STEPS_FOR_PLAYER1 = 7;
    private static final int STEPS_FOR_PLAYER2 = 2;
    private static final int STEPS_FOR_PLAYER3 = 3;

    private List<Player> players;
    private Statistics stats;
    private List<Player> playersLeaderboard;
    private List<Integer>  statsLeaderboard;

    /**
     * Set up the players and the statistics before each test.
     */
    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players.add(new PlayerImpl("Player1", "Blue"));
        players.add(new PlayerImpl("Player2", "Red"));
        players.add(new PlayerImpl("Player3", "Green"));
        stats = new StatisticsImpl(players);
        playersLeaderboard = new ArrayList<>();
        statsLeaderboard = new ArrayList<>();
    }

    /**
     * Tests that the steps are correctly incremented by the method incrementSteps and
     * weather the leaderboard is correct.
     */
    @Test
    void testSteps() {
        stats.incrementSteps(players.get(0), STEPS_FOR_PLAYER1_1);
        stats.incrementSteps(players.get(0), STEPS_FOR_PLAYER1_2);
        stats.incrementSteps(players.get(1), STEPS_FOR_PLAYER2);
        stats.incrementSteps(players.get(2), STEPS_FOR_PLAYER3);
        assertEquals(stats.getStepsMade().getSecond().get(0), STEPS_FOR_PLAYER1);
        assertEquals(stats.getStepsMade().getSecond().get(1), STEPS_FOR_PLAYER3);
        assertEquals(stats.getStepsMade().getSecond().get(2), STEPS_FOR_PLAYER2);
        //Expected leaderboard: Player1, Player3, Player2
        playersLeaderboard.add(players.get(0));
        playersLeaderboard.add(players.get(2));
        playersLeaderboard.add(players.get(1));
        assertEquals(playersLeaderboard, stats.getStepsMade().getFirst());
        statsLeaderboard.add(STEPS_FOR_PLAYER1);
        statsLeaderboard.add(STEPS_FOR_PLAYER3);
        statsLeaderboard.add(STEPS_FOR_PLAYER2);
        assertEquals(statsLeaderboard, stats.getStepsMade().getSecond());
    }

    /**
     * Tests that the steps are correctly incremented by the method incrementRoomsVisited and
     * weather the leaderboard is correct.
     */
    @Test
    void testRoomVisited() {
        stats.incrementRoomsVisited(players.get(0));
        stats.incrementRoomsVisited(players.get(0));
        stats.incrementRoomsVisited(players.get(1));
        assertEquals(stats.getRoomsVisited().getSecond().get(0), 2);
        assertEquals(stats.getRoomsVisited().getSecond().get(1), 1);
        assertEquals(stats.getRoomsVisited().getSecond().get(2), 0);
        //Expected leaderboard: Player1, Player2, Player3
        playersLeaderboard.add(players.get(0));
        playersLeaderboard.add(players.get(1));
        playersLeaderboard.add(players.get(2));
        assertEquals(playersLeaderboard, stats.getRoomsVisited().getFirst());
        statsLeaderboard.add(2);
        statsLeaderboard.add(1);
        statsLeaderboard.add(0);
        assertEquals(statsLeaderboard, stats.getRoomsVisited().getSecond());
    }

    /**
     * Tests that the accusations made are correctly incremented by the method 
     * incrementRoomsVisited and weather the leaderboard is correct.
     */
    @Test
    void testAccusationsMade() {
        stats.incrementAccusationsMade(players.get(0));
        stats.incrementAccusationsMade(players.get(1));
        stats.incrementAccusationsMade(players.get(1));
        stats.incrementAccusationsMade(players.get(1));
        stats.incrementAccusationsMade(players.get(2));
        stats.incrementAccusationsMade(players.get(2));
        assertEquals(stats.getAccusationsMade().getSecond().get(0), 3);
        assertEquals(stats.getAccusationsMade().getSecond().get(1), 2);
        assertEquals(stats.getAccusationsMade().getSecond().get(2), 1);
        //Expected leaderboard: Player2, Player3, Player1
        playersLeaderboard.add(players.get(1));
        playersLeaderboard.add(players.get(2));
        playersLeaderboard.add(players.get(0));
        assertEquals(playersLeaderboard, stats.getAccusationsMade().getFirst());
        statsLeaderboard.add(3);
        statsLeaderboard.add(2);
        statsLeaderboard.add(1);
        assertEquals(statsLeaderboard, stats.getAccusationsMade().getSecond());
    }

    /**
     * Tests that the viewed cards are correctly incremented by the method 
     * incrementRoomsVisited and weather the leaderboard is correct.
     */
    @Test
    void testViewedCards() {
        stats.incrementViewedCards(players.get(0));
        stats.incrementViewedCards(players.get(1));
        stats.incrementViewedCards(players.get(1));
        stats.incrementViewedCards(players.get(1));
        stats.incrementViewedCards(players.get(2));
        stats.incrementViewedCards(players.get(2));
        assertEquals(stats.getViewedCards().getSecond().get(0), 3);
        assertEquals(stats.getViewedCards().getSecond().get(1), 2);
        assertEquals(stats.getViewedCards().getSecond().get(2), 1);
        //Expected leaderboard: Player2, Player3, Player1
        playersLeaderboard.add(players.get(1));
        playersLeaderboard.add(players.get(2));
        playersLeaderboard.add(players.get(0));
        assertEquals(playersLeaderboard, stats.getViewedCards().getFirst());
        statsLeaderboard.add(3);
        statsLeaderboard.add(2);
        statsLeaderboard.add(1);
        assertEquals(statsLeaderboard, stats.getViewedCards().getSecond());
    }
}
