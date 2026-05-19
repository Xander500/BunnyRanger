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
        this.spawnHeight = 10f;

        this.bowTexture = new Texture(Gdx.files.internal("sprites/ui/ui_empty.png"));
        this.bowSprite = new Sprite(bowTexture, 0, 0, 16, 16);
        this.bowSprite.setScale(.7f);
        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/ui/ui_empty.png"));

        this.damageMinArrow = 4;
        this.baseDamageMinArrow = 4;
        this.damageMaxArrow = 8;
        this.baseDamageMaxArrow = 8;
        this.rangeArrow = 50;
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
        float eggY = Math.max(bowY, enemyPositionY);

        this.damageCurrentArrow = (float) Math.round((Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow);

        Projectile projectile = configureProjectile(new ProjectileEnemyArrowdown(world, eggX, eggY, damageCurrentArrow));
        this.projectileList.add(projectile);
    }
}
