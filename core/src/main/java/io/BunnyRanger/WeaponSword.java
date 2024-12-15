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
        this.disBetweenShotsY = 15;

        this.bowTexture = new Texture(Gdx.files.internal("sword1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 32);
        this.bowSprite.setRotation(180f);
        this.bowSprite.setScale(.08f);

        this.inventoryTexture = new Texture(Gdx.files.internal("inventorySword1.png"));

        this.buyPrice = 30;
        this.sellPrice = 15;

        this.name = "Sword 1";

        this.baseCount = 1;
        this.count = 1;
        this.rangeArrow = 30;
        this.baseRangeArrow = 20;
        this.weaponDelay = 30;
        this.baseWeaponDelay = 30;

        this.baseDamageMinArrow = 1;
        this.baseDamageMaxArrow = 3;

        this.densityArrow = 1;

        this.xSizeArrow *=1;
        this.ySizeArrow *=2;

        this.disBetweenShotsX = 1f;
        this.disBetweenShotsY = 1f;

    }

    public Vector2 getArrowMagnitude(float EnemyPositionX, float EnemyPositionY) {

        //the worst algorithm, feel free to fine tune it james

        if (this.bowX - EnemyPositionX >= 0) {
            return new Vector2(-5, 7.5f);
        } else {
            return new Vector2(5, 7.5f);
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

        for (float i = 0; i < this.count; i++) {

            this.magnitudeArrow = new Vector2(4f,0f);
            //this.magnitudeArrow.x += i * this.disBetweenShotsX;
            this.magnitudeArrow.y += i * this.disBetweenShotsY;

            if (this.facingRight) {
                this.magnitudeArrow = new Vector2(4f,0f);
            } else {
                this.magnitudeArrow = new Vector2(-4f,0f);
            }

            Random random = new Random();
            this.damageCurrentArrow = (float) (Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow;

            SlashProjectile projectile;

            projectile = new SlashProjectile(world, xArrow, yArrow, xSizeArrow, ySizeArrow, angleArrow, magnitudeArrow, damageCurrentArrow, densityArrow, friendly, facingRight);

            this.projectileList.add(projectile);

        }

    }

    public void drawWeapon() { // maybe
        super.drawWeapon();
        if (friendly) {
            this.bowSprite.setRotation(270);
        } else {
            this.bowSprite.setRotation(90);
        }
    }

}
