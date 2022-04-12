package sk.stuba.fei.uim.oop.reversi;

import java.awt.*;

public class Stone {

    public void draw(Graphics g, Color color, int width, int height) {
        g.setColor(color);
        g.fillOval(2, 2, width - 4, height - 4);
        g.setColor(Color.BLACK);
    }

}
