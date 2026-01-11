package io.BunnyRanger;

import static io.BunnyRanger.WorldHandler.battleSizeHeight;
import static io.BunnyRanger.WorldHandler.battleSizeWidth;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;

public class Particle {

    Body body;
    float damage;

    BitmapFont font;
    Color color;

    int lingerLength = 120;
    int currentLinger = 0;

    float offsetX;
    float offsetY;
    float offsetCounter = 0;
    float offsetCounterY = 0;

    float xPos;
    float yPos;

    Particle(Body body, float damage, BitmapFont font, Color color) {
        this.body = body;
        this.damage = damage;
        this.font = font;
        this.color = color;
        //this.offsetX = 0.9f * (float) ((Math.random()*80+20));
        //this.offsetY = 0.9f * (float) ((Math.random()*80+20));
        this.offsetX = 0;
        this.offsetY = 0;

        this.xPos = this.body.getPosition().x;
        this.yPos = this.body.getPosition().y;

        if (Math.random() > .5f) {
            this.offsetX = this.offsetX * (-1);
        }

    }

    public boolean drawParticle(Batch batch) {

        if (currentLinger >= lingerLength) {
            return false;
        }

        offsetCounter+= .8f;
        offsetCounterY = 60 * (float) Math.sin(offsetCounter/30f);

        float tmpX = font.getScaleX();
        float tmpY = font.getScaleY();

        font.getData().setScale(2f);
        font.setColor(this.color);

        font.draw(batch, String.valueOf((int) damage), ((xPos* WorldHandler.SCREENWIDTH/battleSizeWidth) + offsetCounter) + offsetX, ((yPos* WorldHandler.SCREENHEIGHT/battleSizeHeight) + offsetCounterY) + offsetY);

        font.getData().setScale(tmpX, tmpY);
        font.setColor(Color.WHITE);

        currentLinger++;

        return true;
    }

}
