package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Card implements Item{

    public Texture inventoryTexture;
    public String description;
    public String name;

    public Card() {

        this.inventoryTexture = new Texture(Gdx.files.internal("BlankCard.png"));
        this.description = "Draw two cards.";
        this.name = "BLANK CARD";

    }

    public Texture getTexture() {
        return this.inventoryTexture;
    }

    public Texture getInventoryTexture() {
        return this.inventoryTexture;
    }

    public int getBuyPrice() {
        return 22;
    }

    public int getSellPrice() {
        return 11;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void effect(int number, Player player) {

        //subclass does stuff

    }

}
