package it.unibo.cluedo.model.room.impl;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.MapComponentVisitor;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.impl.NoEffectImpl;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;

/**
 * Implementation of the room of the Cluedo game.
 */
public class RoomImpl implements Room {
    private final String name;
    private final List<Square> squares;
    private final List<Square> entrances;
    private final List<Player> players;
    private Optional<TrapDoor> trapDoor;

    /**
     * Constructor for the room.
     * 
     * @param name the name of the room
     */
    public RoomImpl(final String name) {
        this.name = name;
        this.squares = new LinkedList<>();
        this.entrances = new LinkedList<>();
        this.trapDoor = Optional.empty();
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
    public void setTrapDoor(final Optional<TrapDoor> trapDoor) {
        this.trapDoor = trapDoor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(final MapComponentVisitor visitor) {
        visitor.visitRoom(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasTrapDoor() {
        return this.trapDoor.isPresent();
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
        return this.trapDoor;
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
    public void removePlayerFromRoom(Player player) {
        this.players.remove(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayersInRoom() {
        return List.copyOf(this.players);
    }
}
