package it.unibo.cluedo.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;
import java.util.ArrayList;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.accusation.impl.AccusationImpl;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.deck.api.Deck;
import it.unibo.cluedo.model.dice.api.Dice;
import it.unibo.cluedo.model.dice.impl.DiceImpl;
import it.unibo.cluedo.model.movement.impl.BoardMovement;
import it.unibo.cluedo.model.movement.impl.MoveInSingleDirection;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.statistics.impl.StatisticsImpl;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.model.turnmanager.impl.TurnManagerImpl;
import it.unibo.cluedo.model.unforeseen.api.UnforeseenEffect;
import it.unibo.cluedo.model.unforeseen.impl.UnforeseenEffectFactory;
import it.unibo.cluedo.model.unforeseen.impl.ReRollDiceEffect;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.utilities.Position;
import it.unibo.cluedo.utilities.TurnFase;

/**
 * Class that implements the GameModel interface, it represents the model of the
 * Cluedo game.
 */

final class GameModelImpl implements GameModel {

    private static final int DICE_SIDES = 6;

    private boolean hasMovedInThisTurn;
    private TurnFase fase;
    private int currentDiceResult;
    private final Accusation accusation;
    private final List<Player> players;
    private final TurnManager turnManager;
    private final Statistics statistics;
    private final Set<Card> solution;
    private final BoardImpl map;

