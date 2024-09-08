package it.unibo.cluedo.view.statistisc;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

/**
 * This class is used to show the statistics of the game.
 */
public class StatisticsView extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Class constructor.
     */
    public StatisticsView() {
        setLayout(new GridLayout(4, 1));
        final StepStatisticView stepStatisticView = new StepStatisticView();
        final RoomStatisticView roomStatisticView = new RoomStatisticView();
        final AccusationStatisticView accusationStatisticView = new AccusationStatisticView();
        final CardStatisticView cardStatisticView = new CardStatisticView();
        stepStatisticView.setBorder(BorderFactory.createTitledBorder("Step statistics"));
        super.add(stepStatisticView);
        cardStatisticView.setBorder(BorderFactory.createTitledBorder("Card statistics"));
        super.add(cardStatisticView);
        roomStatisticView.setBorder(BorderFactory.createTitledBorder("Room statistics"));
        super.add(roomStatisticView);
        accusationStatisticView.setBorder(BorderFactory.createTitledBorder("Accusation statistics"));
        super.add(accusationStatisticView);
    }
}
