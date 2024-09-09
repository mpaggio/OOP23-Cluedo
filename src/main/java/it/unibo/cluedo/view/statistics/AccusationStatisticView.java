package it.unibo.cluedo.view.statistics;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;

/**
 * This class is used to show the leaderboard of the accusations made.
 */
public class AccusationStatisticView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int BORDER_SIZE = 20;
    private static final int STRUT_HEIGHT = 15;

    /**
     * Class constructor.
     */
    public AccusationStatisticView() {
        final StatisticsController statisticsController = Cluedo.CONTROLLER.getStatisticsController();
        final List<String> accusationsLeaderboard = statisticsController.getFullAccusationsLeaderboard();
        super.add(Box.createVerticalStrut(STRUT_HEIGHT));

        accusationsLeaderboard.forEach(player -> {
            final JLabel label = new JLabel("Player: " + player + " accusations made: "
                + statisticsController
                .getAccusationsLeaderboard(player));
            super.add(label);
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        setVisible(true);
    }
}
