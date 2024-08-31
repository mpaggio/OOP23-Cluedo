package it.unibo.cluedo.application;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.cluedo.view.MapView;

/**
 * The main class for the Cluedo application.
 */
public final class Cluedo {
    /**
     * Private constructor to prevent instantiation.
     */
    private Cluedo() {
    }

    /**
     * The main method to start the Cluedo application.
     * 
     * @param args the command line arguments (not used)
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame frame = new JFrame();
                final MapView map = new MapView();
                //final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(map);
                //frame.setSize(screenDimension);
                //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
