package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;

public class ProjectileClusterBomb extends ProjectileBomb {

    private final int childCount;
    private final int childGeneration;
    private final boolean childFriendly;

    public ProjectileClusterBomb(WorldHandler world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int childGeneration) {
        super(world, x, y, xSize, ySize, angle, magnitude, damage, density, friendly, facingRight, childGeneration);
        this.childGeneration = childGeneration;
        this.childFriendly = friendly;
        this.childCount = 5;
    }

    @Override
    public int death() {
        if (!onDeath) {
            spawnMiniBombs();
        }

        return super.death();
    }

    private void spawnMiniBombs() {
        if (childGeneration <= 0) {
            return;
        }

        float centerX = body.getPosition().x;
        float centerY = body.getPosition().y;

        for (int i = 0; i < childCount; i++) {
            float spread = i - (childCount - 1) / 2f;
            Vector2 childVelocity = new Vector2(spread * 28f, 42f + Math.abs(spread) * 10f);
            Projectile child = new ProjectileBomb(
                world,
                centerX,
                centerY,
                xSize * .55f,
                ySize * .55f,
                angle,
                childVelocity,
                Math.max(1f, damage * .45f),
                .5f,
                childFriendly,
                spread >= 0,
                0
            );
            child.spriteDestroyLifeSpan = 140;
            WorldHandler.projectileListAdd.add(child);
        }
    }
}
