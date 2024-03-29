package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileExplorerTest {
    private FileExplorer fileExplorer;
    private File file;

    @BeforeEach
    public void setup() {
        fileExplorer = new FileExplorer();
        file = new File("./data/pizzarecipe.txt");
    }

    @Test
    public void testConstructor() {
        assertEquals("File Explorer", fileExplorer.getName());
        assertEquals(0, fileExplorer.getFiles().size());
    }

    @Test
    public void testAddFile() {
        fileExplorer.addFile(file);
        assertEquals(1, fileExplorer.getFiles().size());
        assertEquals(file, fileExplorer.getFiles().get("pizzarecipe.txt"));
    }

    @Test
    public void testOpenTextFile() {
        fileExplorer.addFile(file);
        try {
            assertEquals(Files.readString(Paths.get(file.getAbsolutePath())), fileExplorer.openTextFile("pizzarecipe.txt"));
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void testRecoverFiles() {
        fileExplorer.recoverFiles();
        assertEquals(1, fileExplorer.getFiles().size());
        assertTrue(fileExplorer.getFiles().containsKey("BibleStudyMail"));
    }
}
