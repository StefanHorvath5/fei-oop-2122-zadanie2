package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.reversi.GameBoard;

public class GameLogic extends UniversalAdapter {

    @Getter
    private Render render;
    @Getter
    private GameBoard gameBoard;
    @Getter
    private Player[] players;

    public GameLogic() {
        this.players = new Player[2];
        this.players[0] = new Player(this, false); // user
        this.players[1] = new Player(this, true); // ai

        this.gameBoard = new GameBoard(players);
        this.render = new Render(this.gameBoard, players);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
    }

}
