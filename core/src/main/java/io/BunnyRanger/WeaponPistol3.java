package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponPistol3 extends WeaponPistol {

    public WeaponPistol3(boolean friendly) {
        super(friendly);

        this.name = "Starcap Revolver";

        this.bowTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_pistol_01.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_pistol_01.png"));

        this.count = 8;
        this.baseCount = 8;
        this.rangeArrow = 85;
        this.baseRangeArrow = 85;
        this.damageMinArrow = 3;
        this.baseDamageMinArrow = 3;
        this.damageMaxArrow = 6;
        this.baseDamageMaxArrow = 6;
        this.weaponDelay = 75;
        this.baseWeaponDelay = 75;
        this.disBetweenShotsY = 10;

        this.buyPrice = 85;
        this.sellPrice = 42;
    }
}
