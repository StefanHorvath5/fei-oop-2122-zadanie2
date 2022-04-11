package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("Reversi!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1020, 800);
        frame.setResizable(false);

        frame.setLayout(new BorderLayout());
        GameLogic logic = new GameLogic();
        frame.addKeyListener(logic);
        frame.add(logic.getRender());

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(new Color(220, 220, 220));
        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);

        sideMenu.setLayout(new GridLayout(2, 3));
        sideMenu.add(logic.getLabel());
        // sideMenu.add(new BoardSizeSlider(logic));
        sideMenu.add(buttonRestart);
        frame.add(sideMenu, BorderLayout.LINE_END);

        frame.setVisible(true);
    }
}
