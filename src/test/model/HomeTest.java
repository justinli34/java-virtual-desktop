package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HomeTest {
    private Home home;

    @BeforeEach
    public void setup() {
        home = new Home();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, home.getAppList().size());
    }

    @Test
    public void testAddApplication() {
        Application app = new FiberSearch();
        home.addApplication(app);
        assertEquals(app, home.getAppList().get(0));
        assertEquals(1, home.getAppList().size());
    }
}
