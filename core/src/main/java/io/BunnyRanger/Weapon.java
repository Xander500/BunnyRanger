package io.BunnyRanger;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public interface Weapon {

    abstract Weapon createWeapon();

    abstract public ArrayList<Projectile> getProjectiles();

    abstract public void weaponProjectileFactory();

    abstract void setEntity(Entity player);

    abstract Sprite getSprite();

    abstract void drawWeapon();

    abstract float getDelay(int ratio);

    abstract public void getClosestTarget();

    //display inventory selected

    abstract String getName();

    abstract int getDamageMax();

    abstract int getDamageMin();

    abstract int getCount();

    public void setProjectileCount(int num);

    abstract int getRange();

    abstract int getBuy();

    abstract int getBaseProjectile();
    abstract int getProjectile();

    abstract float getBaseRange();

    abstract float getBaseMaxDamage();

    abstract float getBaseMinDamage();

    abstract float getBaseDelay();

    abstract void resetToBase();

    abstract boolean getDirection();

    abstract void setDamageColors(Color regularColor, Color critColor, Color magicColor, Color magicCritColor);

    abstract Color getRegularDamageColor();

    abstract Color getCritDamageColor();

    abstract Color getMagicDamageColor();

    abstract Color getMagicCritDamageColor();

}
