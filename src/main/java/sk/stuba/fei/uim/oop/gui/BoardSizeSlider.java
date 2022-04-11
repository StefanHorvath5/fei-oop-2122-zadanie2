package sk.stuba.fei.uim.oop.gui;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sk.stuba.fei.uim.oop.controls.GameLogic;

public class BoardSizeSlider extends JSlider implements ChangeListener {
    private GameLogic logic;

    public BoardSizeSlider(int orientation, int min, int max, int value, GameLogic logic) {
        super(orientation, min, max, value);
        this.logic = logic;
        this.addChangeListener(this);
        this.setFocusable(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
            this.logic.changeBoardSize(((JSlider) e.getSource()).getValue());
        }
    }
}
