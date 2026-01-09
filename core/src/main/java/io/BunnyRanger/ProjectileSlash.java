package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ProjectileSlash extends Projectile {

    public ProjectileSlash(WorldInstance world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int parent) {

        super(world, x, y, xSize / 2, ySize, angle, magnitude, damage, density, friendly, facingRight, parent);

        //lifespan
        this.spriteDestroyLifeSpan = 400;

        this.texture = null;
        this.projectileSprite = null;

        this.texture = new Texture(Gdx.files.internal("SlashProjectile.png"));
        this.projectileSprite = new Sprite(texture, 0, 0, 8, 16);

        this.projectileSprite.setScale(1f);

        if (facingRight) {
            this.angle = angle;
            this.projectileSprite.setRotation(180);

        } else {
            //this.angle = angle - 180;
            projectileSprite.setFlip(false, false);
            this.projectileSprite.setRotation(0);

        }

        this.body.setGravityScale(0f);


    }

    public Sprite getProjectileSprite() {

        //makes lifespan do something
        if (this.spriteDestroyLifeSpan < 0) {
            remove = true;
        } else {
            spriteDestroyLifeSpan--;
        }

        if (!remove) {

            //body.setTransform(body.getPosition().x, body.getPosition().y, Math.max(body.getAngle() - .007f, (float) -(Math.PI / 2)));

            this.projectileSprite.setPosition(this.body.getPosition().x - this.projectileSprite.getWidth() / 2, this.body.getPosition().y - this.projectileSprite.getHeight() / 2);

            //System.out.println("time to draw");


        } else if (this.spriteDestroyDelay > 0) {

            if (alpha == 0) {
                alpha = 32;
            }

            this.spriteDestroyDelay--;
            this.alpha += alphaReduceAmount;
            this.projectileSprite.setAlpha(Math.min(this.alpha, 255));
            //System.out.println("time to lower: " + Math.min(this.alpha,255));

        } else {

            //System.out.println("time to remove");
            MainApplication.world1.addDestroyBody(this.getBody());
            MainApplication.projectileListRemove.add(this);

            this.remove = true;

        }

        return this.projectileSprite;

    }
    public void executeBegin(Entity secondEntity) {
        super.executeBegin(secondEntity);
    }
}
