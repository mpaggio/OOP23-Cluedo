package it.unibo.cluedo.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.accusation.impl.AccusationImpl;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.deck.api.Deck;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.model.dice.api.Dice;
import it.unibo.cluedo.model.dice.impl.DiceImpl;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.notebook.impl.NotebookImpl;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.statistics.impl.StatisticsImpl;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.model.turnmanager.impl.TurnManagerImpl;
import it.unibo.cluedo.utilities.TurnFase;

/**
 * Class that implements the GameModel interface, it represents the model of the
 * Cluedo game.
 */

final class GameModelImpl implements GameModel {

    private TurnFase fase = TurnFase.ROLL_DICE;
    private final Accusation accusation = new AccusationImpl();
    private final List<Player> players;
    private final TurnManager turnManager;
    private final Statistics statistics;
    private final Set<Card> solution;
    private final List<Notebook> notebooks;

    /**
     * Constructor of the class.
     * @param players the players of the game.
     */
    GameModelImpl(final List<Player> players) {
        this.players = players;
        turnManager = new TurnManagerImpl(players);
        statistics = new StatisticsImpl(players);
        final Deck deck = new DeckImpl();
        deck.initializeDeck();
        solution = deck.drawSolution();
        notebooks = new ArrayList();
        players.forEach(player -> {
            final Notebook notebook = new NotebookImpl();
            final List<String> playerCards = new ArrayList<>();
            for (final Card card : player.getPlayerCards()) {
                playerCards.add(card.getName());
            }
            notebook.initialize(playerCards);
            notebooks.add(notebook);
        });
    }

    @Override
    public void applyEffect(final Square position) {
        if (fase == TurnFase.APPLY_EFFECT) {
            if (!position.getEffect().getType().equals(Effect.EffectType.NONE)) {
                position.getEffect().apply(getCurrentPlayer());
                fase = fase.nextFase();
            }
            fase = fase.nextFase();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        if (fase == TurnFase.END_TURN) {
            turnManager.switchTurn();
            fase = fase.nextFase();
        } else {
            throw new IllegalStateException("You can't end the turn now");
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
        return notebooks.get(players.indexOf(getCurrentPlayer()));
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
        return players.stream().anyMatch(Player::hasWon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> makeAccusation(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (fase == TurnFase.MAKE_ACCUSATION) {
            checkConsistencyAcc(weapon, room, character, roomPosition);
            if ("Cluedo".equals(roomPosition.getName())) {
                throw new IllegalArgumentException("You can't make an accusation in the Cluedo room");
            }
            int index = (players.indexOf(getCurrentPlayer()) + 1) % players.size() + 1;
            Optional<Card> result = Optional.empty();
            while (index != players.indexOf(getCurrentPlayer()) && result.equals(Optional.empty())) {
                result = accusation.accuse(weapon, room, character, Set.copyOf(players.get(index).getPlayerCards()));
                index = (index + 1) % players.size();
            }
            statistics.incrementAccusationsMade(getCurrentPlayer());
            fase = fase.nextFase();
            return result;
        }
        throw new IllegalStateException("You can't make an accusation now");
    }

    @Override
    public void movePlayer(final Square position) {
        // TODO Auto-generated method stub
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
        if (fase == TurnFase.ROLL_DICE) {
            final Dice dice = new DiceImpl(6);
            fase = fase.nextFase();
            return dice.rollDice();
        }
        throw new IllegalStateException("You can't roll the dice now");
    }

    @Override
    public boolean makeFinalAccusation(final Card weapon, final Card room, final Card character, final Room roomPosition) {
        if (fase == TurnFase.MAKE_ACCUSATION) {
            checkConsistencyAcc(weapon, room, character, roomPosition);
            if (!"Cluedo".equals(roomPosition.getName())) {
                throw new IllegalArgumentException("You can only make the final accusation in the Cluedo room");
            }
            fase = fase.nextFase();
            statistics.incrementAccusationsMade(getCurrentPlayer());
            if (accusation.finalAccuse(weapon, room, character, solution)) {
                final int index = players.indexOf(getCurrentPlayer());
                if (getCurrentPlayer() instanceof MutablePlayer) {
                    ((MutablePlayer) players.get(index)).setHasWon(isOver());
                }
                return true;
            }
        }
        return false;
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
    }
}
