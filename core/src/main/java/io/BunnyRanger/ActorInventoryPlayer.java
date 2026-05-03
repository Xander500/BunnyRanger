package io.BunnyRanger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActorInventoryPlayer extends InventorySpotActor {

    int number;

    ActorInventoryPlayer(Stage parentStage, Table parentTable, int number) {

        super(parentStage, parentTable);

        this.number = number;

    }

    public void onHit(InventoryScreenStage stage) {
        // Clear any pending item selection, then claim the player slot
        stage.clearSelected();
        stage.setSelectedPlayer(this);
    }

}
