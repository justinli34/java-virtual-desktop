package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiberSearchTest {
    private FiberSearch fiberSearch;

    @BeforeEach
    public void setup() {
        fiberSearch = new FiberSearch();
    }

    @Test
    public void testConstructor() {
        assertEquals("Fiber Search", fiberSearch.getName());
        assertEquals(2, fiberSearch.getWebpages().size());
    }

    @Test
    public void testGetWebpages() {
        ArrayList<Webpage> pages = fiberSearch.getWebpages();

        assertEquals(2, fiberSearch.getWebpages().size());

        pages.get(0).setNumVisits(13);

        assertEquals(1, fiberSearch.getWebpages().size());
    }
}