    /**
     * Constructor of the class.
     * @param players the players of the game.
     * @param deck the deck of the game.
     * @param solution the solution of the game.
     */
    GameModelImpl(final List<Player> players, final Deck deck, final Set<Card> solution) {
        this.players = List.copyOf(players);
        turnManager = new TurnManagerImpl(players);
        statistics = new StatisticsImpl(players);
        this.solution = solution;
        final List<Set<Card>> cards = List.copyOf(deck.distributeCards(players.size()));
        players.forEach(player -> {
            if (player instanceof MutablePlayer) {
                ((MutablePlayer) this.players.get(players.indexOf(player)))
                    .setPlayerCards(List.copyOf(cards.get(players.indexOf(player))));
            }
        });
        fase = TurnFase.ROLL_DICE;
        accusation = new AccusationImpl();
        map = new BoardImpl();
        final List<Position> defaultPositions = new ArrayList<>(Position.getDefaultPositions());
        Collections.shuffle(defaultPositions);
        players.forEach(player -> {
            if (player instanceof MutablePlayer) {
                ((MutablePlayer) player).setPosition(defaultPositions.get(players.indexOf(player)));
            }
        });
    }
    /**
     * Constructor of the class by a saved game.
     * @param players the players of the game.
     * @param solution the solution of the game.
     * @param turnManager the turn manager of the previous game.
     * @param statistics the statistics of the previous game.
     * @param map the map of the previous game.
     */
    GameModelImpl(final List<Player> players, final Set<Card> solution,
        final TurnManager turnManager, final Statistics statistics,
        final BoardImpl map) {
        this.players = List.copyOf(players);
        this.turnManager = turnManager;
        this.statistics = statistics;
        this.solution = solution;
        fase = TurnFase.ROLL_DICE;
        accusation = new AccusationImpl();
        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player endTurn() {
        if (fase == TurnFase.END_TURN) {
            turnManager.switchTurn();
            fase = TurnFase.ROLL_DICE;
            return getCurrentPlayer();
        } else {
            throw new IllegalStateException("You can't end your turn now");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Notebook getNotebook() {
        return getCurrentPlayer().getPlayerNotebook();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Effect getSquareEffects(final Square position) {
        return position.getEffect();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return players.stream().anyMatch(Player::hasWon) || turnManager.isGameFinished();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> makeAccusation(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (fase == TurnFase.MAKE_ACCUSATION) {
            try {
                checkConsistencyAcc(weapon, room, character, roomPosition);
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
            if ("Cluedo".equals(roomPosition.getName())) {
                throw new IllegalArgumentException("You can't make an accusation in the Cluedo room");
            }
            int index = (players.indexOf(getCurrentPlayer())) % players.size() + 1;
            Optional<Card> result = Optional.empty();
            while (index != players.indexOf(getCurrentPlayer()) && result.isEmpty()) {
                result = accusation.accuse(weapon, room, character, Set.copyOf(players.get(index).getPlayerCards()));
                index = (index + 1) % players.size();
            }
            statistics.incrementAccusationsMade(getCurrentPlayer());
            fase = TurnFase.END_TURN;
            result.ifPresent(card -> getNotebook().logSeenCards(card.getName()));
            return result;
        }
        throw new IllegalStateException("You can't make an accusation now");
    }

    @Override
    public void movePlayer(final Square position, final MovementStrategy.Direction direction) {
        final BoardMovement boardMovement = new BoardMovement(map);
        final MoveInSingleDirection move = new MoveInSingleDirection((MutablePlayer) getCurrentPlayer(), 
        1, direction, boardMovement, map);
        if (fase.equals(TurnFase.MOVE_PLAYER)) {
            if (getCurrentPlayer().getCurrentSteps() > 0) {
                try {
                    move.execute();
                } catch (IllegalArgumentException e) {
                    return;
                }
                if (!map.isSquareInRoom(position) && getCurrentPlayer() instanceof MutablePlayer) {
                    ((MutablePlayer) getCurrentPlayer()).setInRoom(false);
                }
                hasMovedInThisTurn = true;
                statistics.incrementSteps(getCurrentPlayer(), 1);
                if (hasMovedInThisTurn && getCurrentPlayer().isInRoom() && getCurrentPlayer() instanceof MutablePlayer) {
                    fase = TurnFase.MAKE_ACCUSATION;
                    ((MutablePlayer) getCurrentPlayer()).setCurrentSteps(0);
                    statistics.incrementRoomsVisited(getCurrentPlayer());
                    return;
                } else {
                    applyEffect(position);
                }
                if (getCurrentPlayer().getCurrentSteps() == 0) {
                    fase = TurnFase.END_TURN;
                }
            } else {
                throw new IllegalStateException("You can't move now");
            }
        } else {
            throw new IllegalStateException("You can't move now");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
        if (fase == TurnFase.ROLL_DICE) {
            hasMovedInThisTurn = false;
            if (getCurrentPlayer().canDoubleRollDice() && getCurrentPlayer() instanceof MutablePlayer) {
                final Dice dice = new DiceImpl(DICE_SIDES);
                fase = TurnFase.MOVE_PLAYER;
                currentDiceResult = dice.rollDice();
                ((MutablePlayer) getCurrentPlayer()).setCurrentSteps(getCurrentPlayer().getSteps() + currentDiceResult);
                ((MutablePlayer) getCurrentPlayer()).setDoubleRollDice(false);
                return currentDiceResult;
            }
            if (getCurrentPlayer().canNextTurn() && getCurrentPlayer() instanceof MutablePlayer) {
                final Dice dice = new DiceImpl(DICE_SIDES);
                fase = TurnFase.DRAW_UNFORESEEN;
                currentDiceResult = dice.rollDice();
                ((MutablePlayer) getCurrentPlayer()).setCurrentSteps(getCurrentPlayer().getSteps() + currentDiceResult);
                return currentDiceResult;
            } else if (getCurrentPlayer() instanceof MutablePlayer) {
                ((MutablePlayer) getCurrentPlayer()).setNextTurn(true);
                fase = TurnFase.END_TURN;
                return 0;
            }
        }
        throw new IllegalStateException("You can't roll the dice now");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean makeFinalAccusation(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (fase.equals(TurnFase.MAKE_ACCUSATION)) {
            checkConsistencyAcc(weapon, room, character, roomPosition);
            if (!"Cluedo".equals(roomPosition.getName())) {
                throw new IllegalArgumentException("You can only make the final accusation in the Cluedo room");
            }
            fase = TurnFase.END_TURN;
            statistics.incrementAccusationsMade(getCurrentPlayer());
            if (accusation.finalAccuse(weapon, room, character, solution)) {
                if (getCurrentPlayer() instanceof MutablePlayer) {
                    ((MutablePlayer) getCurrentPlayer()).setHasWon(true);
                }
                return true;
            }
            turnManager.removePlayer(getCurrentPlayer());
            return false;
        }
        throw new IllegalStateException("You can't make the final accusation now");
    }

    private void applyEffect(final Square position) {
        position.landOn(getCurrentPlayer());
        if (position.getEffect().getType().equals(Effect.EffectType.BONUS)) {
            fase = TurnFase.ROLL_DICE;
        }
    }

    private void checkConsistencyAcc(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (roomPosition == null) {
            throw new IllegalArgumentException("You must be in a room to make an accusation");
        }
        if (weapon.getType() != Card.Type.WEAPON) {
            throw new IllegalArgumentException("The card is not a weapon");
        }
        if (room.getType() != Card.Type.ROOM) {
            throw new IllegalArgumentException("The card is not a room");
        }
        if (character.getType() != Card.Type.CHARACTER) {
            throw new IllegalArgumentException("The card is not a character");
        }
        if ("Cluedo".equals(roomPosition.getName())) {
            return;
        }
        if (!room.getName().equals(roomPosition.getName())) {
            throw new IllegalArgumentException("You must be in the room you are accusing");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useTrapdoor(final Room room) {
        if (fase == TurnFase.MOVE_PLAYER) {
            if (room.isPlayerInRoom(getCurrentPlayer())) {
                if (room.getTrapDoor().isPresent()) {
                    final Square newPosition = room.getTrapDoor().get().getConnectedRoom().getSquares().get(0);
                    if (getCurrentPlayer() instanceof MutablePlayer) {
                        map.getRoomBySquare(map.getSquareByPosition(getCurrentPlayer().getCurrentPosition()))
                            .get().removePlayerFromRoom(getCurrentPlayer());
                        ((MutablePlayer) getCurrentPlayer()).setPosition(newPosition.getPosition());
                        ((MutablePlayer) getCurrentPlayer()).setInRoom(true);
                        map.getRoomBySquare(newPosition).get().addPlayerInRoom(getCurrentPlayer());
                        fase = TurnFase.MAKE_ACCUSATION;
                    }
                } else {
                    throw new IllegalArgumentException("There is no trapdoor in this room");
                }
            } else {
                throw new IllegalArgumentException("You are not in a room");
            }
        } else {
            throw new IllegalStateException("You can't use the trapdoor now");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnforeseenEffect drawUnforeseen() {
        if (fase.equals(TurnFase.DRAW_UNFORESEEN)) {
            final Player nextPlayer = players.get((players.indexOf(getCurrentPlayer()) + 1) % players.size());
            final UnforeseenEffect unforeseen = UnforeseenEffectFactory.createUnforeseenEffect(nextPlayer);
            unforeseen.applyEffect(getCurrentPlayer());
            if (unforeseen instanceof ReRollDiceEffect) {
                fase = TurnFase.ROLL_DICE;
            } else {
                fase = TurnFase.MOVE_PLAYER;
            }
            return unforeseen;
        } else {
            throw new IllegalStateException("You can't draw an unforeseen card now");
        }
    }
}
