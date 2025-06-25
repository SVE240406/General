package kanonen;

import Game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Kanonenkugel { //Valentin
    public int x, y, speed = 5; //Valentin
    public String direction; //Valentin
    public boolean active = true; //Valentin
    public BufferedImage image; //Valentin
    public GamePanel gp; // Nur deklarieren, nicht neu anlegen
    public Rectangle hitbox;

    public Kanonenkugel(GamePanel gp, int startX, int startY, String direction) {
        this.gp = gp;
        this.x = startX;
        this.y = startY;
        this.direction = direction;
        int size = gp.tileSize / 4; // z.B. die Größe der Kugel-Hitbox
        hitbox = new Rectangle(x, y, size, size);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/kanonen/kanone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {
        switch (direction) {
            case "hoch": y -= speed; break;
            case "runter": y += speed; break;
            case "links": x -= speed; break;
            case "rechts": x += speed; break;
        }
        hitbox.setLocation(x, y); // Hitbox an neue Position anpassen
    } //Valentin

    public void draw(Graphics2D g2) { //Valentin
        if (active && image != null) { //Valentin
                g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); //Valentin
            } //Valentin
        } //Valentin
} //Valentin
