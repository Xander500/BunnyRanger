package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyRangerBunny extends EnemyHare {

    public EnemyRangerBunny(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 55;
        maxHealth = 55;
        enemyTexture = new Texture(Gdx.files.internal("sprites/characters/enemy_ranger_bunny.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1.3f);
        setRewards(15, 8);
    }

    public void moveAttack(boolean facingRight) {
        hopTowardTarget(facingRight, 3f, 7f, 38);
        kiteFromTarget(facingRight, 1.8f, 100);
        advanceMovement();
    }
}
