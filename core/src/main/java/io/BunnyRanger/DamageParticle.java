package io.BunnyRanger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;

public class DamageParticle {

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

    DamageParticle(Body body, float damage, BitmapFont font, Color color) {
        this.body = body;
        this.damage = damage;
        this.font = font;
        this.color = color;
        this.offsetX = 0.9f * (float) ((Math.random()*80+20));
        this.offsetY = 0.9f * (float) ((Math.random()*80+20));

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

        font.draw(batch, String.valueOf((int) damage), ((xPos * 20f) + offsetCounter) + offsetX, ((yPos * 22.5f) + offsetCounterY) + offsetY);

        font.getData().setScale(tmpX, tmpY);
        font.setColor(Color.WHITE);

        currentLinger++;

        return true;
    }

}
