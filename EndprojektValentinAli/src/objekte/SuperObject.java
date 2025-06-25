package objekte;

import Game.GamePanel;
import Game.UtilityTool;

import java.awt.Graphics2D;
import  java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int X, Y;
    public Rectangle solidArea = new Rectangle(0,0,45,45);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {
        int col = 1;
        int row = 1;
        int x = 1;
        int y = 1;

        g2.drawImage(image, X, Y, gp.tileSize, gp.tileSize, null);

    }
}
