package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.reversi.GameBoard;
import sk.stuba.fei.uim.oop.reversi.Node;

import javax.swing.*;
import java.awt.event.*;

public class GameLogic extends UniversalAdapter {

    @Getter
    private JLabel label;
    @Getter
    private JLabel boardSizeLabel;
    @Getter
    private Render render;
    @Getter
    private GameBoard gameBoard;
    @Getter
    private Player[] players;
    private Player currentPlayer;
    private boolean gameStatus;

    public GameLogic() {
        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();
        this.gameStatus = false;
        this.players = new Player[2];
        this.players[0] = new Player(false); // user
        this.players[1] = new Player(true); // ai
        this.currentPlayer = this.players[0];

        this.gameBoard = new GameBoard(players);
        this.render = new Render(this.gameBoard);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        this.newGame();
    }

    private void newGame() {
        this.gameStatus = true;
        this.currentPlayer = this.players[0];
        this.label.setText("Player: white(You)");
        this.boardSizeLabel
                .setText("Board size: " + this.gameBoard.getBoardSize() + "x" + this.gameBoard.getBoardSize());
        this.render.repaint();
        turn(false);
    }

    private void restartGame() {
        this.players[0].clear();
        this.players[1].clear();
        this.gameBoard.initializeGameBoard(players);
        this.render.resize();
        newGame();
    }

    public void changeBoardSize(int value) {
        this.gameBoard.setBoardSize(value);
        this.restartGame();
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
        this.gameBoard.clearPlayable();
        this.currentPlayer = this.currentPlayer.isAI() ? this.players[0] : this.players[1];
        this.label.setText("Player: " + (this.currentPlayer.isAI() ? "black(AI)" : "white(You)"));
        this.render.repaint();
        this.turn(notPlayable);
    }

    private void printWinner() {
        if (players[0].getNodesCount() > players[1].getNodesCount()) {
            this.label.setText("Winner: white(you)");

        } else {
            this.label.setText("Winner: black(computer)");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.gameStatus && this.render.getComponentAt(e.getPoint()) instanceof Node) {
            Node clickedNode = (Node) this.render.getComponentAt(e.getPoint());
            if (!this.currentPlayer.isAI() && clickedNode != null
                    && this.currentPlayer.canPlayNode(clickedNode)) {
                this.currentPlayer.playNode(clickedNode);
                this.nextTurn(false);

            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.gameStatus && this.render.getComponentAt(e.getPoint()) instanceof Node) {
            Node hoveredNode = (Node) this.render.getComponentAt(e.getPoint());
            if (!this.currentPlayer.isAI() && hoveredNode != null
                    && this.currentPlayer.canPlayNode(hoveredNode)) {
                hoveredNode.setPlayableHover(true);
            }
            this.render.repaint();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.restartGame();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.restartGame();
    }

}
