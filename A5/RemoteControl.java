/**
 * Kristine Trinh
 * nlt895
 * 11190412
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class RemoteControl extends JFrame implements ActionListener {

    private DVDPlayer dvdPlayer;
    public RemoteControl(DVDPlayer dvdPlayer) {
        this.dvdPlayer = dvdPlayer;

        JButton homeBtn = new JButton("HomeBtn");
        homeBtn.addActionListener(this);
        JButton internetBtn = new JButton("InternetBtn");
        internetBtn.addActionListener(this);
        JButton navRightBtn = new JButton("NavRightBtn");
        navRightBtn.addActionListener(this);
        JButton navLeftBtn = new JButton("NavLeftBtn");
        navLeftBtn.addActionListener(this);
        JButton navUpBtn = new JButton("NavUpBtn");
        navUpBtn.addActionListener(this);
        JButton navDownBtn = new JButton("NavDownBtn");
        navDownBtn.addActionListener(this);
        JButton playBtn = new JButton("PlayBtn");
        playBtn.addActionListener(this);
        JButton stopBtn = new JButton("StopBtn");
        stopBtn.addActionListener(this);
        JButton pauseBtn = new JButton("PauseBtn");
        pauseBtn.addActionListener(this);
        JButton ffwdBtn = new JButton("FFwdBtn");
        ffwdBtn.addActionListener(this);
        JButton fRevBtn = new JButton("FRevBtn");
        fRevBtn.addActionListener(this);
        JButton skipSceneFwdBtn = new JButton("SkipSceneFwdBtn");
        skipSceneFwdBtn.addActionListener(this);
        JButton skipSceneRevBtn = new JButton("SkipSceneRevBtn");
        skipSceneRevBtn.addActionListener(this);
        JButton ejectBtn = new JButton("EjectBtn");
        ejectBtn.addActionListener(this);
        JButton closeDrawerBtn = new JButton("CloseDrawerBtn");
        closeDrawerBtn.addActionListener(this);
        JButton openDrawerBtn = new JButton("OpenDrawerBtn");
        openDrawerBtn.addActionListener(this);
        JButton acceptBtn = new JButton("AcceptBtn");
        acceptBtn.addActionListener(this);
        JButton powerBtn = new JButton("PowerBtn");
        powerBtn.addActionListener(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 3));

        mainPanel.add(homeBtn);
        mainPanel.add(internetBtn);
        mainPanel.add(navRightBtn);
        mainPanel.add(navLeftBtn);
        mainPanel.add(navUpBtn);
        mainPanel.add(navDownBtn);
        mainPanel.add(playBtn);
        mainPanel.add(stopBtn);
        mainPanel.add(pauseBtn);
        mainPanel.add(ffwdBtn);
        mainPanel.add(fRevBtn);
        mainPanel.add(skipSceneFwdBtn);
        mainPanel.add(skipSceneRevBtn);
        mainPanel.add(ejectBtn);
        mainPanel.add(closeDrawerBtn);
        mainPanel.add(openDrawerBtn);
        mainPanel.add(acceptBtn);
        mainPanel.add(powerBtn);

        add(mainPanel);
        setTitle("RemoteControl");
        setPreferredSize(new Dimension(500, 250));
        setLocationByPlatform(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            String command = ((JButton) source).getText();
            if (command != null) dvdPlayer.sendPlayerCMD(command);
        }
    }
}