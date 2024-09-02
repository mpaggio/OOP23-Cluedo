package it.unibo.cluedo.controller.gamesavecontroller.impl;

import java.io.IOException;
import java.util.List;
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
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;

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
     * @param players the list of players in the game.
     * @param map     the list of the map components.
     */
    @Override
    public void saveGame(final List<Player> players, final Board map) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("The list of players cannot be null or empty");
        }
        try {
            saveGameToFile(players, map);
        } catch (IOException e) {
            writeErrorToLogFile("An error occurred while saving the game", e);
        }
    }

    /**
     * Save the game to a file.
     *
     * @param players
     * @param map
     * @throws IOException
     */
    private void saveGameToFile(final List<Player> players, final Board map) throws IOException {
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
}
