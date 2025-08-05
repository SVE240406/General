package ui;

import main.Main;
import scenes.Scene;

import java.awt.*;
import java.awt.event.*;

public class TypeField extends BasicButton {
    private final String text;
    protected String typed = "";
    private final Scene source;
    private final Color normalColor = Color.GRAY, textColor = Color.BLACK;
    private boolean focused;

    public TypeField(String text, Rectangle bounds, Main main, Scene source) {
        super(text, bounds, main);
        super.setSource(this::actionPerformed);
        this.text = text;
        this.source = source;
        updateText();
    }

    private void updateText(){
        if(typed == null || typed.isEmpty()){
            setText(text);
            setForeground(normalColor);
        }
        else{
            setText(typed);
            setForeground(textColor);
        }
}

    public void update(){
        super.update();
        updateText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        source.resetFocus();
        focused = true;
    }

    public void keyTyped(KeyEvent e) {
        if(!focused)
            return;
        if(e.getKeyChar() == '\b'){
            if(!typed.isEmpty())
                typed = typed.substring(0, typed.length() - 1);
            return;
        }
        typed += e.getKeyChar();
    }

    public void mouseClicked(MouseEvent e) {
        resetFocus();
    }

    public void resetFocus() {
        focused = false;
    }

    protected boolean isFocused() {
        return focused;
    }

    public String get(){
        String temp = typed;
        typed = "";
        if(temp == null || temp.isEmpty())
            return null;
        return temp;
    }
}
