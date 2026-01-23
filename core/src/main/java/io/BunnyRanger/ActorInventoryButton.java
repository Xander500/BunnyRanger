package io.BunnyRanger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActorInventoryButton extends InventorySpotActor {

    int number;

    ActorInventoryButton(Stage parentStage, Table parentTable, int number) {

        super(parentStage, parentTable);

        this.number = number;

    }

}
