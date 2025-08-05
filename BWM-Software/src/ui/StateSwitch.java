package ui;

import main.Main;
import main.State;

import java.awt.*;
import java.awt.event.ActionEvent;

public class StateSwitch extends BasicButton {
    private State state;

    public StateSwitch(String text, Rectangle bounds, State state, Main main) {
        super(text, bounds, main);
        super.setSource(this::actionPerformed);
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.setState(state);
    }
}
