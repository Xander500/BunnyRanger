package io.BunnyRanger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Projectile implements Entity {

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    CircleShape circle;
    Body body;
    String nameID = "Projectile";
    WorldInstance world;
    float damage;
    Fixture fixture;

    //subs
    Sprite projectileSprite;
    Texture texture;

    //Sprite fade stuff
    int spriteDestroyDelay;
    int alpha;
    int alphaReduceAmount;

    public Projectile() {
        //blank for subclasses, will break if called
    }

    boolean remove = false;

    //lifespan
    int spriteDestroyLifeSpan = 50000;

    //pellet
    public Projectile(WorldInstance world, float x, float y) {

        damage = 10;

        this.world = world;

        this.bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x, y);

        this.body = world.getWorld().createBody(bodyDef);

        this.circle = new CircleShape();
        circle.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 2.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.8f; // Make it bounce a little bit

        this.fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        circle.dispose();

    }

    public String getNameID() {
        return this.nameID;
    }

    public void executeContact(Entity secondEntity) {

        if (secondEntity.getNameID().equals("Player")) {

            Player removeBox = (Player) secondEntity;

            remove = true;

            for (Fixture fixture : body.getFixtureList()) {

                // Get the current filter data
                Filter filter = fixture.getFilterData();

                // Set the new category bits
                filter.categoryBits = 0x0004;
                filter.maskBits = 0x0001 | 0x0002 | 0x0010;
                // Apply the updated filter data to the fixture
                fixture.setFilterData(filter);

            }

            //destroy function

            //world.addDestroyBody(this.body);

            //DamageValueParticles.makeParticles(this.getDamage());

        }

        if (secondEntity.getNameID().equals("Enemy")) {

            Enemy removeBox = (Enemy) secondEntity;

            remove = true;

            for (Fixture fixture : body.getFixtureList()) {

                // Get the current filter data
                Filter filter = fixture.getFilterData();

                // Set the new category bits
                filter.categoryBits = 0x0004;
                filter.maskBits = 0x0001 | 0x0002 | 0x0010;
                // Apply the updated filter data to the fixture
                fixture.setFilterData(filter);

            }

            //destroy function

            //world.addDestroyBody(this.body);

            //DamageValueParticles.makeParticles(this.getDamage());

        }

        if (secondEntity.getNameID().equals("Floor")) {

            Floor removeBox = (Floor) secondEntity;

            remove = true;

            for (Fixture fixture : body.getFixtureList()) {

                // Get the current filter data
                Filter filter = fixture.getFilterData();

                // Set the new category bits
                filter.categoryBits = 0x0004;
                filter.maskBits = 0x0001 | 0x0002 | 0x0010;
                // Apply the updated filter data to the fixture
                fixture.setFilterData(filter);

            }
            //destroy function

            //world.addDestroyBody(this.body);

        }

    }

    public float getDamage() {
        return this.damage;
    }

    public Body getBody() {
        return this.body;
    }

    public BodyDef getBodyDef() {
        return this.bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return this.fixtureDef;
    }

    public Fixture getFixture() {
        return this.fixture;
    }

    public WorldInstance getWorldInstance() {
        return this.world;
    }

    public void flip(boolean facingRight) {

    }

    public CircleShape getCircleShape() {
        return this.circle;
    }

    public Sprite getProjectileSprite() {
        return null;
    }

    public void drawProjectile(Batch batch) {
        //
    }

    public int getSpriteDestroyDelay() {
        return this.spriteDestroyDelay;
    }

}
