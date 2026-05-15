package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;

public abstract class EnemyMoving extends Enemy {

    protected static final float MOVEMENT_SPEED_MULTIPLIER = 5f;

    int moveCount;
    int movementDirection;

    public EnemyMoving(WorldHandler world, float x, float y) {

        super(world, x, y);
        this.moveCount = 0;
        this.movementDirection = 1;
    }

    protected void paceTowardTarget(boolean facingRight, float speed, int interval) {
        if (moveCount % interval == 0) {
            setVelocity(facingRight ? speed : -speed, getBody().getLinearVelocity().y / MOVEMENT_SPEED_MULTIPLIER);
        }
    }

    protected void hopTowardTarget(boolean facingRight, float speed, float hopPower, int interval) {
        if (moveCount % interval == 0) {
            setVelocity(facingRight ? speed : -speed, hopPower);
        }
    }

    protected void zigZag(float speed, int interval) {
        if (moveCount % interval == 0) {
            movementDirection *= -1;
            setVelocity(speed * movementDirection, getBody().getLinearVelocity().y / MOVEMENT_SPEED_MULTIPLIER);
        }
    }

    protected void kiteFromTarget(boolean facingRight, float speed, int interval) {
        if (moveCount % interval == 0) {
            setVelocity(facingRight ? -speed : speed, getBody().getLinearVelocity().y / MOVEMENT_SPEED_MULTIPLIER);
        }
    }

    protected void burstTowardTarget(boolean facingRight, float speed, float lift, int interval) {
        if (moveCount % interval == 0) {
            setVelocity(facingRight ? speed : -speed, lift);
        }
    }

    protected void burstAwayFromTarget(boolean facingRight, float speed, float lift, int interval) {
        if (moveCount % interval == 0) {
            setVelocity(facingRight ? -speed : speed, lift);
        }
    }

    protected Vector2 getClosestLivingPlayerPosition() {
        Player closestPlayer = null;
        float closestDistance = Float.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            Player player = WorldHandler.getParty().getPlayer(i);

            if (player == null || player.checkIfDead()) {
                continue;
            }

            float distance = Math.abs(getBody().getPosition().x - player.getBody().getPosition().x);

            if (distance < closestDistance) {
                closestDistance = distance;
                closestPlayer = player;
            }
        }

        if (closestPlayer == null) {
            return null;
        }

        return closestPlayer.getBody().getPosition();
    }

    protected void advanceMovement() {
        moveCount++;
    }

    protected void setVelocity(float xSpeed, float ySpeed) {
        getBody().setLinearVelocity(scaled(xSpeed), scaled(ySpeed));
    }

    protected float scaled(float speed) {
        return speed * MOVEMENT_SPEED_MULTIPLIER;
    }

}
