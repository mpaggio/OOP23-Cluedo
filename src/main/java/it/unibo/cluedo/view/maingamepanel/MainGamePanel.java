package it.unibo.cluedo.view.maingamepanel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.cluedo.view.board.BoardView;

//import it.unibo.cluedo.view.dice.DiceView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * GamePanel is the main view class for the Cluedo game.
 * It sets up the user interface components and layout for the game.
 */
public class MainGamePanel extends JFrame {
    private static final int PREFERRED_WIDTH = 200;
    private static final int PREFERRED_HEIGHT = 100;
    private static final int MAX_BUTTON_HEIGHT = 50;
    private static final long serialVersionUID = 2L;
    /**
     * Constructs a new GamePanel object.
     * It initializes the game panel by setting up the layout and adding various
     * sub-panels for different game components. 
     */
    public MainGamePanel() {
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
        final JPanel playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playerPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, MAX_BUTTON_HEIGHT));
        leftPanel.add(playerPanel, BorderLayout.NORTH);

        // Map panel
        final JPanel mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Map"));
        final JPanel boardPanel = new BoardView();
        boardPanel.setAlignmentX(CENTER_ALIGNMENT);
        mapPanel.add(boardPanel);
        leftPanel.add(mapPanel);

        // Dice panel
        final JPanel dicePanel = new JPanel();
            //JPanel dicePanel = new DiceView(null);
        dicePanel.setBorder(BorderFactory.createTitledBorder("Dice"));
        dicePanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        rightPanel.add(dicePanel, BorderLayout.NORTH);

        // Buttons panel
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 2));
        buttonsPanel.add(new JButton("Show cards"));
        buttonsPanel.add(new JButton("Show notebook"));
        buttonsPanel.add(new JButton("Use trapdoor"));
        buttonsPanel.add(new JButton("Make accusation"));
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Joystick panel
        final JPanel joystickPanel = new JPanel();
        joystickPanel.setBorder(BorderFactory.createTitledBorder("Joystick"));
        joystickPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, 3 * PREFERRED_HEIGHT));
        bottomRightPanel.add(joystickPanel);

        // Cluedo panel
        final JPanel cluedoPanel = new JPanel();
        cluedoPanel.setBorder(BorderFactory.createTitledBorder("Cluedo solution"));
        cluedoPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        bottomRightPanel.add(cluedoPanel);

        // Bottom panel and buttons
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        final JButton rulesButton = new JButton("Rules");
        final JButton quitButton = new JButton("Quit");
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
}
