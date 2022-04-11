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
    private boolean gameStatus;

    public GameLogic() {
        this.gameStatus = false;
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
        this.gameStatus = true;
        turn(false);
        this.render.repaint();
    }

    private void turn(boolean notPlayable) {
        boolean playable = false;
        if (this.currentPlayer.isAI()) {
            playable = this.currentPlayer.makeBestMove(this.gameBoard);
            if (playable) {
                nextTurn(false);
            }
        } else {
            playable = this.currentPlayer.updateAndGetPlayableNodesCount(this.gameBoard) != 0;
            this.render.repaint();

        }
        if (!playable) {
            if (notPlayable) {
                this.gameStatus = false;
                this.printWinner();
            } else {
                nextTurn(true);
            }
        }
    }

    private void nextTurn(boolean notPlayable) {
        this.currentPlayer.clearPlayableNodes();
        this.currentPlayer = this.currentPlayer.isAI() ? this.players[0] : this.players[1];
        this.render.repaint();
        this.turn(notPlayable);
    }

    private void printWinner() {
        if (players[0].getNodesCount() > players[1].getNodesCount()) {
            System.out.println("Winner: white(you)");
        } else {
            System.out.println("Winner: black(computer)");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.gameStatus) {
            Node clickedNode = this.gameBoard.getNodeAtPoint(e.getPoint());
            if (!this.currentPlayer.isAI() && clickedNode != null
                    && this.currentPlayer.canPlayNode(clickedNode)) {
                this.currentPlayer.playNode(clickedNode);
                this.nextTurn(false);

            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.gameStatus) {
            Node hoveredNode = this.gameBoard.getNodeAtPoint(e.getPoint());
            if (!this.currentPlayer.isAI() && hoveredNode != null
                    && this.currentPlayer.canPlayNode(hoveredNode)) {
                hoveredNode.setPlayableHover(true);
            }
            this.render.repaint();
        }

    }

}
