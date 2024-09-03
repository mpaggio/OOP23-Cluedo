package it.unibo.cluedo.view.playercards;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.util.List;

/**
 * PlayerCardsPopUpPanel is a custom Panel that displays a collection of player cards.
 * It shows the images of the cards in a popup window and includes a button to close the popup.
 */
public class PlayerCardsPopUpPanel extends JPanel {
    private static final long serialVersionUID = 3L;

    /**
     * Constructs a PlayerCardsPopUpPanel with the specified list of image paths.
     * 
     * @param imagesPaths a list of strings representing the paths to the card images
     */
    public PlayerCardsPopUpPanel(final List<String> imagesPaths) {
        setLayout(new BorderLayout());

        // Panel for disposing cards images
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());

        for (final String path : imagesPaths) {
            final ImageIcon image = new ImageIcon(path);
            final JLabel imageLabel = new JLabel(image);
            imagePanel.add(imageLabel);
        }

        add(imagePanel, BorderLayout.CENTER);

        final JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(PlayerCardsPopUpPanel.this);
                topFrame.dispose();
            }
        });

        add(closeButton, BorderLayout.SOUTH);
    }
}
