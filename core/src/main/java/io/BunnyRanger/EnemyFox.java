package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyFox extends EnemyMoving{

    public EnemyFox(WorldInstance world, float x, float y) {
        super(world, x, y);
        health = 10;
        enemyTexture = new Texture(Gdx.files.internal("fox.png"));
        enemySprite = new Sprite(enemyTexture,0,0,16,16);
        enemySprite.setScale(1f);
        flip(true);
    }

    public void move(boolean facingRight) {

    }

    public void moveAttack(boolean facingRight) {

        if (moveCount > 50) {

            if(facingRight) {

                this.getBody().setLinearVelocity(2f,0);

            } else {

                this.getBody().setLinearVelocity(-2f,0);

            }

            moveCount = 0;

        }

        moveCount++;

    }

}
