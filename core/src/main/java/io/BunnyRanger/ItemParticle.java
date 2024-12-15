package io.BunnyRanger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;

import java.util.HashMap;

public class ItemParticle extends DamageParticle{

    //HashMap<Integer,Item> droppableItems;
    Item selectedItem = null;

    float yTimer = 0.001f;

    ItemParticle(Body body, HashMap<Integer,Item> items, BitmapFont font, Color color) {

        super(body, 0, font, color);

        //this.droppableItems = items;

        System.out.println(items.keySet().size());

        for (Integer current : items.keySet()) {

            if (Math.random() * 100 <= current) {

                //add item
                this.selectedItem = items.get(current);

                if (selectedItem == null) {
                    System.out.println("THIS SHOULD NOT HAPPEN! ERROR 3452");
                    // maybe need something
                }

                MainMenu.inventoryScreen.inventoryScreenStage.addToOpenSlot(selectedItem);
                MainMenu.shopScreen.shopScreenStage.addToOpenSlot(selectedItem);

                System.out.println("added " + selectedItem);

                break;

            }

        }

    }

    public boolean drawParticle(Batch batch) {

        if (currentLinger >= lingerLength) {
            return false;
        }

        float tmpOffSet = 1;
        offsetCounter+= 1.5f;
        offsetCounterY = 80 * (float) Math.sin(offsetCounter/40f);

        float tmpX = font.getScaleX();
        float tmpY = font.getScaleY();

        font.getData().setScale(2f);
        font.setColor(this.color);

        for (Fixture fixture : this.body.getFixtureList()) {

            if (fixture.getType() == Shape.Type.Circle) {
                //tmpOffSet = fixture.getShape().getRadius();
            }

        }

        if (selectedItem == null || selectedItem.getName() == null) {
            return false;
        }

        font.draw(batch, selectedItem.getName(), ((xPos * 20f) + offsetCounter) + offsetX, ((yPos * 22.5f) + offsetCounterY) + offsetY);

        font.getData().setScale(tmpX, tmpY);
        font.setColor(Color.WHITE);

        currentLinger++;

        return true;
    }

}
