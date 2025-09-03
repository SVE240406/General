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

public class KontoAbschliessen implements Scene, ButtonUsage {
    private Main main;
    private BasicButton kontoAbschliessen;
    private TypeField datum;
    private SelectionField kontoB, gegenKontoB;
    Konto[] konten;
    private String[] kontoNames;

    public KontoAbschliessen(Main main, Konto[] konten) {
        this.main = main;
        this.konten = konten;
        initClasses();
    }

    @Override
    public void initClasses() {
        setKonten(konten);
        datum = new TypeField("Datum", new Rectangle((int) (250 * SCALE), main.getGamePanel().getHeight()/2, (int) (75*SCALE), (int) (50*SCALE)), main, this);
        kontoAbschliessen = new BasicButton("Konto abschließen", new Rectangle((int) (600 * SCALE), main.getGamePanel().getHeight()/2, (int) (150*SCALE), (int) (50*SCALE)), main);
        kontoAbschliessen.setSource(this);
    }

    public void setKonten(Konto[] konten) {
        this.konten = konten;
        kontoNames = new String[konten.length];
        for (int i = 0; i < konten.length; i++)
            kontoNames[i] = konten[i].toString();
        kontoB = new SelectionField("Konto", kontoNames, new Rectangle((int) (50 * SCALE), main.getGamePanel().getHeight()/2, (int) (150*SCALE), (int) (50*SCALE)), main, this);
        gegenKontoB = new SelectionField("Gegenkonto", kontoNames, new Rectangle((int) (400 * SCALE), main.getGamePanel().getHeight()/2, (int) (150*SCALE), (int) (50*SCALE)), main, this);
    }

    @Override
    public void update() {
        datum.update();
        kontoB.update();
        gegenKontoB.update();
        kontoAbschliessen.update();
    }

    @Override
    public void draw(Graphics g) {
        datum.repaint();
        kontoB.repaint();
        gegenKontoB.repaint();
        kontoAbschliessen.repaint();
    }

    @Override
    public void changeButtonState(boolean state) {
        datum.setVisible(state);
        kontoB.setVisible(state);
        gegenKontoB.setVisible(state);
        kontoAbschliessen.setVisible(state);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            main.setState(State.MENU);
            return;
        }
        datum.keyTyped(e);
        kontoB.keyTyped(e);
        gegenKontoB.keyTyped(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        datum.mouseClicked(e);
        kontoB.mouseClicked(e);
        gegenKontoB.mouseClicked(e);
    }

    @Override
    public void resetFocus() {
        datum.resetFocus();
        kontoB.resetFocus();
        gegenKontoB.resetFocus();
    }

    @Override
    public void buttonPressed(ActionEvent e) {
        Konto konto = konten[kontoB.getValue()];
        Konto gegenK = konten[gegenKontoB.getValue()];
        GregorianCalendar date = getCalendar(datum.get());
        float betrag = calculateBetrag(konto);
        if(betrag > 0)
            main.addBuchung(new Buchungssatz(date, gegenK, konto, betrag));
        else
            main.addBuchung(new Buchungssatz(date, konto, gegenK, betrag*(-1)));

        main.setState(State.MENU);
    }

    private float calculateBetrag(Konto konto) {
        float res = 0;
        for (int i = 0; i < konto.numOfBuchungen(); i++)
            res += konto.getBuchung(i).getSoll() - konto.getBuchung(i).getHaben();
        return res;
    }
}
