package sk.stuba.fei.uim.oop.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.reversi.GameBoard;

public class Render extends JPanel {

    private GameBoard gameBoard;
    private Player[] players;

    public Render(GameBoard gameBoard, Player[] players) {
        this.gameBoard = gameBoard;
        this.setBackground(Color.WHITE);
        this.players = players;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.gameBoard.draw(g);
        for (Player player : players) {
            player.draw(g);
        }

    }
}
