package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProjectileFirePatch extends Projectile {

    private final float burnDamage;
    private final int burnInterval;
    private final HashMap<Damageable, Integer> touchingTargets;

    public ProjectileFirePatch(WorldHandler world, float x, float y, float damage, boolean friendly) {
        super(world, x, y, .7f, .35f, 0, new Vector2(0, 0), 0, .1f, friendly, true, 0);

        this.burnDamage = damage;
        this.burnInterval = 15;
        this.touchingTargets = new HashMap<>();

        this.texture = new Texture(Gdx.files.internal("fireball1.png"));
        this.projectileSprite = new Sprite(texture);
        this.projectileSprite.setScale(.7f);
        this.body.setGravityScale(0f);
        this.body.setLinearVelocity(0, 0);
        this.fixture.setSensor(true);
        this.spriteDestroyLifeSpan = 90;
        this.spriteDestroyDelay = 12;
    }

    @Override
    public void executeBegin(Entity secondEntity) {
        if (secondEntity instanceof Damageable && !((Damageable) secondEntity).checkIfDead()) {
            touchingTargets.put((Damageable) secondEntity, 0);
        }
    }

    @Override
    public void executeEnd(Entity secondEntity) {
        if (secondEntity instanceof Damageable) {
            touchingTargets.remove((Damageable) secondEntity);
        }
    }

    @Override
    public Sprite getProjectileSprite() {
        if (!remove) {
            burnTouchingTargets();
        }

        if (this.spriteDestroyLifeSpan < 0) {
            remove = true;
        } else {
            spriteDestroyLifeSpan--;
        }

        if (!remove) {
            this.projectileSprite.setPosition(this.body.getPosition().x - this.projectileSprite.getWidth() / 2, this.body.getPosition().y - this.projectileSprite.getHeight() / 2);
        } else if (this.spriteDestroyDelay > 0) {
            this.spriteDestroyDelay--;
            this.projectileSprite.setAlpha(Math.max(0, this.spriteDestroyDelay / 12f));
        } else {
            WorldHandler.getWorldHandler().addDestroyBody(this.getBody());
            WorldHandler.projectileListRemove.add(this);
            this.remove = true;
        }

        return this.projectileSprite;
    }

    private void burnTouchingTargets() {
        Iterator<Map.Entry<Damageable, Integer>> iterator = touchingTargets.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Damageable, Integer> targetEntry = iterator.next();
            Damageable target = targetEntry.getKey();
            int cooldown = targetEntry.getValue();

            if (target.checkIfDead()) {
                iterator.remove();
                continue;
            }

            if (cooldown <= 0) {
                target.takeDamage(burnDamage);
                targetEntry.setValue(burnInterval);
            } else {
                targetEntry.setValue(cooldown - 1);
            }
        }
    }
}
