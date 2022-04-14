package sk.stuba.fei.uim.oop.gui;

import java.awt.*;
import javax.swing.*;

import sk.stuba.fei.uim.oop.reversi.GameBoard;

public class Render extends JPanel {

    private final GameBoard gameBoard;

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
