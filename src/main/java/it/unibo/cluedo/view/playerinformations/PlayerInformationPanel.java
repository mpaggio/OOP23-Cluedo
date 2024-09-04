package it.unibo.cluedo.view.playerinformations;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.view.board.BoardView;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class PlayerInformationPanel extends JPanel {
    private static final long serialVersionUID = 4L;
    private static final int COLOR_PREFERRED_SIZE = 50;
    private static final int AREA_BORDER_SIZE = 10;

    public PlayerInformationPanel() {
        this.setLayout(new BorderLayout());

        // Create the player's color panel
        final JPanel colorPanel = new JPanel();
        colorPanel.setBackground(
            BoardView.ColorEnum.getColorByName(
                Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getColor()
            )
        );
        colorPanel.setPreferredSize(new Dimension(COLOR_PREFERRED_SIZE, COLOR_PREFERRED_SIZE));

        // Create the player's not editable text information area
        final String playerName = Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getUsername();
        final String playerPositionX = String.valueOf(
            Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentPosition().getX()
        );
        final String playerPositionY = String.valueOf(
            Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentPosition().getY()
        );
        final String playerSteps = String.valueOf(Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentSteps());
        final JLabel infoArea = new JLabel(
            "<html><body style='font-size: 14px; font-family: Arial;'>" +
            playerName +
            "\t- current steps: " +
            playerSteps +
            "\t- current position: (" +
            playerPositionX +
            ", " +
            playerPositionY +
            ")" +
            "</body></html>"
        );

        // Add a board to the text area
        infoArea.setBorder(
            BorderFactory.createEmptyBorder(
                AREA_BORDER_SIZE,
                AREA_BORDER_SIZE,
                AREA_BORDER_SIZE,
                AREA_BORDER_SIZE
            )
        );

        super.add(colorPanel, BorderLayout.WEST);
        super.add(infoArea, BorderLayout.CENTER);
    }
}
