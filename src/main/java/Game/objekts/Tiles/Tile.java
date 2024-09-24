package Game.objekts.Tiles;

import Game.objekts.GameObject;

public abstract class Tile extends GameObject {

    protected final int x;

    protected int y;


    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

    }


}
