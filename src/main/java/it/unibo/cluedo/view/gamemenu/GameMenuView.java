package it.unibo.cluedo.view.gamemenu;

import javax.swing.JPanel;
import javax.swing.JFrame;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * Class used to show the game menu in the view.
 */
public class GameMenuView  extends JFrame {

    private final GameMenuController controller = Cluedo.CONTROLLER.getGameMenuController();
    private final JTextField[] playerUsernameFields;
    private final List<JComboBox<String>> playerColorCombos;
    private final JButton startGameButton;
    private final JButton quitGameButton;
    private final JButton viewSavedGamesButton;
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final int MINI_HEIGHT = 50;
    private static final int SIZE = 24;
    private static final int MAX_CHARACTERS = 20;
    private static final String ARIAL = "Arial";

    /**
     * Constructor for the GameMenuView class.
     */
    public GameMenuView() {
        this.playerUsernameFields = new JTextField[3];
        this.playerColorCombos = new ArrayList<>(3);
        this.startGameButton = new JButton("Start Game");
        this.quitGameButton = new JButton("Quit Game");
        this.viewSavedGamesButton = new JButton("View Saved Games");

        setLayout(new BorderLayout());
        final JLabel titleLabel = new JLabel("                CLUEDO - START NEW GAME");
        titleLabel.setFont(new Font(ARIAL, Font.BOLD, SIZE));
        titleLabel.setPreferredSize(new Dimension(WIDTH, MINI_HEIGHT));
        add(titleLabel, BorderLayout.NORTH);

        add(createPlayerPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        addListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPlayerPanel() {
        final JPanel playerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        final String[] colors = { "Red", "Green", "Blue", "Yellow", "Pink", "White" };

        for (int i = 0; i < 3; i++) {
            playerUsernameFields[i] = new JTextField(MAX_CHARACTERS);
            playerColorCombos.add(new JComboBox<>(colors));

            final JPanel userColorPanel = new JPanel(new GridLayout(1, 2, 10, 10));
            userColorPanel.add(playerUsernameFields[i]);
            userColorPanel.add(playerColorCombos.get(i));

            playerPanel.add(new JLabel("Player " + (i + 1) + " Username:"));
            playerPanel.add(userColorPanel);
        }
        return playerPanel;
    }

    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new FlowLayout());
        startGameButton.setFont(new Font(ARIAL, Font.BOLD, 16));
        quitGameButton.setFont(new Font(ARIAL, Font.BOLD, 16));
        viewSavedGamesButton.setFont(new Font(ARIAL, Font.BOLD, 16));

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
                    playerColors.add((String) playerColorCombos.get(i).getSelectedItem());
                }
                if (controller.startGame(playerUsernames, playerColors)) {
                    JOptionPane.showMessageDialog(GameMenuView.this, "Game started successfully!");
                    openMainGameFrame(playerUsernames, playerColors);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(GameMenuView.this, "Game not started! Check your Username or Color!");
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

    private void openMainGameFrame(final List<String> playerNames, final List<String> playerColors) {
        Cluedo.CONTROLLER.initializeGameModel(playerNames, playerColors);
        Cluedo.CONTROLLER.displayMainFrame();
    }
}
