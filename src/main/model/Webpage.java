package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// A webpage for the Fiber Search app. Has a name/title and a body of text.
public class Webpage {
    private String name;
    private File text;
    private int numVisits = 0;

    public Webpage(String name, File text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns text contained in text file. if page has been visited 10 times, print an error message.
    public String getText() throws IOException {
        numVisits++;

        if (numVisits > 10) {
            return "Page could not be reached";
        }
        return Files.readString(Paths.get(text.getAbsolutePath()));
    }

    public int getNumVisits() {
        return numVisits;
    }

    // For testing
    public void setNumVisits(int n) {
        numVisits = n;
    }
}
