package ui;

import model.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Music Player frame. Contains a MusicPlayer object from GUI
public class MusicPlayerFrame extends AppFrame {
    private MusicPlayer musicPlayer;

    // MODIFIES: this
    // EFFECTS: creates AppFrame and initializes songs
    public MusicPlayerFrame(MusicPlayer musicPlayer) {
        super("Music Player", 700, 500);
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(225, 143, 255));

        this.musicPlayer = musicPlayer;

        initSongSelection();
        initPauseResumeButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates scroll pane with songs as buttons
    public void initSongSelection() {
        Box box = Box.createVerticalBox();
        for (String s : musicPlayer.getSongs().keySet()) {
            JButton button = new JButton(s);
            button.setMaximumSize(new Dimension(150, 30));
            button.addActionListener(e -> {
                try {
                    musicPlayer.playSong(s);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            box.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(box);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 2;
        getContentPane().add(scrollPane, c);
    }

    // MODIFIES: this
    // EFFECTS: creates pause and resume buttons
    public void initPauseResumeButtons() {
        JButton pause = new JButton("Pause");
        pause.addActionListener(e -> musicPlayer.pauseSong());
        pause.setPreferredSize(new Dimension(150, 30));
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        getContentPane().add(pause, c);

        JButton resume = new JButton("Resume");
        resume.addActionListener(e -> musicPlayer.resumeSong());
        resume.setPreferredSize(new Dimension(150, 30));
        c.gridx = 1;
        getContentPane().add(resume, c);
    }
}
