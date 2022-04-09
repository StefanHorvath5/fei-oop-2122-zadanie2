package sk.stuba.fei.uim.oop.reversi;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Node {
    @Getter
    @Setter
    private Player owner;
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private Map<Direction, Node> neighbours;
    public static final int NODE_SIZE = 50;
    public static final int NODE_OFFSET = 20;

    public Node(int row, int col) {
        this.owner = null;
        this.x = col * NODE_SIZE + NODE_OFFSET;
        this.y = row * NODE_SIZE + NODE_OFFSET;
        this.neighbours = new HashMap<>();
    }

    public void addNeighbour(Direction direction, Node node) {
        this.neighbours.put(direction, node);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(this.x + 5, this.y + 5, Node.NODE_SIZE - 10, Node.NODE_SIZE - 10);
        g.setColor(Color.BLACK);
    }
}
