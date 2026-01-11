package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public class Wall implements Entity {

    WorldHandler world;
    BodyDef bodyDef;
    FixtureDef fixtureDef;
    Fixture fixture;
    Body body;
    String nameID = "Floor";

    //drawable
    Texture texture;
    TextureRegion textureRegion;
    Sprite sprite;

    public Wall(WorldHandler world, float x, float y, float xSize, float ySize, String string) {

        System.out.println("makin Floor");

        this.world = world;

        if (this.world.getWorld().isLocked()) {
            System.out.println("World is locked");
        }

        if (this.world == null || this.world.getWorld() == null) {
            System.out.println("WorldInstance or its World is null.");
        }

        // First we create a body definition
        this.bodyDef = new BodyDef();

        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.StaticBody;

        // Set our body's starting position in the world
        //bodyDef.position.set(x*16 - xSize * 16, y*16 - ySize * 16);



        // subtract half the size to get it top right not centered
        bodyDef.position.set(x*16 + xSize * 8, y*16 + ySize * 8);

        // Create our body in the world using our body definition
        this.body = world.getWorld().createBody(bodyDef);
        if (this.body == null) {
            System.out.println("Failed to create Body.");
        }
        body.setUserData(this);

        // Create a box shape and set its radius to xSize and ySize
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(xSize*8, ySize*8);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 2.0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.0f;

        fixtureDef.filter.categoryBits = 0x0001;
        fixtureDef.filter.maskBits = (short) 0xFFFF;

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        this.fixture = fixture;

        //drawable
        texture = new Texture(Gdx.files.internal(string));
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        textureRegion = new TextureRegion(texture);

        //textureRegion.setRegion(0,0,xSize,ySize);
        textureRegion.setRegion(0, 0, (int)(xSize * 16), (int)(ySize * 16));

        sprite = new Sprite(textureRegion);

        sprite.setScale(1f);

        sprite.setSize(xSize * 16, ySize * 16);

        sprite.setPosition(x*16, y*16);

        body.setUserData(this);


        groundBox.dispose();
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public String getNameID() {
        return this.nameID;
    }

    public void executeBegin(Entity secondEntity) {
        //
    }

    @Override
    public void executeEnd(Entity secondEntity) {

    }

    public Body getBody() {
        return this.body;
    }

    public BodyDef getBodyDef() {
        return this.bodyDef;
    }

    public Fixture getFixture() {
        return this.fixture;
    }

    public FixtureDef getFixtureDef() {
        return this.fixtureDef;
    }

    public WorldHandler getWorldInstance() {
        return this.world;
    }

    public void flip(boolean facingRight) {

    }

}


