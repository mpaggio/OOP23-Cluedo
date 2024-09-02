package it.unibo.cluedo.view.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unibo.cluedo.controller.mapsetupcontroller.impl.MapSetupController;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.utilities.Position;

/**
 * The MapView class is responsible for rendering the game map.
 * It extends JPanel and uses MapSetupController to get tile informations.
 */
public class BoardView extends JPanel {
    private static final Logger LOGGER = Logger.getLogger(BoardView.class.getName());
    private static final MapSetupController CONTROLLER = new MapSetupController();
    private static final long serialVersionUID = 1L;
    private static final Color TILE_BORDER_COLOR = new Color(0, 0, 0, 0.8f);
    private transient BufferedImage mapImage;

    /**
     * Constructs a MapView with the specified visitor.
     */
    public BoardView() {
        try {
            this.mapImage = ImageIO.read(new File(BoardImpl.getMapImagePath()));
            this.setLayout(null);
            this.setPreferredSize(new Dimension(this.mapImage.getWidth(), this.mapImage.getHeight()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't find the map image file", e);
        }
    }

    /**
     * Paints the component. This method is called whenever the component needs to be rendered.
     * 
     * @param g the Graphics object used for drawing
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final List<Color> playerColors = List.of(
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
            Color.WHITE,
            Color.BLACK
        );
        final Iterator<Color> colorIterator = playerColors.iterator();
        final int panelWidth = getSize().width;
        final int panelHeight = getSize().height;
        final int imageWidth = mapImage.getWidth();
        final int imageHeight = mapImage.getHeight();
        final double imageAspect = (double) imageWidth / imageHeight;
        final int newWidth, newHeight;
        if (panelWidth / imageAspect <= panelHeight) {
            newWidth = panelWidth;
            newHeight = (int) (panelWidth / imageAspect);
        } else {
            newWidth = (int) (panelHeight * imageAspect);
            newHeight = panelHeight;
        }
        g.drawImage(mapImage, 0, 0, newWidth, newHeight, this);
        g.setColor(Color.GRAY);
        drawTiles(g, colorIterator, newWidth, newHeight);
    }

    /**
     * Draws a single tile on the map.
     * 
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @param g the Graphics object used for drawing
     * @param playerColor the color of the player, if present
     * @param tileSize the size of the tile
     */
    private void drawTile(final double x, final double y, final Graphics g,
        final Optional<Color> playerColor, final double tileSize) {
            final Graphics2D g2 = (Graphics2D) g;
            final Rectangle2D rect = new Rectangle2D.Double(x, y, tileSize, tileSize);
            g2.setPaint(TILE_BORDER_COLOR);
            g2.draw(rect);
            if (playerColor.isPresent()) {
                g2.setPaint(playerColor.get());
                final Ellipse2D circle = new Ellipse2D.Double(
                    x + tileSize / 4,
                    y + tileSize / 4,
                    tileSize / 2,
                    tileSize / 2
                );
                g2.fill(circle);
            }
        }

    /**
     * Draws all the tiles on the map.
     * 
     * @param g the Graphics object used for drawing
     * @param colorIterator an iterator over the player colors
     * @param newWidth the new width of the resized image
     * @param newHeight the new heigth of the resized image
     */
    private void drawTiles(final Graphics g, final Iterator<Color> colorIterator,
        final int newWidth, final int newHeight) {
            final double originalOffsetX = MapSetupController.getOffsetX();
            final double originalOffsetY = MapSetupController.getOffsetY();
            final double originalTileSize = MapSetupController.getTileSize();
            final double scaleX = (double) newWidth / mapImage.getWidth();
            final double scaleY = (double) newHeight / mapImage.getHeight();
            final double offsetX = originalOffsetX * scaleX;
            final double offsetY = originalOffsetY * scaleY;
            final double tileSize = originalTileSize * scaleX;
            for (final Position pos : CONTROLLER.getTilesPositions()) {
                final double x = offsetX + ((double) pos.getY()) * tileSize;
                final double y = offsetY + ((double) pos.getX()) * tileSize;
                if (Position.getDefaultPositions().contains(pos) && colorIterator.hasNext()) {
                    drawTile(x, y, g, Optional.of(colorIterator.next()), tileSize);
                } else {
                    drawTile(x, y, g, Optional.empty(), tileSize);
                }
            }
    }
}
