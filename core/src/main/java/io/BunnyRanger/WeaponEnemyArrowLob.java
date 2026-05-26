package io.BunnyRanger;

public class WeaponEnemyArrowLob extends WeaponEnemyAttack {

    public WeaponEnemyArrowLob() {
        super("Arrow Lob");

        this.xSizeArrow = .5f;
        this.ySizeArrow = .25f;
        this.damageMinArrow = 4;
        this.baseDamageMinArrow = 4;
        this.damageMaxArrow = 8;
        this.baseDamageMaxArrow = 8;
        this.rangeArrow = 230;
        this.baseRangeArrow = 230;
        this.weaponDelay = 95;
        this.baseWeaponDelay = 95;
    }

    @Override
    public void weaponProjectileFactory() {
        if (!targetInRange()) {
            return;
        }

        this.xArrow = bowX + (facingRight ? 12f : -12f);
        this.yArrow = bowY + 4f;
        this.magnitudeArrow = getArrowMagnitude(enemyPositionX, enemyPositionY, xArrow, yArrow, enemyPositionXVel, enemyPositionYVel);

        Projectile projectile = configureProjectile(new ProjectileArrow(world, xArrow, yArrow, xSizeArrow, ySizeArrow, angleArrow, magnitudeArrow, rollDamage(), densityArrow, false, facingRight, 0));
        this.projectileList.add(projectile);
    }
}
