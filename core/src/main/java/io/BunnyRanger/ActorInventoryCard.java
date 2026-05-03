package io.BunnyRanger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActorInventoryCard extends InventorySpotActor {

    int number;

    ActorInventoryCard(Stage parentStage, Table parentTable, int number) {

        super(parentStage, parentTable);

        this.number = number;

    }

    public void onHit(InventoryScreenStage stage) {
        stage.clearUpgradeSelection();
        if (handleEmptySelectionCases(stage)) return;

        InventorySpotActor selected = stage.getSelected();
        Item selectedItem = selected.getSpot();
        Item hitItem      = this.getSpot();

        // A Weapon must never land in a card slot
        if (selectedItem instanceof Weapon) return;

        // If the card slot already holds a card and the source is a weapon slot, block
        if (hitItem instanceof Card && selected instanceof ActorInventory) return;

        swapWith(selected, stage);
    }

}
