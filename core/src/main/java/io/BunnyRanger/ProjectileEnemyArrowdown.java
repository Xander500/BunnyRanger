package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEnemyArrowdown extends Projectile {

    public ProjectileEnemyArrowdown(WorldHandler world, float x, float y, float damage) {
        this(world, x, y, damage, .45f, .6f, new Vector2(0, -35f), 1.6f, .7f, 240, 18);
    }

    public ProjectileEnemyArrowdown(WorldHandler world, float x, float y, float damage, float xSize, float ySize, Vector2 velocity, float gravityScale, float spriteScale, int lifespan, int destroyDelay) {
        super(world, x, y, xSize, ySize, 0, velocity, damage, .7f, false, false, 0);

        this.texture = new Texture(Gdx.files.internal("sprites/projectiles/projectile_arrow.png"));
        this.projectileSprite = new Sprite(texture, 0, 0, 16, 16);
        this.projectileSprite.setScale(spriteScale);
        this.projectileSprite.setRotation(-90f);
        this.body.setGravityScale(gravityScale);
        this.spriteDestroyLifeSpan = lifespan;
        this.spriteDestroyDelay = destroyDelay;
    }
}
