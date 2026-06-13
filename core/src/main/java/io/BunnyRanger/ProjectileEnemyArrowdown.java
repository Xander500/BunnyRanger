package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEnemyArrowdown extends Projectile {

    public ProjectileEnemyArrowdown(WorldHandler world, float x, float y, float damage) {
        this(world, x, y, damage, 8f, 8f, new Vector2(0, -35f), 1.6f, 1f, 240, 18, "sprites/projectiles/enemy_down_wave.png", 16, 16);
    }

    public ProjectileEnemyArrowdown(WorldHandler world, float x, float y, float damage, float xSize, float ySize, Vector2 velocity, float gravityScale, float spriteScale, int lifespan, int destroyDelay) {
        this(world, x, y, damage, xSize, ySize, velocity, gravityScale, spriteScale, lifespan, destroyDelay, "sprites/projectiles/enemy_down_wave.png", 16, 16);
    }

    public ProjectileEnemyArrowdown(WorldHandler world, float x, float y, float damage, float xSize, float ySize, Vector2 velocity, float gravityScale, float spriteScale, int lifespan, int destroyDelay, String texturePath, int textureWidth, int textureHeight) {
        super(world, x, y, xSize, ySize, 0, velocity, damage, .7f, false, false, 0);

        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.projectileSprite = new Sprite(texture, 0, 0, textureWidth, textureHeight);
        this.projectileSprite.setScale(spriteScale);
        this.body.setGravityScale(gravityScale);
        this.spriteDestroyLifeSpan = lifespan;
        this.spriteDestroyDelay = destroyDelay;
    }
}
