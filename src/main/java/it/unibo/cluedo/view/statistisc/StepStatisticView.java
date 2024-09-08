package it.unibo.cluedo.view.statistisc;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Box;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;

/**
 * This class is used to show the leaderboard of the steps made.
 */
public class StepStatisticView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int STRUT_HEIGHT = 15;

    /**
     * Class constructor.
     */
    public StepStatisticView() {
        final StatisticsController statisticsController = Cluedo.CONTROLLER.getStatisticsController();
        final List<String> stepsLeaderboard = statisticsController.getFullStepsLeaderboard();
        super.add(Box.createVerticalStrut(STRUT_HEIGHT));

        stepsLeaderboard.forEach(player -> {
            final JLabel label = new JLabel("Player: " + player + " steps made: " +
                statisticsController
                .getStepsLeaderboard(player));
            super.add(label);
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
    }
}
