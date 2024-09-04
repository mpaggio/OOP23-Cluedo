package it.unibo.cluedo.view.gamerules;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * View class used to display the Cluedo game rules.
 */
public class GameRulesView extends JScrollPane {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(GameRulesView.class.getName());
    /**
     * Displays the Cluedo game rules in a dialog box.
     */
    public GameRulesView() {
        final String rules = readRulesFromFile("src/main/resources/rules.txt");
        final JTextArea rulesArea = new JTextArea(rules);
        rulesArea.setEditable(false);
        rulesArea.setLineWrap(true);
        rulesArea.setWrapStyleWord(true);
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
            new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
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
