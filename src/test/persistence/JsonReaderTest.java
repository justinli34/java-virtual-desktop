package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code is based on JsonSerializationDemo from CPSC 210
class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Home home = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderDefaultHome.json");
        try {
            Home home = reader.read();
            assertEquals(0, home.getAppList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHome.json");
        try {
            Home home = reader.read();
            ArrayList<Application> apps = home.getAppList();
            assertEquals(3, apps.size());

            FiberSearch fiberSearch = (FiberSearch) home.getAppList().get(0);
            MusicPlayer musicPlayer = (MusicPlayer) home.getAppList().get(1);
            FileExplorer fileExplorer = (FileExplorer) home.getAppList().get(2);

            assertEquals("Fiber Search", fiberSearch.getName());
            assertEquals("Music Player", musicPlayer.getName());
            assertEquals("File Explorer", fileExplorer.getName());

            assertEquals(2, fiberSearch.getWebpages().size());
            assertEquals(1, musicPlayer.getSongs().size());
            assertEquals(2, fileExplorer.getFiles().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}