package it.unibo.cluedo.view.statistisc;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Font;

import it.unibo.cluedo.model.statistics.api.Statistics;
/**
 * This class is used to show the statistics of the game.
 */
public class StatisticsView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int FONT_SIZE = 24;
    private static final int BORDER_SIZE = 20;
    //private final Statistics playersStatistics;
    /**
     * Class constructor.
     * @param statistics the statistics to show.
     */
    public StatisticsView(final Statistics statistics) {
        //impostazione della finestra
        final JFrame window;
        window = new JFrame("Statistics");
        //playersStatistics = statistics;
        //playersStatistics.getAccusationsMade();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        //label principale
        final JLabel label = new JLabel("Choose a statistic to view");
        label.setForeground(Color.GREEN);
        label.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        //menu per selezionare la statistica
        final String[] options = {"Steps", "Rooms", "Accusations", "Cards"};
        final JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String selected = comboBox.getSelectedItem().toString();
                updateStatisticView(selected);
            }
        });
        //Layout del pannello
        setLayout(new BorderLayout());
        add(comboBox, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);

        add(this);
        setVisible(true);
    }

    private void updateStatisticView(final String selectedView) {
        //each case should create a view for the selected statistic
        switch (selectedView) {
            case "Steps":
                break;
            case "Rooms":
                break;
            case "Accusations":
                break;
            case "Cards":
                break;
            default:
                break;
        }
    }
}
