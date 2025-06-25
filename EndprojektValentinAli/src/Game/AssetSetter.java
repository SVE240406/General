package Game;
import java.util.Random;
import objekte.SpeedBoost;

public class AssetSetter {
    GamePanel gp;
    Random rand = new Random();

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObjekt(){
        gp.obj[0] = new SpeedBoost();
        gp.obj[0].X = rand.nextInt(1,15) * gp.tileSize;
        gp.obj[0].Y = rand.nextInt(1,14) * gp.tileSize;
    }
}
