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

    Table table;

    ActorInventory weaponSlot1;
    ActorInventory weaponSlot2;
    ActorInventory weaponSlot3;
    ActorInventory weaponSlot4;

    CardInventorySpotActor cardSlot1;
    CardInventorySpotActor cardSlot2;
    CardInventorySpotActor cardSlot3;
    CardInventorySpotActor cardSlot4;

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

        this.addToOpenSlot(bow8);
        this.addToOpenSlot(gun1);
        this.addToOpenSlot(bow21);
        this.addToOpenSlot(sword1);
        this.addToOpenSlot(sword1);
        this.addToOpenSlot(bomb1);


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

        this.cardSlot1 = new CardInventorySpotActor(this,null,0);
        this.cardSlot2 = new CardInventorySpotActor(this,null,1);
        this.cardSlot3 = new CardInventorySpotActor(this,null,2);
        this.cardSlot4 = new CardInventorySpotActor(this,null,3);

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




    }

    public Stage instance() {
        return this.inventoryStage;
    }

    public Image getBackgroundImage() {
        return this.image;
    }

    public void parseHit(Actor hit,int button) {

        // Right click = unselect
        if (button == 1) {
            if (selected != null) {
                ((InventorySpotActor) selected).setDrawable(null);
                selected = null;
            }
            return;
        }

// No item hit
        if (hit == null) return;

        InventorySpotActor selectedSpot = (InventorySpotActor) selected;
        InventorySpotActor hitSpot = (InventorySpotActor) hit;

        // Nothing is selected yet → select the new spot
        if (selected == null) {
            hitSpot.setDrawable(selectedIcon);
            selected = hitSpot;
            return;
        }

        // If selected spot is empty → switch selection to hit
        if (selectedSpot.getSpot() == null) {
            selectedSpot.setDrawable(null);
            hitSpot.setDrawable(selectedIcon);
            selected = hitSpot;
            return;
        }

        // Type checking
        Item selectedItem = selectedSpot.getSpot();
        Item hitItem = hitSpot.getSpot();

        // Block invalid drop targets
        boolean selectedIsCard = selectedItem instanceof Card;
        boolean selectedIsWeapon = selectedItem instanceof Weapon;

        boolean hitIsCardSlot = hit instanceof CardInventorySpotActor;
        boolean hitIsWeaponSlot = hit instanceof ActorInventory;

        boolean selectedIsCardSlot = selected instanceof CardInventorySpotActor;
        boolean selectedIsWeaponSlot = selected instanceof ActorInventory;

        // Rule enforcement
        if ((selectedIsWeapon && hitIsCardSlot) || (selectedIsCard && hitIsWeaponSlot)) {
            return; // Weapon trying to go in card slot or vice versa
        }

        if ((hitItem instanceof Weapon && selectedIsCardSlot) || (hitItem instanceof Card && selectedIsWeaponSlot)) {
            return; // Item already in target slot doesn't match
        }

        // All checks passed → perform swap
        Item temp = selectedItem;
        selectedSpot.addSpot(hitItem);
        hitSpot.addSpot(temp);

        selectedSpot.setDrawable(null);
        selected = null;

        populatePartyWeapons(); // Update visuals/stats

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
