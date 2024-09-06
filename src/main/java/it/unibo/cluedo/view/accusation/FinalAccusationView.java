package it.unibo.cluedo.view.accusation;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.model.deck.impl.DeckImpl;

/**
 * This class is used to show the final accusation view.
 */
public class FinalAccusationView extends JDialog {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the class.
     */
    public FinalAccusationView() {
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
            if (Cluedo.CONTROLLER.getFinalAccusationController().isFinalAccusationCorrect()) {
                JOptionPane.showMessageDialog(null, Cluedo.CONTROLLER.getGameSolutionController().showSolution(),
                "Congratulations! You won the game!",
                JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, you lost the game!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
