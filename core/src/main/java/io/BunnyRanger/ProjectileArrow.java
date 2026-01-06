package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class ProjectileArrow extends Projectile {

        public ProjectileArrow(WorldInstance world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int parent) {
            super(world, x, y, xSize, ySize, angle, magnitude, damage, density, friendly, facingRight, parent);
        }

        public Sprite getProjectileSprite() {

        //makes lifespan do something
        if (this.spriteDestroyLifeSpan < 0) {
            remove = true;
        } else {
            spriteDestroyLifeSpan--;
        }

        if (!remove) {

            body.setTransform(body.getPosition().x,body.getPosition().y,Math.max(body.getAngle()-.005f, (float) -(Math.PI/2)));

            this.projectileSprite.setPosition(this.body.getPosition().x - this.projectileSprite.getWidth() / 2, this.body.getPosition().y - this.projectileSprite.getHeight() / 2);

            if (facingRight) {
                this.projectileSprite.setRotation(body.getAngle() * 57.3f);
            } else {
                this.projectileSprite.setRotation(-(body.getAngle() * 57.3f));
            }

            //System.out.println("time to draw");


        } else if (this.spriteDestroyDelay > 0) {

            //override for death explosion
            death();

            if (alpha == 0) {
                alpha = 32;
            }

            this.spriteDestroyDelay--;
            this.alpha += alphaReduceAmount;
            this.projectileSprite.setAlpha(Math.min(this.alpha,255));
            //System.out.println("time to lower: " + Math.min(this.alpha,255));

        } else {

           //System.out.println("time to remove");
           MainApplication.world1.addDestroyBody(this.getBody());
           MainApplication.projectileListRemove.add(this);

           this.remove = true;

        }

        return this.projectileSprite;
    }

}
