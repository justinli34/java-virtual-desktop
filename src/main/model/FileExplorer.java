package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// File explorer app that contains all downloaded files
public class FileExplorer extends Application {
    private Map<String, File> files;

    // EFFECTS: creates new FileExplorer with empty list of files
    public FileExplorer() {
        super("File Explorer");
        files = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds give file to files
    public void addFile(File f) {
        files.put(f.getName(), f);
    }

    // EFFECTS: returns files
    public Set<String> getFiles() {
        return files.keySet();
    }

    public String openTextFile(String s) throws IOException {
        File f = files.get(s);
        return Files.readString(Paths.get(f.getAbsolutePath()));
    }

    // EFFECTS: returns this as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        JSONArray jsonArray = new JSONArray();
        for (String s : files.keySet()) {
            JSONObject file = new JSONObject();
            file.put("path", files.get(s));
            jsonArray.put(file);
        }
        json.put("files", jsonArray);
        return json;
    }
}
