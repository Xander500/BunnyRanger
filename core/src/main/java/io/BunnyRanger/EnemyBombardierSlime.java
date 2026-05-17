package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyBombardierSlime extends EnemyMossSlime {

    public EnemyBombardierSlime(WorldHandler world, float x, float y) {
        super(world, x, y);
        health = 60;
        maxHealth = 60;
        enemyTexture = new Texture(Gdx.files.internal("sprites/characters/enemy_bombardier_slime.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(1.35f);
        setRewards(16, 8);
    }

    public void moveAttack(boolean facingRight) {
        kiteFromTarget(facingRight, 1.4f, 45);
        hopTowardTarget(facingRight, 1f, 5f, 140);
        advanceMovement();
    }
}
