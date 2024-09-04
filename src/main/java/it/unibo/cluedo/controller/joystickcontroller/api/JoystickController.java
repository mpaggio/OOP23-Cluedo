package it.unibo.cluedo.controller.joystickcontroller.api;

/**
 * Interface that defines the method for handling player movement.
 * The movement is controlled by a joystick.
 */
public interface JoystickController {
    /**
     * Method for moving the player up.
     */
    void moveUp();

    /**
     * Method for moving the player down.
     */
    void moveDown();

    /**
     * Method for moving the player left.
     */
    void moveLeft();

    /**
     * Method for moving the player right.
     */
    void moveRight();
}
