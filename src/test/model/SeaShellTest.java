package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeaShellTest {
    private SeaShell seaShell;

    @BeforeEach
    public void setup() {
        seaShell = new SeaShell();
    }

    @Test
    public void testConstructor() {
        assertEquals("Sea Shell", seaShell.getName());
    }

    @Test
    public void testHandleInput() {
        assertEquals(1, seaShell.handleInput("ss 5243817791"));
        assertEquals(2, seaShell.handleInput("ss recover"));
        assertEquals(0, seaShell.handleInput("ss a"));
    }
}
