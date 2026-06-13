package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class EnemyEagle extends EnemyMoving {

    private static final float HOVER_HEIGHT = 92f;
    private static final float HORIZONTAL_SPEED = 5.2f;
    private static final float FLAP_UP = 4.4f;
    private static final float GLIDE_DOWN = -1.8f;
    private static final float HEIGHT_DEAD_ZONE = 16f;

    public EnemyEagle(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 28;
        maxHealth = 28;
        enemyTexture = new Texture(Gdx.files.internal("sprites/characters/enemy_storm_eagle.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1f);
        body.setTransform(x * 16f, y * 16f, body.getAngle());
        bodyB.setTransform(x * 16f, y * 16f + distance, bodyB.getAngle());
        body.setGravityScale(.18f);
        body.setLinearDamping(1.6f);
        setRewards(12, 6);
        addWeapon(new WeaponEnemyArrowdown(false));
    }

    @Override
    public void moveAttack(boolean facingRight) {
        Vector2 targetPosition = getClosestLivingPlayerPosition();

        if (targetPosition == null) {
            flapInPlace();
            advanceMovement();
            return;
        }

        float currentX = getBody().getPosition().x;
        float currentY = getBody().getPosition().y;
        float desiredY = targetPosition.y + HOVER_HEIGHT;

        float vx = 0;
        if (Math.abs(targetPosition.x - currentX) > 8f) {
            vx = targetPosition.x > currentX ? HORIZONTAL_SPEED : -HORIZONTAL_SPEED;
        }

        float vy = 0;
        if (currentY < desiredY - HEIGHT_DEAD_ZONE) {
            vy = FLAP_UP;
        } else if (currentY > desiredY + HEIGHT_DEAD_ZONE) {
            vy = GLIDE_DOWN;
        } else if (moveCount % 30 == 0) {
            vy = FLAP_UP * .7f;
        }

        setVelocity(vx, vy);
        advanceMovement();
    }

    private void flapInPlace() {
        if (moveCount % 30 == 0) {
            setVelocity(0, FLAP_UP * .7f);
        }
    }
}
