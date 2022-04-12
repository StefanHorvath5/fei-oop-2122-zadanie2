package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("Reversi!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620, 700);
        frame.setResizable(false);

        frame.setLayout(new BorderLayout(0, 5));
        GameLogic gameLogic = new GameLogic();
        frame.addKeyListener(gameLogic);
        frame.add(gameLogic.getRender());

        JPanel bottomMenu = new JPanel();
        bottomMenu.setBackground(new Color(220, 220, 220));
        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(gameLogic);
        buttonRestart.setFocusable(false);
        bottomMenu.setLayout(new GridLayout(1, 4));
        bottomMenu.add(gameLogic.getLabel());
        bottomMenu.add(gameLogic.getBoardSizeLabel());
        bottomMenu.add(buttonRestart);
        BoardSizeSlider s = new BoardSizeSlider(JSlider.HORIZONTAL, 6, 12, 6, gameLogic);
        s.setMajorTickSpacing(2);
        s.setPaintLabels(true);
        s.setSnapToTicks(true);
        bottomMenu.add(s);

        bottomMenu.setPreferredSize(new Dimension(40, 80));
        frame.add(bottomMenu, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
