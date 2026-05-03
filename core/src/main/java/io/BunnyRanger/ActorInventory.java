package io.BunnyRanger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActorInventory extends InventorySpotActor {

    int number;

    ActorInventory(Stage parentStage, Table parentTable, int number) {

        super(parentStage, parentTable);

        this.number = number;

    }

    public void onHit(InventoryScreenStage stage) {
        stage.clearUpgradeSelection();
        if (handleEmptySelectionCases(stage)) return;

        InventorySpotActor selected = stage.getSelected();
        Item selectedItem = selected.getSpot();
        Item hitItem      = this.getSpot();

        // A Card must never land in a weapon slot
        if (selectedItem instanceof Card) return;

        // If the weapon slot already holds a weapon and the source is a card slot, block
        if (hitItem instanceof Weapon && selected instanceof ActorInventoryCard) return;

        swapWith(selected, stage);
    }

}
