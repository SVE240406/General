package ui;

import main.Main;

import javax.swing.*;
import java.awt.*;

import static main.Main.*;

public class Panel extends JPanel {
    private MouseInputs mouseInputs;
    private Main main;

    public Panel(Main main) {
        this.main = main;
        setPanelSize();
        addKeyListener(new KeyboardInputs(main));
        mouseInputs = new MouseInputs(main);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        setPreferredSize(size);
    }

    public Main getMain() {
        return main;
    }
}
