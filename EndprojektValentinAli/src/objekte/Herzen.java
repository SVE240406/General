package objekte;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Herzen extends SuperObject{
    GamePanel gp;

    public Herzen(GamePanel gp) {
        this.gp = gp;

        name = "Herzen";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objekte/vollesHerz.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objekte/halbesHerz.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objekte/leeresHerz.png"));
            image = uTool .scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool .scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool .scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
