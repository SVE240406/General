package scenes;

import main.Main;
import main.State;
import ui.BasicButton;
import ui.ButtonUsage;
import ui.TypeField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Main.SCALE;

public class OutToExcel implements Scene, ButtonUsage {
    private Main main;
    private BasicButton createXlsx;
    private TypeField name;

    public OutToExcel(Main main) {
        this.main = main;
        initClasses();
    }

    @Override
    public void initClasses() {
        name = new TypeField("Name", new Rectangle((int) (50 * SCALE), main.getGamePanel().getHeight()/2, (int) (300 * SCALE), (int) (50 * SCALE)), main, this);
        createXlsx = new BasicButton("Create Excel file", new Rectangle((int) (350 * SCALE), main.getGamePanel().getHeight()/2, (int) (200 * SCALE), (int) (50 * SCALE)), main);
        createXlsx.setSource(this);
    }

    @Override
    public void update() {
        name.update();
        createXlsx.update();
    }

    @Override
    public void draw(Graphics g) {
        name.repaint();
        createXlsx.repaint();
    }

    @Override
    public void changeButtonState(boolean state) {
        name.setVisible(state);
        createXlsx.setVisible(state);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            main.setState(State.MENU);
            return;
        }
        name.keyTyped(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        name.mouseClicked(e);
    }

    @Override
    public void resetFocus() {
        name.resetFocus();
    }

    @Override
    public void buttonPressed(ActionEvent e) {
        main.xlsx(name.get());
        main.setState(State.MENU);
    }
}
