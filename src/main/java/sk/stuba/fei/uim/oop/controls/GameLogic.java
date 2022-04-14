package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.reversi.GameBoard;
import sk.stuba.fei.uim.oop.reversi.Node;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class GameLogic extends UniversalAdapter {

    @Getter
    private final JLabel label;
    @Getter
    private final JLabel boardSizeLabel;
    @Getter
    private final Render render;
    private final GameBoard gameBoard;
    private final Player[] players;
    private Player currentPlayer;
    private boolean gameStatus;

    public GameLogic() {
        this.label = new JLabel("", SwingConstants.CENTER);
        this.label.setFont(new Font("Serif", Font.PLAIN, 14));
        this.boardSizeLabel = new JLabel("", SwingConstants.CENTER);
        this.boardSizeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
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
            playable = this.currentPlayer.getPlayableNodesCount(this.gameBoard) != 0;
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
        this.gameBoard.clearPlayable();
        this.currentPlayer = this.currentPlayer.isAI() ? this.players[0] : this.players[1];
        this.label.setText("Player: " + (this.currentPlayer.isAI() ? "black(AI)" : "white(You)"));
        this.render.repaint();
        this.turn(notPlayable);
    }

    private void printWinner() {
        int youNodesCount = players[0].getNodesCount();
        int aiNodesCount = players[1].getNodesCount();
        if (youNodesCount > aiNodesCount) {
            this.label
                    .setText("<html>Winner: white(you)<br />White stones: " + youNodesCount + "<br>Black stones: "
                            + aiNodesCount + "</html>");

        } else {
            this.label.setText(
                    "<html>Winner: black(computer)<br />White stones: " + youNodesCount + "<br>Black stones: "
                            + aiNodesCount
                            + "</html>");
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
