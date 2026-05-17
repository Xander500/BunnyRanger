package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class WeaponWandRollerFire extends WeaponBow {

    public WeaponWandRollerFire(boolean friendly) {
        super(friendly);

        name = "Fire Wand";

        this.bowTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_fire_wand_01.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_fire_wand_01.png"));

        this.damageMinArrow = 0;
        this.baseDamageMinArrow = 0;
        this.damageMaxArrow = 0;
        this.baseDamageMaxArrow = 0;
        this.count = 1;
        this.baseCount = 1;
        this.rangeArrow = 170;
        this.baseRangeArrow = 170;
        this.weaponDelay = 95;
        this.baseWeaponDelay = 95;

        this.buyPrice = 75;
        this.sellPrice = 37;
    }

    @Override
    public void weaponProjectileFactory() {
        if (Math.abs(enemyPositionX - bowX) > rangeArrow) {
            return;
        }

        if (this.facingRight) {
            this.xArrow = this.bowX + entity.getFixture().getShape().getRadius() * 1.5f;
        } else {
            this.xArrow = this.bowX - entity.getFixture().getShape().getRadius() * 1.5f;
        }

        this.yArrow = this.bowY;

        Vector2 velocity = new Vector2(facingRight ? 65f : -65f, 18f);
        Projectile projectile = configureProjectile(new ProjectileFireSeed(world, xArrow, yArrow, velocity, friendly, facingRight), DamageCalculator.DamageType.MAGIC);
        this.projectileList.add(projectile);
    }
}
