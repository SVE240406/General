package scenes;

import main.Main;
import main.State;
import software.Buchungssatz;
import software.Konto;
import ui.BasicButton;
import ui.ButtonUsage;
import ui.SelectionField;
import ui.TypeField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import static main.Constants.getCalendar;
import static main.Main.SCALE;

public class NewBuchung implements Scene, ButtonUsage {
    private Main main;
    private BasicButton newBuchung;
    private TypeField datum, betrag;
    private SelectionField kontoS, kontoH;
    Konto[] konten;
    private String[] kontoNames;

    public NewBuchung(Main main, Konto[] konten) {
        this.main = main;
        this.konten = konten;
        initClasses();
    }

    @Override
    public void initClasses() {
        datum = new TypeField("Datum", new Rectangle((int) (50 * SCALE), main.getGamePanel().getHeight()/2, (int) (75 * SCALE), (int) (50 * SCALE)), main, this);
        setKonten(konten);
        betrag = new TypeField("Betrag", new Rectangle((int) (550 * SCALE), main.getGamePanel().getHeight()/2, (int) (100*SCALE), (int) (50*SCALE)), main, this);
        newBuchung = new BasicButton("Buchung erstellen", new Rectangle((int) (700*SCALE), main.getGamePanel().getHeight()/2, (int) (100*SCALE), (int) (50*SCALE)), main);
        newBuchung.setSource(this);
    }

    public void setKonten(Konto[] konten) {
        this.konten = konten;
        kontoNames = new String[konten.length];
        for (int i = 0; i < konten.length; i++)
            kontoNames[i] = konten[i].toString();

        kontoS = new SelectionField("Konto Soll", kontoNames, new Rectangle((int) (150 * SCALE), main.getGamePanel().getHeight()/2, (int) (150*SCALE), (int) (50*SCALE)), main, this);
        kontoH = new SelectionField("Konto Haben", kontoNames, new Rectangle((int) (350 * SCALE), main.getGamePanel().getHeight()/2, (int) (150*SCALE), (int) (50*SCALE)), main, this);
    }

    @Override
    public void update() {
        datum.update();
        kontoS.update();
        kontoH.update();
        betrag.update();
        newBuchung.update();
    }

    @Override
    public void draw(Graphics g) {
        datum.repaint();
        kontoS.repaint();
        kontoH.repaint();
        betrag.repaint();
        newBuchung.repaint();
    }

    @Override
    public void changeButtonState(boolean state) {
        datum.setVisible(state);
        kontoS.setVisible(state);
        kontoH.setVisible(state);
        betrag.setVisible(state);
        newBuchung.setVisible(state);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            main.setState(State.MENU);
            return;
        }
        datum.keyTyped(e);
        kontoS.keyTyped(e);
        kontoH.keyTyped(e);
        betrag.keyTyped(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        datum.mouseClicked(e);
        kontoS.mouseClicked(e);
        kontoH.mouseClicked(e);
        betrag.mouseClicked(e);
    }

    @Override
    public void resetFocus() {
        datum.resetFocus();
        kontoS.resetFocus();
        kontoH.resetFocus();
        betrag.resetFocus();
    }

    @Override
    public void buttonPressed(ActionEvent e) {
        GregorianCalendar date = getCalendar(datum.get());
        Konto kontoSoll = konten[kontoS.getValue()];
        Konto kontoHaben = konten[kontoH.getValue()];
        float betrag = Float.parseFloat(this.betrag.get());
        main.addBuchung(new Buchungssatz(date, kontoSoll, kontoHaben, betrag));
        main.setState(State.MENU);
    }
}
