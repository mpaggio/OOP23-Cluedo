package it.unibo.cluedo.model.notebook.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.card.api.Card.Type;
import it.unibo.cluedo.model.deck.impl.DeckImpl;

/**
 * Class that represents the implementation of the player's notebook.
 * The notebook is used to keep track of the cards that the player has seen.
 */
public class NotebookImpl implements Notebook {

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
     * {@inheritDoc}
     */
    @Override
    public void initialize(final List<String> playerCards) {
        for (final String card : playerCards) {
            this.logSeenCards(card);
        }
    }

    /**
     * {@inheritDoc}
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
     * @param card the card.
     * @return true if the card is a suspect, false otherwise.
     */
    private boolean isSuspect(final String card) {
        return DeckImpl.getCharacterNames().contains(card);
    }

    /**
     * Check if the card is a weapon.
     * @param card the card.
     * @return true if the card is a weapon, false otherwise.
     */
    private boolean isWeapon(final String card) {
        return DeckImpl.getWeaponNames().contains(card);
    }

    /**
     * Check if the card is a room.
     * @param card the card.
     * @return true if the card is a room, false otherwise.
     */
    private boolean isRoom(final String card) {
        return DeckImpl.getRoomNames().contains(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getSeenSuspects() {
        return new ArrayList<>(this.seenSuspects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getSeenWeapons() {
        return new ArrayList<>(this.seenWeapons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getSeenRooms() {
        return new ArrayList<>(this.seenRooms);
    }
}
