package Game.objekts.Creatures;

import Game.Game;
import Game.objekts.GameObject;

import java.awt.*;

import Game.GameMap;

public abstract class creature extends GameObject {

    protected final Game game;
    protected double centerX;
    protected double centerY;
    protected final double radius;
    protected final double speed;
    protected Color color;

    protected int prefferedDirectionX;
    protected int prefferedDirectionY;
    protected int movingDirectionX;
    protected int movingDirectionY;

    public creature(Game game, double centerX, double centerY, double radius, double speed, Color color) {
        this.game = game;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.speed = speed;
        this.color = color;
    }

    private void tickMovingDirection() {

        if (movingDirectionX == 0 && movingDirectionY == 0) {
            movingDirectionX = prefferedDirectionX;
            movingDirectionY = prefferedDirectionY;
        } else if (movingDirectionX != 0 && prefferedDirectionX != 0) {
            movingDirectionX=prefferedDirectionX;
        } else if (movingDirectionY !=0 && prefferedDirectionY!=0) {
            movingDirectionY=prefferedDirectionY;
        }

    }

    private void tickWallCollisions() {
        GameMap map = game.getMap();

        if (movingDirectionX == 1 && !map.isFree((int) (centerX + 0.5), (int) (centerY))
                || movingDirectionX == -1 && !map.isFree((int) (centerX - 0.5), (int) (centerY))) {
            centerX = (int) centerX + 0.5;
            movingDirectionX = 0;
        } else if (movingDirectionY == 1 && !map.isFree((int) centerX, (int) (centerY + 0.5))
                || movingDirectionY == -1 && !map.isFree((int) centerX, (int) (centerY - 0.5))) {
            centerY = (int) centerY + 0.5;
            movingDirectionY = 0;

        }


    }


    private void tickTurn(boolean crossedCenterX, boolean crossedCenterY) {
    boolean turnXToY = crossedCenterX && movingDirectionX!=0 && prefferedDirectionY !=0 && game.getMap().isFree((int)centerX, (int)centerY, (int) (centerY+prefferedDirectionY);
    }

    public void tick() {
        tickMovingDirection();


        double newX = centerX + movingDirectionX * speed;
        double newY = centerY + movingDirectionY * speed;
        tickWallCollisions();

        boolean crossedCenterX=Math.abs((centerX-0.5) % 1.0 - (newX-0.5)% 1.0) >0.5;
        boolean crossedCenterY=Math.abs((centerY-0.5) % 1.0 - (newY-0.5)% 1.0) >0.5;
        centerX=newX;
        centerY=newY;

        tickTurn(crossedCenterX, crossedCenterY);
        tickWallCollisions();
    }




    public double getCenterX() {
        return centerX;
    }


    public double getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }


}
