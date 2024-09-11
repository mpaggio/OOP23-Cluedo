package it.unibo.cluedo.view.accusation;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.model.card.api.Card;

/**
 * This class is used to show the accusation view.
 */
public class AccusationView extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final String ROOM_INFO = 
        "The room of the accusation has been automatically selected, and it's the one you're in right now.";

    /**
     * Method to initialize the view.
     */
    public void initializeView() {
        if ("Cluedo".equals(Cluedo.CONTROLLER.getAccusationController().getRoomName())
            || Cluedo.CONTROLLER.getAccusationController().getRoomName().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You can't make a normal accusation here.");
            return;
        }
        JOptionPane.showMessageDialog(null, ROOM_INFO);
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JComboBox<String> suspectComboBox = new JComboBox<>(DeckImpl.getCharacterNames().toArray(new String[0]));
        final JComboBox<String> weaponComboBox = new JComboBox<>(DeckImpl.getWeaponNames().toArray(new String[0]));
        final JButton confirmButton = new JButton("Confirm");
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Suspect:"));
        panel.add(suspectComboBox);
        panel.add(new JLabel("Weapon:"));
        panel.add(weaponComboBox);
        panel.add(confirmButton);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);

        confirmButton.addActionListener(e -> {
            final String suspect = suspectComboBox.getSelectedItem().toString();
            final String weapon = weaponComboBox.getSelectedItem().toString();
            final String room = Cluedo.CONTROLLER.getGameInstance().getMap().getRoomBySquare(Cluedo.CONTROLLER.getGameInstance()
                .getSquare()).get().getName();
            dispose();
            JOptionPane.showMessageDialog(null, "You accused " + suspect + " using " + weapon + " int the room " + room,
            "Accusation",
            JOptionPane.INFORMATION_MESSAGE);
            Cluedo.CONTROLLER.getAccusationController().makeAccusation(suspect, weapon, room);
            if (Cluedo.CONTROLLER.getAccusationController().isAccusationCorrect()) {
                final Card card = Cluedo.CONTROLLER.getAccusationController().getCardToShow();
                final ImageIcon image = new ImageIcon(card.getImagePath(), card.getName());
                JOptionPane.showMessageDialog(null, image);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Non of the selected cards is possessed by the other players.");
            }
            Cluedo.CONTROLLER.updateButtons();
        });
        setVisible(true);
    }
}
