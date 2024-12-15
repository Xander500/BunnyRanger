package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Sign implements Entity {

    int currentLevel;
    Screen target;
    boolean reset = false;
    boolean illDoIt = false;

    Sprite signSprite;

    // asdasdas

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    CircleShape circle;
    Body body;
    String nameID = "Enemy";
    WorldInstance world;
    Fixture fixture;

    //actual sprite
    Sprite enemySprite;
    Texture playerTexture;

    public Sign(WorldInstance world, float x, float y, int currentLevel,Screen target) {

        this.world = world;

        this.bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x, y);

        this.body = world.getWorld().createBody(bodyDef);

        this.circle = new CircleShape();
        circle.setRadius(1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 20000.0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit

        fixtureDef.filter.categoryBits = 0x1000;
        fixtureDef.filter.maskBits = 0x0001;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        this.fixture = fixture;

        circle.dispose();

        // actual hitbox

        playerTexture = new Texture(Gdx.files.internal("bun.png"));
        enemySprite = new Sprite(playerTexture,0,0,32,32);

        enemySprite.setScale(.1f);

        //weewoo
        this.enemySprite.flip(true,false);

        // START OF SIGN STUFF

        this.currentLevel = currentLevel;

        //////////////////

        this.fixture.setUserData(this);
        this.nameID = "Sign";

        this.target = target;

        //this.health = 0;

        playerTexture = new Texture(Gdx.files.internal("Sign.png"));
        enemySprite = new Sprite(playerTexture,0,0,16,16);
        enemySprite.setScale(.2f);

        this.body.setUserData(this);

    }

    public void updateEnemySprite() {

        enemySprite.setPosition(body.getPosition().x-enemySprite.getWidth()/2,body.getPosition().y-enemySprite.getHeight()/2 + .55f);

    }

    public String getNameID() {
        return this.nameID;
    }

    public void executeContact(Entity secondEntity) {

        if (secondEntity.getNameID().equals("Player")) {

            Gdx.input.setInputProcessor(null);

            MainMenu.currentScreen = MainMenu.instance.getScreen();

            ((ScreenType) target).setReset(true);

            illDoIt = true;

            MainMenu.swappingScreen = true;
        }

    }

    public void executeSwap() {
        if (!MainApplication.world1.getWorld().isLocked()) {
            MainMenu.instance.setScreen(target);
            MainMenu.indexScreen = currentLevel;
            illDoIt = false;

            // maybe somewhere else
            MainApplication.getParty().resetParty();
        } else {
            System.out.println(666);
        }
    }

    public void changeBits() {

        for (Fixture fixture : body.getFixtureList()) {

            // Get the current filter data
            Filter filter = fixture.getFilterData();

            // Set the new category bits
            filter.categoryBits = 0x0004;
            filter.maskBits = 0x0001 | 0x0002 | 0x0010;
            // Apply the updated filter data to the fixture
            fixture.setFilterData(filter);

        }

    }

    public Sprite getEnemySprite() {
        return this.enemySprite;
    }

    public Body getBody() {
        return this.body;
    }

    public Fixture getFixture() {
        return this.fixture;
    }

    public WorldInstance getWorldInstance() {
        return this.world;
    }

    public void flip(boolean facingRight) {
        //lol
    }
}
