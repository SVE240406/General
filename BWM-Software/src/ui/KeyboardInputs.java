package ui;

import main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    Main main;

    public KeyboardInputs(Main main) {
        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        main.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
