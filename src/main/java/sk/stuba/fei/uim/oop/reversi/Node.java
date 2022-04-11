// package sk.stuba.fei.uim.oop.reversi;

// import lombok.Getter;
// import lombok.Setter;
// import sk.stuba.fei.uim.oop.player.Player;

// import java.awt.*;
// import java.util.HashMap;
// import java.util.Map;

// import javax.swing.*;

// public class Node
// // extends JPanel
// {
//     @Getter
//     @Setter
//     private Player owner;
//     @Getter
//     private final int x;
//     @Getter
//     private final int y;
//     @Getter
//     @Setter
//     private boolean playableHover;
//     @Getter
//     private Map<Direction, Node> neighbours;
//     public static final int NODE_SIZE = 40;
//     public static final int NODE_OFFSET = 20;

//     public Node(int row, int col) {
//         this.owner = null;
//         this.x = col * (NODE_SIZE + 10) + NODE_OFFSET;
//         this.y = row * (NODE_SIZE + 10) + NODE_OFFSET;
//         this.neighbours = new HashMap<>();
//         this.playableHover = false;
//         // this.setBackground(Color.GREEN);

//     }

//     // @Override
//     // public Dimension getPreferredSize() {
//     // return new Dimension(NODE_SIZE, NODE_SIZE);
//     // }

//     public void addNeighbour(Direction direction, Node node) {
//         this.neighbours.put(direction, node);
//     }

//     public void draw(Graphics g) {

//         g.setColor(Color.GREEN);
//         g.fillRect(this.x + 5, this.y + 5, Node.NODE_SIZE, Node.NODE_SIZE);
//         g.setColor(Color.BLACK);
//         if (this.playableHover) {
//             g.setColor(new Color(50, 50, 50, 150));
//             g.fillOval(this.x + 6, this.y + 6, Node.NODE_SIZE - 2, Node.NODE_SIZE - 2);
//             g.setColor(Color.BLACK);
//             this.playableHover = false;
//             // System.out.println("konec playable");
//             // this.playable = false;
//         }

//     }
// }

package sk.stuba.fei.uim.oop.reversi;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Node extends JPanel {
    @Getter
    @Setter
    private Stone stone;
    @Getter
    @Setter
    private Player owner;
    @Getter
    @Setter
    private boolean playableHover;
    @Getter
    @Setter
    private boolean playable;
    @Getter
    private Map<Direction, Node> neighbours;
    public static final int NODE_SIZE = 40;
    public static final int NODE_OFFSET = 20;

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
            this.stone.draw(g, new Color(50, 50, 50, 150), this.getWidth(), this.getHeight());
            this.playableHover = false;
        }
        if (this.owner != null) {
            this.stone.draw(g, this.owner.isAI() ? Color.BLACK : Color.WHITE, this.getWidth(), this.getHeight());
        }
        if (this.playable) {
            this.stone.draw(g, new Color(50, 50, 50, 40), this.getWidth(), this.getHeight());
        }
    }
}
