package it.unibo.cluedo.view.gamemenu;

import javax.swing.JPanel;
import javax.swing.JFrame;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;
import it.unibo.cluedo.controller.gamemenucontroller.impl.GameMenuControllerImpl;
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import it.unibo.cluedo.controller.gamesavecontroller.impl.GameSaveControllerImpl.GameState;

import java.util.Optional;

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
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;

/**
 * Class used to show the game menu in the view.
 */
public class GameMenuView  extends JFrame {

    private final transient GameMenuController controller1 = new GameMenuControllerImpl();
    private final transient GameSaveController controller2 = Cluedo.CONTROLLER.getGameSaveController();

    private final JTextField[] playerUsernameFields;
    private final List<JComboBox<String>> playerColorCombos;
    private final JButton startGameButton;
    private final JButton quitGameButton;
    private final JButton viewSavedGamesButton;
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private static final int MINI_HEIGHT = 80;
    private static final int PLAYER_COLOR_COMBOS_HEIGHT = 30;
    private static final int TITLE_SIZE = 60;
    private static final int SIZE = 20;
    private static final double GBCWEIGHTX = 0.2;
    private static final int COLOR_FONT_SIZE = 18;
    private static final int MAX_CHARACTERS = 20;
    private static final String ARIAL = "Arial";

    /**
     * Constructor for the GameMenuView class.
     */
    public GameMenuView() {
        this.playerUsernameFields = new JTextField[3];
        this.playerColorCombos = new ArrayList<>(3);
        this.startGameButton = new JButton("NEW GAME");
        this.quitGameButton = new JButton("EXIT");
        this.viewSavedGamesButton = new JButton("RESUME GAME");

        setLayout(new BorderLayout());
        final JLabel titleLabel = new JLabel("CLUEDO", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, TITLE_SIZE));
        titleLabel.setForeground(Color.RED);
        titleLabel.setPreferredSize(new Dimension(WIDTH, MINI_HEIGHT));
        add(titleLabel, BorderLayout.NORTH);

        add(createPlayerPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        addListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private JPanel createPlayerPanel() {
        final JPanel playerPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        final String[] colors = { "RED", "GREEN", "BLUE", "YELLOW", "PINK", "WHITE" };

        for (int i = 0; i < 3; i++) {
            playerUsernameFields[i] = new JTextField(MAX_CHARACTERS);
            playerUsernameFields[i].setFont(new Font(ARIAL, Font.PLAIN, SIZE));

            playerColorCombos.add(new JComboBox<>(colors));
            playerColorCombos.get(i).setFont(new Font(ARIAL, Font.BOLD, COLOR_FONT_SIZE));
            playerColorCombos.get(i).setPreferredSize(new Dimension(MINI_HEIGHT, PLAYER_COLOR_COMBOS_HEIGHT));

            final JLabel playerLabel = new JLabel("PLAYER " + (i + 1) + " :");
            playerLabel.setFont(new Font(ARIAL, Font.BOLD, SIZE));
            playerLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.weightx = GBCWEIGHTX;
            playerPanel.add(playerLabel, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.weightx = 1.0;
            playerPanel.add(playerUsernameFields[i], gbc);

            gbc.gridx = 3;
            gbc.gridwidth = 1;
            gbc.weightx = GBCWEIGHTX;
            playerPanel.add(playerColorCombos.get(i), gbc);
        }
        return playerPanel;
    }

    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new FlowLayout());
        startGameButton.setFont(new Font(ARIAL, Font.BOLD, COLOR_FONT_SIZE));
        quitGameButton.setFont(new Font(ARIAL, Font.BOLD, COLOR_FONT_SIZE));
        viewSavedGamesButton.setFont(new Font(ARIAL, Font.BOLD, COLOR_FONT_SIZE));

        buttonPanel.add(startGameButton);
        buttonPanel.add(viewSavedGamesButton);
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
                    playerColors.add((String) playerColorCombos.get(i).getSelectedItem());
                }
                if (controller1.startGame(playerUsernames, playerColors)) {
                    JOptionPane.showMessageDialog(GameMenuView.this, "Game started successfully!");
                    Cluedo.CONTROLLER.initializeGameModel(playerUsernames, playerColors);
                    Cluedo.CONTROLLER.displayMainFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(GameMenuView.this, "Game not started! Check your Username or Color!");
                }
            }
        });

        quitGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller1.quitGame();
            }
        });

        viewSavedGamesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final Optional<GameState> gameState = controller2.loadGame("");
                if (gameState.isPresent()) {
                    JOptionPane.showMessageDialog(GameMenuView.this, "Game loaded successfully, Resuming game...");
                    controller1.viewSavedGames();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(GameMenuView.this, "No saved games found!");
                }
            }
        });
    }
}
