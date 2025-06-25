package entity;
import Game.Controlls;
import Game.GamePanel;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    public GamePanel gp; //Valentin
    public Controlls controlls; //Valentin
    public boolean hasBoost = false; //Valentin

    public Player(GamePanel gp, Controlls controlls) { //Valentin
        this.gp = gp; //Valentin
        this.controlls = controlls; //Valentin

        solidArea = new Rectangle(15, 24, 32, 35); //Valentin
        solidAreaDefaultX = solidArea.x; //Valentin
        solidAreaDefaultY = solidArea.y; //Valentin

        setDefaultValues(); //Valentin
        getPlayerImage(); //Valentin
    }

    public void setDefaultValues() { //Valentin
        x = 450; //Valentin
        y = 350; //Valentin
        speed = 10; //Valentin
        direction = "links"; //Valentin

        maxLife = 6; //Valentin
        life = maxLife; //Valentin
    }

    public void getPlayerImage() {
        try {
            gerade1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur 1.png.png"));
            gerade2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur Gerade 2.png.png"));
            hinten1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur Hinten1.png.png"));
            hinten2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur Hinter 2.png.png"));
            rechts1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur Rechts1.png.png"));
            rechts2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur Rechts2.png.png"));
            links1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur Links1.png.png"));
            links2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur Links 2.png.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() { //Valentin
        if (controlls.oben == true || controlls.unten == true || controlls.links == true || controlls.rechts == true) { //Valentin
            if (controlls.oben) { //Valentin
                direction = "hinten"; //Valentin
            } else if (controlls.unten) { //Valentin
                direction = "gerade"; //Valentin
            } else if (controlls.links) { //Valentin
                direction = "links";  //Valentin
            } else if (controlls.rechts) { //Valentin
                direction = "rechts"; //Valentin
            }

            //Check Flaeche Collision
            collisionOn = false; //Valentin
            gp.collisionChecker.checkTile(this); //Valentin

            //Check Objekt Collision
            int objIndex = gp.collisionChecker.checkObjekt(this, true); //Valentin
            pickUpObject(objIndex); //Valentin

            if (collisionOn == false) { //Valentin

                switch (direction) { //Valentin
                    case "hinten": //Valentin
                        y -= speed; //Valentin
                        break; //Valentin
                    case "gerade": //Valentin
                        y += speed; //Valentin
                        break; //Valentin
                    case "links": //Valentin
                        x -= speed; //Valentin
                        break; //Valentin
                    case "rechts": //Valentin
                        x += speed; //Valentin
                        break; //Valentin
                }
            }

            spriteCounter++; //Valentin
            if (spriteCounter > 10) { //Valentin
                if (spriteNum == 1) { //Valentin
                    spriteNum = 2; //Valentin
                } else if (spriteNum == 2) { //Valentin
                    spriteNum = 1; //Valentin
                }
                spriteCounter = 0; //Valentin
            }
        }
    }

    public void pickUpObject(int i) { //Valentin
        if (i != 999) { //Valentin
            String objektName = gp.obj[i].name; //Valentin
            switch (objektName) { //Valentin
                case "SpeedBoost": //Valentin
                    gp.playSE(1); //Valentin
                    hasBoost = true; //Valentin
                    gp.obj[i] = null; //Valentin
                    speed = speed * 2; //Valentin
                    gp.ui.showMessage("Du hast einen Speed Boost erhalten!"); //Valentin

                    // Timer starten, um Speed zurückzusetzen
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            speed = speed / 2;
                            hasBoost = false;
                            gp.ui.showMessage("Speed Boost ist abgelaufen!");
                        }
                    }, 20000);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) { //Valentin
        BufferedImage img = null; //Valentin
        switch (direction) { //Valentin
            case "gerade": //Valentin
                if (spriteNum == 1) { //Valentin
                    img = gerade1; //Valentin
                }
                if (spriteNum == 2) { //Valentin
                    img = gerade2; //Valentin
                }
                break; //Valentin
            case "hinten": //Valentin
                if (spriteNum == 1) { //Valentin
                    img = hinten1; //Valentin
                }
                if (spriteNum == 2) { //Valentin
                    img = hinten2; //Valentin
                }
                break; //Valentin
            case "links": //Valentin
                if (spriteNum == 1) { //Valentin
                    img = links1; //Valentin
                }
                if (spriteNum == 2) { //Valentin
                    img = links2; //Valentin
                }
                break; //Valentin
            case "rechts": //Valentin
                if (spriteNum == 1) { //Valentin
                    img = rechts1; //Valentin
                }
                if (spriteNum == 2) { //Valentin
                    img = rechts2; //Valentin
                }
                break; //Valentin
        }

        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null); //Valentin

    }
}

