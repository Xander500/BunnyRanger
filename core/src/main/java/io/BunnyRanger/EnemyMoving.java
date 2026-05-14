package io.BunnyRanger;

public abstract class EnemyMoving extends Enemy {

    int moveCount;
    int movementDirection;

    public EnemyMoving(WorldHandler world, float x, float y) {

        super(world, x, y);
        this.moveCount = 0;
        this.movementDirection = 1;
    }

    protected void paceTowardTarget(boolean facingRight, float speed, int interval) {
        if (moveCount % interval == 0) {
            getBody().setLinearVelocity(facingRight ? speed : -speed, getBody().getLinearVelocity().y);
        }
    }

    protected void hopTowardTarget(boolean facingRight, float speed, float hopPower, int interval) {
        if (moveCount % interval == 0) {
            getBody().setLinearVelocity(facingRight ? speed : -speed, hopPower);
        }
    }

    protected void zigZag(float speed, int interval) {
        if (moveCount % interval == 0) {
            movementDirection *= -1;
            getBody().setLinearVelocity(speed * movementDirection, getBody().getLinearVelocity().y);
        }
    }

    protected void kiteFromTarget(boolean facingRight, float speed, int interval) {
        if (moveCount % interval == 0) {
            getBody().setLinearVelocity(facingRight ? -speed : speed, getBody().getLinearVelocity().y);
        }
    }

}
