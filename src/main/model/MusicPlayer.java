package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class MusicPlayer extends Application {
    private ArrayList<String> songs;
    private String currentSong;
    private AudioInputStream audio;
    private Clip clip;
    private boolean playing = false;

    public MusicPlayer() {
        super("Music Player");
        songs = new ArrayList<>();
    }

    // EFFECTS: if no song playing, plays song. if song playing, stops current song and plays new song
    public void playSong(String song) {
        String sep = System.getProperty("file.separator");
        File songFile = new File(System.getProperty("user.dir") + sep + "src" + sep + "main"
                + sep + "resources" + sep + song + ".wav");

        if (playing) {
            clip.stop();
            playing = false;
        }
        try {
            audio = AudioSystem.getAudioInputStream(songFile);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            playing = true;
        } catch (Exception e) {
            System.out.println("Failed to play song");
        }
    }

    // EFFECTS: pauses song
    public void pauseSong() {

    }

    // EFFECTS: start song at given time point
    public void scrubSong(int time) {

    }

    public void addSong(String song) {
        songs.add(song);
    }

    public ArrayList<String> getSongs() {
        return songs;
    }
}
