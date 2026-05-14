package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyFox extends EnemyMoving{

    public EnemyFox(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 10;
        maxHealth = 10;
        enemyTexture = new Texture(Gdx.files.internal("fox.png"));
        enemySprite = new Sprite(enemyTexture,0,0,16,16);
        enemySprite.setScale(1f);
        flip(true);
        setRewards(4, 2);
    }

    public void move(boolean facingRight) {

    }

    public void moveAttack(boolean facingRight) {

        paceTowardTarget(facingRight, 2.1f, 35);
        if (moveCount % 100 == 0) {
            getBody().setLinearVelocity(facingRight ? 3.2f : -3.2f, 2f);
        }
        moveCount++;

    }

}
