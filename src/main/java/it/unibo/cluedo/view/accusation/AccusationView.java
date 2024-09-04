package it.unibo.cluedo.view.accusation;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JOptionPane;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.model.deck.impl.DeckImpl;

/**
 * This class is used to show the accusation view.
 */
public class AccusationView extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the class.
     */
    public AccusationView() {
        final JComboBox<String> suspectComboBox = new JComboBox<>(DeckImpl.getCharacterNames().toArray(new String[0]));
        final JComboBox<String> weaponComboBox = new JComboBox<>(DeckImpl.getWeaponNames().toArray(new String[0]));
        final JComboBox<String> roomComboBox = new JComboBox<>(DeckImpl.getRoomNames().toArray(new String[0]));
        final JButton confirmButton = new JButton("Confirm");
        setLayout(new GridLayout(4, 2));
        add(new JLabel("Suspect:"));
        add(suspectComboBox);
        add(new JLabel("Weapon:"));
        add(weaponComboBox);
        add(new JLabel("Room:"));
        add(roomComboBox);
        add(confirmButton);

        confirmButton.addActionListener(e -> {
            final String suspect = suspectComboBox.getSelectedItem().toString();
            final String weapon = weaponComboBox.getSelectedItem().toString();
            final String room = roomComboBox.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "You accused " + suspect + " using " + weapon + " int the room " + room,
            "Accusation",
            JOptionPane.INFORMATION_MESSAGE);
            Cluedo.CONTROLLER.getAccusationController().makeAccusation(suspect, weapon, room);
        });
    }
}
