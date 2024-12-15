package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class ArrowProjectile extends Projectile {

    float x;
    float y;
    float angle;
    Vector2 magnitude;
    float xSize;
    float ySize;

    boolean facingRight;

    public ArrowProjectile(WorldInstance world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight) {

        texture = new Texture(Gdx.files.internal("ArrowProjectile.png"));
        projectileSprite = new Sprite(texture,0,0,32,8);
        projectileSprite.setScale(.05f);

        this.damage = damage;
        this.world = world;
        this.bodyDef = new BodyDef();
        this.x = x;
        this.y = y;

        if (facingRight) {
            this.angle = angle;
        } else {
            this.angle = angle - 180;
            projectileSprite.setFlip(true,false);
        }

        this.magnitude = magnitude;
        this.damage = damage;
        this.xSize = xSize;
        this.ySize = ySize;
        this.spriteDestroyDelay = 28;
        this.alpha = 0;
        this.alphaReduceAmount = 8;
        this.facingRight = facingRight;

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x, y);

        this.body = world.getWorld().createBody(bodyDef);
        this.body.setTransform(x,y,angle);
        this.body.setUserData(this);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(xSize, ySize);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = density;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit

        if (friendly) {
            fixtureDef.filter.categoryBits = 0x0010;
            fixtureDef.filter.maskBits =  0x0001 | 0x0004;
        } else {
            fixtureDef.filter.categoryBits = 0x0020;
            fixtureDef.filter.maskBits = 0x0001 | 0x0002;
            projectileSprite.setColor(Color.RED);
        }

        this.fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        this.body.applyLinearImpulse(magnitude,new Vector2(x,y),false);
        this.body.setGravityScale(1);
        this.body.setUserData(this);

    }

    public Sprite getProjectileSprite() {

        //makes lifespan do something
        if (this.spriteDestroyLifeSpan < 0) {
            remove = true;
        } else {
            spriteDestroyLifeSpan--;
        }

        if (!remove) {

            body.setTransform(body.getPosition().x,body.getPosition().y,Math.max(body.getAngle()-.007f, (float) -(Math.PI/2)) );

            this.projectileSprite.setPosition(this.body.getPosition().x - this.projectileSprite.getWidth() / 2, this.body.getPosition().y - this.projectileSprite.getHeight() / 2);

            if (facingRight) {
                this.projectileSprite.setRotation(body.getAngle() * 57.3f);
            } else {
                this.projectileSprite.setRotation(-(body.getAngle() * 57.3f));
            }

            //System.out.println("time to draw");


        } else if (this.spriteDestroyDelay > 0) {

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
