package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyBossBunny extends EnemyMoving {

    public EnemyBossBunny(WorldHandler world, float x, float y) {
        super(world, x, y);

        health = 220;
        maxHealth = 220;
        healthSize = .8f;
        distance = 7f;

        enemyTexture = new Texture(Gdx.files.internal("sprites/characters/enemy_ranger_bunny.png"));
        enemySprite = new Sprite(enemyTexture, 0, 0, 16, 16);
        enemySprite.setScale(2.1f);

        addWeapon(new WeaponBow3(false));
        addWeapon(new WeaponClusterBomb(false));
        addWeapon(new WeaponWandRollerFire(false));

        setRewards(60, 30);
    }

    @Override
    public void moveAttack(boolean facingRight) {
        kiteFromTarget(facingRight, 1.2f, 55);
        hopTowardTarget(facingRight, 1.7f, 8f, 105);
        burstAwayFromTarget(facingRight, 4.2f, 4f, 170);
        advanceMovement();
    }
}
