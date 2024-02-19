package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}