package it.unibo.cluedo.model.notebook.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import it.unibo.cluedo.model.notebook.api.Notebook;

/**
 * Class that represents the notebook of the player.
 * The notebook is used to keep track of the cards that the player has seen.
 * The player can log the suspects, the weapons and the rooms that he has seen.
 * The player can also get the list of the suspects, the weapons and the rooms that he has not seen yet.
 */
public class NotebookImpl implements Notebook {

    private final Set<String> unselectedSuspects;
    private final Set<String> unselectedWeapons;
    private final Set<String> unselectedRooms;

    /**
     * Constructor of the class.
     *
     * @param allSuspects the list of all the suspects.
     * @param allWeapons the list of all the weapons.
     * @param allRooms the list of all the rooms.
     */
    public NotebookImpl(final List<String> allSuspects, final List<String> allWeapons,  final List<String> allRooms) {
        this.unselectedSuspects = new HashSet<>(allSuspects);
        this.unselectedWeapons = new HashSet<>(allWeapons);
        this.unselectedRooms = new HashSet<>(allRooms);
    }

    /**
     * The following methods remove the suspect from the corresponding Set.
     */
    @Override
    public void logSuspect(final String suspect) {
        this.unselectedSuspects.remove(suspect);
    }

    /**
     * The following methods remove the weapon from the corresponding Set.
     */
    @Override
    public void logWeapon(final String weapon) {
        this.unselectedWeapons.remove(weapon);
    }

    /**
     * The following methods remove the room from the corresponding Set.
     */
    @Override
    public void logRoom(final String room) {
        this.unselectedRooms.remove(room);
    }

    /**
     * The following methods return a new ArrayList with the elements of the corresponding Set.
     */
    @Override
    public List<String> getUnselectedSuspects() {
        return new ArrayList<>(this.unselectedSuspects);
    }

    /**
     * The following methods return a new ArrayList with the elements of the corresponding Set.
     */
    @Override
    public List<String> getUnselectedWeapons() {
        return new ArrayList<>(this.unselectedWeapons);
    }

    /**
     * The following methods return a new ArrayList with the elements of the corresponding Set.
     */
    @Override
    public List<String> getUnselectedRooms() {
        return new ArrayList<>(this.unselectedRooms);
    }

}
