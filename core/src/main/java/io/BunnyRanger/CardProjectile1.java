package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CardProjectile1 extends Card{

    int number;
    Player player;

    public CardProjectile1 () {

        super();

        this.inventoryTexture = new Texture(Gdx.files.internal("sprites/cards/card_projectile.png"));

        this.description = "Doubles Projectiles.";

        this.name = "Projectile card 1";

    }

    public void effect(int number, Player player) {

        this.number = number;
        this.player = player;

        // based on current projectiles
        this.player.getCurrentWeapon().setProjectileCount(this.player.getCurrentWeapon().getProjectile() * 2);

    }

}
