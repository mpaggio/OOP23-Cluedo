package it.unibo.cluedo.view.gamemenu;

import javax.swing.JPanel;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;

/**
 * Class used to show the game menu in the view.
 */
public class GameMenuView  extends JPanel {

    private final GameMenuController controller;
    private final JTextField[] playerUsernameFields;
    private final JComboBox<String>[] playerColorCombos;
    private final JButton startGameButton;
    private final JButton quitGameButton;
    private static final long serialVersionUID = 1L;

    /**
     * Suppresed unchecked warning for JComboBox.
     * @param controller
     */
    @SuppressWarnings("unchecked")

    /**
     * Constructor for the GameMenuView class.
     * @param controller the GameMenuController to set
     */
    public GameMenuView(final GameMenuController controller) {
        this.controller = controller;
        this.playerUsernameFields = new JTextField[3];
        this.playerColorCombos = new JComboBox[3];
        this.startGameButton = new JButton("Start Game");
        this.quitGameButton = new JButton("Quit Game");

        setLayout(new BorderLayout());
        add(createPlayerPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        addListeners();
    }

    private JPanel createPlayerPanel() {
        final JPanel playerPanel = new JPanel(new GridLayout(3, 2));
        final String[] colors = {"Red", "Green", "Blue", "Yellow", "Purple", "Orange"};
        for (int i = 0; i < 3; i++) {
            playerUsernameFields[i] = new JTextField();
            playerColorCombos[i] = new JComboBox<>(colors);
            playerPanel.add(new JLabel("Player " + (i + 1) + " Username:"));
            playerPanel.add(playerUsernameFields[i]);
            playerPanel.add(new JLabel("Player " + (i + 1) + " Color:"));
            playerPanel.add(playerColorCombos[i]);
        }
        return playerPanel;
    }

    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startGameButton);
        buttonPanel.add(quitGameButton);
        return buttonPanel;
    }

    private void addListeners() {
        startGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> playerUsernames = new ArrayList<>();
                final List<String> playerColors = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    playerUsernames.add(playerUsernameFields[i].getText().trim());
                    playerColors.add((String) playerColorCombos[i].getSelectedItem());
                }
                if (controller.startGame(playerUsernames, playerColors)) {
                    JOptionPane.showMessageDialog(GameMenuView.this, "Game started successfully!");
                } else {
                    JOptionPane.showMessageDialog(GameMenuView.this, "Game not started!");
                }
            }
        });

        quitGameButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    controller.quitGame();
                }
        });
    }
}
