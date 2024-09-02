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

public class StatisticsView extends JPanel {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    
    final JFrame window;
    final Statistics playersStatistics;

    public StatisticsView(final Statistics statistics) {
        //impostazione della finestra
        window = new JFrame("Statistics");
        playersStatistics = statistics;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        //label principale
        final JLabel label = new JLabel("Choose a statistic to view");
        label.setForeground(new Color(1, 200, 32));
        label.setFont(new Font("Serif", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //menu per selezionare la statistica
        final String[] options = {"Steps", "Rooms", "Accusations", "Cards"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String selected = comboBox.getSelectedItem().toString();
                updateStatisticView(selected);
            }
        });
        //Layout del pannello
        setLayout(new BorderLayout());
        add(comboBox, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);

        window.add(this);
        window.setVisible(true);
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
