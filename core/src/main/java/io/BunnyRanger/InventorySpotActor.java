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

        //this.setDrawable(new TextureRegionDrawable(new Texture(Gdx.files.internal("sprites/projectiles/projectile_arrow.png"))));

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

    public void onHit(InventoryScreenStage stage) {
        stage.clearUpgradeSelection();
        if (handleEmptySelectionCases(stage)) return;

        InventorySpotActor selected = stage.getSelected();
        Item selectedItem = selected.getSpot();
        Item hitItem = this.getSpot();

        // Basic slots accept anything — no type restrictions
        // But still block cross-type swaps if the slots themselves are typed
        if (selectedItem instanceof Card && this instanceof ActorInventory) return;
        if (selectedItem instanceof Weapon && this instanceof ActorInventoryCard) return;
        if (hitItem instanceof Weapon && selected instanceof ActorInventoryCard) return;
        if (hitItem instanceof Card && selected instanceof ActorInventory) return;

        swapWith(selected, stage);
    }

    void selectAsMain(InventoryScreenStage stage) {
        setDrawable(stage.getSelectedIcon());
        stage.setSelected(this);
    }

    void swapWith(InventorySpotActor other, InventoryScreenStage stage) {
        Item temp = other.getSpot();
        other.addSpot(this.getSpot());
        this.addSpot(temp);

        other.setDrawable(null);
        stage.clearSelected();
        stage.populatePartyWeapons();
    }

    protected boolean handleEmptySelectionCases(InventoryScreenStage stage) {
        InventorySpotActor selected = stage.getSelected();

        if (selected == null) {
            selectAsMain(stage);
            return true;
        }

        if (selected.getSpot() == null) {
            selected.setDrawable(null);
            selectAsMain(stage);
            return true;
        }

        return false;
    }

}
