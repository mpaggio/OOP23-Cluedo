package it.unibo.cluedo.controller.maincontroller.api;

import java.util.List;

import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.controller.joystickcontroller.api.JoystickController;
import it.unibo.cluedo.controller.mapsetupcontroller.impl.MapSetupController;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;
import it.unibo.cluedo.model.GameModel;

/**
 * MainController is the primary interface that provides access to the model
 * and to the various controllers related to the game.
 */
public interface MainController {
    /**
     * Starts the game view by creating a new GamePanel.
     */
    void startView();

    /**
     * Returns the current game model instance.
     *
     * @return the current game model instance
     */
    GameModel getGameInstance();

    /**
     * Returns a list of the current player's cards path.
     *
     * @return a list of the current player's cards path
     */
    List<String> getCurrentPlayerCardsPaths();

    /**
     * Returns a instance of NotebookController.
     * @return the NotebookController
     */
    NotebookController getNotebookController();

    /**
     * Returns a instance of GameSolutionController.
     * @return the GameSolutionController
     */
    GameSolutionController getGameSolutionController();

    /**
     * Returns a instance of MapSetUpController.
     * @return the MapSetUpController
     */
    MapSetupController getMapController();

    /**
     * Returns a instance of JoystickController.
     * @return the JoystickController
     */
    JoystickController getJoystickController();
}