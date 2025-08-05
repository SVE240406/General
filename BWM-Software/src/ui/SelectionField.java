package ui;

import main.Main;
import scenes.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static main.Main.SCALE;

public class SelectionField extends TypeField implements ButtonUsage {
    private final String[] choices;
    private int x, y, w, h;
    private BasicButton[] usedButtons;
    private ArrayList<BasicButton> buttons;

    public SelectionField(String text, String[] choices, Rectangle bounds, Main main, Scene source) {
        this.choices = choices;
        super(text, bounds, main, source);
        initButtons();
    }

    private void initButtons() {
        x = bounds.x;
        y = bounds.y;
        w = bounds.width;
        h = bounds.height;
        buttons = new ArrayList<>();
        for(int i = 0; i < choices.length; i++) {
            buttons.add(new BasicButton(choices[i], new Rectangle(x, (int) (y + 50 * SCALE * (i + 1)), w, h), super.main));
            buttons.getLast().setSource(this);
        }
        usedButtons = buttons.toArray(new BasicButton[buttons.size()]);
    }

    @Override
    public void update(){
        super.update();
        if(buttons != null)
            for(int i = 0; i < usedButtons.length; i++)
                usedButtons[i].setBounds(new Rectangle(x, (int) (y+50*(i+1)*SCALE), w, h));
    }

    @Override
    public void buttonPressed(ActionEvent e) {
        typed = e.getActionCommand();
        resetFocus();
    }

    private void updateChoices(){
        ArrayList<BasicButton> newButtons = new ArrayList<>();
        for(int i = 0; i < choices.length; i++)
            if(choices[i].toLowerCase().startsWith(typed.toLowerCase()))
                newButtons.add(buttons.get(i));
        usedButtons = newButtons.toArray(new BasicButton[newButtons.size()]);
    }

    @Override
    public void repaint(){
        super.repaint();
        if(buttons != null)
            for (BasicButton b : buttons)
                b.setVisible(false);
        if(usedButtons != null)
            for (int i = 0; i < usedButtons.length; i++) {
                usedButtons[i].setVisible(isFocused());
                usedButtons[i].repaint();
            }
    }
    @Override
    public void keyTyped(KeyEvent e){
        super.keyTyped(e);
        updateChoices();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    public int getValue(){
        int index = 0;
        for(int i = 0; i < choices.length; i++)
            if(choices[i].equals(typed))
                index = i;
        get();
        return index;
    }
}
