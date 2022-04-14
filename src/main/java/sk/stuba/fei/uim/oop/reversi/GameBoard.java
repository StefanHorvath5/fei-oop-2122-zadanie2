package sk.stuba.fei.uim.oop.reversi;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

public class GameBoard {
    @Getter
    @Setter
    private int boardSize;
    private Node[][] board;

    public GameBoard(Player[] players) {
        this.boardSize = 6;
        this.initializeGameBoard(players);
    }

    public void initializeGameBoard(Player[] players) {
        this.board = new Node[this.boardSize][this.boardSize];
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                this.board[row][col] = new Node();
            }
        }

        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                if (row != 0) {
                    this.board[row][col].addNeighbour(Direction.UP, this.board[row - 1][col]);
                    if (col != 0) {
                        this.board[row][col].addNeighbour(Direction.UP_LEFT, this.board[row - 1][col - 1]);
                    }
                    if (col != this.boardSize - 1) {
                        this.board[row][col].addNeighbour(Direction.UP_RIGHT, this.board[row - 1][col + 1]);
                    }
                }
                if (row != this.boardSize - 1) {
                    this.board[row][col].addNeighbour(Direction.DOWN, this.board[row + 1][col]);
                    if (col != 0) {
                        this.board[row][col].addNeighbour(Direction.DOWN_LEFT, this.board[row + 1][col - 1]);
                    }
                    if (col != this.boardSize - 1) {
                        this.board[row][col].addNeighbour(Direction.DOWN_RIGHT, this.board[row + 1][col + 1]);
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

        players[0].addNode(this.board[this.boardSize / 2][this.boardSize / 2 - 1]);
        players[0].addNode(this.board[this.boardSize / 2 - 1][this.boardSize / 2]);
        players[1].addNode(this.board[this.boardSize / 2 - 1][this.boardSize / 2 - 1]);
        players[1].addNode(this.board[this.boardSize / 2][this.boardSize / 2]);
    }

    public void clearPlayable() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                this.board[row][col].setPlayable(false);
            }
        }
    }

    public Node getNodeAtPos(int row, int col) {
        return this.board[row][col];
    }

}
