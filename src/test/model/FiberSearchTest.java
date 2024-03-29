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
        assertEquals(3, fiberSearch.getWebpages().size());
    }

    @Test
    public void testGetWebpages() {
        ArrayList<Webpage> pages = fiberSearch.getWebpages();

        assertEquals(3, fiberSearch.getWebpages().size());
    }

    @Test
    public void testGetUrl() {
        assertEquals("www.machinegallery.com", fiberSearch.getUrl());
    }
}