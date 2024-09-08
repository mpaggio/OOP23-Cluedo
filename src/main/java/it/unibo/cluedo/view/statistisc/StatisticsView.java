package it.unibo.cluedo.view.statistisc;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Font;

/**
 * This class is used to show the statistics of the game.
 */
public class StatisticsView extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int FONT_SIZE = 24;
    private static final int BORDER_SIZE = 20;
    //private final Statistics playersStatistics;
    /**
     * Class constructor.
     */
    public StatisticsView() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JLabel label = new JLabel("Choose a statistic to view");
        label.setForeground(Color.GREEN);
        label.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        final String[] options = {"Steps", "Rooms", "Accusations", "Cards"};
        final JComboBox<String> comboBox = new JComboBox<>(options);
        final JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String selected = comboBox.getSelectedItem().toString();
                updateStatisticView(selected);
            }
        });
        panel.add(confirmButton, BorderLayout.SOUTH);
        panel.add(comboBox, BorderLayout.CENTER);
        panel.add(label, BorderLayout.NORTH);
        super.add(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void updateStatisticView(final String selectedView) {
        switch (selectedView) {
            case "Steps":
                JOptionPane.showMessageDialog(
                    null,
                    new StepStatisticView(),
                    "Steps Leaderboard",
                    JOptionPane.PLAIN_MESSAGE
                );
                break;
            case "Rooms":
            JOptionPane.showMessageDialog(
                null,
                new RoomStatisticView(),
                "Rooms Leaderboard",
                JOptionPane.PLAIN_MESSAGE
            );
                break;
            case "Accusations":
            JOptionPane.showMessageDialog(
                null,
                new AccusationStatisticView(),
                "Accusations Leaderboard",
                JOptionPane.PLAIN_MESSAGE
            );
                break;
            case "Cards":
            JOptionPane.showMessageDialog(
                null,
                new CardStatisticView(),
                "Cards Leaderboard",
                JOptionPane.PLAIN_MESSAGE
            );
                break;
            default:
                break;
        }
    }
}
