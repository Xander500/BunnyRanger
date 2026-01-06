package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class EnemySlime extends EnemyMoving {

    public EnemySlime(WorldInstance world, float x, float y) {
        super(world, x, y);
        maxHealth = 10;
        health = 10;
        enemyTexture = new Texture(Gdx.files.internal("slime.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1);
    }

    public void move(boolean facingRight) {


    }

    public void moveAttack(boolean facingRight) {

        if (moveCount > 50) {

            if (Math.random() > .5) {

                this.getBody().setLinearVelocity(2f, 0);

            } else {

                this.getBody().setLinearVelocity(-2f, 0);

            }

            moveCount = 0;

        }

        moveCount++;


    }

}
