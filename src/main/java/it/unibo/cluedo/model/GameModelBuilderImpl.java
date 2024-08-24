package it.unibo.cluedo.model;

import it.unibo.cluedo.model.trapdoor.api.TrapDoor;
import it.unibo.cluedo.model.unforeseen.api.Unforeseen;
import java.util.ArrayList;
import java.util.List;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.PlayerBuilder;
import it.unibo.cluedo.model.player.impl.PlayerBuilderImpl;

/**
 * Implementation of the {@link GameModelBuilder} interface.
 * It is used to build a new instance of GameModel.
 */
public class GameModelBuilderImpl implements GameModelBuilder {

    private final List<Player> players = new ArrayList<>();
    private final List<Unforeseen> unforseenCards = new ArrayList<>();
    private final List<TrapDoor> trapDoors = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder addPlayer(final String username, final String color) {
        final PlayerBuilder builder = new PlayerBuilderImpl();
        final Player player = builder.username(username).color(color).buildPlayer();
        players.forEach(p -> {
            if (p.getUsername().equals(player.getUsername()) || p.getColor().equals(player.getColor())) {
                throw new IllegalArgumentException("Player with the same username or color already exists");
            }
        });
        if (this.players.size() >= MAX_PLAYERS) {
            throw new IllegalArgumentException("Maximum number of players reached");
        }
        this.players.add(player);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder addUnforseen(final Unforeseen unforeseen) {
        if (this.unforseenCards.size() >= MAX_UNFORSEEN_CARDS) {
            throw new IllegalArgumentException("Maximum number of unforseen cards reached");
        }
        this.unforseenCards.add(unforeseen);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder addTrapdoor(final TrapDoor trapDoor) {
        if (this.trapDoors.size() >= MAX_TRAP_DOORS) {
            throw new IllegalArgumentException("Maximum number of trapdoors reached");
        }
        this.trapDoors.add(trapDoor);
        return this;
    }
}
