package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileBomb extends Projectile {
    public ProjectileBomb(WorldHandler world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int parent) {
        super(world, x, y, xSize, ySize, angle, magnitude, damage, density, friendly, facingRight, parent);
        texture = new Texture(Gdx.files.internal("sprites/weapons/weapon_bomb_01.png"));
        projectileSprite = new Sprite(texture);
        projectileSprite.setScale(1f);
    }

    @Override
    public int death() {
        if (!onDeath) {
            WorldHandler.particleList.add(new ParticleImage(this.body, 50000, WorldHandler.getParty().font, new Color(Color.RED),"sprites/projectiles/projectile_fire_burst.png", 8,32,1));
        }

        onDeath = true;
        return super.death();
    }
}
