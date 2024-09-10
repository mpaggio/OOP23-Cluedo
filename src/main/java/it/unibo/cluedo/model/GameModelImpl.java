package it.unibo.cluedo.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.accusation.impl.AccusationImpl;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.board.api.Board;
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
import it.unibo.cluedo.model.unforeseen.impl.SwapCardEffect;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.utilities.Position;
import it.unibo.cluedo.utilities.TurnFase;

/**
 * Class that implements the GameModel interface, it represents the model of the
 * Cluedo game.
 */

public class GameModelImpl implements GameModel {

    private static final int DICE_SIDES = 6;
    private static final int NUM_OF_STEPS = 1;
    private static final String MOVE_ERROR = "You can't move now";
    private static final String ROLL_ERROR = "You can't roll the dice now";
    private static final String ACCUSATION_ERROR = "You can't make an accusation now";
    private static final String ACCUSATION_ERROR_CLUEDO = "You can't make an accusation in the Cluedo room";
    private static final String FINAL_ACCUSATION_ERROR = "You can't make the final accusation now";
    private static final String TRAPDOOR_ERROR = "You can't use the trapdoor now";
    private static final String UNFORESEEN_ERROR = "You can't draw an unforeseen card now";
    private static final String NO_SUSPECT = "The card is not a character";
    private static final String NO_WEAPON = "The card is not a weapon";
    private static final String NO_ROOM = "The card is not a room";
    private static final String NOT_IN_ROOM = "You are not in a room";
    private static final String NOT_SAME_ROOM = "You must be in the room you are accusing";
    private static final String NOT_CLUEDO = "You can only make the final accusation in the Cluedo room";
    private static final String NO_TRAPDOOR = "There is no trapdoor in this room";

    private TurnFase fase;
    private int currentDiceResult;
    private final Accusation accusation;
    private final List<Player> players;
    private final TurnManager turnManager;
    private final Statistics statistics;
    private final Set<Card> solution;
    private final Set<Card> allCards;
    private final Board map;

