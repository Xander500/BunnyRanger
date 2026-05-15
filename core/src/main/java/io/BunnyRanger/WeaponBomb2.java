package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponBomb2 extends WeaponBomb {

    public WeaponBomb2(boolean friendly) {
        super(friendly);

        name = "Burrow Bomb";

        this.bowTexture = new Texture(Gdx.files.internal("bomb1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("bomb1.png"));

        this.count = 1;
        this.baseCount = 1;
        this.rangeArrow = 230;
        this.baseRangeArrow = 230;
        this.damageMinArrow = 16;
        this.baseDamageMinArrow = 16;
        this.damageMaxArrow = 28;
        this.baseDamageMaxArrow = 28;
        this.weaponDelay = 170;
        this.baseWeaponDelay = 170;

        this.buyPrice = 70;
        this.sellPrice = 35;
    }
}
