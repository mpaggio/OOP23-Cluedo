package it.unibo.cluedo.view.unforeseen;

import javax.swing.JOptionPane;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.unforeseencontroller.api.UnforeseenController;

/**
 * Class used to show the unforeseen effect in the view.
 */
public class UnforeseenView {

    private final UnforeseenController controller = Cluedo.CONTROLLER.getUnforeseenController();

    /**
     * Shows the effect of the unforeseen.
     */
    public void showEffect() {
        final String effectType = controller.getEffectType();
        final String effectDescription = controller.getEffectDescription();
        final String message = "You picked " + effectType + "! \n\n" + effectDescription;
        JOptionPane.showMessageDialog(null, message, "Unforeseen Effect", JOptionPane.INFORMATION_MESSAGE);
    }
}
