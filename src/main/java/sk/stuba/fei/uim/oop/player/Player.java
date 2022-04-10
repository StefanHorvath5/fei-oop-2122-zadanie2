package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.reversi.Direction;
import sk.stuba.fei.uim.oop.reversi.Node;

import java.awt.*;

public class Player {
    @Getter
    private boolean aI;
    @Getter
    private List<Node> nodes;

    private GameLogic logic;

    public Player(GameLogic logic, boolean aI) {
        this.logic = logic;
        this.nodes = new ArrayList<>();
        this.aI = aI;
    }

    public void draw(Graphics g) {
        for (Node node : nodes) {
            // System.out.println(this.aI);
            g.setColor(this.aI == true ? Color.BLACK : Color.WHITE);
            // new Color(83, 205, 132)
            g.fillOval(node.getX() + 6, node.getY() + 6, Node.NODE_SIZE - 2, Node.NODE_SIZE - 2);
            g.setColor(Color.BLACK);
        }

    }

    public void addNode(Node node) {
        this.nodes.add(node);
        node.setOwner(this);
    }

    public void removeNode(Node node) {
        this.nodes.remove(node);
    }

    public boolean canPlayNode(Node node) {
        return this.checkPlayability(Direction.UP, node) ||
                this.checkPlayability(Direction.DOWN, node) ||
                this.checkPlayability(Direction.LEFT, node) ||
                this.checkPlayability(Direction.RIGHT, node) ||
                this.checkPlayability(Direction.DOWNLEFT, node) ||
                this.checkPlayability(Direction.DOWNRIGHT, node) ||
                this.checkPlayability(Direction.UPRIGHT, node) ||
                this.checkPlayability(Direction.UPLEFT, node);
    }

    public boolean checkPlayability(Direction dir, Node node) {
        if (node.getOwner() != null) {
            return false;
        }
        Node dirNode = node.getNeighbours().get(dir);
        if (dirNode == null || dirNode.getOwner() == this || dirNode.getOwner() == null) {
            return false;
        } else {
            return isMyStoneInDir(dir, dirNode);
        }
    }

    public boolean isMyStoneInDir(Direction dir, Node node) {
        Node dirNode = node.getNeighbours().get(dir);
        if (dirNode == null || dirNode.getOwner() == null) {
            return false;
        }
        while (dirNode.getOwner() != this) {
            dirNode = dirNode.getNeighbours().get(dir);
            if (dirNode == null || dirNode.getOwner() == null) {
                return false;
            }
        }
        return true;
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

    public void overtakeNodesIfPossible(Direction dir, Node node) {
        if (this.checkPlayability(dir, node)) {
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
