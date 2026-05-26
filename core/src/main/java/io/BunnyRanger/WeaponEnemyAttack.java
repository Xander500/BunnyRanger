package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class WeaponEnemyAttack extends WeaponBow {

    public WeaponEnemyAttack(String name) {
        super(false);

        this.name = name;
        this.bowTexture = new Texture(Gdx.files.internal("sprites/ui/ui_empty.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(1f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/ui/ui_empty.png"));
        this.buyPrice = 0;
        this.sellPrice = 0;
    }

    protected float rollDamage() {
        return (float) Math.round((Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow);
    }

    protected boolean targetInRange() {
        return Math.abs(enemyPositionX - bowX) <= rangeArrow;
    }
}
