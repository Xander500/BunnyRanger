package io.BunnyRanger;

public abstract class EnemyMoving extends Enemy {

    int moveCount;

    public EnemyMoving(WorldInstance world, float x, float y) {

        super(world, x, y);
        this.moveCount = 0;
    }

}
