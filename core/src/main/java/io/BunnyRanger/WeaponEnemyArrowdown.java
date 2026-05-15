package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponEnemyArrowdown extends WeaponBow {

    private final float spawnSpread;
    private final float spawnHeight;

    public WeaponEnemyArrowdown(boolean friendly) {
        super(friendly);

        this.name = "Egg Drop";
        this.spawnSpread = 42f;
        this.spawnHeight = 120f;

        this.bowTexture = new Texture(Gdx.files.internal("empty.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(.7f);
        this.inventoryTexture = new Texture(Gdx.files.internal("empty.png"));

        this.damageMinArrow = 4;
        this.baseDamageMinArrow = 4;
        this.damageMaxArrow = 8;
        this.baseDamageMaxArrow = 8;
        this.rangeArrow = 20;
        this.baseRangeArrow = 20;
        this.weaponDelay = 95;
        this.baseWeaponDelay = 95;

        this.buyPrice = 0;
        this.sellPrice = 0;
    }

    @Override
    public void weaponProjectileFactory() {
        if (Math.abs(enemyPositionX - bowX) > rangeArrow) {
            return;
        }

        float randomOffset = (float) ((Math.random() * spawnSpread * 2f) - spawnSpread);
        float eggX = enemyPositionX + randomOffset;
        float eggY = Math.max(bowY, enemyPositionY + spawnHeight);

        this.damageCurrentArrow = (float) Math.round((Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow);

        Projectile projectile = new ProjectileEnemyArrowdown(world, eggX, eggY, damageCurrentArrow);
        this.projectileList.add(projectile);
    }
}
