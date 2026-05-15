package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyBanditFox extends EnemyFox {

    public EnemyBanditFox(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 32;
        maxHealth = 32;
        enemyTexture = new Texture(Gdx.files.internal("fox.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1.15f);
        setRewards(9, 5);
    }

    public void moveAttack(boolean facingRight) {
        kiteFromTarget(facingRight, 2.2f, 55);
        burstTowardTarget(facingRight, 3.6f, 3f, 95);
        advanceMovement();
    }
}
