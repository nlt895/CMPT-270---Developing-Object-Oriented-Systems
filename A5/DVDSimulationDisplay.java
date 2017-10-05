/**
 * Kristine Trinh
 * nlt895
 * 11190412
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DVDSimulationDisplay extends JFrame {
    private Params params;
    private JTextArea textArea;

    private JLabel isDVD_Inserted = new JLabel();
    private JLabel isDVD_Playing = new JLabel();
    private JLabel isPaused = new JLabel();
    private JLabel isDrawer_Open = new JLabel();
    private JLabel isStreaming_Movie = new JLabel();
    private JLabel isInetConnected = new JLabel();
    private JLabel isInetAvailable = new JLabel();
    private JLabel isLastVisited = new JLabel();


    public DVDSimulationDisplay(Params params) {
        this.params = params;
        JPanel mainPanel = new JPanel();
        JPanel paramsPanel = new JPanel();
        paramsPanel.setBorder(new EmptyBorder(0,10,0,0));
        mainPanel.setLayout(new GridLayout(2, 1));

        paramsPanel.setLayout(new GridLayout(8, 1));
        paramsPanel.add(isDVD_Inserted);
        paramsPanel.add(isDVD_Playing);
        paramsPanel.add(isPaused);
        paramsPanel.add(isDrawer_Open);
        paramsPanel.add(isStreaming_Movie);
        paramsPanel.add(isInetConnected);
        paramsPanel.add(isInetAvailable);
        paramsPanel.add(isLastVisited);

        textArea = new JTextArea();
        textArea.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scroll);
        mainPanel.add(paramsPanel);
        add(mainPanel);
        setTitle("DVD Simulation Display - Turned Off");
        setPreferredSize(new Dimension(500, 750));
        setLocationByPlatform(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void displayCmd(String command, Params params) {
        this.params = params;
        if (command != null) {
            if (command.equals("")) {}
            textArea.append(command + '\n');
            updateLabels();
        }
    }

    private void updateLabels() {
        if (params.state == State.INITIALSTATE) this.setTitle("DVD Simulation Display - Turned Off");
        else {
            switch(params.state) {
                case HOMESTATE:
                    this.setTitle("Home State - Turned On");
                    break;
                case INTERNETMODE:
                    this.setTitle("Internet Mode - Turned On");
                    break;
                case DVDMODE:
                    this.setTitle("DVD Mode - Turned On");
                    break;
            }
        }
        if (params.isDVD_Inserted) isDVD_Inserted.setText("DVD is inserted");
        else isDVD_Inserted.setText("DVD is inserted");
        if (params.isDVD_Playing) isDVD_Playing.setText("DVD is playing");
        else isDVD_Playing.setText("DVD is not playing");
        if (params.isPaused) isPaused.setText("Paused");
        else isPaused.setText("Not paused");
        if (params.isDrawer_Open) isDrawer_Open.setText("Drawer is open");
        else isDrawer_Open.setText("Drawer is closed");
        if (params.isStreaming_Movie) isStreaming_Movie.setText("Streaming movie");
        else isStreaming_Movie.setText("Not streaming movie");
        if (params.isInetConnected) isInetConnected.setText("Internet is connected");
        else isInetConnected.setText("Internet is not connected");
        if (params.isInetAvailable) isInetAvailable.setText("Internet is available");
        else isInetAvailable.setText("Internet is not available");
        if (params.isLastVisited) isLastVisited.setText("Last visited site is defined");
        else isLastVisited.setText("Last visited site is not defined");
    }
}
