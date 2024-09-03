package it.unibo.cluedo.view.gamesolution;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * Class used to show in the view the solution of the game.
 */
public class GameSolutionView extends JPanel {
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for the GameSolutionView class.
     * @param imagesPath a list of strings representing the paths to the card images
     */
    public GameSolutionView(final List<String> imagesPath) {
        setLayout(new BorderLayout());

        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());

        for (final String path : imagesPath) {
            final ImageIcon image = new ImageIcon(path);
            final JLabel imageLabel = new JLabel(image);
            imagePanel.add(imageLabel);
        }
    }

    /**
     * Show a message in the view when the final accusation is incorrect.
     * @param message an error messagge
     */
    public void showFailureMessage(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Show the Cards of the game solution in the view.
     * @param cardInfo the Set of Cards representing the solution
     */
    public void displaySolution(final List<String> cardInfo) {
        JOptionPane.showMessageDialog(null, new GameSolutionView(cardInfo), "Game Solution", JOptionPane.PLAIN_MESSAGE);
    }
}
