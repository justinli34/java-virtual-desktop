package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code is based on JsonSerializationDemo from CPSC 210
class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Home home = new Home();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterDefaultHome() {
        try {
            Home home = new Home();
            JsonWriter writer = new JsonWriter("./data/testWriterDefaultHome.json");
            writer.open();
            writer.write(home);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDefaultHome.json");
            home = reader.read();
            assertEquals(0, home.getAppList().size());

        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    void testWriterGeneralHome() {
        try {
            Home home = new Home();
            FiberSearch fiberSearch = new FiberSearch();
            MusicPlayer musicPlayer = new MusicPlayer();
            FileExplorer fileExplorer = new FileExplorer();
            home.addApplication(fiberSearch);
            home.addApplication(musicPlayer);
            home.addApplication(fileExplorer);

            musicPlayer.addSong("Imagine");
            fileExplorer.addFile(new File("./data/Imagine.wav"));
            fileExplorer.addFile(new File("./data/pizzarecipe.txt"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHome.json");
            writer.open();
            writer.write(home);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHome.json");
            home = reader.read();
            fiberSearch = (FiberSearch) home.getAppList().get(0);
            musicPlayer = (MusicPlayer) home.getAppList().get(1);
            fileExplorer = (FileExplorer) home.getAppList().get(2);
            assertEquals(3, home.getAppList().size());
            assertEquals("Fiber Search", fiberSearch.getName());
            assertEquals("Music Player", musicPlayer.getName());
            assertEquals("File Explorer", fileExplorer.getName());

            assertEquals(2, fiberSearch.getWebpages().size());
            assertEquals(1, musicPlayer.getSongs().size());
            assertEquals(2, fileExplorer.getFiles().size());

        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }
}