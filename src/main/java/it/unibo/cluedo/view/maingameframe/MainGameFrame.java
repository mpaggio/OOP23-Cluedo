package it.unibo.cluedo.view.maingameframe;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

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
    private static final int FONT_SIZE = 15;
    private final PlayerInformationPanel playerPanel;
    private final BoardView boardPanel;
    private final DiceView dicePanel;
    private final JoystickView joystickPanel;
    private final JButton useTrapDoorButton;
    private final JButton endTurnButton;
    private final JButton normalAccusationButton;
    private final JButton finalAccusationButton;
    private final JTextArea possibleActionsArea;

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
        playerPanel.setPreferredSize(
            new Dimension(
                PREFERRED_WIDTH,
                MAX_BUTTON_HEIGHT
            )
        );
        playerPanel.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                MAX_BUTTON_HEIGHT
            )
        );
        leftPanel.add(playerPanel, BorderLayout.NORTH);

        // Map panel
        final JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBorder(BorderFactory.createTitledBorder("Map"));
        this.boardPanel = new BoardView();
        mapPanel.add(boardPanel, BorderLayout.CENTER);
        leftPanel.add(new JScrollPane(mapPanel));

        // Dice panel
        this.dicePanel = new DiceView();
        dicePanel.setBorder(BorderFactory.createTitledBorder("Dice"));
        dicePanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        rightPanel.add(dicePanel, BorderLayout.NORTH);

        // Buttons panel
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Action buttons"));
        final JButton showCardsButton = new JButton("Show cards");
        final JButton showNotebookButton = new JButton("Show notebook");
        this.useTrapDoorButton = new JButton("Use trapdoor");
        this.endTurnButton = new JButton("End turn");
        this.normalAccusationButton = new JButton("Make normal accusation");
        this.finalAccusationButton = new JButton("Make final accusation");

        showCardsButton.addActionListener(e -> {
            final PlayerCardsPanel cardPanel = new PlayerCardsPanel(
                Cluedo.CONTROLLER.getMainGameFrameController()
                .getCurrentPlayerCardsPaths()
            );
            JOptionPane.showMessageDialog(
                null,
                cardPanel,
                "Player cards",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        showNotebookButton.addActionListener(e -> {
            final NotebookView notebookPanel = new NotebookView();
            notebookPanel.initView();
            JOptionPane.showMessageDialog(
                null,
                notebookPanel,
                "Player notebook",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        useTrapDoorButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getGameInstance().useTrapdoor(
                Cluedo.CONTROLLER.getGameInstance().getMap().getRoomBySquare(
                    Cluedo.CONTROLLER.getGameInstance().getMap().getSquareByPosition(
                        Cluedo.CONTROLLER.getGameInstance()
                            .getCurrentPlayer()
                            .getCurrentPosition()
                    )
                ).get()
            );
            updateInformations();
            updateBoard();
            enableButtons();
        });

        endTurnButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getGameInstance().endTurn();
            updateInformations();
            updateBoard();
            updateDice();
            enableButtons();
        });

        normalAccusationButton.addActionListener(e -> {
            final AccusationView normalAccusationView = new AccusationView();
            normalAccusationView.initializeView();
            enableButtons();
        });

        finalAccusationButton.addActionListener(e -> {
            final FinalAccusationView finalAccusationView = new FinalAccusationView();
            finalAccusationView.initializeView();
            enableButtons();
        });

        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.add(showCardsButton);
        buttonsPanel.add(showNotebookButton);
        buttonsPanel.add(useTrapDoorButton);
        buttonsPanel.add(endTurnButton);
        buttonsPanel.add(normalAccusationButton);
        buttonsPanel.add(finalAccusationButton);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Joystick panel
        this.joystickPanel = new JoystickView();
        joystickPanel.setBorder(BorderFactory.createTitledBorder("Joystick"));
        bottomRightPanel.add(joystickPanel);

        // Player's possible action panel
        final JPanel actionPanel = new JPanel(new BorderLayout());
        actionPanel.setBorder(BorderFactory.createTitledBorder("Player\'s possible actions"));
        actionPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));

        // List of player possible actions
        this.possibleActionsArea = new JTextArea();
        this.possibleActionsArea.setEditable(false);
        this.possibleActionsArea.setLineWrap(true);
        this.possibleActionsArea.setWrapStyleWord(true);
        this.possibleActionsArea.setEditable(false);
        this.possibleActionsArea.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        final JScrollPane scrollPane = new JScrollPane(this.possibleActionsArea);
        actionPanel.add(scrollPane, BorderLayout.CENTER);

        bottomRightPanel.add(actionPanel);

        // Bottom panel and buttons
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        bottomPanel.setLayout(new GridLayout(1, 2));
        final JButton rulesButton = new JButton("Rules");
        final QuitGameView quitButton = new QuitGameView();

        rulesButton.addActionListener(e -> {
            final GameRulesView rulesPanel = new GameRulesView();
            JOptionPane.showMessageDialog(
                null,
                rulesPanel,
                "Game rules",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        bottomPanel.add(rulesButton);
        bottomPanel.add(quitButton);
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_BUTTON_HEIGHT));

        leftPanel.add(bottomPanel);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);

        super.add(leftPanel);
        super.add(rightPanel);

        enableButtons();

        // Sets normal settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Updates the board view.
     */
    public final void updateBoard() {
        this.boardPanel.updateBoard();
    }

    /**
     * Updates the information view.
     */
    public final void updateInformations() {
        this.playerPanel.updatePlayerInformationPanel();
    }

    /**
     * Updates the dice view.
     */
    public final void updateDice() {
        this.dicePanel.updateDiceView();
    }

    /**
     * Enable the correct buttons.
     */
    public final void enableButtons() {
        this.possibleActionsArea.setText("");
        this.finalAccusationButton.setEnabled(false);
        this.normalAccusationButton.setEnabled(false);
        this.endTurnButton.setEnabled(false);
        this.dicePanel.disableButton();
        this.useTrapDoorButton.setEnabled(false);
        this.joystickPanel.disableButtons();
        this.possibleActionsArea.append("-> View your cards\n");
        this.possibleActionsArea.append("-> View your notebook\n");
        switch (Cluedo.CONTROLLER.getGameInstance().getTurnFase()) {
            case ROLL_DICE:
                this.possibleActionsArea.append("-> Roll the dice\n");
                this.dicePanel.enableButton();
                break;
            case DRAW_UNFORESEEN:
                break;
            case MOVE_PLAYER:
                if (Cluedo.CONTROLLER.getMainGameFrameController().canPlayerUseTrapDoor()) {
                    this.possibleActionsArea.append("-> Use the trapdoor\n");
                    this.useTrapDoorButton.setEnabled(true);
                }
                if (!Cluedo.CONTROLLER.getMainGameFrameController().areStepsZero()) {
                    this.possibleActionsArea.append("-> Use the joystick to move\n");
                    this.joystickPanel.enableButtons();
                }
                this.possibleActionsArea.append("-> End your turn\n");
                this.endTurnButton.setEnabled(true);
                break;
            case MAKE_ACCUSATION:
                this.possibleActionsArea.append("-> Make an accusation\n");
                this.possibleActionsArea.append("-> End your turn\n");
                this.normalAccusationButton.setEnabled(true);
                this.finalAccusationButton.setEnabled(true);
                this.endTurnButton.setEnabled(true);
                break;
            case END_TURN:
                this.possibleActionsArea.append("-> End your turn\n");
                this.endTurnButton.setEnabled(true);
                break;
            default:
                break;
        }
    }

    /**
     * Shows the effect of the square.
     * @param type the type of the square effect
     * @param description the description of the square effect
     */
    public void showSquareEffect(final String type, final String description) {
        JOptionPane.showMessageDialog(
            null,
            "You landed on a " + type + " square:\n" + description
        );
    }
}
