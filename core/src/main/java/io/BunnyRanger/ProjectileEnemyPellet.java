package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEnemyPellet extends ProjectileBullet {

    public ProjectileEnemyPellet(WorldHandler world, float x, float y, Vector2 velocity, float damage, boolean facingRight) {
        super(world, x, y, 8f, 8f, 0, velocity, damage, .5f, false, facingRight, 0);

        this.texture = new Texture(Gdx.files.internal("sprites/projectiles/enemy_ring.png"));
        this.projectileSprite = new Sprite(texture);
        this.projectileSprite.setScale(1f);
        this.body.setGravityScale(.15f);
        this.spriteDestroyLifeSpan = 180;
        this.spriteDestroyDelay = 8;
    }
}
