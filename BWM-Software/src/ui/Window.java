package ui;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Window {
    private JFrame jframe;

    public Window(Panel panel) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(panel);

        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);

        jframe.setVisible(true);
    }
}
