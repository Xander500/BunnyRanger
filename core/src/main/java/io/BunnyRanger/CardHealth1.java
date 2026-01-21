package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CardHealth1 extends Card{

    int number;
    Player player;

    public CardHealth1 () {

        super();

        this.inventoryTexture = new Texture(Gdx.files.internal("HealthCard.png"));

        this.description = "Doubles maximum health.";

        this.name = "Health Card 1";

    }


    public void effect(int number, Player player) {

        this.number = number;
        this.player = player;

        this.player.maxHealth += this.player.baseMaxHealth * 2f;

    }

}
