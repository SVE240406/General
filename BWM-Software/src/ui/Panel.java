package ui;

import main.Main;

import javax.swing.*;
import java.awt.*;

import static main.Main.*;

public class Panel extends JPanel {
    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private Main main;

    public Panel(Main main) {
        mouseInputs = new MouseInputs(this);
        this.main = main;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        main.render(g);
    }

    public Main getMain() {
        return main;
    }
}
