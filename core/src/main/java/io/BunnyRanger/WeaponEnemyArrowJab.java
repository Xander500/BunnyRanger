package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;

public class WeaponEnemyArrowJab extends WeaponEnemyAttack {

    public WeaponEnemyArrowJab() {
        super("Arrow Jab");

        this.damageMinArrow = 3;
        this.baseDamageMinArrow = 3;
        this.damageMaxArrow = 6;
        this.baseDamageMaxArrow = 6;
        this.rangeArrow = 48;
        this.baseRangeArrow = 48;
        this.weaponDelay = 42;
        this.baseWeaponDelay = 42;
    }

    @Override
    public void weaponProjectileFactory() {
        if (!targetInRange()) {
            return;
        }

        boolean attackRight = enemyPositionX > bowX;
        float jabX = bowX + (attackRight ? 12f : -12f);
        float jabY = bowY;
        Vector2 velocity = new Vector2(attackRight ? 20f : -20f, 0f);

        Projectile projectile = configureProjectile(new ProjectileEnemyArrowJab(world, jabX, jabY, velocity, rollDamage(), attackRight));
        this.projectileList.add(projectile);
    }
}
