package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemySlime extends EnemyMoving {

    public EnemySlime(WorldHandler world, float x, float y) {
        super(world, x, y);
        maxHealth = 10;
        health = 10;
        enemyTexture = new Texture(Gdx.files.internal("sprites/characters/enemy_slime.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1);
        setRewards(2, 1);
    }

    public void move(boolean facingRight) {


    }

    public void moveAttack(boolean facingRight) {

        zigZag(1.3f, 65);
        advanceMovement();
    }

}
