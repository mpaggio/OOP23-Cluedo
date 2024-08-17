package it.unibo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.PlayerImpl;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.statistics.impl.StatisticsImpl;
import it.unibo.cluedo.utilities.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

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
     * Test that the steps are correctly incremented by the method incrementSteps and
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
     * Test that the steps are correctly incremented by the method incrementRoomsVisited and
     * weather the leaderboard is correct.
     */
    @Test
    void testRoomVisited() {
        
    }

    @Test
    void testAccusationsMade() {
        
    }
}
