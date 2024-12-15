package io.BunnyRanger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InventorySpotActor extends Image {

    Stage parentStage;
    Table parentTable;
    Item mySpot = null;

    Texture texture;

    //cool

    boolean selected;

    int buyPrice;
    int sellPrice;

    InventorySpotActor(Stage parentStage,Table parentTable) {

        this.parentStage = parentStage;
        this.parentTable = parentTable;
        this.mySpot = null;

        this.buyPrice = 1;

        this.sellPrice = -9;

        //this.setDrawable(new TextureRegionDrawable(new Texture(Gdx.files.internal("ArrowProjectile.png"))));

        //TextureRegionDrawable test = new TextureRegionDrawable(texture);

        //this.image.setDrawable(test);

    }

    public void draw(Batch batch, float alpha) {

        if(this.mySpot != null) {

            TextureRegionDrawable temp = (TextureRegionDrawable) this.getDrawable();

            try {
                this.setDrawable(new TextureRegionDrawable(this.mySpot.getInventoryTexture()));
            } catch(Exception e) {
                System.out.println("wewo");
            }
            super.draw(batch,alpha);

            this.setDrawable(temp);

        }

        super.draw(batch,alpha);

    }

    public Image getImage() {
        return this;
    }

    public void setImagePostion(float x, float y) {
        this.setPosition(x,y);
    }

    public void setImageSize(float x, float y) {
        this.setSize(x,y);
    }

    public void addSpot(Item spot) {
        this.mySpot = spot;
        updatePrice();
    }

    public void updatePrice() {

        if (this.mySpot == null) {

            this.buyPrice = 999999999;

            this.sellPrice = -9;

            return;

        }

        this.buyPrice = mySpot.getBuyPrice();
        this.sellPrice = mySpot.getSellPrice();

    }

    public Item getSpot() {
        return this.mySpot;
    }

    public void changeImage(Texture texture) {

        float xSize = this.getHeight();
        float ySize = this.getWidth();
        float x = this.getOriginX();
        float y = this.getOriginY();

        this.setDrawable(new TextureRegionDrawable(texture));
        this.setSize(xSize,ySize);
        this.setPosition(x,y);

    }



}
