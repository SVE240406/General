package Game;

import Tile.TileManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controlls implements KeyListener {

    GamePanel gp;
    TileManager tm;
    public boolean oben, unten, links, rechts;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public Controlls(GamePanel gp){
        this.gp = gp;
        this.tm = new TileManager(gp);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.titleScreen){
           if(code == KeyEvent.VK_ENTER){
               tm.getTileImage();
               tm.loadMap();
               gp.gameState = gp.playState;
           }
        }

        if (code == KeyEvent.VK_W) {
            oben = true;
        }
        if (code == KeyEvent.VK_S) {
            unten = true;
        }
        if (code == KeyEvent.VK_A) {
            links = true;
        }
        if (code == KeyEvent.VK_D) {
            rechts = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            oben = false;
        }
        if (code == KeyEvent.VK_S) {
            unten = false;
        }
        if (code == KeyEvent.VK_A) {
            links = false;
        }
        if (code == KeyEvent.VK_D) {
            rechts = false;
        }
    }
}
