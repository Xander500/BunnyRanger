package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponSword3 extends WeaponSword {

    public WeaponSword3(boolean friendly) {
        super(friendly);

        this.name = "Carrotsteel Saber";

        this.bowTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_sword_01.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setRotation(16f);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/weapons/weapon_sword_01.png"));

        this.count = 3;
        this.baseCount = 3;
        this.rangeArrow = 240;
        this.baseRangeArrow = 240;
        this.damageMinArrow = 5;
        this.baseDamageMinArrow = 5;
        this.damageMaxArrow = 12;
        this.baseDamageMaxArrow = 12;
        this.weaponDelay = 35;
        this.baseWeaponDelay = 35;

        this.buyPrice = 80;
        this.sellPrice = 40;
    }
}
