package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemySnake extends EnemyMoving {

    public EnemySnake(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 30;
        maxHealth = 30;
        enemyTexture = new Texture(Gdx.files.internal("snek.png"));
        enemySprite = new Sprite(enemyTexture,0,0,16,16);
        enemySprite.setScale(1);
        setRewards(6, 3);
    }

    public void move(boolean facingRight) {

    }

    public void moveAttack(boolean facingRight) {

        hopTowardTarget(facingRight, 1.7f, 8f, 115);
        if (moveCount % 45 == 0) {
            paceTowardTarget(facingRight, .7f, 1);
        }
        moveCount++;

    }

}
