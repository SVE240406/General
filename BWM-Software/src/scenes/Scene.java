package scenes;

import main.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Scene {
    void initClasses();
    void update();
    void draw(Graphics g);
    void changeButtonState(boolean state);
    void keyTyped(KeyEvent e);
    void mouseClicked(MouseEvent e);
    void resetFocus();
}
