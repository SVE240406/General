package Game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame(); //Valentin
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Valentin
        window.setResizable(false); //Valentin
        window.setTitle("DodgeGame"); //Valentin


        GamePanel gamePanel = new GamePanel(); //Valentin
        window.add(gamePanel); //Valentin

        window.pack(); //Valentin

        window.setLocationRelativeTo(null); //Valentin
        window.setVisible(true); //Valentin

        gamePanel.setupGame(); //Valentin
        gamePanel.startGameThread(); //Valentin
    }
}