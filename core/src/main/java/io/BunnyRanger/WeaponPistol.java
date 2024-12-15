package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class WeaponPistol extends WeaponBow {

    public void drawWeapon() {
        super.drawWeapon();
        if (friendly) {
            this.bowSprite.setRotation(270);
        } else {
            this.bowSprite.setRotation(90);
        }
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

        for (float i = 0 - this.count / 2f; i < this.count / 2f; i++) {

            this.magnitudeArrow = getArrowMagnitude(enemyPositionX, enemyPositionY);
            this.magnitudeArrow.x += i * this.disBetweenShotsX;
            this.magnitudeArrow.y += i * this.disBetweenShotsY;

            Random random = new Random();
            this.damageCurrentArrow = (float) (Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow;

            MusketBallProjectile projectile;

            projectile = new MusketBallProjectile(world, xArrow, yArrow, xSizeArrow, ySizeArrow, angleArrow, magnitudeArrow, damageCurrentArrow, densityArrow, friendly, facingRight);

            this.projectileList.add(projectile);

        }

    }

    public WeaponPistol(Boolean friendly) {
        super(friendly);
        this.angleArrow = (float) 0;
        this.disBetweenShotsY = 15;

        this.bowTexture = new Texture(Gdx.files.internal("gun1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 32);
        this.bowSprite.flip(false, false);
        this.bowSprite.setRotation(90);
        this.bowSprite.setScale(.08f);

        this.inventoryTexture = new Texture(Gdx.files.internal("GunInventorySprite.png"));

        this.buyPrice = 30;
        this.sellPrice = 15;

        this.name = "Gun 1";

        this.count = 5;
        this.rangeArrow = 50;
        this.weaponDelay = 120;

        this.densityArrow = 1;

    }

    public Vector2 getArrowMagnitude(float EnemyPositionX, float EnemyPositionY) {

        //the worst algorithm, feel free to fine tune it james

        if (this.bowX - EnemyPositionX >= 0) {
            return new Vector2(-250, 25);
        } else {
            return new Vector2(250, 25);
        }
    }

}
