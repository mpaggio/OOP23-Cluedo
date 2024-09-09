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
    private final JButton upButton;
    private final JButton downButton;
    private final JButton leftButton;
    private final JButton rightButton;
    /**
     * Constructor for the JoystickView class.
     */
    public JoystickView() {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        this.upButton = new JButton("UP");
        this.downButton = new JButton("DOWN");
        this.leftButton = new JButton("LEFT");
        this.rightButton = new JButton("RIGHT");
        upButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveUp();
            Cluedo.CONTROLLER.updateButtons();
        });
        downButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveDown();
            Cluedo.CONTROLLER.updateButtons();
        });
        leftButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveLeft();
            Cluedo.CONTROLLER.updateButtons();
        });
        rightButton.addActionListener(e -> {
            Cluedo.CONTROLLER.getJoystickController().moveRight();
            Cluedo.CONTROLLER.updateButtons();
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

    /**
     * Disable all the joystick buttons.
     */
    public void disableButtons() {
        this.upButton.setEnabled(false);
        this.downButton.setEnabled(false);
        this.leftButton.setEnabled(false);
        this.rightButton.setEnabled(false);
    }

    /**
     * Enable all the joystick buttons.
     */
    public void enableButtons() {
        this.upButton.setEnabled(true);
        this.downButton.setEnabled(true);
        this.leftButton.setEnabled(true);
        this.rightButton.setEnabled(true);
    }
}
