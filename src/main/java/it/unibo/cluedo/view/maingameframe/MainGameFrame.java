package it.unibo.cluedo.view.maingameframe;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.view.accusation.AccusationView;
import it.unibo.cluedo.view.accusation.FinalAccusationView;
import it.unibo.cluedo.view.board.BoardView;
import it.unibo.cluedo.view.dice.DiceView;
import it.unibo.cluedo.view.gamerules.GameRulesView;
import it.unibo.cluedo.view.joistick.JoystickView;
import it.unibo.cluedo.view.notebook.NotebookView;
import it.unibo.cluedo.view.playercards.PlayerCardsPanel;
import it.unibo.cluedo.view.playerinformations.PlayerInformationPanel;
import it.unibo.cluedo.view.quitgame.QuitGameView;

//import it.unibo.cluedo.view.dice.DiceView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * GamePanel is the main view class for the Cluedo game.
 * It sets up the user interface components and layout for the game.
 */
public class MainGameFrame extends JFrame {
    private static final int PREFERRED_WIDTH = 200;
    private static final int PREFERRED_HEIGHT = 100;
    private static final int MAX_BUTTON_HEIGHT = 50;
    private static final long serialVersionUID = 2L;
    private final PlayerInformationPanel playerPanel;
    private final BoardView boardPanel;
    /**
     * Constructs a new GamePanel object.
     * It initializes the game panel by setting up the layout and adding various
     * sub-panels for different game components.
     */
    public MainGameFrame() {
        // Sets the layout
        setLayout(new GridLayout(1, 2));

        // Left panel
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Right panel
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Bottom right panel
        final JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new GridLayout(2, 1));

        // Player panel
        playerPanel = new PlayerInformationPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playerPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, MAX_BUTTON_HEIGHT));
        leftPanel.add(playerPanel, BorderLayout.NORTH);

        // Map panel
        final JPanel mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Map"));
        this.boardPanel = new BoardView();
        boardPanel.setAlignmentX(CENTER_ALIGNMENT);
        mapPanel.add(boardPanel);
        leftPanel.add(new JScrollPane(mapPanel));

        // Dice panel
        final DiceView dicePanel = new DiceView();
        dicePanel.setBorder(BorderFactory.createTitledBorder("Dice"));
        dicePanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        rightPanel.add(dicePanel, BorderLayout.NORTH);

        // Buttons panel
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Action buttons"));
        final JButton showCardsButton = new JButton("Show cards");
        final JButton showNotebookButton = new JButton("Show notebook");
        final JButton endTurnButton = new JButton("End turn");
        final JButton normalAccusationButton = new JButton("Make normal accusation");
        final JButton finalAccusationButton = new JButton("Make final accusation");

        showCardsButton.addActionListener(e -> {
            final PlayerCardsPanel cardPanel = new PlayerCardsPanel(Cluedo.CONTROLLER.getCurrentPlayerCardsPaths());
            JOptionPane.showMessageDialog(null, cardPanel, "Player cards", JOptionPane.PLAIN_MESSAGE);
        });

        showNotebookButton.addActionListener(e -> {
            final NotebookView notebookPanel = new NotebookView();
            notebookPanel.initView();
            JOptionPane.showMessageDialog(null, notebookPanel, "Player notebook", JOptionPane.PLAIN_MESSAGE);
        });

        endTurnButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getGameInstance().endTurn();
            updateInformations();
            updateBoard();
        });

        normalAccusationButton.addActionListener(e -> {
            final AccusationView normalAccusationView = new AccusationView();
            JOptionPane.showMessageDialog(null, normalAccusationView, "Make normal accuse", JOptionPane.PLAIN_MESSAGE);
        });

        finalAccusationButton.addActionListener(e -> {
            final FinalAccusationView finalAccusationView = new FinalAccusationView();
            JOptionPane.showMessageDialog(null, finalAccusationView, "Make normal accuse", JOptionPane.PLAIN_MESSAGE);
        });

        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.add(showCardsButton);
        buttonsPanel.add(showNotebookButton);
        buttonsPanel.add(new JButton("Use trapdoor"));
        buttonsPanel.add(endTurnButton);
        buttonsPanel.add(normalAccusationButton);
        buttonsPanel.add(finalAccusationButton);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Joystick panel
        final JPanel joystickPanel = new JoystickView();
        joystickPanel.setBorder(BorderFactory.createTitledBorder("Joystick"));
        // joystickPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, 3 * PREFERRED_HEIGHT));
        bottomRightPanel.add(joystickPanel);

        // Cluedo panel
        final JPanel cluedoPanel = new JPanel();
        cluedoPanel.setBorder(BorderFactory.createTitledBorder("Cluedo solution"));
        cluedoPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        bottomRightPanel.add(cluedoPanel);

        // Bottom panel and buttons
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        bottomPanel.setLayout(new GridLayout(1, 2));
        final JButton rulesButton = new JButton("Rules");

        rulesButton.addActionListener(e -> {
            final GameRulesView rulesPanel = new GameRulesView();
            JOptionPane.showMessageDialog(null, rulesPanel, "Game rules", JOptionPane.PLAIN_MESSAGE);
        });

        final QuitGameView quitButton = new QuitGameView();
        bottomPanel.add(rulesButton);
        bottomPanel.add(quitButton);
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_BUTTON_HEIGHT));

        leftPanel.add(bottomPanel);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);

        super.add(leftPanel);
        super.add(rightPanel);

        // Sets normal settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void updateBoard() {
        this.boardPanel.updateBoard();
    }

    public void updateInformations() {
        this.playerPanel.updatePlayerInformationPanel();
    }
}
