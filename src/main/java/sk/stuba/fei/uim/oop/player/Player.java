package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import sk.stuba.fei.uim.oop.controls.GameLogic;
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
            System.out.println(this.aI);
            g.setColor(this.aI == true ? Color.BLACK : Color.WHITE);
            // new Color(83, 205, 132)
            g.fillOval(node.getX() + 6, node.getY() + 6, Node.NODE_SIZE - 12, Node.NODE_SIZE - 12);
            g.setColor(Color.BLACK);
        }

    }

    public void addNode(Node node) {
        this.nodes.add(node);
        node.setOwner(this);
    }

}
