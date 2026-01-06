package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class WeaponBow2 extends WeaponBow {

    public WeaponBow2(Boolean friendly) {

        super(friendly);
        this.baseCount = 1;
        this.angleArrow = (float) 0;
        this.disBetweenShotsX = 10;

        this.bowTexture = new Texture(Gdx.files.internal("bow1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);

        this.weaponDelay = 100;

        this.damageMaxArrow = 4;
        this.damageMinArrow = 2;

        this.buyPrice = 20;
        this.sellPrice = 10;

        name = "WoodBow+";

        this.inventoryTexture = new Texture(Gdx.files.internal("bow1.png"));

    }

    public Vector2 getArrowMagnitude(float EnemyPositionX, float EnemyPositionY) {
        return super.getArrowMagnitude(EnemyPositionX,EnemyPositionY,bowX,bowY,enemyPositionXVel,enemyPositionYVel);
    }

}

