package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import Game.GamePanel;

public class Entity {
    public int x, y; // Position der Entität //Valentin
    public int speed; // Bewegungsgeschwindigkeit //Valentin

    public BufferedImage gerade1, gerade2, hinten1, hinten2, rechts1, rechts2, links1, links2; // Sprites für verschiedene Richtungen //Valentin
    public String direction; // Aktuelle Bewegungsrichtung //Valentin

    public int spriteCounter = 0; // Zähler zur Animationssteuerung //Valentin
    public int spriteNum = 1; // Aktuelle Sprite-Nummer (z. B. gerade1 oder gerade2) //Valentin

    public int solidAreaDefaultX, solidAreaDefaultY; // Standardposition des Kollisionsbereichs //Valentin
    public Rectangle solidArea; // Kollisionsbereich der Entität //Valentin
    public boolean collisionOn = false; // Kollision erkannt? //Valentin

    public int maxLife; // Maximale Lebenspunkte //Valentin
    public int life; // Aktuelle Lebenspunkte //Valentin
}
