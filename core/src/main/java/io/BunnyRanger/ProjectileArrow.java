package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;

public class ProjectileArrow extends Projectile {
    public ProjectileArrow(WorldHandler world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int parent) {
        super(world, x, y, xSize, ySize, angle, magnitude, damage, density, friendly, facingRight, parent);
    }

    @Override
    protected boolean rotatesInAir() {
        return true;
    }
}
