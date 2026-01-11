package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemySnake extends EnemyMoving {

    public EnemySnake(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 30;
        enemyTexture = new Texture(Gdx.files.internal("snek.png"));
        enemySprite = new Sprite(enemyTexture,0,0,16,16);
        enemySprite.setScale(1);
    }

    public void move(boolean facingRight) {

    }

    public void moveAttack(boolean facingRight) {

        if (moveCount > 200) {

            if(facingRight) {

                this.getBody().setLinearVelocity(1f,10);

            } else {

                this.getBody().setLinearVelocity(-1f,10);

            }

            moveCount = 0;

        }

        moveCount++;

    }

}
