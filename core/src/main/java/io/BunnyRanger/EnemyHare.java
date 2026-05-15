package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyHare extends EnemyBunny {

    public EnemyHare(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 22;
        maxHealth = 22;
        enemyTexture = new Texture(Gdx.files.internal("bun.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1.15f);
        setRewards(8, 4);
    }

    public void moveAttack(boolean facingRight) {
        hopTowardTarget(facingRight, 2.4f, 6f, 45);
        burstTowardTarget(facingRight, 4f, 4f, 120);
        advanceMovement();
    }
}
