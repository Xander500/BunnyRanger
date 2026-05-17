package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponBomb3 extends WeaponBomb {

    public WeaponBomb3(boolean friendly) {
        super(friendly);

        name = "Warren Breaker";

        this.bowTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_bomb_01.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_bomb_01.png"));

        this.count = 2;
        this.baseCount = 2;
        this.rangeArrow = 250;
        this.baseRangeArrow = 250;
        this.damageMinArrow = 22;
        this.baseDamageMinArrow = 22;
        this.damageMaxArrow = 38;
        this.baseDamageMaxArrow = 38;
        this.weaponDelay = 150;
        this.baseWeaponDelay = 150;
        this.disBetweenShotsX = 12;

        this.buyPrice = 110;
        this.sellPrice = 55;
    }
}
