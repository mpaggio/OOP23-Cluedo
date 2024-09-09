package it.unibo.cluedo.view.statistics;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.util.List;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;

/**
 * This class is used to show the leaderboard of the rooms visited.
 */
public class RoomStatisticView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int BORDER_SIZE = 20;
    private static final int STRUT_HEIGHT = 15;

    /**
     * Class constructor.
     */
    public RoomStatisticView() {
        final StatisticsController statisticsController = Cluedo.CONTROLLER.getStatisticsController();
        final List<String> roomsLeaderboard = statisticsController.getFullRoomsLeaderboard();
        super.add(Box.createVerticalStrut(STRUT_HEIGHT));

        roomsLeaderboard.forEach(player -> {
            final JLabel label = new JLabel("Player: " + player + " rooms visited: "
                + statisticsController
                .getRoomLeaderboard(player));
            super.add(label);
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
    }
}
