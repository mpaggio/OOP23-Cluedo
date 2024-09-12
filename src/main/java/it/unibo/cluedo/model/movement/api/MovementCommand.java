package it.unibo.cluedo.model.movement.api;

/**
 * The {@code MovementCommmand} interface represents a command for moving a player on the map.
 * This interface is part of the Command Pattern.
 * Implementations of this interface define concrete commands for different types of player
 * movements (e.g., single direction movement, combined direction movement). 
 * The {@code execute()} method is called to perform the movement.
 */
public interface MovementCommand {
    /**
     * Executes the command to move the player according to the specific movement strategy.
     * This method incapsulates the logic for moving a player on the map. When called,
     * it will performs the movement as defined by the specific command implementation.
     */
    void execute();
}