    /**
     * Constructor of the class.
     * @param players the players of the game.
     * @param deck the deck of the game.
     * @param solution the solution of the game.
     */
    public GameModelImpl(final List<Player> players, final Deck deck, final Set<Card> solution) {
        this.players = List.copyOf(players);
        this.turnManager = new TurnManagerImpl(players);
        this.statistics = new StatisticsImpl(players);
        this.solution = new HashSet<>(solution);
        this.allCards = Set.copyOf(deck.getAllCards());
        final List<Set<Card>> cards = List.copyOf(deck.distributeCards(players.size()));
        players.forEach(player -> {
            if (player instanceof MutablePlayer) {
                ((MutablePlayer) this.players.get(players.indexOf(player)))
                    .setPlayerCards(List.copyOf(cards.get(players.indexOf(player))));
            }
        });
        this.fase = TurnFase.ROLL_DICE;
        this.accusation = new AccusationImpl();
        this.map = new BoardImpl();
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
     * @param allCards all the cards of the game.
     * @param fase the fase of the previous game.
     */
     public GameModelImpl(final List<Player> players, final Set<Card> solution,
        final TurnManager turnManager, final Statistics statistics,
        final Board map, final Set<Card> allCards, final TurnFase fase) {
        this.allCards = new HashSet<>(allCards);
        this.players = List.copyOf(players);
        this.turnManager = new TurnManagerImpl(
            turnManager.getPlayers(),
            turnManager.getCurrentPlayerIndex(),
            turnManager.isGameFinished()
        );
        this.statistics = statistics;
        this.solution = new HashSet<>(solution);
        this.fase = fase;
        this.accusation = new AccusationImpl();
        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
        if (this.fase.equals(TurnFase.ROLL_DICE)) {
            if (getCurrentPlayer().canDoubleRollDice() && getCurrentPlayer() instanceof MutablePlayer) {
                final Dice dice = new DiceImpl(DICE_SIDES);
                this.fase = TurnFase.MOVE_PLAYER;
                this.currentDiceResult = dice.rollDice();
                ((MutablePlayer) getCurrentPlayer()).setCurrentSteps(getCurrentPlayer().getCurrentSteps() + getDiceResult());
                ((MutablePlayer) getCurrentPlayer()).setDoubleRollDice(false);
                return getDiceResult();
            }
            if (getCurrentPlayer().canNextTurn() && getCurrentPlayer() instanceof MutablePlayer) {
                final Dice dice = new DiceImpl(DICE_SIDES);
                this.fase = TurnFase.DRAW_UNFORESEEN;
                this.currentDiceResult = dice.rollDice();
                ((MutablePlayer) getCurrentPlayer()).setCurrentSteps(getDiceResult());
                return getDiceResult();
            }
        }
        throw new IllegalStateException(ROLL_ERROR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnforeseenEffect drawUnforeseen() {
        if (this.fase.equals(TurnFase.DRAW_UNFORESEEN)) {
            final Player nextPlayer = turnManager.getPlayers().get(
                (turnManager.getPlayers().indexOf(getCurrentPlayer()) + 1) % turnManager.getPlayers().size()
            );
            final UnforeseenEffect unforeseen = UnforeseenEffectFactory.createUnforeseenEffect(nextPlayer);
            unforeseen.applyEffect(getCurrentPlayer());
            if (unforeseen instanceof ReRollDiceEffect) {
                this.fase = TurnFase.ROLL_DICE;
            } else {
                if (unforeseen instanceof SwapCardEffect) {
                    this.statistics.incrementViewedCards(getCurrentPlayer());
                    this.statistics.incrementViewedCards(nextPlayer);
                }
                this.fase = TurnFase.MOVE_PLAYER;
            }
            return unforeseen;
        } else {
            throw new IllegalStateException(UNFORESEEN_ERROR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final MovementStrategy.Direction direction) {
        final BoardMovement boardMovement = new BoardMovement(getMap());
        final MoveInSingleDirection move = new MoveInSingleDirection(getCurrentPlayer(), NUM_OF_STEPS,
            direction, boardMovement, getMap());
        if (this.fase.equals(TurnFase.MOVE_PLAYER)) {
            if (getCurrentPlayer().getCurrentSteps() > 0) {
                try {
                    move.execute();
                } catch (IllegalArgumentException e) {
                    return;
                }
                if (!getMap().isSquareInRoom(getSquare()) && getCurrentPlayer() instanceof MutablePlayer) {
                    ((MutablePlayer) getCurrentPlayer()).setInRoom(false);
                }
                this.statistics.incrementSteps(getCurrentPlayer());
                if (getCurrentPlayer().isInRoom()) {
                    this.fase = TurnFase.MAKE_ACCUSATION;
                    this.statistics.incrementRoomsVisited(getCurrentPlayer());
                    return;
                }
                if (getCurrentPlayer().getCurrentSteps() == 0) {
                    this.fase = TurnFase.END_TURN;
                }
                if (!getCurrentPlayer().isInRoom()) {
                    applyEffect(getSquare());
                }
            } 
        } else {
            throw new IllegalStateException(MOVE_ERROR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useTrapdoor(final Room room) {
        if (this.fase.equals(TurnFase.MOVE_PLAYER)) {
            if (room.isPlayerInRoom(getCurrentPlayer())) {
                if (room.getTrapDoor().isPresent() && getCurrentPlayer() instanceof MutablePlayer
                && getMap().getRoomByName(room.getTrapDoor().get().getConnectedRoom()).isPresent()) {
                    final Square newPosition = getMap().getRoomByName(room.getTrapDoor().get().
                        getConnectedRoom()).get().getEntrances().get(0);
                    ((MutablePlayer) getCurrentPlayer()).setPosition(newPosition.getPosition());
                    ((MutablePlayer) getCurrentPlayer()).setInRoom(true);
                    ((MutablePlayer) getCurrentPlayer()).setCurrentSteps(0);
                    getMap().getRoomBySquare(newPosition).get().addPlayerInRoom(getCurrentPlayer());
                    statistics.incrementRoomsVisited(getCurrentPlayer());
                    this.fase = TurnFase.MAKE_ACCUSATION;
                } else {
                    throw new IllegalArgumentException(NO_TRAPDOOR);
                }
            } else {
                throw new IllegalArgumentException(NOT_IN_ROOM);
            }
        } else {
            throw new IllegalStateException(TRAPDOOR_ERROR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> makeAccusation(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (this.fase.equals(TurnFase.MAKE_ACCUSATION)) {
            try {
                checkConsistencyAcc(weapon, room, character, roomPosition);
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
            if ("Cluedo".equals(roomPosition.getName())) {
                throw new IllegalArgumentException(ACCUSATION_ERROR_CLUEDO);
            }
            int index = (this.players.indexOf(getCurrentPlayer()) + 1) % this.players.size();
            Optional<Card> result = Optional.empty();
            while (index != this.players.indexOf(getCurrentPlayer()) && result.isEmpty()) {
                result = this.accusation.accuse(weapon, room, character,
                    Set.copyOf(this.players.get(index).getPlayerCards()));
                index = (index + 1) % this.players.size();
            }
            this.statistics.incrementAccusationsMade(getCurrentPlayer());
            this.fase = TurnFase.END_TURN;
            result.ifPresent(card -> {
                getNotebook().logSeenCards(card.getName());
                this.statistics.incrementViewedCards(getCurrentPlayer());
            });
            return result;
        }
        throw new IllegalStateException(ACCUSATION_ERROR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean makeFinalAccusation(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (this.fase.equals(TurnFase.MAKE_ACCUSATION)) {
            checkConsistencyAcc(weapon, room, character, roomPosition);
            if (!"Cluedo".equals(roomPosition.getName())) {
                throw new IllegalArgumentException(NOT_CLUEDO);
            }
            this.fase = TurnFase.END_TURN;
            this.statistics.incrementAccusationsMade(getCurrentPlayer());
            if (accusation.finalAccuse(weapon, room, character, solution)) {
                if (getCurrentPlayer() instanceof MutablePlayer) {
                    ((MutablePlayer) getCurrentPlayer()).setHasWon(true);
                }
                return true;
            }
            if (getCurrentPlayer() instanceof MutablePlayer) {
                ((MutablePlayer) getCurrentPlayer()).setHasLost(true);
            }
            return false;
        }
        throw new IllegalStateException(FINAL_ACCUSATION_ERROR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player endTurn() {
        if (getCurrentPlayer() instanceof MutablePlayer) {
            ((MutablePlayer) getCurrentPlayer()).setCurrentSteps(0);
        }
        final List<Player> managerPlayers = this.turnManager.getPlayers();
        if (managerPlayers.get((managerPlayers
            .indexOf(getCurrentPlayer()) + 1) % managerPlayers.size())
            .canNextTurn()) {
            if (getCurrentPlayer().hasLost()) {
                this.turnManager.removePlayer(getCurrentPlayer());
            }
            getMap().resetAllEffectedSquares();
            this.turnManager.switchTurn();
            this.fase = TurnFase.ROLL_DICE;
            return getCurrentPlayer();
        }
        if (getCurrentPlayer().hasLost()) {
            this.turnManager.removePlayer(getCurrentPlayer());
        }
        getMap().resetAllEffectedSquares();
        this.turnManager.switchTurn();
        this.fase = TurnFase.END_TURN;
        if (getCurrentPlayer() instanceof MutablePlayer) {
            ((MutablePlayer) getCurrentPlayer()).setNextTurn(true);
        }
        return getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.players.stream()
            .anyMatch(Player::hasWon) || players.stream().allMatch(Player::hasLost);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statistics getStatistics() {
        return this.statistics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.turnManager.getCurrentPlayer();
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
        return this.players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Effect getSquareEffects(final Square position) {
        return position.getEffect();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TurnFase getTurnFase() {
        return this.fase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDiceResult() {
        return this.currentDiceResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Card> getSolution() {
        return new HashSet<>(this.solution);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board getMap() {
        return this.map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Card> getAllCards() {
        return new HashSet<>(this.allCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TurnManager getTurnManager() {
        return new TurnManagerImpl(
            this.turnManager.getPlayers(),
            this.turnManager.getCurrentPlayerIndex(),
            this.turnManager.isGameFinished()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Square getSquare() {
        return getMap().getSquareByPosition(getCurrentPlayer().getCurrentPosition());
    }

    private void applyEffect(final Square position) {
        position.landOn(getCurrentPlayer());
        if (getCurrentPlayer().canDoubleRollDice()) {
            this.fase = TurnFase.ROLL_DICE;
        }
    }

    private void checkConsistencyAcc(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (roomPosition == null) {
            throw new IllegalArgumentException(NOT_IN_ROOM);
        }
        if (!weapon.getType().equals(Card.Type.WEAPON)) {
            throw new IllegalArgumentException(NO_WEAPON);
        }
        if (!room.getType().equals(Card.Type.ROOM)) {
            throw new IllegalArgumentException(NO_ROOM);
        }
        if (!character.getType().equals(Card.Type.CHARACTER)) {
            throw new IllegalArgumentException(NO_SUSPECT);
        }
        if ("Cluedo".equals(roomPosition.getName())) {
            return;
        }
        if (!room.getName().equals(roomPosition.getName())) {
            throw new IllegalArgumentException(NOT_SAME_ROOM);
        }
    }
}
