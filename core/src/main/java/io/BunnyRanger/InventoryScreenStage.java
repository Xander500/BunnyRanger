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

    ScreenInventory parentScreen;

    Stage inventoryStage;

    ArrayList<InventorySpotActor> actorArrayList = new ArrayList<InventorySpotActor>();

    Image image = new Image(new Texture(Gdx.files.internal("menuInventory.png")));

    Actor selected = null;
    Actor selectedPlayer = null;
    ActorInventoryUpgrade selectedUpgrade;


    Table table;

    ActorInventory weaponSlot1;
    ActorInventory weaponSlot2;
    ActorInventory weaponSlot3;
    ActorInventory weaponSlot4;

    ActorInventoryCard cardSlot1;
    ActorInventoryCard cardSlot2;
    ActorInventoryCard cardSlot3;
    ActorInventoryCard cardSlot4;
    ActorInventoryCard cardSlot5;
    ActorInventoryCard cardSlot6;
    ActorInventoryCard cardSlot7;
    ActorInventoryCard cardSlot8;

    TextureRegionDrawable selectedIcon = new TextureRegionDrawable(new Texture((Gdx.files.internal("selected.png"))));

    InventoryScreenStage(ScreenInventory parentScreen) {

        this.parentScreen = parentScreen;

        this.inventoryStage = new Stage();

        this.table = new Table();

        inventoryStage.setDebugAll(true);
        inventoryStage.setDebugAll(false);

        inventoryStage.addActor(table);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 12; j++) {

                InventorySpotActor inventorySpotActorTemp = new InventorySpotActor(this,null);
                actorArrayList.add(inventorySpotActorTemp);
                inventorySpotActorTemp.setImageSize(15f*4,15f*4);
                inventorySpotActorTemp.setImagePostion(68 + (j*64f), 80 + (i*64f));
                table.addActor(inventorySpotActorTemp);

            }
        }

        //starting test

        WeaponBow bow8 = new WeaponBow(true);
        WeaponBow2 bow21 = new WeaponBow2(true);
        WeaponPistol gun1 = new WeaponPistol(true);
        WeaponSword sword1 = new WeaponSword(true);
        WeaponBomb bomb1 = new WeaponBomb(true);
        WeaponClusterBomb clusterBomb = new WeaponClusterBomb(true);
        WeaponWandRollerFire fireWand = new WeaponWandRollerFire(true);

        this.addToOpenSlot(bow8);
        this.addToOpenSlot(gun1);
        this.addToOpenSlot(bow21);
        this.addToOpenSlot(sword1);
        this.addToOpenSlot(bomb1);
        this.addToOpenSlot(clusterBomb);
        this.addToOpenSlot(fireWand);

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


        this.weaponSlot1 = new ActorInventory(this,null,0);
        this.weaponSlot2 = new ActorInventory(this,null,1);
        this.weaponSlot3 = new ActorInventory(this,null,2);
        this.weaponSlot4 = new ActorInventory(this,null,3);

        actorArrayList.add(this.weaponSlot1);
        actorArrayList.add(this.weaponSlot2);
        actorArrayList.add(this.weaponSlot3);
        actorArrayList.add(this.weaponSlot4);

        this.weaponSlot1.setImageSize(64,64f);
        this.weaponSlot2.setImageSize(64f,64f);
        this.weaponSlot3.setImageSize(64f,64f);
        this.weaponSlot4.setImageSize(64f,64f);

        this.weaponSlot1.setImagePostion(68 + (0*64f), 80 + (8*64f));
        this.weaponSlot2.setImagePostion(68 + (2*64f), 80 + (8*64f));
        this.weaponSlot3.setImagePostion(68 + (4*64f), 80 + (8*64f));
        this.weaponSlot4.setImagePostion(68 + (6*64f), 80 + (8*64f));

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

        this.cardSlot1 = new ActorInventoryCard(this,null,0);
        this.cardSlot2 = new ActorInventoryCard(this,null,1);
        this.cardSlot3 = new ActorInventoryCard(this,null,2);
        this.cardSlot4 = new ActorInventoryCard(this,null,3);

        actorArrayList.add(this.cardSlot1);
        actorArrayList.add(this.cardSlot2);
        actorArrayList.add(this.cardSlot3);
        actorArrayList.add(this.cardSlot4);

        this.cardSlot1.setImageSize(64f,64f);
        this.cardSlot2.setImageSize(64f,64f);
        this.cardSlot3.setImageSize(64f,64f);
        this.cardSlot4.setImageSize(64f,64f);

        this.cardSlot1.setImagePostion(68 + (0*64f), 80 + (7*64f));
        this.cardSlot2.setImagePostion(68 + (2*64f), 80 + (7*64f));
        this.cardSlot3.setImagePostion(68 + (4*64f), 80 + (7*64f));
        this.cardSlot4.setImagePostion(68 + (6*64f), 80 + (7*64f));

        this.cardSlot1.addSpot(card1);
        this.cardSlot2.addSpot(card2);
        this.cardSlot3.addSpot(card3);
        this.cardSlot4.addSpot(card4);

        table.addActor(this.cardSlot1);
        table.addActor(this.cardSlot2);
        table.addActor(this.cardSlot3);
        table.addActor(this.cardSlot4);

        // second card slot
        this.cardSlot5 = new ActorInventoryCard(this,null,0);
        this.cardSlot6 = new ActorInventoryCard(this,null,1);
        this.cardSlot7 = new ActorInventoryCard(this,null,2);
        this.cardSlot8 = new ActorInventoryCard(this,null,3);

        actorArrayList.add(this.cardSlot5);
        actorArrayList.add(this.cardSlot6);
        actorArrayList.add(this.cardSlot7);
        actorArrayList.add(this.cardSlot8);

        this.cardSlot5.setImageSize(64f,64f);
        this.cardSlot6.setImageSize(64f,64f);
        this.cardSlot7.setImageSize(64f,64f);
        this.cardSlot8.setImageSize(64f,64f);

        this.cardSlot5.setImagePostion(68 + (0*64f), 80 + (6*64f));
        this.cardSlot6.setImagePostion(68 + (2*64f), 80 + (6*64f));
        this.cardSlot7.setImagePostion(68 + (4*64f), 80 + (6*64f));
        this.cardSlot8.setImagePostion(68 + (6*64f), 80 + (6*64f));

        table.addActor(this.cardSlot5);
        table.addActor(this.cardSlot6);
        table.addActor(this.cardSlot7);
        table.addActor(this.cardSlot8);


        //LEVEL SYSTEM
         for (int j = 8; j < 12; j++) {
            InventorySpotActor inventorySpotActorTemp = new ActorInventoryPlayer(this,null, j - 8);
            actorArrayList.add(inventorySpotActorTemp);
            inventorySpotActorTemp.setImageSize(15f*4,15f*4);
            inventorySpotActorTemp.setImagePostion(75 + (j*64f), 125 + (5*64f));
            table.addActor(inventorySpotActorTemp);
         }
        int upgrades = 0;
        for(int i = 3; i < 5; i++) {
            for(int j = 8; j < 12; j++) {

                InventorySpotActor inventorySpotActorTemp = new ActorInventoryUpgrade(this,null, upgrades);
                actorArrayList.add(inventorySpotActorTemp);
                inventorySpotActorTemp.setImageSize(15f*4,15f*4);
                inventorySpotActorTemp.setImagePostion(75 + (j*64f), 125 + (i*64f));
                table.addActor(inventorySpotActorTemp);
                upgrades++;

            }
        }


    }

    public Stage instance() {
        return this.inventoryStage;
    }

    public Image getBackgroundImage() {
        return this.image;
    }

    public void parseHit(Actor hit, int button) {
        if (button == 1) {
            clearSelections();
            return;
        }
        if (hit == null) {
            return;
        }

        System.out.println("hit: " + (hit != null ? hit.getClass().getName() : "null"));
        ((InventorySpotActor) hit).onHit(this); // 'this' satisfies InventoryContext

    }

    private void clearSelections() {
        if (selected != null) {
            ((InventorySpotActor) selected).setDrawable(null);
            selected = null;
        }
        if (selectedPlayer != null) {
            ((InventorySpotActor)selectedPlayer).setDrawable(null);
            selectedPlayer = null;
        }
        if (selectedUpgrade != null) {
            selectedUpgrade.setDrawable(null);
        }
        clearUpgradeSelection(); // zeroes out both selectedPlayer and selectedUpgrade}
    }

    public ActorInventoryUpgrade getSelectedUpgrade() {
        return selectedUpgrade;
    }

    InventorySpotActor getSelected() {
        return (InventorySpotActor) selected;
    }
    InventorySpotActor getSelectedPlayer() {
        return (InventorySpotActor) selectedPlayer;
    }
    void setSelected(InventorySpotActor spot) {
        this.selected = spot;
    }
    void clearSelected() {
        if (selected != null) {
            ((InventorySpotActor) selected).setDrawable(null);
            selected = null;
        }
        if (selectedPlayer != null) {
            ((InventorySpotActor) selectedPlayer).setDrawable(null);
            selectedPlayer = null;
        }
    }
    void clearSelectedPlayer() {
        //??????
    }
    TextureRegionDrawable getSelectedIcon() {
       return selectedIcon;
    }

    public void setSelectedPlayer(ActorInventoryPlayer player) {
        selectedPlayer = player;
        ((InventorySpotActor) selectedPlayer).setDrawable(selectedIcon);
    }

    public void setSelectedUpgrade(ActorInventoryUpgrade upgrade) {
        if (selectedUpgrade != null) selectedUpgrade.setDrawable(null);
        selectedUpgrade = upgrade;
        selectedUpgrade.setDrawable(selectedIcon);
    }

    public void clearUpgradeSelection() {
        if (selectedPlayer != null) ((InventorySpotActor) selectedPlayer).setDrawable(null);
        if (selectedUpgrade != null) selectedUpgrade.setDrawable(null);
        selectedPlayer = null;
        selectedUpgrade = null;
    }


    public void populatePartyWeapons() {

        //maybe change
        if (this.weaponSlot1.getSpot() != null) {
            WorldHandler.getParty().getPlayer(0).addWeapon((Weapon) this.weaponSlot1.getSpot());
        } else {
            WorldHandler.getParty().getPlayer(0).addWeapon(new WeaponEmpty(true));
        }
        if (this.weaponSlot2.getSpot() != null) {
            WorldHandler.getParty().getPlayer(1).addWeapon((Weapon) this.weaponSlot2.getSpot());
        }else {
            WorldHandler.getParty().getPlayer(1).addWeapon(new WeaponEmpty(true));
        }
        if (this.weaponSlot3.getSpot() != null) {
            WorldHandler.getParty().getPlayer(2).addWeapon((Weapon) this.weaponSlot3.getSpot());
        }else {
            WorldHandler.getParty().getPlayer(2).addWeapon(new WeaponEmpty(true));
        }
        if (this.weaponSlot4.getSpot() != null) {
            WorldHandler.getParty().getPlayer(3).addWeapon((Weapon) this.weaponSlot4.getSpot());
        }else {
            WorldHandler.getParty().getPlayer(3).addWeapon(new WeaponEmpty(true));
        }

        // base ability -> hats -> cards -> cards
        this.populatePartyCards();

    }

    public void populatePartyCards() {

        WorldHandler.getParty().getPlayer(0).getCurrentWeapon().resetToBase();
        WorldHandler.getParty().getPlayer(1).getCurrentWeapon().resetToBase();
        WorldHandler.getParty().getPlayer(2).getCurrentWeapon().resetToBase();
        WorldHandler.getParty().getPlayer(3).getCurrentWeapon().resetToBase();

        if (this.cardSlot1.getSpot() != null) {
            ((Card) this.cardSlot1.getSpot()).effect(0, WorldHandler.getParty().getPlayer(0));
        }
        if (this.cardSlot2.getSpot() != null) {
            ((Card) this.cardSlot2.getSpot()).effect(0, WorldHandler.getParty().getPlayer(1));
        }
        if (this.cardSlot3.getSpot() != null) {
            ((Card) this.cardSlot3.getSpot()).effect(0, WorldHandler.getParty().getPlayer(2));
        }
        if (this.cardSlot4.getSpot() != null) {
            ((Card) this.cardSlot4.getSpot()).effect(0, WorldHandler.getParty().getPlayer(3));
        }

        //add ssecond card slot
        if (this.cardSlot5.getSpot() != null) {
            ((Card) this.cardSlot5.getSpot()).effect(0, WorldHandler.getParty().getPlayer(0));
        }
        if (this.cardSlot6.getSpot() != null) {
            ((Card) this.cardSlot6.getSpot()).effect(0, WorldHandler.getParty().getPlayer(1));
        }
        if (this.cardSlot7.getSpot() != null) {
            ((Card) this.cardSlot7.getSpot()).effect(0, WorldHandler.getParty().getPlayer(2));
        }
        if (this.cardSlot8.getSpot() != null) {
            ((Card) this.cardSlot8.getSpot()).effect(0, WorldHandler.getParty().getPlayer(3));
        }
    }


    public void addToOpenSlot(Item item) {

        for (int i = 0; i < 36;i++) {

            if (this.table.getChild(i) instanceof ActorInventory) {
                return;
            }

            if (((InventorySpotActor) this.table.getChild(i)).getSpot() == null) {

                ((InventorySpotActor) this.table.getChild(i)).addSpot(item);
                return;

            }

        }

    }

    public void hydrate() {

        for (int i = 0; i < 36; i++) {

            Item item = ((InventorySpotActor) GameHandler.screenShop.shopScreenStage.table.getChild(i)).getSpot();

            ((InventorySpotActor) this.table.getChild(i)).addSpot(item);

        }

    }

}
