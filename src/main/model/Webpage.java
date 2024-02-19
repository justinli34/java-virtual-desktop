package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Webpage {
    private String name;
    private File text;

    public Webpage(String name, File text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns text contained in text file
    public String getText() throws IOException {
        return Files.readString(Paths.get(text.getAbsolutePath()));
    }
}
