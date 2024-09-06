package it.unibo.cluedo.view.gamesolution;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
     * @param cardInfo the info about the cards 
     */
    public GameSolutionView(final List<String> cardInfo) {
        setLayout(new BorderLayout());
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        for (final String path : cardInfo) {
            final ImageIcon image = new ImageIcon(path);
            final JLabel imageLabel = new JLabel(image);
            imagePanel.add(imageLabel);
        }
    }
}
