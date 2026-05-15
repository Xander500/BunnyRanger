package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponPistol2 extends WeaponPistol {

    public WeaponPistol2(boolean friendly) {
        super(friendly);

        this.name = "Quickdraw Pistol";

        this.bowTexture = new Texture(Gdx.files.internal("gun1.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("gun1.png"));

        this.count = 6;
        this.baseCount = 6;
        this.rangeArrow = 70;
        this.baseRangeArrow = 70;
        this.damageMinArrow = 2;
        this.baseDamageMinArrow = 2;
        this.damageMaxArrow = 4;
        this.baseDamageMaxArrow = 4;
        this.weaponDelay = 95;
        this.baseWeaponDelay = 95;

        this.buyPrice = 55;
        this.sellPrice = 27;
    }
}
