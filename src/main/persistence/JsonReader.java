package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Home from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Home from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Home read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHome(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Home from JSON object and returns it
    private Home parseHome(JSONObject jsonObject) {
        Home h = new Home();
        addApps(h, jsonObject);
        return h;
    }

    // MODIFIES: h
    // EFFECTS: parses apps from JSON object and adds them to home
    private void addApps(Home h, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("apps");
        for (Object json : jsonArray) {
            JSONObject app = (JSONObject) json;
            addApp(h, app);
        }
    }

    // MODIFIES: h
    // EFFECTS: parses app from JSON object and adds it to home
    private void addApp(Home h, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Application app = null;
        if (name.equals("Fiber Search")) {
            app = new FiberSearch();
        } else if (name.equals("Music Player")) {
            app = new MusicPlayer();
            JSONArray jsonArray = jsonObject.getJSONArray("songs");
            for (Object json : jsonArray) {
                String song = ((JSONObject) json).getString("songName");
                ((MusicPlayer) app).addSong(song);
            }
        } else if (name.equals("File Explorer")) {
            app = new FileExplorer();
            JSONArray jsonArray = jsonObject.getJSONArray("files");
            for (Object json : jsonArray) {
                File file = new File(((JSONObject) json).getString("path"));
                ((FileExplorer) app).addFile(file);
            }
        } else if (name.equals("Sea Shell")) {
            app = new SeaShell();
        }

        h.addApplication(app);
    }
}
