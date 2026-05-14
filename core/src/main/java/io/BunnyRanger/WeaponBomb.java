package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponBomb extends WeaponBow{
    public WeaponBomb(boolean friendly) {
        super(friendly);

        name = "bomb1";

        this.bowTexture = new Texture(Gdx.files.internal("bomb1.png"));
        this.bowSprite = new Sprite(bowTexture,0,0,16,16);
        this.bowSprite.setScale(1f);
        this.bowAngle = 15;

        this.xSizeArrow = .5f;
        this.ySizeArrow = .5f;

        this.angleArrow = 0f;
        this.densityArrow = 1;

        //might break stuff
        this.disBetweenShotsX = 20;
        this.disBetweenShotsY = 0;

        this.damageMinArrow = 10;
        this.baseDamageMinArrow = 10;
        this.damageMaxArrow = 20;
        this.baseDamageMaxArrow = 20;

        this.count = 1;
        this.baseCount = 1;

        this.rangeArrow = 200;
        this.baseRangeArrow = 200;

        this.weaponDelay = 200;
        this.baseWeaponDelay = 200;

        // shop
        this.inventoryTexture = new Texture(Gdx.files.internal("bomb1.png"));
        this.buyPrice = 45;
        this.sellPrice = 22;
    }

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

        for (int i = 0; i < this.count; i++) {

            this.magnitudeArrow = getArrowMagnitude(enemyPositionX,enemyPositionY,this.xArrow,this.yArrow,enemyPositionXVel,enemyPositionYVel);

            //spread
            this.magnitudeArrow.x += i * this.disBetweenShotsX;

            this.damageCurrentArrow = (float) Math.round((Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow);

            Projectile projectile = new ProjectileBomb(world, xArrow, yArrow, xSizeArrow, ySizeArrow, angleArrow, magnitudeArrow, damageCurrentArrow, densityArrow, friendly, facingRight,1);

            this.projectileList.add(projectile);

        }

    }

}
