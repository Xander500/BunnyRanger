package io.BunnyRanger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActorInventoryUpgrade extends InventorySpotActor {

    int number;

    ActorInventoryUpgrade(Stage parentStage, Table parentTable, int number) {

        super(parentStage, parentTable);

        this.number = number;

    }

    public void onHit(InventoryScreenStage stage) {

        ActorInventoryPlayer selectedPlayer   = (ActorInventoryPlayer) stage.getSelectedPlayer();
        ActorInventoryUpgrade previousUpgrade = stage.getSelectedUpgrade();
        if (selectedPlayer == null) return;

        setDrawable(stage.getSelectedIcon());
        stage.setSelectedUpgrade(this);

        if (this.number == 3 && previousUpgrade != null) {
            previousUpgrade.execute(selectedPlayer);
            previousUpgrade.setDrawable(null);
            stage.clearUpgradeSelection();
            return;
        }

        if (this.number == 3) {
            stage.clearUpgradeSelection();
        }

    }

    public void execute(ActorInventoryPlayer player) {
        //specific implementation
        System.out.println("upgraded " + this.number);
    }

}
