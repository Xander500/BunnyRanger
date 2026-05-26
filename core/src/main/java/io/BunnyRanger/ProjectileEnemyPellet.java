package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;

public class ProjectileEnemyPellet extends ProjectileBullet {

    public ProjectileEnemyPellet(WorldHandler world, float x, float y, Vector2 velocity, float damage, boolean facingRight) {
        super(world, x, y, .35f, .25f, 0, velocity, damage, .5f, false, facingRight, 0);

        this.projectileSprite.setScale(.35f);
        this.body.setGravityScale(.15f);
        this.spriteDestroyLifeSpan = 90;
        this.spriteDestroyDelay = 8;
    }
}
