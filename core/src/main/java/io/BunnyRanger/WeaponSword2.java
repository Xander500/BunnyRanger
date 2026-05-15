package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponSword2 extends WeaponSword {

    public WeaponSword2(boolean friendly) {
        super(friendly);

        this.name = "Briar Blade";

        this.bowTexture = new Texture(Gdx.files.internal("sword1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setRotation(16f);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sword1.png"));

        this.count = 2;
        this.baseCount = 2;
        this.rangeArrow = 220;
        this.baseRangeArrow = 220;
        this.damageMinArrow = 3;
        this.baseDamageMinArrow = 3;
        this.damageMaxArrow = 8;
        this.baseDamageMaxArrow = 8;
        this.weaponDelay = 45;
        this.baseWeaponDelay = 45;

        this.buyPrice = 50;
        this.sellPrice = 25;
    }
}
