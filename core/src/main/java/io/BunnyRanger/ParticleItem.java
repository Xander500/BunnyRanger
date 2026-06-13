package io.BunnyRanger;

import static io.BunnyRanger.WorldHandler.battleSizeHeight;
import static io.BunnyRanger.WorldHandler.battleSizeWidth;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import java.util.HashMap;

public class ParticleItem extends Particle {

    //HashMap<Integer,Item> droppableItems;
    Item selectedItem = null;

    ParticleItem(Body body, HashMap<Item, Float> items, BitmapFont font, Color color) {

        super(body, 0, font, color);

        //this.droppableItems = items;

        System.out.println(items.keySet().size());

        for (Item current : items.keySet()) {

            if (Math.random() * 100 <= items.get(current)) {

                //add item
                this.selectedItem = current;

                if (selectedItem == null) {
                    System.out.println("THIS SHOULD NOT HAPPEN! ERROR 3452");
                    // maybe need something
                }

                GameHandler.screenInventory.inventoryScreenStage.addToOpenSlot(selectedItem);
                GameHandler.screenShop.shopScreenStage.addToOpenSlot(selectedItem);

                System.out.println("added " + selectedItem);

                break;

            }

        }

    }

    public boolean drawParticle(Batch batch) {

        if (currentLinger >= lingerLength) {
            return false;
        }

        offsetCounter+= 1.5f;
        offsetCounterY = 80 * (float) Math.sin(offsetCounter/40f);

        float tmpX = font.getScaleX();
        float tmpY = font.getScaleY();

        if (selectedItem == null || selectedItem.getName() == null) {
            return false;
        }

        font.getData().setScale(2f);
        font.setColor(this.color);

        font.draw(batch, selectedItem.getName(), ((xPos* WorldHandler.SCREENWIDTH/battleSizeWidth) + offsetCounter) + offsetX, ((yPos* WorldHandler.SCREENHEIGHT/battleSizeHeight) + offsetCounterY) + offsetY);

        font.getData().setScale(tmpX, tmpY);
        font.setColor(Color.WHITE);

        currentLinger++;

        return true;
    }

}
