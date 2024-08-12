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
     * Initialize the notebook with the cards that the player has in his hand.
     *
     * @param playerCards the list of the cards that the player has in his hand.
     */
    @Override
    public void initialize(final List<String> playerCards) {
        for (final String card : playerCards) {
            this.logSeenCards(card);
        }
    }

    /**
     * Log the card that the player has seen.
     * @param card
     */
    @Override
    public void logSeenCards(final String card) {
        if (unselectedSuspects.contains(card)) {
            unselectedSuspects.remove(card);
        } else if (unselectedWeapons.contains(card)) {
            unselectedWeapons.remove(card);
        } else if (unselectedRooms.contains(card)) {
            unselectedRooms.remove(card);
        }
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
