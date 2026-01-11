package io.BunnyRanger;

import static io.BunnyRanger.GameHandler.fontShader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class ParticleImage extends Particle {

    Sprite image;

    int alpha;
    int alphaReduceAmount;
    ParticleImage(Body body, float damage, BitmapFont font, Color color, String src, int lingerLen, int alphaReduceAmount, float size) {
        super(body,damage,font,color);

        this.xPos = this.body.getPosition().x;
        this.yPos = this.body.getPosition().y;

        Texture texture = new Texture(Gdx.files.internal(src));
        image = new Sprite(texture);
        image.setScale(size);

        int salt = (int) ((Math.random() * (5 + 5)) - 5);

        lingerLength = lingerLen;
        image.setPosition(salt + xPos - image.getWidth() / 2, salt + yPos - image.getHeight() / 2);

        this.alpha = 0;
        this.alphaReduceAmount = alphaReduceAmount;

    }

    public boolean drawParticle(Batch batch) {

        if (currentLinger >= lingerLength) {
            return false;
        }

        //fade
        this.alpha += alphaReduceAmount;
        image.setAlpha(Math.min(this.alpha,255));

        GameHandler.batch.setShader(null);
        GameHandler.batch.setProjectionMatrix(WorldHandler.camera.combined);
        image.draw(batch);
        GameHandler.batch.setProjectionMatrix(WorldHandler.textCamera.combined);
        GameHandler.batch.setShader(fontShader);
        currentLinger++;

        return true;
    }
}
