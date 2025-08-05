package scenes;

import main.Main;
import main.State;
import ui.BasicButton;
import ui.ButtonUsage;
import ui.StateSwitch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Main.SCALE;

public class StartMenu implements Scene, ButtonUsage {
    private Main main;
    private BasicButton[] buttons;

    private int buttonPosX = (int) (550 * SCALE), buttonW = (int) (150 * SCALE), buttonH = (int) (50 * SCALE);
    private Rectangle[] bounds = {
            new Rectangle(buttonPosX, (int) (25 * SCALE), buttonW, buttonH),
            new Rectangle(buttonPosX, (int) (100 * SCALE), buttonW, buttonH),
            new Rectangle(buttonPosX, (int) (175 * SCALE), buttonW, buttonH),
            new Rectangle(buttonPosX, (int) (250 * SCALE), buttonW, buttonH),
            new Rectangle(buttonPosX, (int) (325 * SCALE), buttonW, buttonH),
    };

    public StartMenu(Main main) {
        this.main = main;
        initClasses();
    }

    public void initClasses() {
        buttons = new BasicButton[]{new StateSwitch("Neues Konto", bounds[0], State.N_KONTO, main), new StateSwitch("Neue Buchung", bounds[1], State.BUCHUNG, main), new BasicButton("Print Buchungssätze", bounds[2], main),
                new StateSwitch("Konten Abschließen", bounds[3], State.K_ABSCHLIESSEN, main), new StateSwitch("Erzeuge Excel-File", bounds[4], State.XLSX, main)};
        buttons[2].setSource(this);
    }

    public void update() {
        for (BasicButton button : buttons)
            button.update();
    }

    public void draw(Graphics g) {
        for(BasicButton button : buttons)
            button.repaint();
    }

    public void changeButtonState(boolean state) {
        for (BasicButton button : buttons)
            button.setVisible(state);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void resetFocus() {

    }

    @Override
    public void buttonPressed(ActionEvent e) {
        if (buttons[2] == e.getSource())
            main.printBuchungssaetze();
    }
}
