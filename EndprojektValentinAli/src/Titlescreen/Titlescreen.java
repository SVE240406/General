package Titlescreen;

import Game.Controlls;
import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Titlescreen {
    public GamePanel gp;
    public Titlescreen(GamePanel gp) {
        this.gp = gp;
        getPlayKnoepfe();
    }
    public BufferedImage playKnopf1, playKnopf2;
    public void getPlayKnoepfe(){
        try{
            playKnopf1 = ImageIO.read(getClass().getResourceAsStream("/Titlescreen/play.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
