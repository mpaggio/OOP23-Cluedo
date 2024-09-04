package it.unibo.cluedo.view.gamemenu;

import javax.swing.JPanel;
import javax.swing.JFrame;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;
import it.unibo.cluedo.view.maingameframe.MainGameFrame;

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
public class GameMenuView  extends JFrame {

    private final GameMenuController controller = Cluedo.CONTROLLER.getGameMenuController();
    private final JTextField[] playerUsernameFields;
    private final JComboBox<String>[] playerColorCombos;
    private final JButton startGameButton;
    private final JButton quitGameButton;
    private final JButton viewSavedGamesButton;
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 400;
    private static final int LENGTH = 300;

    /**
     * Suppresed unchecked warning for JComboBox.
     * @param controller
     */
    @SuppressWarnings("unchecked")

    /**
     * Constructor for the GameMenuView class.
     * @param controller the GameMenuController to set
     */
    public GameMenuView() {
        this.playerUsernameFields = new JTextField[3];
        this.playerColorCombos = new JComboBox[3];
        this.startGameButton = new JButton("Start Game");
        this.quitGameButton = new JButton("Quit Game");
        this.viewSavedGamesButton = new JButton("View Saved Games");

        setLayout(new BorderLayout());
        add(createPlayerPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        addListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, LENGTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPlayerPanel() {
        final JPanel playerPanel = new JPanel(new GridLayout(3, 2));
        final String[] colors = {"Red", "Green", "Blue", "Yellow", "Purple", "White"};
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
        buttonPanel.add(viewSavedGamesButton);
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
                    openMainGameFrame();
                    dispose();
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

        viewSavedGamesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> savedGames = controller.viewSavedGames();
                if (savedGames.isEmpty()) {
                    JOptionPane.showMessageDialog(GameMenuView.this, "No saved games found!");
                } else {
                    final StringBuilder message = new StringBuilder("Saved games:\n");
                    for (final String savedGame : savedGames) {
                        message.append(savedGame).append('\n');
                    }
                    JOptionPane.showMessageDialog(GameMenuView.this, message.toString());
                }
            }
        });
    }

    private void openMainGameFrame() {
        final MainGameFrame mainGameFrame = new MainGameFrame();
        mainGameFrame.setVisible(true);
    }
}
