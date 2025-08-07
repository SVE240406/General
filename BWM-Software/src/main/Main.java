package main;

import out.Excel;
import scenes.*;
import software.Buchungssatz;
import software.Konto;
import ui.Panel;
import scenes.StartMenu;
import ui.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main implements Runnable {
    public final static float SCALE = 1.875f; //1.875f;
    public static final int WINDOW_WIDTH = (int) (832 * SCALE);
    public final static int WINDOW_HEIGHT = (int) (448 * SCALE);

    private Panel panel;
    private Thread thread;

    private StartMenu startMenu;
    private NewKonto newKonto;
    private NewBuchung newBuchung;
    private KontoAbschliessen kontoAbschliessen;
    private OutToExcel outToExcel;

    public ArrayList<Konto> konten = new ArrayList<>();
    public ArrayList<Buchungssatz> buchungssaetze = new ArrayList<>();
    private Excel excel = new Excel();

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        initClasses();
        startGameLoop();
        setState(State.MENU);
    }

    private void initClasses() {
        panel = new Panel(this);
        new Window(panel);
        panel.requestFocusInWindow();
        startMenu = new StartMenu(this);
        newKonto = new NewKonto(this);
        newBuchung = new NewBuchung(this, konten.toArray(new Konto[0]));
        kontoAbschliessen = new KontoAbschliessen(this, konten.toArray(new Konto[0]));
        outToExcel = new OutToExcel(this);
    }

    public void update(){
        startMenu.update();
        newKonto.update();
        newBuchung.update();
        kontoAbschliessen.update();
        outToExcel.update();
    }

    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        while (true) {
            update();
            panel.repaint();
        }
    }

    public void addKonto(Konto k) {
        konten.add(k);
        newBuchung.setKonten(konten.toArray(new Konto[konten.size()]));
        kontoAbschliessen.setKonten(konten.toArray(new Konto[konten.size()]));
    }
    public void addBuchung(Buchungssatz b) {
        buchungssaetze.add(b);
        b.createBuchungen();
    }
    public void setState(State state) {
        State.currentState = state;
        resetAllButtons();
        switch (state) {
            case MENU -> startMenu.changeButtonState(true);
            case N_KONTO -> newKonto.changeButtonState(true);
            case BUCHUNG -> newBuchung.changeButtonState(true);
            case K_ABSCHLIESSEN -> kontoAbschliessen.changeButtonState(true);
            case XLSX -> outToExcel.changeButtonState(true);
        }
    }
    private void resetAllButtons() {
        startMenu.changeButtonState(false);
        newKonto.changeButtonState(false);
        newBuchung.changeButtonState(false);
        kontoAbschliessen.changeButtonState(false);
        outToExcel.changeButtonState(false);
    }

    public Panel getGamePanel() {
        return panel;
    }

    public void printBuchungssaetze() {
        for(Buchungssatz buchungssatz : buchungssaetze)
            System.out.println(buchungssatz);
    }

    public void xlsx(String filename) {
        excel.setKonten(konten.toArray(new Konto[konten.size()]));
        excel.createFile(filename);
    }

    public void keyTyped(KeyEvent e) {
        switch (State.currentState){
            case MENU -> startMenu.keyTyped(e);
            case N_KONTO -> newKonto.keyTyped(e);
            case BUCHUNG -> newBuchung.keyTyped(e);
            case K_ABSCHLIESSEN -> kontoAbschliessen.keyTyped(e);
            case XLSX -> outToExcel.keyTyped(e);
        }
    }

    public void mouseClicked(MouseEvent e) {
        switch (State.currentState){
            case MENU -> startMenu.mouseClicked(e);
            case N_KONTO -> newKonto.mouseClicked(e);
            case BUCHUNG -> newBuchung.mouseClicked(e);
            case K_ABSCHLIESSEN -> kontoAbschliessen.mouseClicked(e);
            case XLSX -> outToExcel.mouseClicked(e);
        }
    }
}