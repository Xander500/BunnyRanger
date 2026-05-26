package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;

public class WeaponEnemyPelletShot extends WeaponEnemyAttack {

    public WeaponEnemyPelletShot() {
        super("Pellet Shot");

        this.count = 5;
        this.baseCount = 5;
        this.damageMinArrow = 2;
        this.baseDamageMinArrow = 2;
        this.damageMaxArrow = 4;
        this.baseDamageMaxArrow = 4;
        this.rangeArrow = 120;
        this.baseRangeArrow = 120;
        this.weaponDelay = 85;
        this.baseWeaponDelay = 85;
    }

    @Override
    public void weaponProjectileFactory() {
        if (!targetInRange()) {
            return;
        }

        boolean attackRight = enemyPositionX > bowX;
        float pelletX = bowX + (attackRight ? 14f : -14f);
        float pelletY = bowY + 2f;
        float horizontalSpeed = attackRight ? 95f : -95f;
        float center = (count - 1) / 2f;

        for (int i = 0; i < count; i++) {
            float spread = i - center;
            Vector2 velocity = new Vector2(horizontalSpeed, spread * 42f);
            Projectile projectile = configureProjectile(new ProjectileEnemyPellet(world, pelletX, pelletY, velocity, rollDamage(), attackRight));
            this.projectileList.add(projectile);
        }
    }
}
