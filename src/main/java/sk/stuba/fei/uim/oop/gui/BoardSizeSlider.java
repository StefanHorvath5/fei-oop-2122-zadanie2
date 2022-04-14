package sk.stuba.fei.uim.oop.gui;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sk.stuba.fei.uim.oop.controls.GameLogic;

public class BoardSizeSlider extends JSlider implements ChangeListener {
    private final GameLogic logic;

    public BoardSizeSlider(int orientation, int min, int max, int value, GameLogic logic) {
        super(orientation, min, max, value);
        this.logic = logic;
        this.setFocusable(false);
        this.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
            this.logic.changeBoardSize(((JSlider) e.getSource()).getValue());
        }
    }
}
