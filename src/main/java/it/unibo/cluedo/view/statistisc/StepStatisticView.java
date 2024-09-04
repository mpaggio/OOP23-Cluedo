package it.unibo.cluedo.view.statistisc;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JFrame;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;

/**
 * This class is used to show the leaderboard of the steps made.
 */
public class StepStatisticView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int FONT_SIZE = 24;
    private static final int BORDER_SIZE = 20;
    private static final int STRUT_HEIGHT = 15;

    /**
     * Class constructor.
     */
    public StepStatisticView() {
        final StatisticsController statisticsController = Cluedo.CONTROLLER.getStatisticsController();
        final JFrame window;
        window = new JFrame("Statistics");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        final JLabel title = new JLabel("Leaderboard for the steps made");
        title.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        title.setAlignmentX(CENTER_ALIGNMENT);
        window.add(Box.createVerticalStrut(STRUT_HEIGHT));

        while (statisticsController.stepsLeaderboardHasNext()) {
            final String entryString = statisticsController.getStepsLeaderboard();
            final JLabel entryLabel = new JLabel(entryString);
            entryLabel.setFont(new Font("Serif", Font.PLAIN, FONT_SIZE));
            window.add(entryLabel);
        }

        super.add(title);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        super.add(this);
        setVisible(true);
    }
}
