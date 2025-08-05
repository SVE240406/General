package scenes;

import main.Main;
import main.State;
import software.Buchung;
import software.Konto;
import software.KontoTyp;
import ui.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Main.SCALE;

public class NewKonto implements Scene, ButtonUsage {
    private Main main;
    private BasicButton newKonto;
    private TypeField kontoNr, kontoName, anfangBestand;
    private SelectionField kontoTyp, abSH;

    public NewKonto(Main main) {
        this.main = main;
        initClasses();
    }

    @Override
    public void initClasses() {
        kontoNr = new TypeField("Kontonummer", new Rectangle((int) (50 * SCALE), main.getGamePanel().getHeight()/2, (int) (100 * SCALE), (int) (50 * SCALE)), main, this);
        kontoName = new TypeField("Kontoname", new Rectangle((int) (200 * SCALE), main.getGamePanel().getHeight()/2, (int) (150 * SCALE), (int) (50 * SCALE)), main, this);
        kontoTyp = new SelectionField("KontoTyp", new String[]{"aBk", "pBk", "E", "A", "G"}, new Rectangle((int) (400*SCALE), main.getGamePanel().getHeight()/2, (int) (150*SCALE), (int) (50*SCALE)), main, this);

        anfangBestand = new TypeField("Anfangsbestand", new Rectangle((int) (50 * SCALE), main.getGamePanel().getHeight()/2 + (int)(100 * SCALE), (int) (150 * SCALE), (int) (50 * SCALE)), main, this);
        abSH = new SelectionField("Soll/Haben", new String[]{"Soll", "Haben"}, new Rectangle((int) (200 * SCALE), main.getGamePanel().getHeight()/2 + (int)(100 * SCALE), (int) (100 * SCALE), (int) (50 * SCALE)), main, this);

        newKonto = new BasicButton("Create Konto", new Rectangle((int) (600*SCALE), main.getGamePanel().getHeight()/2, (int) (150 * SCALE), (int) (50*SCALE)), main);
        newKonto.setSource(this);
    }

    @Override
    public void update() {
        kontoNr.update();
        kontoName.update();
        kontoTyp.update();

        anfangBestand.update();
        abSH.update();

        newKonto.update();
    }

    @Override
    public void draw(Graphics g) {
        kontoNr.repaint();
        kontoName.repaint();
        kontoTyp.repaint();

        anfangBestand.repaint();
        abSH.repaint();

        newKonto.repaint();
    }

    @Override
    public void changeButtonState(boolean state) {
        kontoNr.setVisible(state);
        kontoName.setVisible(state);
        kontoTyp.setVisible(state);

        anfangBestand.setVisible(state);
        abSH.setVisible(state);

        newKonto.setVisible(state);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            main.setState(State.MENU);
            return;
        }
        kontoNr.keyTyped(e);
        kontoName.keyTyped(e);
        kontoTyp.keyTyped(e);

        anfangBestand.keyTyped(e);
        abSH.keyTyped(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        kontoNr.mouseClicked(e);
        kontoName.mouseClicked(e);
        kontoTyp.mouseClicked(e);

        anfangBestand.mouseClicked(e);
        abSH.mouseClicked(e);
    }

    @Override
    public void resetFocus() {
        kontoNr.resetFocus();
        kontoName.resetFocus();
        kontoTyp.resetFocus();

        anfangBestand.resetFocus();
        abSH.resetFocus();
    }

    @Override
    public void buttonPressed(ActionEvent e) {
        int nr = Integer.parseInt(kontoNr.get());
        String name = kontoName.get();
        KontoTyp kontotyp;
        switch (kontoTyp.getValue()){
            case 0 -> kontotyp = KontoTyp.AKTIVES_BK;
            case 1 -> kontotyp = KontoTyp.PASSIVES_BK;
            case 2 -> kontotyp = KontoTyp.ERLOES;
            case 3 -> kontotyp = KontoTyp.AUFWAND;
            default -> kontotyp = KontoTyp.GRAU;
        }

        String ab = anfangBestand.get();
        if(ab != null)
            main.addKonto(new Konto(nr, name, kontotyp, Float.parseFloat(ab), (abSH.getValue()==0)));
        else
            main.addKonto(new Konto(nr, name, kontotyp));

        main.setState(State.MENU);
    }
}
