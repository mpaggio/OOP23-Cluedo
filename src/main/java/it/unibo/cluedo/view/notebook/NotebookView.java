package it.unibo.cluedo.view.notebook;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.util.List;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;


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
    private static final int LAY = 10;
    private static final int COLS = 20;
    private static final int BORD = 15;
    private static final int BORD1 = 0;
    private static final int BORD2 = 5;
    private static final int BACK = 240;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the NotebookView class.
     */
    public NotebookView() {

        setLayout(new BorderLayout());

        setLayout(new BorderLayout(LAY, LAY));
        setBorder(new EmptyBorder(BORD, BORD, BORD, BORD));
        setBackground(new Color(BACK, BACK, BACK));

        suspectsArea = createTextArea();
        weaponsArea = createTextArea();
        roomsArea = createTextArea();

        final JScrollPane suspectsScrollPane = new JScrollPane(suspectsArea);
        final JScrollPane weaponsScrollPane = new JScrollPane(weaponsArea);
        final JScrollPane roomsScrollPane = new JScrollPane(roomsArea);

        final JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(new LineBorder(Color.BLACK, 2));
        leftPanel.add(createSectionLabel("Seen Suspects"), BorderLayout.NORTH);
        leftPanel.add(suspectsScrollPane, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        final JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new LineBorder(Color.BLACK, 2));
        centerPanel.add(createSectionLabel("Seen Weapons"), BorderLayout.NORTH);
        centerPanel.add(weaponsScrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(new LineBorder(Color.BLACK, 2));
        rightPanel.add(createSectionLabel("Seen Rooms"), BorderLayout.NORTH);
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

    private JTextArea createTextArea() {
        final JTextArea textArea = new JTextArea(LAY, COLS);
        textArea.setFont(new Font("Arial", Font.PLAIN, COLS));
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setForeground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JLabel createSectionLabel(final String text) {
        final JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new EmptyBorder(BORD2, BORD1, BORD2, BORD1));
        return label;
    }
}
