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
    private List<String> imagesPath;

    /**
     * Constructor for the GameSolutionView class.
     */
    public GameSolutionView() {
        setLayout(new BorderLayout());
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        for (final String path : this.imagesPath) {
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
        this.imagesPath = List.copyOf(cardInfo);
        JOptionPane.showMessageDialog(null, new GameSolutionView(), "Game Solution", JOptionPane.PLAIN_MESSAGE);
    }
}
