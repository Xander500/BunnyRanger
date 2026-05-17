package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyMossSlime extends EnemySlime {

    public EnemyMossSlime(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 28;
        maxHealth = 28;
        enemyTexture = new Texture(Gdx.files.internal("sprites/characters/enemy_moss_slime.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1.2f);
        setRewards(7, 3);
    }

    public void moveAttack(boolean facingRight) {
        zigZag(2f, 40);
        hopTowardTarget(facingRight, 1.5f, 4.5f, 130);
        advanceMovement();
    }
}
