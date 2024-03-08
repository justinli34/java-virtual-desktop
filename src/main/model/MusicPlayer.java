package model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

// A music player app containing a list of downloaded songs.
public class MusicPlayer extends Application {
    private ArrayList<File> songs;
    private String currentSong;
    private AudioInputStream audio;
    private Clip clip;
    private boolean playing = false;

    public MusicPlayer() {
        super("Music Player");
        songs = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if no song playing, plays song. if song playing, stops current song and plays song at given pos
    public void playSong(int pos) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (playing) {
            clip.stop();
            playing = false;
        }
        audio = AudioSystem.getAudioInputStream(songs.get(pos));
        clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
        playing = true;
    }

//    // EFFECTS: pauses song
//    public void pauseSong() {
//
//    }
//
//    // EFFECTS: start song at given time point
//    public void scrubSong(int time) {
//
//    }

    // MODIFIES: this
    // EFFECTS: adds song with given name to songs list
    public void addSong(String song) {
        String sep = System.getProperty("file.separator");
        File songFile = new File(System.getProperty("user.dir") + sep + "data" + sep + song + ".wav");
        songs.add(songFile);
    }

    public ArrayList<File> getSongs() {
        return songs;
    }

    public boolean isPlaying() {
        return playing;
    }

    // EFFECTS: returns this as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);

        JSONArray jsonArray = new JSONArray();
        for (File f : songs) {
            JSONObject song = new JSONObject();
            song.put("songName", f.getName().substring(0, f.getName().length() - 4));
            jsonArray.put(song);
        }
        json.put("songs", jsonArray);
        return json;
    }
}
