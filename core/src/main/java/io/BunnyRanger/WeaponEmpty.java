package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponEmpty extends WeaponBow{

    public WeaponEmpty(boolean friendly) {
        super(friendly);
        baseWeaponDelay = 999999999;

        this.bowTexture = new Texture(Gdx.files.internal("Empty.png"));
        this.bowSprite = new Sprite(bowTexture,0,0,16,32);
        this.bowSprite.setScale(1f);

    }
}
