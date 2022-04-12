package sk.stuba.fei.uim.oop.reversi;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Node extends JPanel {
    private Stone stone;
    @Getter
    @Setter
    private Player owner;
    @Setter
    private boolean playableHover;
    @Setter
    private boolean playable;
    @Getter
    private Map<Direction, Node> neighbours;

    public Node() {
        this.owner = null;
        this.neighbours = new HashMap<>();
        this.playableHover = false;
        this.setBackground(Color.GREEN);
        this.stone = new Stone();
    }

    public void addNeighbour(Direction direction, Node node) {
        this.neighbours.put(direction, node);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.playableHover) {
            this.stone.draw(g, Color.LIGHT_GRAY, this.getWidth(), this.getHeight());
            this.playableHover = false;
        }
        if (this.owner != null) {
            this.stone.draw(g, this.owner.isAI() ? Color.BLACK : Color.WHITE, this.getWidth(), this.getHeight());
        }
        if (this.playable) {
            this.stone.draw(g, new Color(0, 0, 0, 35), this.getWidth(), this.getHeight());
        }
    }
}
