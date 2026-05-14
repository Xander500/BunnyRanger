package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyBunny extends EnemyMoving{

    public EnemyBunny(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 10;
        maxHealth = 10;
        enemyTexture = new Texture(Gdx.files.internal("bun.png"));
        enemySprite = new Sprite(enemyTexture,0,0,16,16);
        enemySprite.setScale(1f);
        setRewards(3, 1);
    }

    public void move(boolean facingRight) {

    }

    public void moveAttack(boolean facingRight) {

        hopTowardTarget(facingRight, .8f, 3.5f, 70);
        moveCount++;

    }

}
