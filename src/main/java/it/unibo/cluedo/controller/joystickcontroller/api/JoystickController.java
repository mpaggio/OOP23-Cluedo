package it.unibo.cluedo.controller.joystickcontroller.api;

import it.unibo.cluedo.model.movement.api.MovementStrategy;

/**
 * Interface that defines the method for handling player movement.
 * The movement is controlled by a joystick.
 */
public interface JoystickController {
    /**
     * Moves the player in the specified direction.
     * @param direction the direction where the player wants to move
     */
    void movePlayer(MovementStrategy.Direction direction);
}
