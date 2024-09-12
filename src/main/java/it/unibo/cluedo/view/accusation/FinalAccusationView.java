package it.unibo.cluedo.view.accusation;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.Window;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.view.statistics.StatisticsView;

/**
 * This class is used to show the final accusation view.
 */
public class FinalAccusationView extends JDialog {
    private static final long serialVersionUID = 1L;

    /**
     * Method to initialize the view.
     */
    public void initializeView() {
        if (!"Cluedo".equals(Cluedo.CONTROLLER.getAccusationController().getRoomName())) {
            JOptionPane.showMessageDialog(null, "You can't make the final accusation here.");
            return;
        }
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JComboBox<String> suspectComboBox = new JComboBox<>(DeckImpl.getCharacterNames().toArray(new String[0]));
        final JComboBox<String> weaponComboBox = new JComboBox<>(DeckImpl.getWeaponNames().toArray(new String[0]));
        final JComboBox<String> roomComboBox = new JComboBox<>(DeckImpl.getRoomNames().toArray(new String[0]));
        final JButton confirmButton = new JButton("Confirm");
        JOptionPane.showMessageDialog(null, "Remember, you can make the final accusatione only once!",
                "Warning",
                JOptionPane.INFORMATION_MESSAGE);
        setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Suspect:"));
        panel.add(suspectComboBox);
        panel.add(new JLabel("Weapon:"));
        panel.add(weaponComboBox);
        panel.add(new JLabel("Room:"));
        panel.add(roomComboBox);
        panel.add(confirmButton);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);

        confirmButton.addActionListener(e -> {
            final String suspect = suspectComboBox.getSelectedItem().toString();
            final String weapon = weaponComboBox.getSelectedItem().toString();
            final String room = roomComboBox.getSelectedItem().toString();
            Cluedo.CONTROLLER.getFinalAccusationController().makeFinalAccusation(suspect, weapon, room);
            dispose();
            if (Cluedo.CONTROLLER.getFinalAccusationController().isFinalAccusationCorrect()) {
                JOptionPane.showMessageDialog(
                    null,
                    Cluedo.CONTROLLER.getGameSolutionController().showSolution(),
                    "Congratulations! You won the game!",
                    JOptionPane.PLAIN_MESSAGE
                );
                JOptionPane.showMessageDialog(null, new StatisticsView(), "Statistics", JOptionPane.PLAIN_MESSAGE);
                Cluedo.CONTROLLER.closeMainGameFrame();
                final Window[] windows = Window.getWindows();
                for (final Window window : windows) {
                    window.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, you lost the game!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
                Cluedo.CONTROLLER.updateButtons();
                if (Cluedo.CONTROLLER.getGameInstance().isOver()) {
                    JOptionPane.showMessageDialog(null, "All the players have lost, the game has ended.");
                    JOptionPane.showMessageDialog(
                        null,
                        Cluedo.CONTROLLER.getGameSolutionController().showSolution(),
                        "The solution was:",
                        JOptionPane.PLAIN_MESSAGE
                    );
                    JOptionPane.showMessageDialog(null, new StatisticsView(), "Statistics", JOptionPane.PLAIN_MESSAGE);
                    Cluedo.CONTROLLER.closeMainGameFrame();
                    final Window[] windows = Window.getWindows();
                    for (final Window window : windows) {
                        window.dispose();
                    }
                }
            }
        });
        setVisible(true);
    }
}
