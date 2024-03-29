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
        songFile2 = new File(System.getProperty("user.dir") + sep + "data" + sep + "BillieJean" + ".wav");
    }

    @Test
    public void testAddSong() {
        musicPlayer.addSong("Imagine");
        assertEquals(musicPlayer.getSongs().get("Imagine"), songFile);
        assertEquals(1, musicPlayer.getSongs().size());
    }

    @Test
    public void testPlaySong() {
        musicPlayer.addSong("Imagine");
        try {
            musicPlayer.playSong("Imagine");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
        assertTrue(musicPlayer.isPlaying());
//        assertTrue(musicPlayer.getClip().isActive());
    }

    @Test
    public void testPlaySongSwitchSong() {
        musicPlayer.addSong("Imagine");
        musicPlayer.addSong("BillieJean");

        try {
            musicPlayer.playSong("Imagine");
            assertTrue(musicPlayer.isPlaying());
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        try {
            musicPlayer.playSong("BillieJean");
            assertTrue(musicPlayer.isPlaying());
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void testCheckStop() {
        musicPlayer.addSong("Imagine");

        assertNull(musicPlayer.getClip());
        try {
            musicPlayer.playSong("Imagine");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        musicPlayer.checkStop();
        assertFalse(musicPlayer.getClip().isActive());

    }

    @Test
    public void testPauseSong() {
        musicPlayer.addSong("Imagine");
        musicPlayer.pauseSong();
        assertFalse(musicPlayer.isPaused());
        assertNull(musicPlayer.getClip());
        try {
            musicPlayer.playSong("Imagine");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
        musicPlayer.pauseSong();
        assertTrue(musicPlayer.isPaused());
        assertFalse(musicPlayer.getClip().isActive());
    }

    @Test
    public void testResumeSong() {
        musicPlayer.addSong("Imagine");
        musicPlayer.resumeSong();
        assertNull(musicPlayer.getClip());
        assertFalse(musicPlayer.isPaused());
        assertFalse(musicPlayer.isPlaying());
        try {
            musicPlayer.playSong("Imagine");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
        musicPlayer.pauseSong();
        musicPlayer.resumeSong();
        assertFalse(musicPlayer.isPaused());
        assertTrue(musicPlayer.isPlaying());
//        assertTrue(musicPlayer.getClip().isActive());
    }

    @Test
    public void testIsPlaying() {
        assertFalse(musicPlayer.isPlaying());
    }
}
