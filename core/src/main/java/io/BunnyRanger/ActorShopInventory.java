package io.BunnyRanger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActorShopInventory extends InventorySpotActor {

    int number;

    boolean useState;

    ActorShopInventory(Stage parentStage, Table parentTable, int number, int buyPrice, int sellPrice) {

        super(parentStage, parentTable);

        this.number = number;

        useState = false;

    }

}
