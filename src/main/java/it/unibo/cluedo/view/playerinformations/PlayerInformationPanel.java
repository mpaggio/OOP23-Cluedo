package it.unibo.cluedo.view.playerinformations;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.view.board.BoardView;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * PlayerInformationPanel is a panle that displays the current player's information.
 * The panle includes a colored square on the left representing the player's color
 * and a not editable text area on the right showing player's name, steps and position.
 */
public class PlayerInformationPanel extends JPanel {
    private static final long serialVersionUID = 4L;
    private static final int COLOR_PREFERRED_SIZE = 50;
    private static final int AREA_BORDER_SIZE = 10;
    private final JPanel colorPanel;
    private final JLabel infoArea;

    /**
     * Constructs a PlayerInformationPanel.
     */
    public PlayerInformationPanel() {
        // Set the layout
        this.setLayout(new BorderLayout());

        // Create the player's color panel
        colorPanel = new JPanel();
        colorPanel.setBackground(
            BoardView.ColorEnum.getColorByName(
                Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getColor()
            )
        );
        colorPanel.setPreferredSize(new Dimension(COLOR_PREFERRED_SIZE, COLOR_PREFERRED_SIZE));

        infoArea = new JLabel();
        updatePlayerInfo();

        // Add a board to the text area
        infoArea.setBorder(
            BorderFactory.createEmptyBorder(
                AREA_BORDER_SIZE,
                AREA_BORDER_SIZE,
                AREA_BORDER_SIZE,
                AREA_BORDER_SIZE
            )
        );

        // Add component to the main panel
        super.add(colorPanel, BorderLayout.WEST);
        super.add(infoArea, BorderLayout.CENTER);
    }

    /**
     * Updates the player information view by repainting it.
     */
    public void updatePlayerInformationPanel() {
        updatePlayerInfo();
        super.repaint();
    }

    private void updatePlayerInfo() {
        final String playerName = Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getUsername();
        final String playerPositionX = String.valueOf(
            Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentPosition().getX()
        );
        final String playerPositionY = String.valueOf(
            Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentPosition().getY()
        );
        final String playerSteps = String.valueOf(Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentSteps());
        infoArea.setText(
            "<html><body style='font-size: 14px; font-family: Arial;'>"
            + playerName
            + "\t- current steps: "
            + playerSteps
            + "\t- current position: ("
            + playerPositionX
            + ", "
            + playerPositionY
            + ")"
            + "</body></html>"
        );
        colorPanel.setBackground(
            BoardView.ColorEnum.getColorByName(
                Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getColor()
            )
        );
    }
}
