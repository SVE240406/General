package Tile;

import Game.GamePanel;
import Game.UtilityTool;

import java.awt.*;
import java.io.*;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class TileManager {
    GamePanel gp; //Valentin
    public Tile[] tile; //Valentin
    public int mapTileNum[][]; //Valentin

    public TileManager(GamePanel gp) {

        this.gp = gp; //Valentin

        tile = new Tile[10]; //Valentin

        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; //Valentin

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

            setup(0, "Gras", false);

            setup(1, "Stein", true);

            setup(2, "Kanone hoch", true);

            setup(3, "Kanone links", true);

            setup(4, "Kanone rechts", true);

            setup(5, "Kanone runter", true);

    }

    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = read(getClass().getResourceAsStream("/tile/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/Maps/Map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers = line.split(" ")[col];
                    int num = Integer.parseInt(numbers);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e){

        }
    }

    public void draw(Graphics2D g2) {
        int col = 0; //Valentin
        int row = 0; //Valentin
        int x = 0; //Valentin
        int y = 0; //Valentin

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) { //Valentin
            int tileNum = mapTileNum[col][row]; //Valentin
                g2.drawImage(tile[tileNum].image, x, y, null); //Valentin
                col++; //Valentin
                x += gp.tileSize; //Valentin

                if (col == gp.maxScreenCol) { //Valentin
                    col = 0; //Valentin
                    x = 0; //Valentin
                    row++; //Valentin
                    y += gp.tileSize; //Valentin
                }
        }
    }
}
