package sk.stuba.fei.uim.oop.reversi;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;

public class GameBoard {
    @Getter
    @Setter
    private int boardSize = 6;
    @Getter
    private Node[][] board;

    public GameBoard(Player players[]) {
        this.initializeGameBoard(players);
    }

    public void initializeGameBoard(Player players[]) {
        this.board = new Node[this.boardSize][this.boardSize];
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                this.board[row][col] = new Node(row, col);
            }
        }

        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                if (row != 0) {
                    this.board[row][col].addNeighbour(Direction.UP, this.board[row - 1][col]);
                    if (col != 0) {
                        this.board[row][col].addNeighbour(Direction.UPLEFT, this.board[row - 1][col - 1]);
                    }
                    if (col != this.boardSize - 1) {
                        this.board[row][col].addNeighbour(Direction.UPRIGHT, this.board[row - 1][col + 1]);
                    }
                }
                if (row != this.boardSize - 1) {
                    this.board[row][col].addNeighbour(Direction.DOWN, this.board[row + 1][col]);
                    if (col != 0) {
                        this.board[row][col].addNeighbour(Direction.DOWNLEFT, this.board[row + 1][col - 1]);
                    }
                    if (col != this.boardSize - 1) {
                        this.board[row][col].addNeighbour(Direction.DOWNRIGHT, this.board[row + 1][col + 1]);
                    }
                }
                if (col != 0) {
                    this.board[row][col].addNeighbour(Direction.LEFT, this.board[row][col - 1]);
                }
                if (col != this.boardSize - 1) {
                    this.board[row][col].addNeighbour(Direction.RIGHT, this.board[row][col + 1]);
                }
            }
        }

        players[0].addNode(this.board[this.boardSize / 2 - 1][this.boardSize / 2 - 1]);
        players[0].addNode(this.board[this.boardSize / 2][this.boardSize / 2]);
        players[1].addNode(this.board[this.boardSize / 2][this.boardSize / 2 - 1]);
        players[1].addNode(this.board[this.boardSize / 2 - 1][this.boardSize / 2]);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.board[i][j].draw(g);
            }
        }
        g.drawRect(Node.NODE_OFFSET, Node.NODE_OFFSET, this.boardSize * (Node.NODE_SIZE + 10), this.boardSize *
                (Node.NODE_SIZE + 10));
    }

    public Node getNodeAtPos(int row, int col) {
        return this.board[row][col];
    }

    public Node getNodeAtPoint(Point point) {
        // System.out.println((point.getX() - Node.NODE_OFFSET - 5) / (Node.NODE_SIZE +
        // 10));
        double possibleXIndex = (point.getX() - Node.NODE_OFFSET - 5) / (Node.NODE_SIZE + 10);
        double possibleYIndex = (point.getY() - Node.NODE_OFFSET - 5) / (Node.NODE_SIZE + 10);
        double decimalPartX = possibleXIndex - Math.floor(possibleXIndex);
        double decimalPartY = possibleYIndex - Math.floor(possibleYIndex);

        if ((decimalPartX > Node.NODE_SIZE / (double) (Node.NODE_SIZE + 10))
                || (decimalPartY > Node.NODE_SIZE / (double) (Node.NODE_SIZE + 10))) {
            return null;
        } else {
            int xIndex = (int) Math.floor(possibleXIndex);
            int yIndex = (int) Math.floor(possibleYIndex);
            System.out.println("Point: [" + xIndex + ", " + yIndex + "]");
            return this.board[yIndex][xIndex];
        }
    }
}
