package kanonen;

import Game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class KanonenManager { //Valentin
    public GamePanel gp; //Valentin
    public ArrayList<Kanonenkugel> kanonenkugeln = new ArrayList<>(); //Valentin
    private ArrayList<Kanone> alleKanonen = new ArrayList<>(); //Valentin
    private int timer = 0; //Valentin
    private Set<Integer> aktiveKanonen = new HashSet<>(); //Valentin
    private Random random = new Random(); //Valentin


    public KanonenManager(GamePanel gp) { //Valentin
        this.gp = gp; //Valentin
        initKanonen(); //Valentin
    } //Valentin

    private void initKanonen() {

        // Oben nach unten (6 Stück)
        for (int i = 0; i < 6; i++) {
            int x = 70 + i * 125;
            alleKanonen.add(new Kanone(x, 0, "runter"));
        }

        // Unten nach oben (6 Stück)
        for (int i = 0; i < 6; i++) {
            int x = 70 + i * 125;
            alleKanonen.add(new Kanone(x, gp.screenHeight, "hoch"));
        }

        // Links nach rechts (6 Stück)
        for (int i = 0; i < 6; i++) {
            int y = 70 + i * 105;
            alleKanonen.add(new Kanone(0, y, "rechts"));
        }

        // Rechts nach links (6 Stück)
        for (int i = 0; i < 6; i++) {
            int y = 70 + i * 105;
            alleKanonen.add(new Kanone(gp.screenWidth, y, "links"));
        }
    }

    public void update() {
        timer++;

        if (timer % 180 == 0 && aktiveKanonen.size() < alleKanonen.size()) {
            // Zufällige nicht aktivierte Kanone auswählen
            ArrayList<Integer> nochNichtAktiv = new ArrayList<>();
            for (int i = 0; i < alleKanonen.size(); i++) {
                if (!aktiveKanonen.contains(i)) {
                    nochNichtAktiv.add(i);
                }
            }
            if (!nochNichtAktiv.isEmpty()) {
                int zufallIndex = nochNichtAktiv.get(random.nextInt(nochNichtAktiv.size()));
                aktiveKanonen.add(zufallIndex);
            }
        }

        if (timer % 180 == 0) {
            spawnCannonballs();
        }

        for (int i = 0; i < kanonenkugeln.size(); i++) {
            Kanonenkugel c = kanonenkugeln.get(i);
            c.update();

            int kugelSize = gp.tileSize / 4;
            int offset = -4;

            Rectangle kugelRect = new Rectangle(c.x + offset, c.y + offset, kugelSize - 2 * offset, kugelSize - 2 * offset);
            Rectangle spielerRect = new Rectangle(gp.player.x, gp.player.y, gp.tileSize, gp.tileSize);

            if (kugelRect.intersects(spielerRect)) {
                kanonenkugeln.remove(c);
                gp.player.life--;

                if (gp.player.life <= 0) {
                    gp.ui.gameOver = true;
                    gp.stopMusic();
                    gp.playSE(3);
                } else {
                    gp.ui.showMessage("Du hast ein Leben verloren!");
                    gp.playSE(2);
                }

                break;
            }
        }

        kanonenkugeln.removeIf(c -> c.x < 0 || c.y < 0 || c.x > gp.screenWidth || c.y > gp.screenHeight); //Valentin
    }

    public void draw(Graphics2D g2) {
        for (Kanonenkugel c : kanonenkugeln) { //Valentin
            c.draw(g2); //Valentin
        }
    }

    private void spawnCannonballs() {
        for (int index : aktiveKanonen) {
            Kanone k = alleKanonen.get(index);
            kanonenkugeln.add(new Kanonenkugel(gp, k.x, k.y, k.richtung));
        }
    }


    private static class Kanone {
        int x, y;
        String richtung;

        public Kanone(int x, int y, String richtung) {
            this.x = x;
            this.y = y;
            this.richtung = richtung;
        }
    }
}
