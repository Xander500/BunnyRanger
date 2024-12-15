package io.BunnyRanger;

import com.badlogic.gdx.graphics.Texture;

public abstract interface Item {

    public abstract Texture getTexture();

    public abstract Texture getInventoryTexture();

    public abstract int getBuyPrice();

    public abstract int getSellPrice();

    public abstract String getName();

}
