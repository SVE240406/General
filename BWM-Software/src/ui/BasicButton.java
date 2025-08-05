package ui;

import main.Main;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.runtime.ObjectMethods;

public class BasicButton extends JButton implements ActionListener {
    protected Main main;
    protected Rectangle bounds;
    protected ButtonUsage source;

    public BasicButton(String text, Rectangle bounds, Main main) {
        super(text);
        this.bounds = bounds;
        super.setBounds(this.bounds);
        this.main = main;
        setFont(new Font("Arial", Font.PLAIN, (int) (Main.SCALE * 16)));
        setBackground(Color.LIGHT_GRAY);
        addActionListener(this);
        setFocusable(false);
        main.getGamePanel().add(this);
    }
    public void setSource(ButtonUsage source) {
        this.source = source;
    }

    @Override
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
        update();
    }

    public void update() {
        super.setBounds(bounds);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        source.buttonPressed(e);
    }
}
