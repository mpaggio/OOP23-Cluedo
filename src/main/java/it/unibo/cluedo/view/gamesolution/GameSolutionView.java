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
    private static final int HGAP = 20;
    private static final int VGAP = 0;

    /**
     * Constructor for the GameSolutionView class.
     * @param cardInfo the info about the cards 
     */
    public GameSolutionView(final List<String> cardInfo) {
        setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
        setBorder(BorderFactory.createTitledBorder("Game Solution"));
        for (final String path : cardInfo) {
            final ImageIcon image = new ImageIcon(ClassLoader.getSystemResource(path));
            final JLabel imageLabel = new JLabel(image);
            super.add(imageLabel);
        }
    }
}
