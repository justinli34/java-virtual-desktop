package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MusicPlayerTest {
    private MusicPlayer musicPlayer;
    private File songFile;
    private File songFile2;

    @BeforeEach
    public void setup() {
        musicPlayer = new MusicPlayer();
        String sep = System.getProperty("file.separator");
        songFile = new File(System.getProperty("user.dir") + sep + "data" + sep + "Imagine" + ".wav");
        songFile2 = new File(System.getProperty("user.dir") + sep + "data" + sep + "Beep" + ".wav");
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
        try {
            musicPlayer.playSong(0);
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
        assertTrue(musicPlayer.isPlaying());
    }

    @Test
    public void testPlaySongSwitchSong() {
        musicPlayer.addSong("Imagine");
        musicPlayer.addSong("Beep");

        try {
            musicPlayer.playSong(0);
            assertTrue(musicPlayer.isPlaying());
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        try {
            musicPlayer.playSong(1);
            assertTrue(musicPlayer.isPlaying());
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void testIsPlaying() {
        assertFalse(musicPlayer.isPlaying());
    }
}
