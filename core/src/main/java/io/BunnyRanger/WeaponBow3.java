package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponBow3 extends WeaponBow {

    public WeaponBow3(boolean friendly) {
        super(friendly);

        name = "Moonbranch Bow";

        this.bowTexture = new Texture(Gdx.files.internal("bow1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("bow1.png"));

        this.count = 2;
        this.baseCount = 2;
        this.rangeArrow = 260;
        this.baseRangeArrow = 260;
        this.damageMinArrow = 4;
        this.baseDamageMinArrow = 4;
        this.damageMaxArrow = 7;
        this.baseDamageMaxArrow = 7;
        this.weaponDelay = 80;
        this.baseWeaponDelay = 80;

        this.buyPrice = 45;
        this.sellPrice = 22;
    }
}
