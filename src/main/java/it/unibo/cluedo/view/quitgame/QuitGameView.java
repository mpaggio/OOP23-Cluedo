package it.unibo.cluedo.view.quitgame;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;

/**
 * A button that allows the user to quit the game.
 */
public class QuitGameView extends JButton {
    private static final long serialVersionUID = 5L;
    private final transient GameSaveController controller = Cluedo.CONTROLLER.getGameSaveController();

    /**
     * Constructs a new QuitGameView object.
     */
    public QuitGameView() {
        super("Quit Game");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                showQuitGameView();
            }
        });
    }

    /**
     * This method shows a dialog asking the user if they want to save the game before quitting.
     */
    public void showQuitGameView() {
        final String[] options = {"Quit without saving the game", "Quit and save the game", "Cancel"};
        final int choice = JOptionPane.showOptionDialog(
                null,
                "Do you want to save the game before quitting?",
                "Quit Game",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]
        );
        switch (choice) {
            case 0:
                final Window[] windows = Window.getWindows();
                for (final Window window : windows) {
                    window.dispose();
                }
                break;
            case 1:
                saveAndQuit();
                break;
            default:
                break;
            }
        }

    /**
     * This method saves the game and quits.
     */
    private void saveAndQuit() {
        try {
            controller.saveGame(
                Cluedo.CONTROLLER.getGameInstance().getPlayers(),
                Cluedo.CONTROLLER.getGameInstance().getSolution(),
                Cluedo.CONTROLLER.getGameInstance().getTurnManager(),
                Cluedo.CONTROLLER.getGameInstance().getStatistics(),
                Cluedo.CONTROLLER.getGameInstance().getMap(),
                Cluedo.CONTROLLER.getGameInstance().getAllCards(),
                Cluedo.CONTROLLER.getGameInstance().getTurnFase(),
                ""
            );
            JOptionPane.showMessageDialog(null, "Game saved successfully!", "Game Saved", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            final Window[] windows = Window.getWindows();
            for (final Window window : windows) {
                window.dispose();
            }
        }
    }
}
