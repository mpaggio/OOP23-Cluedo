package it.unibo.cluedo.view.gamesolution;

import java.util.Set;
import it.unibo.cluedo.model.card.api.Card;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Class used to show in the view the solution of the game.
 */
public class GameSolutionView {
    private final JFrame frame;
    private final JButton gameSolutionButton;

    /**
     * Constructor for the GameSolutionView class.
     */
    public GameSolutionView() {
        this.frame = new JFrame("Cluedo Game Solution");
        this.gameSolutionButton = new JButton("Game Solution");
        initComponent();
    }
    
    /**
     * Initializes the components of the view.
     */
    private void initComponent() {
        this.frame.setLayout(new BorderLayout());
        this.gameSolutionButton.setEnabled(false);
    }

    /**
     * Enable the "Game Solution" button in the view.
     */
    public void enableGameSolutionButton() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enableGameSolutionButton'");
    }
    
    /**
     * Show a message in the view when the final accusation is incorrect.
     * @param string an error messagge
     */
    public void showFailureMessage(final String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showFailureMessage'");
    }

    /**
     * Show the Cards of the game solution in the view.
     * @param solution the Set of Cards representing the solution
     */
    public void displaySolution(final Set<Card> solution) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displaySolution'");
    }
}
