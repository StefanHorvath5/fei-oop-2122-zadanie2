package sk.stuba.fei.uim.oop.reversi;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Node
// extends JPanel
{
    @Getter
    @Setter
    private Player owner;
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    @Setter
    private boolean playable;
    @Getter
    private Map<Direction, Node> neighbours;
    public static final int NODE_SIZE = 40;
    public static final int NODE_OFFSET = 20;

    public Node(int row, int col) {
        this.owner = null;
        this.x = col * (NODE_SIZE + 10) + NODE_OFFSET;
        this.y = row * (NODE_SIZE + 10) + NODE_OFFSET;
        this.neighbours = new HashMap<>();
        this.playable = false;
        // this.setBackground(Color.GREEN);

    }

    // @Override
    // public Dimension getPreferredSize() {
    // return new Dimension(NODE_SIZE, NODE_SIZE);
    // }

    public void addNeighbour(Direction direction, Node node) {
        this.neighbours.put(direction, node);
    }

    public void draw(Graphics g) {
        if (this.playable) {
            g.setColor(Color.RED);
            g.fillRect(this.x + 5, this.y + 5, Node.NODE_SIZE, Node.NODE_SIZE);
            g.setColor(Color.BLACK);
            // System.out.println("konec playable");
            // this.playable = false;
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(this.x + 5, this.y + 5, Node.NODE_SIZE, Node.NODE_SIZE);
            g.setColor(Color.BLACK);
        }

    }
}
