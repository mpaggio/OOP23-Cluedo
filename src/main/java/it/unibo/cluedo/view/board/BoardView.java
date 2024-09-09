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
import java.util.Optional;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.utilities.Position;

/**
 * The BoardView class is responsible for rendering the game map.
 * It extends JPanel and uses MapSetupController to get tile informations.
 */
public class BoardView extends JPanel {
    private static final Logger LOGGER = Logger.getLogger(BoardView.class.getName());
    private static final long serialVersionUID = 1L;
    private static final Color TILE_BORDER_COLOR = new Color(0, 0, 0, 0.8f);
    private static final double TILE_SIZE = 23;
    private static final double OFFSET_X = 41.7;
    private static final double OFFSET_Y = 23.3;
    private transient BufferedImage mapImage;

    /**
     * Enum representing player colors.
     */
    public enum ColorEnum {
        /**
         * Red color.
         */
        RED(Color.RED),
        /**
         * Green color.
         */
        GREEN(Color.GREEN),
        /**
         * Blue color.
         */
        BLUE(Color.BLUE),
        /**
         * White color.
         */
        WHITE(Color.WHITE),
        /**
         * Pink color.
         */
        PINK(Color.PINK),
        /**
         * Yellow color.
         */
        YELLOW(Color.YELLOW);

        private final Color color;

        ColorEnum(final Color color) {
            this.color = color;
        }

        /**
         * Gets the color object associated with the enum constant.
         * 
         * @return the color object associated with the enum constant
         */
        public Color getColor() {
            return this.color;
        }

        /**
         * Gets the Color object by name.
         * 
         * @param name the name of the color
         * @return the Color object, or null if the name is invalid
         */
        public static Color getColorByName(final String name) {
            try {
                return ColorEnum.valueOf(name.toUpperCase(Locale.ROOT)).getColor();
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    /**
     * Constructs a BoardView with the specified visitor.
     */
    public BoardView() {
        try {
            this.mapImage = ImageIO.read(new File(BoardImpl.getMapImagePath()));
            this.setLayout(new BorderLayout());
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
        // Panel and image size and aspect
        final int panelWidth = getSize().width;
        final int panelHeight = getSize().height;
        final int imageWidth = mapImage.getWidth();
        final int imageHeight = mapImage.getHeight();
        final double imageAspect = (double) imageWidth / imageHeight;
        final int newWidth, newHeight;

        // Check the aspect relationship with heigth and width to resize
        if (panelWidth / imageAspect <= panelHeight) {
            newWidth = panelWidth;
            newHeight = (int) (panelWidth / imageAspect);
        } else {
            newWidth = (int) (panelHeight * imageAspect);
            newHeight = panelHeight;
        }

        // Offset to set the grid correctly above the centered image
        final int x = (panelWidth - newWidth) / 2;
        final int y = (panelHeight - newHeight) / 2;

        // Sets the visual image, color and grid
        g.drawImage(mapImage, x, y, newWidth, newHeight, this);
        g.setColor(Color.GRAY);
        drawTiles(g, newWidth, newHeight, x, y);
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
            // Create the geometric components to draw
            final Graphics2D g2 = (Graphics2D) g;
            final Rectangle2D rect = new Rectangle2D.Double(x, y, tileSize, tileSize);
            g2.setPaint(TILE_BORDER_COLOR);
            g2.draw(rect);

            // Print a center circle with the diameter half the size of the tile
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
     * @param newWidth the new width of the resized image
     * @param newHeight the new heigth of the resized image
     */

    /**
     * Draws all the tiles on the map.
     * 
     * @param g the Graphics object used for drawing
     * @param newWidth the new width of the resized image
     * @param newHeight the new witdth of the resized image
     * @param offsetX the x offset of the panel
     * @param offsetY the y offset of the panel
     */
    private void drawTiles(final Graphics g, final int newWidth, final int newHeight,
        final int offsetX, final int offsetY) {
            // Sets the scale and the offset from the border of the resized image
            final double scaleX = (double) newWidth / mapImage.getWidth();
            final double scaleY = (double) newHeight / mapImage.getHeight();
            final double tileOffsetX = OFFSET_X * scaleX + offsetX;
            final double tileOffsetY = OFFSET_Y * scaleY + offsetY;
            final double tileSize = TILE_SIZE * scaleX;

            // Calculate coordinates of the new tile and check if there is a player
            for (final Position pos : Cluedo.CONTROLLER.getMapController().getTilesPositions()) {
                final double x = tileOffsetX + ((double) pos.getY()) * tileSize;
                final double y = tileOffsetY + ((double) pos.getX()) * tileSize;
                if (Cluedo.CONTROLLER.getMapController().getPlayersPositions().contains(pos)) {
                    final String color = Cluedo.CONTROLLER.getMapController().getPlayersPositionsAndColors().get(pos);
                    drawTile(x, y, g, Optional.of(ColorEnum.getColorByName(color)), tileSize);
                } else {
                    drawTile(x, y, g, Optional.empty(), tileSize);
                }
            }

            //Sets and draws the tile of the current player always at the end
            final double currentX = (double) Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentPosition().getX();
            final double currentY = (double) Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getCurrentPosition().getY();
            final String color = Cluedo.CONTROLLER.getGameInstance().getCurrentPlayer().getColor();
            final double x = tileOffsetX + (currentY * tileSize);
            final double y = tileOffsetY + (currentX * tileSize);
            drawTile(x, y, g, Optional.of(ColorEnum.getColorByName(color)), tileSize);
    }

    /**
     * Updates the board view by repainting it.
     */
    public void updateBoard() {
        super.repaint();
    }
}
