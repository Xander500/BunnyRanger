package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class InventoryScreenStage extends Stage {

    InventoryScreen parentScreen;

    Stage inventoryStage;

    ArrayList<InventorySpotActor> actorArrayList = new ArrayList<InventorySpotActor>();

    Image image = new Image(new Texture(Gdx.files.internal("inventory.png")));

    Actor selected = null;

    Table table;

    WeaponInventorySpotActor weaponSlot1;
    WeaponInventorySpotActor weaponSlot2;
    WeaponInventorySpotActor weaponSlot3;
    WeaponInventorySpotActor weaponSlot4;

    CardInventorySpotActor cardSlot1;
    CardInventorySpotActor cardSlot2;
    CardInventorySpotActor cardSlot3;
    CardInventorySpotActor cardSlot4;

    InventoryScreenStage(InventoryScreen parentScreen) {

        this.parentScreen = parentScreen;

        this.inventoryStage = new Stage();

        this.table = new Table();

        inventoryStage.setDebugAll(true);

        inventoryStage.addActor(table);

        for(int i = 0; i < 5; i++) {

            for(int j = 0; j < 11; j++) {

                InventorySpotActor inventorySpotActorTemp = new InventorySpotActor(this,null);

                actorArrayList.add(inventorySpotActorTemp);

                inventorySpotActorTemp.setImageSize(98f,60f);

                inventorySpotActorTemp.setImagePostion(50 + (j*98f) + (j*10.2f), 25 + (i*60f) + (i*8));

                table.addActor(inventorySpotActorTemp);

            }

        }

        //starting test

        WeaponBow bow8 = new WeaponBow(true);
        WeaponPistol gun1 = new WeaponPistol(true);
        WeaponBow2 bow21 = new WeaponBow2(true);
        WeaponSword sword1 = new WeaponSword(true);

        this.addToOpenSlot(bow8);
        this.addToOpenSlot(gun1);
        this.addToOpenSlot(bow21);
        this.addToOpenSlot(sword1);


        CardHealth1 cardHealth1 = new CardHealth1();
        CardHealth1 cardHealth2 = new CardHealth1();

        this.addToOpenSlot(cardHealth1);
        this.addToOpenSlot(cardHealth2);

        CardProjectile1 cardProjectile1 = new CardProjectile1();
        CardProjectile1 cardProjectile2 = new CardProjectile1();

        this.addToOpenSlot(cardProjectile1);
        this.addToOpenSlot(cardProjectile2);

        // ending test

        //Weapon slots

        WeaponBow bow1 = new WeaponBow(true);
        WeaponBow bow2 = new WeaponBow(true);
        WeaponBow bow3 = new WeaponBow(true);
        WeaponBow bow4 = new WeaponBow(true);


        this.weaponSlot1 = new WeaponInventorySpotActor(this,null,0);
        this.weaponSlot2 = new WeaponInventorySpotActor(this,null,1);
        this.weaponSlot3 = new WeaponInventorySpotActor(this,null,2);
        this.weaponSlot4 = new WeaponInventorySpotActor(this,null,3);

        actorArrayList.add(this.weaponSlot1);
        actorArrayList.add(this.weaponSlot2);
        actorArrayList.add(this.weaponSlot3);
        actorArrayList.add(this.weaponSlot4);

        this.weaponSlot1.setImageSize(98f,60f);
        this.weaponSlot2.setImageSize(98f,60f);
        this.weaponSlot3.setImageSize(98f,60f);
        this.weaponSlot4.setImageSize(98f,60f);

        this.weaponSlot1.setImagePostion(50 + (0*98f) + (0*10.2f), 25 + (9*60f) + (9*8) + 4);
        this.weaponSlot2.setImagePostion(50 + (2*98f) + (2*10.2f), 25 + (9*60f) + (9*8) + 4);
        this.weaponSlot3.setImagePostion(50 + (4*98f) + (4*10.2f), 25 + (9*60f) + (9*8) + 4);
        this.weaponSlot4.setImagePostion(50 + (6*98f) + (6*10.2f), 25 + (9*60f) + (9*8) + 4);

        this.weaponSlot1.addSpot(bow1);
        this.weaponSlot2.addSpot(bow2);
        this.weaponSlot3.addSpot(bow3);
        this.weaponSlot4.addSpot(bow4);

        table.addActor(this.weaponSlot1);
        table.addActor(this.weaponSlot2);
        table.addActor(this.weaponSlot3);
        table.addActor(this.weaponSlot4);


        // CARD SLOT

        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();
        Card card4 = new Card();

        this.cardSlot1 = new CardInventorySpotActor(this,null,0);
        this.cardSlot2 = new CardInventorySpotActor(this,null,1);
        this.cardSlot3 = new CardInventorySpotActor(this,null,2);
        this.cardSlot4 = new CardInventorySpotActor(this,null,3);

        actorArrayList.add(this.cardSlot1);
        actorArrayList.add(this.cardSlot2);
        actorArrayList.add(this.cardSlot3);
        actorArrayList.add(this.cardSlot4);

        this.cardSlot1.setImageSize(98f,60f);
        this.cardSlot2.setImageSize(98f,60f);
        this.cardSlot3.setImageSize(98f,60f);
        this.cardSlot4.setImageSize(98f,60f);

        this.cardSlot1.setImagePostion(50 + (0*98f) + (0*10.2f), 25 + (8*60f) + (8*8) + 4);
        this.cardSlot2.setImagePostion(50 + (2*98f) + (2*10.2f), 25 + (8*60f) + (8*8) + 4);
        this.cardSlot3.setImagePostion(50 + (4*98f) + (4*10.2f), 25 + (8*60f) + (8*8) + 4);
        this.cardSlot4.setImagePostion(50 + (6*98f) + (6*10.2f), 25 + (8*60f) + (8*8) + 4);

        this.cardSlot1.addSpot(card1);
        this.cardSlot2.addSpot(card2);
        this.cardSlot3.addSpot(card3);
        this.cardSlot4.addSpot(card4);

        table.addActor(this.cardSlot1);
        table.addActor(this.cardSlot2);
        table.addActor(this.cardSlot3);
        table.addActor(this.cardSlot4);




    }

    public Stage instance() {
        return this.inventoryStage;
    }

    public Image getBackgroundImage() {
        return this.image;
    }

    public void parseHit(Actor hit,int button) {

        if (button == 1) {

            if (selected != null) {
                ((InventorySpotActor) selected).setDrawable(null);
                selected = null;
            }

            return;
        }

        if(hit == null) {
            return;
        }

        if (selected == null) {

            ((InventorySpotActor) hit).setDrawable(new TextureRegionDrawable(new Texture((Gdx.files.internal("sleected.png")))));

            selected = (InventorySpotActor) hit;

        } else if (((InventorySpotActor) selected).getSpot() == null) {

            System.out.println("adsads");

            ((InventorySpotActor) selected).setDrawable(null);
            selected = null;

            ((InventorySpotActor) hit).setDrawable(new TextureRegionDrawable(new Texture((Gdx.files.internal("sleected.png")))));
            selected = (InventorySpotActor) hit;

        } else if (((InventorySpotActor) selected).getSpot() instanceof Weapon && hit instanceof CardInventorySpotActor)  {

            return;

        } else if (((InventorySpotActor) selected).getSpot() instanceof Card && hit instanceof WeaponInventorySpotActor)  {

            return;

        } else {

            Item temp = ((InventorySpotActor) selected).getSpot();

            ((InventorySpotActor) selected).addSpot(((InventorySpotActor) hit).getSpot());

            ((InventorySpotActor) hit).addSpot(temp);

            ((InventorySpotActor) selected).setDrawable(null);
            selected = null;

            this.populatePartyWeapons();

        }


    }

    public void populatePartyWeapons() {

        if (this.weaponSlot1.getSpot() != null) {
            MainApplication.getParty().getPlayer(0).addWeapon((Weapon) this.weaponSlot1.getSpot());
        } else {
            MainApplication.getParty().getPlayer(0).addWeapon(new WeaponEmpty(true));
        }
        if (this.weaponSlot2.getSpot() != null) {
            MainApplication.getParty().getPlayer(1).addWeapon((Weapon) this.weaponSlot2.getSpot());
        }else {
            MainApplication.getParty().getPlayer(1).addWeapon(new WeaponEmpty(true));
        }
        if (this.weaponSlot3.getSpot() != null) {
            MainApplication.getParty().getPlayer(2).addWeapon((Weapon) this.weaponSlot3.getSpot());
        }else {
            MainApplication.getParty().getPlayer(2).addWeapon(new WeaponEmpty(true));
        }
        if (this.weaponSlot4.getSpot() != null) {
            MainApplication.getParty().getPlayer(3).addWeapon((Weapon) this.weaponSlot4.getSpot());
        }else {
            MainApplication.getParty().getPlayer(3).addWeapon(new WeaponEmpty(true));
        }

        this.populatePartyCards();

    }

    public void populatePartyCards() {

        MainApplication.getParty().getPlayer(0).getCurrentWeapon().resetToBase();
        MainApplication.getParty().getPlayer(1).getCurrentWeapon().resetToBase();
        MainApplication.getParty().getPlayer(2).getCurrentWeapon().resetToBase();
        MainApplication.getParty().getPlayer(3).getCurrentWeapon().resetToBase();

        if (this.cardSlot1.getSpot() != null) {
            ((Card) this.cardSlot1.getSpot()).effect(0,MainApplication.getParty().getPlayer(0));
        }
        if (this.cardSlot2.getSpot() != null) {
            ((Card) this.cardSlot2.getSpot()).effect(0,MainApplication.getParty().getPlayer(1));
        }
        if (this.cardSlot3.getSpot() != null) {
            ((Card) this.cardSlot3.getSpot()).effect(0,MainApplication.getParty().getPlayer(2));
        }
        if (this.cardSlot4.getSpot() != null) {
            ((Card) this.cardSlot4.getSpot()).effect(0,MainApplication.getParty().getPlayer(3));
        }

    }


    public void addToOpenSlot(Item item) {

        for (int i = 0; i < 60;i++) {

            if (this.table.getChild(i) instanceof WeaponInventorySpotActor) {
                return;
            }

            if (((InventorySpotActor) this.table.getChild(i)).getSpot() == null) {

                ((InventorySpotActor) this.table.getChild(i)).addSpot(item);
                return;

            }

        }

    }

    public void hydrate() {

        for (int i = 0; i < 55; i++) {

            Item item = ((InventorySpotActor) MainMenu.shopScreen.shopScreenStage.table.getChild(i)).getSpot();

            ((InventorySpotActor) this.table.getChild(i)).addSpot(item);

        }

    }

}
