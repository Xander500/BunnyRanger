package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class WeaponSword extends WeaponBow {

    public WeaponSword(boolean friendly) {
        super(friendly);

        this.angleArrow = (float) 0;
        this.disBetweenShotsY = 1000000;

        this.bowTexture = new Texture(Gdx.files.internal("sword1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setRotation(16f);
        this.bowSprite.setScale(1f);

        this.inventoryTexture = new Texture(Gdx.files.internal("sword1.png"));

        this.buyPrice = 30;
        this.sellPrice = 15;

        this.name = "Sword 1";

        this.baseCount = 5;
        this.count = 1;
        this.rangeArrow = 200;
        this.baseRangeArrow = 200;
        this.weaponDelay = 30;
        this.baseWeaponDelay = 30;

        this.baseDamageMinArrow = 1;
        this.baseDamageMaxArrow = 5;

        this.densityArrow = 1;

        this.xSizeArrow *=1;
        this.ySizeArrow *=2;

        this.disBetweenShotsX = 1f;
        this.disBetweenShotsY = 1f;

    }

    public void weaponProjectileFactory() {

        if (Math.abs(enemyPositionX - bowX) > rangeArrow) {
            return;
        }

        if (this.facingRight) {
            this.xArrow = this.bowX + entity.getFixture().getShape().getRadius() * 2;
        } else {
            this.xArrow = this.bowX - entity.getFixture().getShape().getRadius() * 2;
        }

        this.yArrow = this.bowY;

        for (float i = 0; i < this.count; i++) {

            if (this.facingRight) {
                this.magnitudeArrow = new Vector2(15f,0f);
            } else {
                this.magnitudeArrow = new Vector2(-15f,0f);
            }

            //this.magnitudeArrow.x += i * this.disBetweenShotsX;
            this.magnitudeArrow.y += i * this.disBetweenShotsY;

            Random random = new Random();
            this.damageCurrentArrow = (float) (Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow;

            ProjectileSlash projectile = new ProjectileSlash(world, xArrow, yArrow, xSizeArrow, ySizeArrow, angleArrow, magnitudeArrow, damageCurrentArrow, densityArrow, friendly, facingRight, 1);

            this.projectileList.add(projectile);

        }

    }

    public void drawWeapon() { // maybe
        super.drawWeapon();
    }

}
