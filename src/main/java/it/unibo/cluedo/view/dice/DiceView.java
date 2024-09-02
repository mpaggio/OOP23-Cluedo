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

import it.unibo.cluedo.model.dice.api.Dice;

/**
 * This class is used to show the dice roll animation.
 */
public class DiceView extends JPanel {

    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_INTERVAL = 100;
    private static final int DICE_SIDES = 6;
    private static final long serialVersionUID = 1L;

    private  transient final Dice dice;
    private final JLabel diceLabel;
    private final Random random = new Random();

    /**
     * Class constructor.
     * @param dice the dice to show.
     */
    public DiceView(final Dice dice) {
        this.dice = dice;
        this.diceLabel = new JLabel("Roll the dice!", SwingConstants.CENTER);
        this.setLayout(new BorderLayout());
        final JButton rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(e -> startDiceRollAnimation());
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
        final int result = dice.rollDice();
        diceLabel.setText("You rolled: " + result);
    }
}
