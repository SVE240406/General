package objekte; //Valentin

import javax.imageio.ImageIO; //Valentin
import java.io.IOException; //Valentin

public class SpeedBoost extends SuperObject { //Valentin

    public SpeedBoost() { //Valentin
        name = "SpeedBoost"; //Valentin
        try{ //Valentin
            image = ImageIO.read(getClass().getResourceAsStream("/boosts/speed boost.png")); //Valentin
        }catch(IOException e){ //Valentin
            e.printStackTrace(); //Valentin
        } //Valentin
    } //Valentin
} //Valentin
