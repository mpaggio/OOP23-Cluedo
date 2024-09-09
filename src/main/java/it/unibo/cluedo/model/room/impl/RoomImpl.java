package it.unibo.cluedo.model.room.impl;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.impl.NoEffectImpl;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;

/**
 * Implementation of the room of the Cluedo game.
 */
public class RoomImpl implements Room {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final List<Square> squares;
    private final List<Square> entrances;
    private final List<Player> players;
    private TrapDoor trapDoor;

    /**
     * Constructor for the room.
     * 
     * @param name the name of the room
     */
    public RoomImpl(final String name) {
        this.name = name;
        this.squares = new LinkedList<>();
        this.entrances = new LinkedList<>();
        this.trapDoor = null;
        this.players = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSquare(final Square square) {
        if (square.getEffect() instanceof NoEffectImpl) {
            this.squares.add(square);
        } else {
            throw new IllegalArgumentException("A room can contain only normal squares");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntrance(final Square entrance) {
        if (entrance.getEffect() instanceof NoEffectImpl) {
            this.entrances.add(entrance);
        } else {
            throw new IllegalArgumentException("A room can contain only normal squares");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEntrance(final Square square) {
        return this.entrances.contains(square);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrapDoor(final TrapDoor trapDoor) {
        this.trapDoor = trapDoor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasTrapDoor() {
        return this.trapDoor != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Square> getSquares() {
        return List.copyOf(this.squares);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Square> getEntrances() {
        return List.copyOf(this.entrances);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TrapDoor> getTrapDoor() {
        return Optional.ofNullable(trapDoor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayerInRoom(final Player player) {
        this.players.add(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlayerFromRoom(final Player player) {
        this.players.remove(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayerInRoom(final Player player) {
        return this.players.contains(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayersInRoom() {
        return List.copyOf(this.players);
    }
}
