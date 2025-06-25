package Game;

import Titlescreen.Titlescreen;
import objekte.Herzen;
import objekte.SpeedBoost;
import objekte.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI { //Valentin

    Titlescreen ts; //Valentin
    GamePanel gp; //Valentin
    Graphics2D g2; //Valentin
    Font arial_40, arial_80B; //Valentin
    BufferedImage sB; //Valentin
    public boolean messageOn = false; //Valentin
    public String message = ""; //Valentin
    int messageCounter = 0; //Valentin
    public boolean gameOver = false; //Valentin
    BufferedImage vollesHerz, halbesHerz, leeresHerz; //Valentin

    double playTime; //Valentin
    DecimalFormat dFormat = new DecimalFormat("#0.00"); //Valentin

    public UI(GamePanel gp) { //Valentin
        this.gp = gp; //Valentin
        this.ts = new Titlescreen(gp); //Valentin

        arial_40 = new Font("Arial", Font.PLAIN, 40); //Valentin
        arial_80B = new Font("Arial", Font.BOLD, 80); //Valentin
        SpeedBoost sb = new SpeedBoost(); //Valentin
        sB = sb.image; //Valentin

        SuperObject herz = new Herzen(gp); //Valentin
        vollesHerz = herz.image; //Valentin
        halbesHerz = herz.image2; //Valentin
        leeresHerz = herz.image3; //Valentin
    } //Valentin

    public void showMessage(String text) { //Valentin
        message = text; //Valentin
        messageOn = true; //Valentin
    } //Valentin

    public void draw(Graphics2D g2) { //Valentin
        if (gp.gameState == gp.playState) { //Valentin
            if (gameOver == true) { //Valentin
                g2.setFont(arial_40); //Valentin
                g2.setColor(Color.white); //Valentin

                String text; //Valentin
                int textLenght; //Valentin
                int x; //Valentin
                int y; //Valentin

                text = "Du hast verloren!"; //Valentin
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Valentin
                x = gp.screenWidth / 2 - textLenght / 2; //Valentin
                y = gp.screenHeight / 2 - (gp.tileSize * 3); //Valentin
                g2.drawString(text, x, y); //Valentin

                text = "Deine Zeit ist: " + dFormat.format(playTime) + "!"; //Valentin
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Valentin
                x = gp.screenWidth / 2 - textLenght / 2; //Valentin
                y = gp.screenHeight / 2 + (gp.tileSize * 4); //Valentin
                g2.drawString(text, x, y); //Valentin

                g2.setFont(arial_80B); //Valentin
                g2.setColor(Color.yellow); //Valentin

                text = "Game Over!"; //Valentin
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Valentin
                x = gp.screenWidth / 2 - textLenght / 2; //Valentin
                y = gp.screenHeight / 2 + (gp.tileSize * 2); //Valentin
                g2.drawString(text, x, y); //Valentin

                gp.gameThread = null; //Valentin
            } else if (playTime >= 60) { //Valentin
                g2.setFont(arial_40); //Valentin
                g2.setColor(Color.white); //Valentin

                String text; //Valentin
                int textLenght; //Valentin
                int x; //Valentin
                int y; //Valentin

                text = "Du hast gewonnen!"; //Valentin
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Valentin
                x = gp.screenWidth / 2 - textLenght / 2; //Valentin
                y = gp.screenHeight / 2 - (gp.tileSize * 3); //Valentin
                g2.drawString(text, x, y); //Valentin

                text = "Du hast " + dFormat.format(playTime) + " Sekunden überlebt!"; //Valentin
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Valentin
                x = gp.screenWidth / 2 - textLenght / 2; //Valentin
                y = gp.screenHeight / 2 + (gp.tileSize * 4); //Valentin
                g2.drawString(text, x, y); //Valentin

                g2.setFont(arial_80B); //Valentin
                g2.setColor(Color.yellow); //Valentin

                text = "Game won!"; //Valentin
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Valentin
                x = gp.screenWidth / 2 - textLenght / 2; //Valentin
                y = gp.screenHeight / 2 + (gp.tileSize * 2); //Valentin
                g2.drawString(text, x, y); //Valentin

                gp.gameThread = null; //Valentin
            } else { //Valentin
                g2.setFont(arial_40); //Valentin
                g2.setColor(Color.white); //Valentin

                if (gp.gameState == gp.playState) { //Valentin
                    playTime += (double) 1 / 60; //Valentin
                } //Valentin
                g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 85); //Valentin

                if (messageOn == true) { //Valentin
                    g2.setFont(g2.getFont().deriveFont(20f)); //Valentin
                    g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5); //Valentin

                    messageCounter++; //Valentin
                    if (messageCounter > 120) { //Valentin
                        messageCounter = 0; //Valentin
                        messageOn = false; //Valentin
                    } //Valentin
                } //Valentin
            } //Valentin
        } //Valentin

        this.g2 = g2; //Valentin

        g2.setFont(arial_40); //Valentin
        g2.setColor(Color.white); //Valentin

        if(gp.gameState == gp.titleScreen){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
    }

    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(leeresHerz, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while(i < gp.player.life){
            g2.drawImage(halbesHerz, x, y,null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(vollesHerz, x, y,null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        // Farbverlauf als Hintergrund
        GradientPaint gpGradient = new GradientPaint(0, 0, Color.darkGray, 0, gp.screenHeight, Color.black);
        g2.setPaint(gpGradient);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Titel mit Schatten
        String title = "DODGE GAME";
        g2.setFont(new Font("Impact", Font.BOLD, 96));
        int x = getXforCenterText(title);
        int y = gp.tileSize * 3;

        // Schatten zeichnen (leicht versetzt)
        g2.setColor(Color.black);
        g2.drawString(title, x + 4, y + 4);

        // Haupttitel
        g2.setColor(Color.yellow);
        g2.drawString(title, x, y);

        // Halbtransparenter Kasten für Steuerhinweise
        int boxWidth = gp.screenWidth / 3;
        int boxHeight = gp.tileSize * 5;
        int boxX = gp.screenWidth / 2 - boxWidth / 2;
        int boxY = y + gp.tileSize * 2;

        g2.setColor(new Color(0, 0, 0, 150)); // schwarz mit Alpha 150 (halbtransparent)
        g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 15, 15);

        // Steuerhinweise
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.PLAIN, 28));
        g2.drawString("Mit W, A, S, D steuern", boxX + 20, boxY + 40);
        g2.drawString("ESC für Pause", boxX + 20, boxY + 80);
        g2.drawString("Überlebe für 1 Minute", boxX + 20, boxY + 120);

        // Play-Button (zentriert unter den Texten)
        int btnWidth = gp.tileSize * 2;
        int btnHeight = gp.tileSize * 2;
        int btnX = gp.screenWidth / 2 - btnWidth / 2;
        int btnY = boxY+150;

        // Einfacher Hover-Effekt (z.B. größer oder farbiger wenn Maus drüber)
        // Hier als Beispiel: normal anzeigen, du kannst Mausposition abfragen, wenn du hast
        g2.drawImage(ts.playKnopf1, btnX, btnY, btnWidth, btnHeight, null);

        // Schrift für andere UI-Elemente zurücksetzen
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
    }


    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x,y);
    }

    public int getXforCenterText(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
