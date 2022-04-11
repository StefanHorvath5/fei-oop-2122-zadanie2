// package sk.stuba.fei.uim.oop.gui;

// import sk.stuba.fei.uim.oop.controls.GameLogic;

// import javax.swing.*;
// import java.awt.*;

// public class Game {
//     public Game() {
//         JFrame frame = new JFrame("Reversi!");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(1200, 800);
//         frame.setResizable(false);

//         frame.setLayout(new BorderLayout());
//         GameLogic logic = new GameLogic();
//         frame.addKeyListener(logic);
//         frame.add(logic.getRender());

//         JPanel sideMenu = new JPanel();
//         sideMenu.setBackground(new Color(220, 220, 220));
//         JButton buttonRestart = new JButton("RESTART");
//         buttonRestart.addActionListener(logic);
//         buttonRestart.setFocusable(false);
//         sideMenu.setLayout(new GridLayout(2, 2));
//         sideMenu.add(logic.getLabel());
//         sideMenu.add(logic.getBoardSizeLabel());
//         sideMenu.add(buttonRestart);
//         BoardSizeSlider s = new BoardSizeSlider(JSlider.HORIZONTAL, 6, 12, 6, logic);
//         s.setMajorTickSpacing(2);
//         s.setPaintLabels(true);
//         s.setSnapToTicks(true);
//         sideMenu.add(s);
//         frame.add(sideMenu, BorderLayout.LINE_END);

//         frame.setVisible(true);
//     }
// }

package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("Reversi!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setResizable(false);

        frame.setLayout(new BorderLayout(0, 5));
        GameLogic logic = new GameLogic();
        frame.addKeyListener(logic);
        frame.add(logic.getRender());

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(new Color(220, 220, 220));
        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);
        sideMenu.setLayout(new GridLayout(1, 4));
        sideMenu.add(logic.getLabel());
        sideMenu.add(logic.getBoardSizeLabel());
        sideMenu.add(buttonRestart);
        BoardSizeSlider s = new BoardSizeSlider(JSlider.HORIZONTAL, 6, 12, 6, logic);
        s.setMajorTickSpacing(2);
        s.setPaintLabels(true);
        s.setSnapToTicks(true);
        sideMenu.add(s);
        frame.add(sideMenu, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
