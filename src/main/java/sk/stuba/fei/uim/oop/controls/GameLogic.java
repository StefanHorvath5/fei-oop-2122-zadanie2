package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.reversi.GameBoard;
import sk.stuba.fei.uim.oop.reversi.Node;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameLogic extends UniversalAdapter {

    @Getter
    private Render render;
    @Getter
    private GameBoard gameBoard;
    @Getter
    private Player[] players;
    private Player currentPlayer;

    public GameLogic() {
        this.players = new Player[2];
        this.players[0] = new Player(this, false); // user
        this.players[1] = new Player(this, true); // ai
        this.currentPlayer = this.players[0];

        this.gameBoard = new GameBoard(players);
        this.render = new Render(this.gameBoard, players);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        this.startGame();
    }

    // private void startGame() {
    // this.nextTurn();
    // }

    // private void nextTurn() {
    // for (int row = 0; row < this.gameBoard.getBoardSize(); row++) {
    // for (int col = 0; col < this.gameBoard.getBoardSize(); col++) {
    // if (this.currentPlayer.canPlayNode(this.gameBoard.getNodeAtPos(row, col))) {
    // this.gameBoard.getNodeAtPos(row, col).setPlayable(true);
    // }
    // }
    // }
    // this.render.repaint();
    // // this.currentPlayer = this.currentPlayer.isAI() ? this.players[0] :
    // // this.players[1];
    // }

    // @Override
    // public void mouseClicked(MouseEvent e) {
    // Node clickedNode = this.gameBoard.getNodeAtPoint(e.getPoint());
    // if (this.currentPlayer.canPlayNode(clickedNode)) {
    // this.currentPlayer.playNode(clickedNode);
    // this.render.repaint();
    // this.nextTurn();

    // }

    // }
    private void startGame() {
        turn();
        this.render.repaint();
    }

    private void turn() {
        for (int row = 0; row < this.gameBoard.getBoardSize(); row++) {
            for (int col = 0; col < this.gameBoard.getBoardSize(); col++) {
                if (this.currentPlayer.canPlayNode(this.gameBoard.getNodeAtPos(row, col))) {
                    this.gameBoard.getNodeAtPos(row, col).setPlayable(true);
                    // System.out.println("playable");
                } else {
                    this.gameBoard.getNodeAtPos(row, col).setPlayable(false);
                }
            }
        }
        // System.out.println("repaint");
    }

    private void nextTurn() {
        // System.out.println("jak?");
        this.currentPlayer = this.currentPlayer.isAI() ? this.players[0] : this.players[1];
        this.turn();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Node clickedNode = this.gameBoard.getNodeAtPoint(e.getPoint());
        if (this.currentPlayer.canPlayNode(clickedNode)) {
            // System.out.println("hei");
            this.currentPlayer.playNode(clickedNode);
            this.render.repaint();
            this.nextTurn();

        }

        // for (int row = 0; row < this.gameBoard.getBoardSize(); row++) {
        // for (int col = 0; col < this.gameBoard.getBoardSize(); col++) {
        // if (this.gameBoard.getNodeAtPos(row, col) ==
        // this.render.getComponentAt(e.getPoint())) {
        // System.out.println("tu: " + row + " " + col);
        // } else {
        // System.out.println(
        // "ne: " + this.gameBoard.getNodeAtPos(row, col) +
        // this.render.getComponentAt(e.getPoint()));
        // }
        // }
        // }
        // Node current = this.maze.getNode(convertPosition(e.getX()),
        // convertPosition(e.getY()));
        // if (current == null) {
        // return;
        // }
        // if (this.playerClicked) {
        // if (this.player.canReachNode(current)) {
        // this.player.setCurrentNode(current);
        // this.repaint();
        // this.playerClicked = false;
        // }
        // } else {
        // if (this.player.getCurrentNode() == current) {
        // this.playerClicked = true;
        // }
        // }
    }

}
