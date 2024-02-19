package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicPlayerTest {
    private MusicPlayer musicPlayer;
    private File songFile;

    @BeforeEach
    public void setup() {
        musicPlayer = new MusicPlayer();
        String sep = System.getProperty("file.separator");
        songFile = new File(System.getProperty("user.dir") + sep + "data" + sep + "Imagine" + ".wav");
    }

    @Test
    public void testAddSong() {
        musicPlayer.addSong("Imagine");
        assertEquals(musicPlayer.getSongs().get(0), songFile);
        assertEquals(1, musicPlayer.getSongs().size());
    }

    @Test
    public void testPlaySong() {
        musicPlayer.addSong("Imagine");
        musicPlayer.playSong(0);
        assertTrue(musicPlayer.isPlaying());
    }

    @Test
    public void testPlaySongTwice() {
        musicPlayer.addSong("Imagine");
        musicPlayer.playSong(0);
        assertTrue(musicPlayer.isPlaying());

        musicPlayer.playSong(0);
        assertTrue(musicPlayer.isPlaying());
    }
}
