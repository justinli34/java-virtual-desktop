package ui;

import model.*;

import javax.swing.*;
import java.awt.*;

// Music Player frame. contains a MusicPlayer object from GUI
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
    }

    // MODIFIES: this
    // EFFECTS: creates scroll pane with songs as buttons
    public void initSongSelection() {
        Box box = Box.createVerticalBox();
        for (String s : musicPlayer.getSongs().keySet()) {
            JButton button = new JButton(s);
            button.setMaximumSize(new Dimension(150, 30));
            box.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(box);
        scrollPane.setPreferredSize(new Dimension(150, 100));

        getContentPane().add(scrollPane);
    }
}
