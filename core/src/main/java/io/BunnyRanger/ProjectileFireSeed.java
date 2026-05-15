package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class ProjectileFireSeed extends Projectile {

    private int spawnCounter;
    private final boolean friendlyFire;

    public ProjectileFireSeed(WorldHandler world, float x, float y, Vector2 magnitude, boolean friendly, boolean facingRight) {
        super(world, x, y, .45f, .45f, 0, magnitude, 0, .7f, friendly, facingRight, 0);

        this.friendlyFire = friendly;
        this.texture = new Texture(Gdx.files.internal("fireball1.png"));
        this.projectileSprite = new Sprite(texture);
        this.projectileSprite.setScale(.65f);
        this.spriteDestroyLifeSpan = 150;
        this.spriteDestroyDelay = 10;
        this.body.setGravityScale(1f);
        this.body.setAngularVelocity(facingRight ? -8f : 8f);

        for (Fixture fixture : body.getFixtureList()) {
            Filter filter = fixture.getFilterData();
            filter.categoryBits = friendly ? (short) 0x0010 : (short) 0x0020;
            filter.maskBits = 0x0001;
            fixture.setFilterData(filter);
        }
    }

    @Override
    public void executeBegin(Entity secondEntity) {
        // The seed rolls on floor tiles and does no direct damage.
    }

    @Override
    public Sprite getProjectileSprite() {
        if (!remove && spawnCounter % 12 == 0) {
            WorldHandler.projectileListAdd.add(new ProjectileFirePatch(world, body.getPosition().x, body.getPosition().y, 2f, friendlyFire));
        }
        spawnCounter++;

        return super.getProjectileSprite();
    }
}
