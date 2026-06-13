package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEnemyArrowJab extends Projectile {

    public ProjectileEnemyArrowJab(WorldHandler world, float x, float y, Vector2 velocity, float damage, boolean facingRight) {
        super(world, x, y, 8f, 8f, 0, velocity, damage, .5f, false, facingRight, 0);

        this.texture = new Texture(Gdx.files.internal("sprites/projectiles/enemy_side_wave.png"));
        this.projectileSprite = new Sprite(texture, 0, 0, 16, 16);
        this.projectileSprite.setScale(1f);
        this.projectileSprite.setRotation(facingRight ? 0f : 180f);
        this.body.setGravityScale(0f);
        this.spriteDestroyLifeSpan = 12;
        this.spriteDestroyDelay = 15;
    }
}
