package it.unibo.cluedo.view.dice;

import java.util.Random;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;


import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.dicecontroller.api.DiceController;

/**
 * This class is used to show the dice roll animation.
 */
public class DiceView extends JPanel {

    private final DiceController controller = Cluedo.CONTROLLER.getDiceController();

    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_INTERVAL = 100;
    private static final int DICE_SIDES = 6;
    private static final int SIZE = 48;
    private static final long serialVersionUID = 1L;

    private final JLabel diceLabel;
    private final Random random = new Random();

    /**
     * Class constructor.
     */
    public DiceView() {
        this.diceLabel = new JLabel("Dice: ", SwingConstants.CENTER);
        final JButton rollButton = new JButton("Roll Dice");

        setLayout(new BorderLayout());
        diceLabel.setFont(new Font("Arial", Font.BOLD, SIZE));
        add(diceLabel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                startDiceRollAnimation();
            }
        });
    }

    private void startDiceRollAnimation() {
        final Timer animationTimer = new Timer(ANIMATION_INTERVAL, new ActionListener() {
            private final long startTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(final ActionEvent e) {
                final long elapsed = System.currentTimeMillis() - startTime;
                if (elapsed < ANIMATION_DURATION) {
                    diceLabel.setText("Rolling dice..." + random.nextInt(DICE_SIDES) + 1);
                } else {
                    ((Timer) e.getSource()).stop();
                    showFinalDiceResult();
                }
            }
        });
        animationTimer.start();
    }

    private void showFinalDiceResult() {
        final int result = controller.getResult();
        diceLabel.setText("You rolled: " + result);
    }
}
