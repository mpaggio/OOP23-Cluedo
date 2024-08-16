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
    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players.add(new PlayerImpl("Player1", "Blue"));
        players.add(new PlayerImpl("Player2", "Red"));
        players.add(new PlayerImpl("Player3", "Green"));
        stats = new StatisticsImpl(players);
    }

    @Test
    void testSteps() {
        stats.incrementSteps(players.get(0), STEPS_FOR_PLAYER1_1);
        stats.incrementSteps(players.get(0), STEPS_FOR_PLAYER1_2);
        stats.incrementSteps(players.get(1), STEPS_FOR_PLAYER2);
        stats.incrementSteps(players.get(2), STEPS_FOR_PLAYER3);
        assertEquals(stats.getStepsMade().get(players.get(0)), STEPS_FOR_PLAYER1);
        assertEquals(stats.getStepsMade().get(players.get(1)), STEPS_FOR_PLAYER2);
        assertEquals(stats.getStepsMade().get(players.get(2)), STEPS_FOR_PLAYER3);
        //Expected leaderboard: Player1, Player3, Player2
        final Map<Player, Integer> leaderboard = new LinkedHashMap<>();
        leaderboard.put(players.get(0), STEPS_FOR_PLAYER1);
        leaderboard.put(players.get(2), STEPS_FOR_PLAYER3);
        leaderboard.put(players.get(1), STEPS_FOR_PLAYER2);
        assertEquals(leaderboard, stats.getStepsMade());
    }
}
