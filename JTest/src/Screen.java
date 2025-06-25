import javax.swing.*;
import java.awt.*;

public class Screen {
    public JFrame frame = new JFrame();
    public JLabel label = new JLabel();
    private int width = 800, height = 600;


    public Screen() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Screen");
        frame.getContentPane().setBackground(new Color(0,150,255));
        frame.setVisible(true);


    }
}
