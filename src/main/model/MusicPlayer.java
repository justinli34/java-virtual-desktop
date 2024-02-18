package model;

import java.util.ArrayList;

public class MusicPlayer extends Application {
    private ArrayList<String> songs;
    private String currentSong;

    public MusicPlayer() {
        super("Music Player");
        songs = new ArrayList<>();
    }

    // EFFECTS: plays song
    public void playSong() {

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
}
