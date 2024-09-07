package it.unibo.cluedo.view.gamesolution;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;

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
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        setBorder(BorderFactory.createTitledBorder("Game Solution"));
        for (final String path : cardInfo) {
            final ImageIcon image = new ImageIcon(path);
            final JLabel imageLabel = new JLabel(image);
            super.add(imageLabel);
        }
    }
}
