package it.unibo.cluedo.controller.gamesolutioncontroller.api;

import it.unibo.cluedo.view.gamesolution.GameSolutionView;

/**
 * Interface for the controller class that handles the game solution logic.
 */
public interface GameSolutionController {
    /**
     * Displays the solution cards in the view when the "Game solution" button is clicked.
     * Only works if the player has won the game
     */
    GameSolutionView showSolution();
}
