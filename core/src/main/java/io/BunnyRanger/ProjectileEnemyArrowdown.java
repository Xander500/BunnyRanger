package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEnemyArrowdown extends Projectile {

    public ProjectileEnemyArrowdown(WorldHandler world, float x, float y, float damage) {
        super(world, x, y, .45f, .6f, 0, new Vector2(0, -35f), damage, .7f, false, false, 0);

        this.texture = new Texture(Gdx.files.internal("bomb1.png"));
        this.projectileSprite = new Sprite(texture, 0, 0, 16, 16);
        this.projectileSprite.setScale(.7f);
        this.body.setGravityScale(1.6f);
        this.spriteDestroyLifeSpan = 240;
        this.spriteDestroyDelay = 18;
    }
}
