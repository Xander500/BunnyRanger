package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponClusterBomb extends WeaponBomb {

    public WeaponClusterBomb(boolean friendly) {
        super(friendly);

        name = "Burrow Cluster";

        this.bowTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_bomb_01.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_bomb_01.png"));

        this.damageMinArrow = 8;
        this.baseDamageMinArrow = 8;
        this.damageMaxArrow = 14;
        this.baseDamageMaxArrow = 14;
        this.weaponDelay = 185;
        this.baseWeaponDelay = 185;
        this.rangeArrow = 240;
        this.baseRangeArrow = 240;

        this.buyPrice = 95;
        this.sellPrice = 47;
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

        this.magnitudeArrow = getArrowMagnitude(enemyPositionX, enemyPositionY, this.xArrow, this.yArrow, enemyPositionXVel, enemyPositionYVel);
        this.damageCurrentArrow = (float) Math.round((Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow);

        Projectile projectile = configureProjectile(new ProjectileClusterBomb(world, xArrow, yArrow, xSizeArrow, ySizeArrow, angleArrow, magnitudeArrow, damageCurrentArrow, densityArrow, friendly, facingRight, 1));
        this.projectileList.add(projectile);
    }
}
