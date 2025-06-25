package Game;

import Tile.TileManager;
import entity.Player;
import kanonen.KanonenManager;
import objekte.SuperObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Perplexity //Valentin
    final int originalTileSize = 16; //Valentin
    final int scale = 4; //Valentin

    public final int tileSize = originalTileSize * scale; //Valentin
    public final int maxScreenCol = 16; //Valentin
    public final int maxScreenRow = 12; //Valentin
    public final int screenWidth = tileSize * maxScreenCol; //Valentin
    public final int screenHeight = tileSize * maxScreenRow; //Valentin

    //FPS //Valentin
    int FPS = 30; //Valentin

    //System //Valentin
    TileManager tileM = new TileManager(this); //Valentin
    Controlls controlls = new Controlls(this); //Valentin
    Sound music = new Sound(); //Valentin
    Sound se = new Sound(); //Valentin
    public CollisionChecker collisionChecker = new CollisionChecker(this); //Valentin
    public AssetSetter aSetter = new AssetSetter(this); //Valentin
    public UI ui = new UI(this); //Valentin
    public Thread gameThread; //Valentin
    KanonenManager kanonenManager = new KanonenManager(this); //Valentin

    //Entity und Player //Valentin
    public Player player = new Player(this, controlls); //Valentin
    public SuperObject obj[] = new SuperObject[2]; //Valentin

    //GAMESTATE //Valentin
    public int gameState;
    public final int titleScreen = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Valentin
        this.setBackground(Color.BLACK); //Valentin
        this.setDoubleBuffered(true); //Valentin
        this.addKeyListener(controlls); //Valentin
        this.setFocusable(true); //Valentin
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameState == titleScreen) {  // nur auf Titelbildschirm reagieren
                    int mouseX = e.getX();
                    int mouseY = e.getY();

                    // Playbutton Position und Größe wie im UI
                    int btnWidth = tileSize * 2;
                    int btnHeight = tileSize * 2;
                    int boxY = tileSize * 3 + tileSize * 2;  // wie in UI.drawTitleScreen()
                    int btnX = screenWidth / 2 - btnWidth / 2;
                    int btnY = boxY + 150;

                    // Prüfen, ob Klick im Bereich des Playbuttons
                    if (mouseX >= btnX && mouseX <= btnX + btnWidth &&
                            mouseY >= btnY && mouseY <= btnY + btnHeight) {
                        // Spiel starten
                        gameState = playState;
                        ui.playTime = 0;
                        ui.gameOver = false;
                    }
                }
            }
        });
    }

    public void setupGame() {
        aSetter.setObjekt(); //Valentin
        playMusic(0); //Valentin
        gameState = titleScreen; //Valentin
    }

    public void startGameThread() {
        gameThread = new Thread(this); //Valentin
        gameThread.start(); //Valentin
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //Valentin
        double delta = 0; //Valentin
        long lastTime = System.nanoTime(); //Valentin
        long timer = 0; //Valentin
        int drawCount = 0; //Valentin

        while (gameThread != null) {
            long currentTime = System.nanoTime(); //Valentin

            delta += (currentTime - lastTime) / drawInterval; //Valentin
            timer += (currentTime - lastTime); //Valentin
            lastTime = currentTime; //Valentin

            if (delta >= 1) {
                update(); //Valentin
                repaint(); //Valentin
                delta--; //Valentin
                drawCount++; //Valentin
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount); //Valentin
                drawCount = 0; //Valentin
                timer = 0; //Valentin
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            kanonenManager.update();
            tileM.getTileImage();
        }
        if (gameState == pauseState) {
            // Pause-Logik hier
        }
        if (gameState == titleScreen) {
            // Titelbildschirm-Logik hier
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Valentin

        Graphics2D g2 = (Graphics2D) g; //Valentin

        //TITLESCREEN
        if (gameState == titleScreen) {
            ui.draw(g2);
        } else {
            //TILE
            tileM.draw(g2);

            //UI
            ui.draw(g2);

            //OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            //PLAYER
            player.draw(g2);

            kanonenManager.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i); //Valentin
        music.play(); //Valentin
        music.loop(); //Valentin
    }

    public void stopMusic() {
        music.stop(); //Valentin
    }

    public void playSE(int i) {
        se.setFile(i); //Valentin
        se.play(); //Valentin
    }
}
