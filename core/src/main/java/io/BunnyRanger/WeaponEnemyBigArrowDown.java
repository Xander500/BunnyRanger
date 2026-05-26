package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;

public class WeaponEnemyBigArrowDown extends WeaponEnemyAttack {

    private final float spawnSpread;
    private final float spawnHeight;

    public WeaponEnemyBigArrowDown() {
        super("Big Arrow Drop");

        this.spawnSpread = 80f;
        this.spawnHeight = 155f;
        this.damageMinArrow = 8;
        this.baseDamageMinArrow = 8;
        this.damageMaxArrow = 14;
        this.baseDamageMaxArrow = 14;
        this.rangeArrow = 260;
        this.baseRangeArrow = 260;
        this.weaponDelay = 140;
        this.baseWeaponDelay = 140;
    }

    @Override
    public void weaponProjectileFactory() {
        if (!targetInRange()) {
            return;
        }

        float randomOffset = (float) ((Math.random() * spawnSpread * 2f) - spawnSpread);
        float arrowX = enemyPositionX + randomOffset;
        float arrowY = enemyPositionY + spawnHeight;

        Projectile projectile = configureProjectile(new ProjectileEnemyArrowdown(
            world,
            arrowX,
            arrowY,
            rollDamage(),
            .95f,
            1.8f,
            new Vector2(0, -11f),
            .18f,
            1.75f,
            420,
            24
        ));
        this.projectileList.add(projectile);
    }
}
