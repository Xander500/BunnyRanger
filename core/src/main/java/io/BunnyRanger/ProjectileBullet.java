package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileBullet extends Projectile {

    public ProjectileBullet(WorldHandler world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int parent) {
        super(world, x, y, xSize/2, ySize, angle, magnitude, damage, density, friendly, facingRight, parent);

        this.texture = null;
        this.projectileSprite = null;

        this.texture = new Texture(Gdx.files.internal("musketBall.png"));
        this.projectileSprite = new Sprite(texture);

        this.projectileSprite.setScale(.5f);

    }

    @Override
    public int death() {
        if (!onDeath) {
            WorldHandler.particleList.add(new Particle(this.body, 50000, WorldHandler.getParty().font, new Color(Color.RED)));
        }
        onDeath = true;
        return super.death();
    }
}
