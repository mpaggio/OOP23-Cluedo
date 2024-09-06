package it.unibo.cluedo.controller.unforeseencontroller.api;

/**
 * Interface for the controller class that handles the unforeseen logic.
 */
public interface UnforeseenController {

    /**
     * This method is used to get the type of the unforeseen.
     * @return the type of the unforeseen.
     */
    String getEffectType();

    /**
     * This method is used to get the description of the unforeseen.
     * @return the description of the unforeseen.
     */
    String getEffectDescription();

    /**
     * This method is used to show the unforeseen.
     */
    void showUnforeseen();

    /**
     * This method is used to initialize the controller.
     */
    void initializeController();

    /**
     * Returns true if the unforeseen has been shown.
     * 
     * @return true if the unforeseen has been shown, false otherwise
     */
    boolean hasUnforeseenBeenShown();
}
