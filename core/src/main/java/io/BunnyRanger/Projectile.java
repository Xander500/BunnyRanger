package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Projectile implements Entity {

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    CircleShape circle;
    Body body;
    String nameID = "Projectile";
    WorldHandler world;
    float damage;
    Fixture fixture;

    //subs
    Sprite projectileSprite;
    Texture texture;

    //Sprite fade stuff
    int spriteDestroyDelay;
    int alpha;
    int alphaReduceAmount;

    //projectile stuff
    float x;
    float y;
    float angle;
    Vector2 magnitude;
    float xSize;
    float ySize;

    public Projectile() {
        //blank for subclasses, will break if called
    }

    boolean remove = false;

    //lifespan
    int spriteDestroyLifeSpan = 500;

    boolean facingRight;

    boolean onDeath = false;

    int parent;

    Entity damageSource;
    DamageCalculator.DamageType damageType = DamageCalculator.DamageType.REGULAR;
    DamageCalculator.Palette damagePalette = DamageCalculator.defaultPalette();

    //pellet
    public Projectile(WorldHandler world, float x, float y) {

        damage = 10;

        this.world = world;

        this.bodyDef = new BodyDef();

        bodyDef.linearDamping = 0.0f;

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x, y);

        this.body = world.getWorld().createBody(bodyDef);

        this.circle = new CircleShape();
        circle.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 2.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit

        this.fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        circle.dispose();

    }

    public Projectile(WorldHandler world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int parent) {

        texture = new Texture(Gdx.files.internal("sprites/projectiles/projectile_arrow.png"));
        projectileSprite = new Sprite(texture);
        projectileSprite.setScale(1f);

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

        bodyDef.linearDamping = 0.0f;

        bodyDef.position.set(x, y);

        this.body = world.getWorld().createBody(bodyDef);
        this.body.setTransform(x,y,angle);
        this.body.setUserData(this);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(xSize, ySize);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = density;
        fixtureDef.friction = 0f;
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

        //this.body.applyLinearImpulse(magnitude,new Vector2(x,y),false);
        this.body.setLinearVelocity(magnitude);

        this.body.setGravityScale(1);
        this.body.setUserData(this);

        this.projectileSprite.setRotation(getSpriteRotation(this.body.getAngle()));

        this.parent = parent;
    }

    public String getNameID() {
        return this.nameID;
    }

    public void executeBegin(Entity secondEntity) {

        if (secondEntity.getNameID().equals("Player")) {

            remove = true;

            for (Fixture fixture : body.getFixtureList()) {

                // Get the current filter data
                Filter filter = fixture.getFilterData();

                // Set the new category bits
                filter.categoryBits = 0x0000;
                filter.maskBits = 0x0001 | 0x0002 | 0x0010;
                // Apply the updated filter data to the fixture
                fixture.setFilterData(filter);

            }

        }

        if (secondEntity.getNameID().equals("Enemy")) {

            remove = true;

            for (Fixture fixture : body.getFixtureList()) {

                // Get the current filter data
                Filter filter = fixture.getFilterData();

                // Set the new category bits
                filter.categoryBits = 0x0000;
                filter.maskBits = 0x0001;
                // Apply the updated filter data to the fixture
                fixture.setFilterData(filter);

            }

        }

        if (secondEntity.getNameID().equals("Floor")) {

            remove = true;

            for (Fixture fixture : body.getFixtureList()) {

                // Get the current filter data
                Filter filter = fixture.getFilterData();

                // Set the new category bits
                filter.categoryBits = 0x0000;
                filter.maskBits = 0x0001;
                // Apply the updated filter data to the fixture
                fixture.setFilterData(filter);

            }

        }

    }

    @Override
    public void executeEnd(Entity secondEntity) {

    }

    public float getDamage() {
        return this.damage;
    }

    public DamageCalculator.Result getDamageResult(Damageable target) {
        return getDamageResult(this.damage, target);
    }

    public DamageCalculator.Result getDamageResult(float baseDamage, Damageable target) {
        return DamageCalculator.calculate(damageSource, target, baseDamage, damageType, damagePalette);
    }

    public void setDamageSource(Entity damageSource) {
        this.damageSource = damageSource;
    }

    public void setDamageType(DamageCalculator.DamageType damageType) {
        this.damageType = damageType;
    }

    public void setDamagePalette(DamageCalculator.Palette damagePalette) {
        this.damagePalette = damagePalette;
    }

    public void copyDamageSettingsTo(Projectile projectile) {
        projectile.setDamageSource(this.damageSource);
        projectile.setDamageType(this.damageType);
        projectile.setDamagePalette(this.damagePalette);
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

    public WorldHandler getWorldInstance() {
        return this.world;
    }

    public void flip(boolean facingRight) {

    }

    public CircleShape getCircleShape() {
        return this.circle;
    }

    public Sprite getProjectileSprite() {

        //makes lifespan do something
        if (this.spriteDestroyLifeSpan < 0) {
            remove = true;
        } else {
            spriteDestroyLifeSpan--;
        }

        if (!remove) {

            if (rotatesInAir()) {
                body.setTransform(body.getPosition().x,body.getPosition().y,Math.max(body.getAngle()-.005f, (float) -(Math.PI/2)));
            }

            this.projectileSprite.setPosition(this.body.getPosition().x - this.projectileSprite.getWidth() / 2, this.body.getPosition().y - this.projectileSprite.getHeight() / 2);

            if (rotatesInAir()) {
                this.projectileSprite.setRotation(getSpriteRotation(body.getAngle()));
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
            WorldHandler.getWorldHandler().addDestroyBody(this.getBody());
            WorldHandler.projectileListRemove.add(this);

            this.remove = true;

        }

        return this.projectileSprite;
    }

    protected boolean rotatesInAir() {
        return false;
    }

    protected float getSpriteRotation(float angleRadians) {
        float rotation = angleRadians * MathUtils.radiansToDegrees;
        return facingRight ? rotation : -rotation;
    }

    public void drawProjectile(Batch batch) {
        //
    }

    public int getSpriteDestroyDelay() {
        return this.spriteDestroyDelay;
    }

    public int death() {
        return 0;
    }

    public int children(int amount, Projectile type) {

       return 0;
    }

}
