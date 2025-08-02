package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class ShopScreenStage extends Stage {

    ShopScreen parentScreen;

    Stage inventoryStage;

    ArrayList<InventorySpotActor> actorArrayList = new ArrayList<InventorySpotActor>();

    Image image = new Image(new Texture(Gdx.files.internal("inventory.png")));

    TextureRegionDrawable buyGreen = new TextureRegionDrawable(new Texture((Gdx.files.internal("buyButton.png"))));
    TextureRegionDrawable buyRed = new TextureRegionDrawable(new Texture((Gdx.files.internal("buyButtonRed.png"))));
    TextureRegionDrawable buyRedGrey = new TextureRegionDrawable(new Texture((Gdx.files.internal("buyButtonGrey.png"))));

    TextureRegionDrawable sellGreen = new TextureRegionDrawable(new Texture((Gdx.files.internal("sellButton.png"))));
    TextureRegionDrawable sellRed = new TextureRegionDrawable(new Texture((Gdx.files.internal("sellButtonRed.png"))));
    TextureRegionDrawable sellRedGrey = new TextureRegionDrawable(new Texture((Gdx.files.internal("sellButtonGrey.png"))));


    Actor selected = null;

    Table table = new Table();

    ShopScreenModifyActor buyButton;
    ShopScreenModifyActor sellButton;

    ShopScreenStage(ShopScreen parentScreen) {

        this.parentScreen = parentScreen;

        this.inventoryStage = new Stage();

        this.table = new Table();

        inventoryStage.setDebugAll(true);

        inventoryStage.addActor(table);

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 11; j++) {

                InventorySpotActor inventorySpotActorTemp = new InventorySpotActor(this, null);

                actorArrayList.add(inventorySpotActorTemp);

                inventorySpotActorTemp.setImageSize(98f,60f);

                inventorySpotActorTemp.setImagePostion(50 + (j*98f) + (j*10.2f), 25 + (i*60f) + (i*8));

                table.addActor(inventorySpotActorTemp);

            }

        }

        //starting test

        //Weapon slots types

        /*
        this.shopSlot1 = new ShopInventorySpotActor(this, null, 0, 5, 1);
        this.shopSlot2 = new ShopInventorySpotActor(this, null, 1, 5, 1);
        this.shopSlot3 = new ShopInventorySpotActor(this, null, 2, 5, 1);
        this.shopSlot4 = new ShopInventorySpotActor(this, null, 3, 5, 1);

        actorArrayList.add(this.shopSlot1);
        actorArrayList.add(this.shopSlot2);
        actorArrayList.add(this.shopSlot3);
        actorArrayList.add(this.shopSlot4);

        this.shopSlot1.setImageSize(99, 54.5f);
        this.shopSlot2.setImageSize(99, 54.5f);
        this.shopSlot3.setImageSize(99, 54.5f);
        this.shopSlot4.setImageSize(99, 54.5f);

        this.shopSlot1.setImagePostion((1f + (98 + 8.5f) * 1), 3.6f + 59.8f * 9);
        this.shopSlot2.setImagePostion((1f + (98 + 8.5f) * 3), 3.6f + 59.8f * 9);
        this.shopSlot3.setImagePostion((1f + (98 + 8.5f) * 5), 3.6f + 59.8f * 9);
        this.shopSlot4.setImagePostion((1f + (98 + 8.5f) * 7), 3.6f + 59.8f * 9);

        this.shopSlot1.addSpot(bow1);
        this.shopSlot2.addSpot(bow2);
        this.shopSlot3.addSpot(bow3);
        this.shopSlot4.addSpot(bow4);

        table.addActor(this.shopSlot1);
        table.addActor(this.shopSlot2);
        table.addActor(this.shopSlot3);
        table.addActor(this.shopSlot4);
        */

        for (int i = 6; i < 10; i++) {

            for (int j = 0; j < 8; j++) {

                ShopInventorySpotActor shopSpotActorTemp = new ShopInventorySpotActor(this, null, -1, 10, 5);

                actorArrayList.add(shopSpotActorTemp);

                shopSpotActorTemp.setImageSize(99, 54.5f);

                shopSpotActorTemp.setImageSize(98f,60f);

                shopSpotActorTemp.setImagePostion(50 + (j*98f) + (j*10.2f), 25 + (i*60f) + (i*8) + 4);

                table.addActor(shopSpotActorTemp);

            }


        }

        WeaponBow bow1 = new WeaponBow(true);
        WeaponBow bow2 = new WeaponBow2(true);
        WeaponBow gun1 = new WeaponPistol(true);

        ((ShopInventorySpotActor) table.getChild(55)).addSpot(bow1);
        ((ShopInventorySpotActor) table.getChild(56)).addSpot(bow2);
        ((ShopInventorySpotActor) table.getChild(57)).addSpot(gun1);


        this.buyButton = new ShopScreenModifyActor(this, null);
        this.sellButton = new ShopScreenModifyActor(this, null);

        actorArrayList.add(buyButton);
        actorArrayList.add(buyButton);

        buyButton.setImagePostion(50 + (4*98f) + (4*10.2f), 25 + (5*60f) + (5*8) + 2);
        buyButton.setDrawable(null);
        buyButton.setImageSize(98f*2+10.2f,60f);

        sellButton.setImagePostion(50 + (6*98f) + (6*10.2f), 25 + (5*60f) + (5*8) + 2);
        sellButton.setDrawable(null);
        sellButton.setImageSize(98f*2+10.2f,60f);

        table.addActor(buyButton);
        table.addActor(sellButton);

    }

    public Stage instance() {
        return this.inventoryStage;
    }

    public Image getBackgroundImage() {
        return this.image;
    }

    public void parseHit(Actor hit, int button) {

        if (button == 1) {

            if (selected != null) {
                ((InventorySpotActor) selected).setDrawable(null);
                selected = null;
            }

            if (selected == null || ((InventorySpotActor) selected).getSpot() != null) {
                buyButton.setDrawable(buyRedGrey);
                buyButton.active = false;
                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;
            }

            return;
        }

        if (selected != null && selected instanceof ShopInventorySpotActor && !(hit instanceof ShopInventorySpotActor) && !(hit instanceof ShopScreenModifyActor)) {

            ((InventorySpotActor) selected).setDrawable(null);
            selected = null;

        }

        if (selected != null && !(selected instanceof ShopInventorySpotActor) && !(selected instanceof ShopScreenModifyActor) && hit instanceof ShopInventorySpotActor) {

            ((InventorySpotActor) selected).setDrawable(null);
            selected = null;
            System.out.println("bbb");

        }

        if (!(selected instanceof ShopInventorySpotActor) && !(hit instanceof ShopInventorySpotActor) && !(selected instanceof ShopScreenModifyActor) && !(hit instanceof ShopScreenModifyActor)) {

            if (hit == null) {
                if (selected == null || ((InventorySpotActor) selected).getSpot() == null) {
                    buyButton.setDrawable(buyRedGrey);
                    buyButton.active = false;
                    sellButton.setDrawable(sellRedGrey);
                    sellButton.active = false;
                }
                return;
            }

            if (hit instanceof ShopScreenModifyActor && selected == null) {
                if (selected == null || ((InventorySpotActor) selected).getSpot() == null) {
                    buyButton.setDrawable(buyRedGrey);
                    buyButton.active = false;
                    sellButton.setDrawable(sellRedGrey);
                    sellButton.active = false;
                }
                return;
            }

            if (selected == null) {

                ((InventorySpotActor) hit).setDrawable(new TextureRegionDrawable(new Texture((Gdx.files.internal("sleected.png")))));

                selected = (InventorySpotActor) hit;

                buyButton.setDrawable(buyRedGrey);
                buyButton.active = false;

                if (((InventorySpotActor) selected).getSpot() != null ) {

                    sellButton.setDrawable(sellGreen);
                    sellButton.active = true;

                } else {
                    sellButton.setDrawable(sellRedGrey);
                    sellButton.active = false;
                }

                return;

            } else if (((InventorySpotActor) selected).getSpot() == null) {

                System.out.println("adsads");

                ((InventorySpotActor) selected).setDrawable(null);
                selected = null;

                ((InventorySpotActor) hit).setDrawable(new TextureRegionDrawable(new Texture((Gdx.files.internal("sleected.png")))));
                selected = (InventorySpotActor) hit;

                if (((InventorySpotActor) selected).getSpot() != null) {
                    sellButton.setDrawable(sellGreen);
                    sellButton.active = true;
                    System.out.println("sellASDASDASDASDASD");
                    return;
                }

            } else {

                Item temp = ((InventorySpotActor) selected).getSpot();

                ((InventorySpotActor) selected).addSpot(((InventorySpotActor) hit).getSpot());

                ((InventorySpotActor) hit).addSpot(temp);

                ((InventorySpotActor) selected).setDrawable(null);
                selected = null;

            }

            if (selected != null) {

                //light up sell actor
                //unlight buy actor

                buyButton.setDrawable(buyRedGrey);
                buyButton.active = false;
                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;

            }
            if (selected == null || ((InventorySpotActor) selected).getSpot() == null) {
                buyButton.setDrawable(buyRedGrey);
                buyButton.active = false;
                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;
            }
            return;
        }

        // MAYBE NOT

        if (selected != null && selected instanceof ShopInventorySpotActor && hit instanceof ShopInventorySpotActor) {

            ((InventorySpotActor) selected).setDrawable(null);

            ((InventorySpotActor) hit).setDrawable(new TextureRegionDrawable(new Texture((Gdx.files.internal("sleected.png")))));

            selected = ((ShopInventorySpotActor) hit);

            if (((InventorySpotActor) selected).getSpot() != null) {

                if (MainApplication.getParty().testGold(((InventorySpotActor) selected).getSpot().getBuyPrice())) {
                    buyButton.setDrawable(buyGreen);
                    buyButton.active = true;
                } else {
                    buyButton.setDrawable(buyRed);
                    buyButton.active = false;
                }

                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;

            } else {
                buyButton.setDrawable(buyRedGrey);
                buyButton.active = false;
                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;
            }


            return;
        }

        //////

        if (selected == null) {

            ((InventorySpotActor) hit).setDrawable(new TextureRegionDrawable(new Texture((Gdx.files.internal("sleected.png")))));

            selected = (InventorySpotActor) hit;

            //light up buy actor if sufficient funds
            //light up insufficient funds if not sufficient funds
            //unlight sell actor

            if (((InventorySpotActor) selected).getSpot() != null) {

                if (MainApplication.getParty().testGold(((InventorySpotActor) selected).getSpot().getBuyPrice())) {
                    buyButton.setDrawable(buyGreen);
                    buyButton.active = true;
                } else {
                    buyButton.setDrawable(buyRed);
                    buyButton.active = false;
                }

                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;

            }

            if (selected instanceof ShopScreenModifyActor) {
                System.out.println("CRINGEHAHAHAHAHA");

                ((InventorySpotActor) selected).setDrawable(null);
                selected = null;

            }
            if (selected == null || ((InventorySpotActor) selected).getSpot() == null) {
                buyButton.setDrawable(buyRedGrey);
                buyButton.active = false;
                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;
            }
            return;

        }

        if (selected instanceof ShopInventorySpotActor && hit instanceof ShopScreenModifyActor && ((ShopInventorySpotActor) selected).getSpot() != null && buyButton.active && hit == buyButton) {

            Item temp = ((InventorySpotActor) selected).getSpot();

            MainApplication.getParty().addGold(-((ShopInventorySpotActor) selected).buyPrice);

            ((InventorySpotActor) selected).addSpot(((InventorySpotActor) hit).getSpot());

            addToOpenSlot(temp);

            ((InventorySpotActor) selected).setDrawable(null);
            selected = null;

            if (selected == null || ((InventorySpotActor) selected).getSpot() == null) {
                buyButton.setDrawable(buyRedGrey);
                buyButton.active = false;
                sellButton.setDrawable(sellRedGrey);
                sellButton.active = false;
            }

            return;

        }

        if (selected instanceof InventorySpotActor && ((InventorySpotActor) selected).getSpot() != null && hit instanceof ShopScreenModifyActor && sellButton.active && hit == sellButton) {

            Item temp = ((InventorySpotActor) selected).getSpot();

            MainApplication.getParty().addGold(((InventorySpotActor) selected).getSpot().getSellPrice());

            ((InventorySpotActor) selected).addSpot(((InventorySpotActor) hit).getSpot());

            addToOpenSlotShop(temp);

            ((InventorySpotActor) selected).setDrawable(null);

            selected = null;

        }

        if (selected == null || ((InventorySpotActor) selected).getSpot() == null) {
            buyButton.setDrawable(buyRedGrey);
            buyButton.active = false;
            sellButton.setDrawable(sellRedGrey);
            sellButton.active = false;
        }

    }

    public void hydrate() {

        for (int i = 0; i < 36; i++) {

            Item item = ((InventorySpotActor) MainMenu.inventoryScreen.inventoryScreenStage.table.getChild(i)).getSpot();

            ((InventorySpotActor) this.table.getChild(i)).addSpot(item);

        }

    }

    public void addToOpenSlot(Item item) {

        for (int i = 0; i < 36;i++) {

            if (this.table.getChild(i) instanceof WeaponInventorySpotActor) {
                return;
            }

            if (((InventorySpotActor) this.table.getChild(i)).getSpot() == null) {

                ((InventorySpotActor) this.table.getChild(i)).addSpot(item);
                return;

            }

        }

    }
//maybe change
    public void addToOpenSlotShop(Item item) {

        for (int i = 55; i < 92;i++) {

            if (this.table.getChild(i) instanceof WeaponInventorySpotActor) {
                return;
            }

            if (((InventorySpotActor) this.table.getChild(i)).getSpot() == null) {

                ((InventorySpotActor) this.table.getChild(i)).addSpot(item);
                return;

            }

        }

    }

}

