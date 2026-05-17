package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyViper extends EnemySnake {

    public EnemyViper(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 42;
        maxHealth = 42;
        enemyTexture = new Texture(Gdx.files.internal("sprites/characters/enemy_viper.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1.15f);
        setRewards(10, 5);
    }

    public void moveAttack(boolean facingRight) {
        paceTowardTarget(facingRight, 1.6f, 25);
        hopTowardTarget(facingRight, 2.8f, 9f, 90);
        advanceMovement();
    }
}
