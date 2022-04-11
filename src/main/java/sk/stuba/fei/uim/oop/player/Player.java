package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.gui.Game;
import sk.stuba.fei.uim.oop.reversi.Direction;
import sk.stuba.fei.uim.oop.reversi.GameBoard;
import sk.stuba.fei.uim.oop.reversi.Node;

import java.awt.*;

public class Player {
    @Getter
    private boolean aI;
    @Getter
    private List<Node> nodes;
    @Getter
    private List<Node> playableNodes;

    private GameLogic logic;

    public Player(GameLogic logic, boolean aI) {
        this.logic = logic;
        this.nodes = new ArrayList<>();
        this.playableNodes = new ArrayList<>();
        this.aI = aI;
    }

    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.aI == true ? Color.BLACK : Color.WHITE);
            g.fillOval(node.getX() + 6, node.getY() + 6, Node.NODE_SIZE - 2, Node.NODE_SIZE - 2);
            g.setColor(Color.BLACK);
        }
        for (Node node : this.playableNodes) {
            g.setColor(new Color(50, 50, 50, 40));
            g.fillOval(node.getX() + 6, node.getY() + 6, Node.NODE_SIZE - 2, Node.NODE_SIZE - 2);
            g.setColor(Color.BLACK);
        }

    }

    public void clearPlayableNodes() {
        this.playableNodes.clear();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
        node.setOwner(this);
    }

    public void removeNode(Node node) {
        this.nodes.remove(node);
    }

    public int getNodesCount() {
        return this.nodes.size();
    }

    public boolean canPlayNode(Node node) {
        return this.checkPlayability(Direction.UP, node) != 0 ||
                this.checkPlayability(Direction.DOWN, node) != 0 ||
                this.checkPlayability(Direction.LEFT, node) != 0 ||
                this.checkPlayability(Direction.RIGHT, node) != 0 ||
                this.checkPlayability(Direction.DOWNLEFT, node) != 0 ||
                this.checkPlayability(Direction.DOWNRIGHT, node) != 0 ||
                this.checkPlayability(Direction.UPRIGHT, node) != 0 ||
                this.checkPlayability(Direction.UPLEFT, node) != 0;
    }

    public int updateAndGetPlayableNodesCount(GameBoard gameBoard) {
        this.playableNodes.clear();
        this.playableNodes.addAll(this.getPlayable(gameBoard));
        return this.playableNodes.size();

    }

    private List<Node> getPlayable(GameBoard gameBoard) {
        List<Node> playable = new ArrayList<>();
        for (int row = 0; row < gameBoard.getBoardSize(); row++) {
            for (int col = 0; col < gameBoard.getBoardSize(); col++) {
                if (this.canPlayNode(gameBoard.getNodeAtPos(row, col))) {
                    playable.add(gameBoard.getNodeAtPos(row, col));
                }
            }
        }
        return playable;
    }

    public boolean makeBestMove(GameBoard gameBoard) {
        Node bestNode = null;
        int bestNodeOvertakeCount = 0;
        for (Node node : this.getPlayable(gameBoard)) {
            int currentNodeOvertakeCount = this.nodeOvertakeCount(node);
            if (currentNodeOvertakeCount > bestNodeOvertakeCount) {
                bestNodeOvertakeCount = currentNodeOvertakeCount;
                bestNode = node;
            }
        }
        if (bestNode != null) {
            this.playNode(bestNode);
            return true;
        }
        return false;
    }

    private int nodeOvertakeCount(Node node) {
        return this.checkPlayability(Direction.UP, node) +
                this.checkPlayability(Direction.DOWN, node) +
                this.checkPlayability(Direction.LEFT, node) +
                this.checkPlayability(Direction.RIGHT, node) +
                this.checkPlayability(Direction.DOWNLEFT, node) +
                this.checkPlayability(Direction.DOWNRIGHT, node) +
                this.checkPlayability(Direction.UPRIGHT, node) +
                this.checkPlayability(Direction.UPLEFT, node);
    }

    private int checkPlayability(Direction dir, Node node) {
        if (node.getOwner() != null) {
            return 0;
        }
        Node dirNode = node.getNeighbours().get(dir);
        if (dirNode == null || dirNode.getOwner() == this || dirNode.getOwner() == null) {
            return 0;
        } else {
            return isMyStoneInDir(dir, dirNode);
        }
    }

    private int isMyStoneInDir(Direction dir, Node node) {
        int overtakeCount = 1;
        Node dirNode = node.getNeighbours().get(dir);
        if (dirNode == null || dirNode.getOwner() == null) {
            return 0;
        }
        while (dirNode.getOwner() != this) {
            overtakeCount++;
            dirNode = dirNode.getNeighbours().get(dir);
            if (dirNode == null || dirNode.getOwner() == null) {
                return 0;
            }
        }
        return overtakeCount;
    }

    public void playNode(Node node) {
        this.overtakeNodesIfPossible(Direction.UP, node);
        this.overtakeNodesIfPossible(Direction.DOWN, node);
        this.overtakeNodesIfPossible(Direction.LEFT, node);
        this.overtakeNodesIfPossible(Direction.RIGHT, node);
        this.overtakeNodesIfPossible(Direction.DOWNLEFT, node);
        this.overtakeNodesIfPossible(Direction.DOWNRIGHT, node);
        this.overtakeNodesIfPossible(Direction.UPRIGHT, node);
        this.overtakeNodesIfPossible(Direction.UPLEFT, node);
        node.setOwner(this);
        this.addNode(node);
    }

    private void overtakeNodesIfPossible(Direction dir, Node node) {
        if (this.checkPlayability(dir, node) != 0) {
            Node dirNode = node.getNeighbours().get(dir);
            if (dirNode != null) {
                while (dirNode.getOwner() != this) {
                    dirNode.getOwner().removeNode(dirNode);
                    dirNode.setOwner(this);
                    this.addNode(dirNode);
                    dirNode = dirNode.getNeighbours().get(dir);
                }
            }
        }
    }
}
