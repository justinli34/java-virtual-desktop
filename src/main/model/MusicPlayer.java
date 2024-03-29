package model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

// A music player app containing a list of downloaded songs.
public class MusicPlayer extends Application {
    private Map<String, File> songs;
    private String currentSong;
    private AudioInputStream audio;
    private Clip clip;
    private boolean playing;
    private boolean paused;

    public MusicPlayer() {
        super("Music Player");
        songs = new HashMap<>();
        playing = false;
        paused = false;
        currentSong = "";
    }

    // MODIFIES: this
    // EFFECTS: if no song playing, plays song. if song playing, stops current song and plays given song
    public void playSong(String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//            if (playing) {
//                clip.stop();
//                playing = false;
//            }
        checkStop();
        audio = AudioSystem.getAudioInputStream(songs.get(name));
        clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
        playing = true;

    }

    // MODIFIES: this
    // EFFECTS: checks if clip is currently playing and stops if it is
    public void checkStop() {
        if (clip != null) {
            clip.stop();
        }
    }

    // MODIFIES: this
    // EFFECTS: pauses song
    public void pauseSong() {
        if (playing) {
            clip.stop();
            paused = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: resumes song
    public void resumeSong() {
        if (paused) {
            clip.start();
            playing = true;
            paused = false;
        }
    }

    // MODIFIES: this, fileExplorer
    // EFFECTS: adds song with given name to songs list
    public void addSong(String song) {
        String sep = System.getProperty("file.separator");
        File songFile = new File(System.getProperty("user.dir") + sep + "data" + sep + song + ".wav");
        songs.put(song, songFile);
    }

    public Map<String, File> getSongs() {
        return songs;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isPaused() {
        return paused;
    }

    public Clip getClip() {
        return clip;
    }

    // EFFECTS: returns this as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);

        JSONArray jsonArray = new JSONArray();
        for (File f : songs.values()) {
            JSONObject song = new JSONObject();
            song.put("songName", f.getName().substring(0, f.getName().length() - 4));
            jsonArray.put(song);
        }
        json.put("songs", jsonArray);
        return json;
    }
}
