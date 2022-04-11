// package sk.stuba.fei.uim.oop.gui;

// import java.awt.*;
// import javax.swing.*;

// import sk.stuba.fei.uim.oop.player.Player;
// import sk.stuba.fei.uim.oop.reversi.GameBoard;

// public class Render extends JPanel {

//     private GameBoard gameBoard;
//     private Player[] players;

//     public Render(GameBoard gameBoard, Player[] players) {
//         // this.setLayout(new GridLayout(gameBoard.getBoardSize(),
//         // gameBoard.getBoardSize()));
//         this.gameBoard = gameBoard;
//         this.setBackground(Color.WHITE);
//         this.players = players;

//         // for (int row = 0; row < this.gameBoard.getBoardSize(); row++) {
//         // for (int col = 0; col < this.gameBoard.getBoardSize(); col++) {
//         // this.add(this.gameBoard.getNodeAtPos(row, col));
//         // }
//         // }
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         this.gameBoard.draw(g);
//         for (Player player : players) {
//             player.draw(g);
//         }
//     }

//     // @Override
//     // public Component getComponentAt(Point p) {
//     // // System.out.println(p.getX() + " " + p.getY());
//     // // return null;
//     // return this.gameBoard.getNodeAtPoint(p);

//     // }

// }

package sk.stuba.fei.uim.oop.gui;

import java.awt.*;
import javax.swing.*;

import sk.stuba.fei.uim.oop.reversi.GameBoard;

public class Render extends JPanel {

    private GameBoard gameBoard;

    public Render(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.setBackground(Color.WHITE);
        resize();
    }

    public void resize() {
        this.removeAll();
        this.revalidate();
        this.setLayout(new GridLayout(gameBoard.getBoardSize(), gameBoard.getBoardSize(), 4, 4));
        for (int row = 0; row < this.gameBoard.getBoardSize(); row++) {
            for (int col = 0; col < this.gameBoard.getBoardSize(); col++) {
                this.add(this.gameBoard.getNodeAtPos(row, col));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
