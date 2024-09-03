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
import it.unibo.cluedo.controller.statisticscontroller.impl.StatisticsControllerImpl;

/**
 * This class is used to show the leaderboard of the rooms visited.
 */
public class RoomStatisticView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int FONT_SIZE = 24;
    private static final int BORDER_SIZE = 20;
    private static final int STRUT_HEIGHT = 15;

    /**
     * Class constructor.
     */
    public RoomStatisticView() {
        final StatisticsController statisticsController = new StatisticsControllerImpl(Cluedo
            .CONTROLLER.getGameInstance().getStatistics());
        final JFrame window;
        window = new JFrame("Statistics");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        final JLabel title = new JLabel("Leaderboard for the rooms visited");
        title.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        title.setAlignmentX(CENTER_ALIGNMENT);
        window.add(Box.createVerticalStrut(STRUT_HEIGHT));

        while (statisticsController.roomsLeaderboardHasNext()) {
            final String entryString = statisticsController.getRoomsLeaderboard();
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
