package io.BunnyRanger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActorInventoryUpgrade extends InventorySpotActor {

    int number;

    ActorInventoryUpgrade(Stage parentStage, Table parentTable, int number) {

        super(parentStage, parentTable);

        this.number = number;

    }

}
