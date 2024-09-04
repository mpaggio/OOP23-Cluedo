package it.unibo.cluedo.view.joistick;

import javax.swing.JPanel;

import it.unibo.cluedo.application.Cluedo;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
/**
 * Class that represents the joystick Panel in the view.
 */
public class JoystickView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    /**
     * Constructor for the JoystickView class.
     */
    public JoystickView() {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        final JButton upButton = new JButton("UP");
        final JButton downButton = new JButton("DOWN");
        final JButton leftButton = new JButton("LEFT");
        final JButton rightButton = new JButton("RIGHT");
        upButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveUp();
        });
        downButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveDown();
        });
        leftButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveLeft();
        });
        rightButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveRight();
        });

        gbc.gridx = 1;
        gbc.gridy = 0;
        super.add(upButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        super.add(leftButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        super.add(rightButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        super.add(downButton, gbc);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
