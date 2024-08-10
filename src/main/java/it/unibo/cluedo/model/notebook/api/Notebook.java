package it.unibo.cluedo.model.notebook.api;
import java.util.List;

/**
 * Interface that represents the notebook of the player.
 * The notebook is used to keep track of the cards that the player has seen.
 * The player can log the suspects, the weapons and the rooms that he has seen.
 * The player can also get the list of the suspects, the weapons and the rooms that he has not seen yet.
 */

public interface Notebook {
    /**
     * Log the suspect that the player has seen.
     *
     * @param suspect the suspect that the player has seen.
     */
    void logSuspect(String suspect);

    /**
     * Log the weapon that the player has seen.
     *
     * @param weapon the weapon that the player has seen.
     */
    void logWeapon(String weapon);

    /**
     * Log the room that the player has seen.
     *
     * @param room the room that the player has seen.
     */
    void logRoom(String room);

    /**
     * Get the list of the suspects that the player has seen.
     *
     * @return the list of the suspects that the player has seen.
     */
    List<String> getUnselectedSuspects();

    /**
     * Get the list of the weapons that the player has seen.
     *
     * @return the list of the weapons that the player has seen.
     */
    List<String> getUnselectedWeapons();

    /**
     * Get the list of the rooms that the player has seen.
     *
     * @return the list of the rooms that the player has seen.
     */
    List<String> getUnselectedRooms();
}
