package it.unibo.cluedo.model.notebook.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.card.api.Card.Type;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import java.io.Serializable;

/**
 * Class that represents the notebook of the player.
 * The notebook is used to keep track of the cards that the player has seen.
 * The player can log the suspects, the weapons and the rooms that he has seen.
 * The player can also get the list of the suspects, the weapons and the rooms
 * that he has not seen yet.
 */
public class NotebookImpl implements Notebook, Serializable {

    private final Set<String> seenSuspects;
    private final Set<String> seenWeapons;
    private final Set<String> seenRooms;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the class.
     * It initializes the sets of the suspects, the weapons and the rooms that the
     * player has not seen yet.
     */
    public NotebookImpl() {
        this.seenSuspects = new HashSet<>();
        this.seenWeapons = new HashSet<>();
        this.seenRooms = new HashSet<>();
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
     * @param card the card that the player has seen.
     */
    @Override
    public void logSeenCards(final String card) {
        final Type cardType = getCardType(card);
        if (cardType == Type.CHARACTER) {
            this.seenSuspects.add(card);
        } else if (cardType == Type.WEAPON) {
            this.seenWeapons.add(card);
        } else if (cardType == Type.ROOM) {
            this.seenRooms.add(card);
        }
    }

    /**
     * Get the type of the card.
     *
     * @param card the card.
     * @return the type of the card.
     */
    private Type getCardType(final String card) {
        if (isSuspect(card)) {
            return Type.CHARACTER;
        } else if (isWeapon(card)) {
            return Type.WEAPON;
        } else if (isRoom(card)) {
            return Type.ROOM;
        }
        return null;
    }

    /**
     * Check if the card is a suspect.
     *
     * @param card the card.
     * @return true if the card is a suspect, false otherwise.
     */
    private boolean isSuspect(final String card) {
        return DeckImpl.getCharacterNames().contains(card);
    }

    /**
     * Check if the card is a weapon.
     *
     * @param card the card.
     * @return true if the card is a weapon, false otherwise.
     */
    private boolean isWeapon(final String card) {
        return DeckImpl.getWeaponNames().contains(card);
    }

    /**
     * Check if the card is a room.
     *
     * @param card the card.
     * @return true if the card is a room, false otherwise.
     */
    private boolean isRoom(final String card) {
        return DeckImpl.getRoomNames().contains(card);
    }

    /**
     * The following method return a new ArrayList with the elements of the
     * corresponding Set.
     */
    @Override
    public List<String> getSeenSuspects() {
        return new ArrayList<>(this.seenSuspects);
    }

    /**
     * The following method return a new ArrayList with the elements of the
     * corresponding Set.
     */
    @Override
    public List<String> getSeenWeapons() {
        return new ArrayList<>(this.seenWeapons);
    }

    /**
     * The following method return a new ArrayList with the elements of the
     * corresponding Set.
     */
    @Override
    public List<String> getSeenRooms() {
        return new ArrayList<>(this.seenRooms);
    }
}
