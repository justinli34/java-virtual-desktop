package ui;

import javax.swing.*;

// The superclass for all application frames
public class AppFrame extends JInternalFrame {

    // EFFECTS: creates a JInternalFrame with given title, width and height
    public AppFrame(String title, int w, int h) {
        super(title, false, true, false, true);

        setSize(w,h);

        setLocation(10, 10);
    }
}
