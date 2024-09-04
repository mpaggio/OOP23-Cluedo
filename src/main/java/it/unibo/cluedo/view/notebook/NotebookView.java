package it.unibo.cluedo.view.notebook;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.util.List;

import it.unibo.cluedo.application.Cluedo;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;

/**
 * Class used to show the notebook in the view.
 */
public class NotebookView extends JPanel {

    private final NotebookController controller = Cluedo.CONTROLLER.getNotebookController();
    private final JTextArea suspectsArea;
    private final JTextArea weaponsArea;
    private final JTextArea roomsArea;
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the NotebookView class.
     */
    public NotebookView() {

        setLayout(new BorderLayout());

        suspectsArea = new JTextArea(ROWS, COLUMNS);
        weaponsArea = new JTextArea(ROWS, COLUMNS);
        roomsArea = new JTextArea(ROWS, COLUMNS);

        final JScrollPane suspectsScrollPane = new JScrollPane(suspectsArea);
        final JScrollPane weaponsScrollPane = new JScrollPane(weaponsArea);
        final JScrollPane roomsScrollPane = new JScrollPane(roomsArea);

        final JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Seen Suspects"), BorderLayout.NORTH);
        leftPanel.add(suspectsScrollPane, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        final JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Seen Weapons"), BorderLayout.NORTH);
        centerPanel.add(weaponsScrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Seen Rooms"), BorderLayout.NORTH);
        rightPanel.add(roomsScrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    /**
     * Initializes the view.
     */
    public void initView() {
        updateNotebookView();
    }

    /**
     * Updates the notebook view.
     */
    public void updateNotebookView() {
        updateTextArea(suspectsArea, controller.getSeenSuspect());
        updateTextArea(weaponsArea, controller.getSeenWeapon());
        updateTextArea(roomsArea, controller.getSeenRoom());
    }

    /**
     * Updates the text area.
     * @param textArea the JTextArea to update
     * @param data the data to set
     */
    private void updateTextArea(final JTextArea textArea, final List<String> data) {
        textArea.setText("");
        for (final String s : data) {
            textArea.append(s + "\n");
        }
    }
}
