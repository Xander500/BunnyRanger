package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MusketBallProjectile extends ArrowProjectile {

    public MusketBallProjectile(WorldInstance world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight) {
        super(world, x, y, xSize/2, ySize, angle, magnitude, damage, density, friendly, facingRight);

        this.texture = null;
        this.projectileSprite = null;

        this.texture = new Texture(Gdx.files.internal("musketBall.png"));
        this.projectileSprite = new Sprite(texture, 0, 0, 16, 16);

        this.projectileSprite.setScale(.05f);

    }
}
