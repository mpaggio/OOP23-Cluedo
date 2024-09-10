package it.unibo.cluedo.controller.joystickcontroller.impl;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.joystickcontroller.api.JoystickController;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Effect.EffectType;
/**
 * Controller class for handling the joystick movement in the Cluedo game.
 */
public class JoystickControllerImpl implements JoystickController {
    /**
     * {@inheritDoc}
     */
    @Override
    public void moveUp() {
        move(MovementStrategy.Direction.UP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveDown() {
        move(MovementStrategy.Direction.DOWN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveLeft() {
        move(MovementStrategy.Direction.LEFT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveRight() {
        move(MovementStrategy.Direction.RIGHT);
    }

    /**
     * Method for moving the player in the specified direction.
     * @param direction the direction in which tha player should move
     */
    private void move(final MovementStrategy.Direction direction) {
        Cluedo.CONTROLLER.getGameInstance().movePlayer(direction);
        Cluedo.CONTROLLER.updateBoard();
        Cluedo.CONTROLLER.updateInformations();
        final var square = Cluedo.CONTROLLER.getGameInstance().getMap().getSquareByPosition(
            Cluedo.CONTROLLER.getGameInstance()
                .getCurrentPlayer()
                .getCurrentPosition()
        );
        if (square.getEffect().getType().equals(Effect.EffectType.MALUS)
            || square.getEffect().getType().equals(Effect.EffectType.BONUS)) {
            final EffectType effectType = square.getEffect().getType();
            Cluedo.CONTROLLER.showSquareEffect(
                effectType.getTitle(),
                effectType.getDescription());
        }
    }
}
