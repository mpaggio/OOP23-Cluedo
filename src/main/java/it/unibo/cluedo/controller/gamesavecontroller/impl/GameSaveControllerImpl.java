package it.unibo.cluedo.controller.gamesavecontroller.impl;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.Date;
import java.util.Locale;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.card.impl.CardImpl;
import it.unibo.cluedo.model.card.api.Card.Type;
import it.unibo.cluedo.model.room.impl.RoomImpl;
import it.unibo.cluedo.model.square.impl.BonusEffectImpl;
import it.unibo.cluedo.model.square.impl.MalusEffectImpl;
import it.unibo.cluedo.model.square.impl.SquareImpl;


/**
 * Class that implements the GameSaveManager interface.
 */
public class GameSaveControllerImpl implements GameSaveController {

    private static final String FILE_NAME = "cluedo_saved_games.txt";
    private static final String ERROR_LOG_FILE = "cluedo_error.log";
    private static final Logger LOGGER = Logger.getLogger(GameSaveControllerImpl.class.getName());

    /**
     * Save the current game state in a file.
     *
     */
    @Override
    public void saveGame() {
        final List<Player> players = Cluedo.CONTROLLER.getGameInstance().getPlayers();
        final Board map = Cluedo.CONTROLLER.getGameInstance().getMap();
        final int currentPlayerIndex = Cluedo.CONTROLLER.getGameInstance().getPlayers().
            indexOf(Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer());

        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("The list of players cannot be null or empty");
        }
        try {
            saveGameToFile(players, map, currentPlayerIndex);
        } catch (IOException e) {
            writeErrorToLogFile("An error occurred while saving the game", e);
        }
    }

    /**
     * Save the game to a file.
     *
     * @param players the list of players in the game.
     * @param map the map of the game.
     * @param currentPlayerIndex the index of the current player.
     * @throws IOException
     */
    private void saveGameToFile(final List<Player> players, final Board map, final int currentPlayerIndex) throws IOException {
        final File file = new File(FILE_NAME);
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Cannot create the file " + FILE_NAME);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8, true))) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            final String timeStamp = dateFormat.format(new Date());
            writer.println("Game saved on " + timeStamp + " ***");

            for (final Player player : players) {
                writer.println(formatPlayerInfo(player));
            }
            writer.println("CurrentPlayerIndex: " + currentPlayerIndex);
            writer.println("Map:" + formatMapInfo(map));
            writer.println("\n");

            if (writer.checkError()) {
                throw new IOException("An error occurred while writing the file " + FILE_NAME);
            }
        }
    }

    /**
     * Format the player information.
     *
     * @param player
     * @return the formatted string.
     */
    private String formatPlayerInfo(final Player player) {
        final StringBuilder info = new StringBuilder(50);
        info.append("Player: ").append(player.getUsername())
                .append("Position: ").append(player.getCurrentPosition())
                .append("Cards: ");

        final List<Card> cards = player.getPlayerCards();
        for (int i = 0; i < cards.size(); i++) {
            final Card card = cards.get(i);
            info.append(card.getName())
                    .append(" (")
                    .append(card.getType())
                    .append(')');
            if (i < cards.size() - 1) {
                info.append(", ");
            }
        }
        return info.toString();
    }

    /**
     * Format the map information.
     *
     * @param map
     * @return the formatted string.
     */
    private String formatMapInfo(final Board map) {
        final StringBuilder info = new StringBuilder();
        for (final Room room : map.getRooms()) {
            info.append(room.toString()).append(',');
        }
        for (final Square square : map.getSquares()) {
            info.append(square.toString()).append(',');
        }
        return info.toString();
    }

    /**
     * View the saved games.
     *
     * @return the list of saved games.
     */
    @Override
    public List<String> viewSavedGames() {
        final List<String> savedGames = new ArrayList<>();
        final File file = new File(FILE_NAME);

        if (!file.exists()) {
            return savedGames;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME, StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null) {
                savedGames.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            writeErrorToLogFile("An error occurred while reading the file " + FILE_NAME, e);
        }
        return savedGames;
    }

    /**
     * Get the output of the saved games.
     *
     * @return an optional containing the string representing the saved games.
     */
    @Override
    public Optional<String> getOutputSavedGames() {
        final List<String> savedGames = viewSavedGames();
        if (savedGames.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of("Game History\n\n" + String.join("\n", savedGames));
    }

    /**
     * Write the error message to the log file.
     *
     * @param message
     * @param e
     */
    private void writeErrorToLogFile(final String message, final IOException e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ERROR_LOG_FILE, StandardCharsets.UTF_8, true))) {
            LOGGER.severe(message);
            e.printStackTrace(writer);
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, message, ioException);
        }
    }

    /**
     * Load the game from the file.
     *
     * @return an optional containing the game state.
     */
    public Optional<GameState> loadGame() {
        final File file = new File(FILE_NAME);
        if (!file.exists()) {
            LOGGER.log(Level.WARNING, "The file " + FILE_NAME + " does not exist");
            return Optional.empty();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            final List<Player> players = new ArrayList<>();
            Board map = new BoardImpl();
            int currentPlayerIndex = 0;

            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("Player:")) {
                    players.add(parsePlayer(line));
                } else if (line.startsWith("CurrentPlayerIndex:")) {
                    currentPlayerIndex = Integer.parseInt(line.split(":")[1]);
                } else if (line.startsWith("Map:")) {
                    map = parseMap(line.substring(4));
                }
                line = reader.readLine();
            }
            return Optional.of(new GameState(players, map, currentPlayerIndex));
        } catch (IOException e) {
            writeErrorToLogFile("An error occurred while loading the game", e);
            return Optional.empty();
        }
    }

    /**
     * Parse the player information.
     *
     * @param line the line containing the player information.
     * @return the player object.
     */
    private Player parsePlayer(final String line) {
        final String[] parts = line.split(" ");
        final String username = parts[1];
        final String[] pos = parts[3].replace("Position: ", "").replace("(", "").replace(")", "").split(",");
        final Position position = new Position(Integer.parseInt(pos[0].trim()), Integer.parseInt(pos[1].trim()));
        final MutablePlayer player = new MutablePlayerImpl(username, "Red");

        player.setPosition(position);
        final List<Card> cards = new ArrayList<>();
        final String[] cardParts = line.split("Cards: ")[1].split(",");
        for (final String cardInfo : cardParts) {
            final String[] cardDetails = cardInfo.trim().split(" ");
            final String cardName = cardDetails[0];
            final String cardType = cardDetails[1].replace("(", "").replace(")", "");
            cards.add(createCard(cardName, cardType));
        }
        player.setPlayerCards(cards);
        return player;
    }

    /**
     * Create a card object.
     *
     * @param name the name of the card.
     * @param type the type of the card.
     * @return the card object.
     */
    private Card createCard(final String name, final String type) {
        return new CardImpl(Type.valueOf(type.toUpperCase(Locale.ROOT)), name, "");
    }

    private Board parseMap(final String mapData) {
        final Board map = new BoardImpl();
        final List<Square> squares = new ArrayList<>();
        final List<Room> rooms = new ArrayList<>();

        for (final String component : mapData.split(",")) {
           final String trimmedComponent = component.trim();

           if (isRoom(trimmedComponent)) {
               final String[] parts = trimmedComponent.split(":");
               final String roomName = parts[0];
               final String[] coordinates = parts[1].split(",");
               final int x = Integer.parseInt(coordinates[0]);
               final int y = Integer.parseInt(coordinates[1]);

               final Room room = new RoomImpl(roomName);
               final Position position = new Position(x, y);
               final Effect effect = createEffect("none");
               final Square square = new SquareImpl(position, effect);
               room.addSquare(square);
               rooms.add(room);
           } else {
                final String[] parts = trimmedComponent.split(":");
                final String effectType = parts[0];
                final String[] coordinates = parts[1].split(",");
                final int x = Integer.parseInt(coordinates[0]);
                final int y = Integer.parseInt(coordinates[1]);

                final Position position = new Position(x, y);
                final Effect effect = createEffect(effectType);
                final Square square = new SquareImpl(position, effect);
                squares.add(square);
           }
        }
        for (final Square square : squares) {
            map.getSquares().add(square);
        }
        for (final Room room : rooms) {
            map.getRooms().add(room);
        }
        return map;
    }

    /**
     * Create an effect object.
     *
     * @param effectType the type of the effect.
     * @return the effect object.
     */
    private Effect createEffect(final String effectType) {
        switch (effectType.toLowerCase(Locale.ROOT)) {
            case "bonus":
                return new BonusEffectImpl();
            case "malus":
                return new MalusEffectImpl();
            default:
                return null;
        }
    }

    /**
     * Check if the component is a room.
     *
     * @param component the component to check.
     * @return true if the component is a room, false otherwise.
     */
    private boolean isRoom(final String component) {
        return Set.of("Kitchen", "Ballroom", "Conservatory", "Dining Room", "Billiard Room",
         "Library", "Lounge", "Hall", "Study").contains(component);
    }

    /**
     * Class representing the game state.
     */
    public static class GameState {
        private final List<Player> players;
        private final Board map;
        private final int currentPlayerIndex;

        /**
         * Constructs a new GameState object.
         *
         * @param players the list of players.
         * @param map the map of the game.
         * @param currentPlayerIndex the index of the current player.
         */
        public GameState(final List<Player> players, final Board map, final int currentPlayerIndex) {
            this.players = new ArrayList<>(players);
            this.map = map;
            this.currentPlayerIndex = currentPlayerIndex;
        }

        /**
         * Get the list of players.
         * @return the list of players.
         */
        public List<Player> getPlayers() {
            return new ArrayList<>(players);
        }

        /**
         * Get the map of the game.
         * @return the map of the game.
         */
        public Board getMap() {
            return map;
        }

        /**
         * Get the index of the current player.
         * @return the index of the current player.
         */
        public int getCurrentPlayerIndex() {
            return currentPlayerIndex;
        }
    }
}
