package it.unibo.cluedo.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import it.unibo.cluedo.model.accusation.api.Accusation;
import it.unibo.cluedo.model.accusation.impl.AccusationImpl;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.dice.api.Dice;
import it.unibo.cluedo.model.dice.impl.DiceImpl;
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
import it.unibo.cluedo.utilities.Pair;
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

    /**
     * Constructor of the class.
     * @param players the players of the game.
     */
    GameModelImpl(final List<Player> players) {
        this.players = players;
        turnManager = new TurnManagerImpl(players);
        statistics = new StatisticsImpl(players);
        solution = new HashSet<>();
    }

    @Override
    public void applyEffect() {
        // TODO Auto-generated method stub
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

    @Override
    public Notebook getNotebook() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Pair<Square, Effect> getSquareEffects(final Square position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        return false;
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
                final Set<Card> playerCardsSet = new HashSet<>(players.get(index).getPlayerCards());
                result = accusation.accuse(weapon, room, character, playerCardsSet);
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
                ((MutablePlayer) players.get(index)).setHasWon(isOver());
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
