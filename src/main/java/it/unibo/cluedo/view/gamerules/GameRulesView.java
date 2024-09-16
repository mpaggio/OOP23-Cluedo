package it.unibo.cluedo.view.gamerules;

import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * View class used to display the Cluedo game rules.
 */
public class GameRulesView extends JScrollPane {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(GameRulesView.class.getName());
    /**
     * Displays the Cluedo game rules in a dialog box.
     */
    public GameRulesView() {
        final String rules = readRulesFromFile("rules.txt");
        final JTextArea rulesArea = new JTextArea(rules);
        rulesArea.setEditable(false);
        rulesArea.setLineWrap(true);
        rulesArea.setWrapStyleWord(true);
        rulesArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        rulesArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        super.setViewportView(rulesArea);
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Reads the game rules from a file.
     * @param filePath the path to the file containing the game rules
     * @return a string containing the game rules
     */
    private String readRulesFromFile(final String filePath) {
        final StringBuilder rules = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null) {
                rules.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading game rules from file: " + filePath, e);
        }
        return rules.toString();
    }
}
