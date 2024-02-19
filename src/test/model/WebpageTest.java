package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WebpageTest {
    private Webpage page;

    @BeforeEach
    public void setup() {
        String sep = System.getProperty("file.separator");
        File file = new File(System.getProperty("user.dir") + sep + "data" + sep + "WebpageTestText");
        page = new Webpage("Apple", file);
    }

    @Test
    public void testGetText() {
        try {
            assertEquals("apples", page.getText());
        } catch (IOException e) {
            fail("Threw unexpected IOException");
        }
    }

    @Test
    public void testGetTextAfter10Visits() {
        page.setNumVisits(11);
        try {
            assertEquals("Page could not be reached", page.getText());
        } catch (IOException e) {
            fail("Threw unexpected IOException");
        }
    }

    @Test
    public void testGetName() {
        assertEquals("Apple", page.getName());
    }
}
