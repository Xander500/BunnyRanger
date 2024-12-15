package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.Array;

public class Player implements Entity, Damageable {

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    Body body;
    Fixture fixture;

    Floor floor;
    WorldInstance world;
    MouseJointDef jointDef = null;
    MouseJoint joint = null;
    Player player = null;
    Camera camera;
    String nameID = "Player";

    //Damageable
    Body bodyB;
    float health = 50;
    float maxHealth = 50;
    float baseMaxHealth = 50;

    //healthbar
    Sprite healthBarSprite;
    private Texture texture;
    float newScale = 1;

    //draggable
    int number;

    //weapon
    Weapon currentWeapon;

    //sprite
    Sprite playerSprite;
    Texture playerTexture;

    boolean grounded;

    public Player(WorldInstance world, float x, float y, Camera camera, Floor floor,int number) {

        this.world = world;
        this.floor = floor;
        this.player = this;
        this.camera = camera;

        this.number = number;

        // MAKING PLAYER
        this.bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x, y);

        this.body = world.getWorld().createBody(bodyDef);

        this.body.setLinearDamping(2f);

        CircleShape circle = new CircleShape();
        circle.setRadius(1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 10.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.1f; // Make it bounce a little bit

        fixtureDef.filter.categoryBits = 0x0002;
        fixtureDef.filter.maskBits = 0x0004 | 0x0001 | 0x0020;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        this.fixture = fixture;

        circle.dispose();
        /////////

        //WASH
        this.jointDef = new MouseJointDef();

        this.jointDef.bodyA = floor.getBody();
        this.jointDef.bodyB = player.getBody();

        this.jointDef.collideConnected = true;
        this.jointDef.maxForce = 5000;

        texture = new Texture(Gdx.files.internal("GreenHealthBar.png"));
        healthBarSprite = new Sprite(texture,0,0,32,8);

        healthBarSprite.setScale(.05f);

        this.createHealthBar(world);

        //actual sprite
        playerTexture = new Texture(Gdx.files.internal("bun.png"));
        playerSprite = new Sprite(playerTexture,0,0,32,32);

        playerSprite.setScale(.1f);

        this.body.setUserData("Player body");
        this.bodyB.setUserData("Player healthBar");

    }

    public String getNameID() {
        return this.nameID;
    }

    public void executeContact(Entity secondEntity) {

        if (secondEntity.getNameID().equals("Enemy")) {

            Enemy collidedEnemy = (Enemy) secondEntity;

            System.out.println("hit by Enemy for " + this.takeDamage(collidedEnemy.getDamage()));
        }

        if (secondEntity.getNameID().equals("Projectile")) {

            Projectile removeProjectile = (Projectile) secondEntity;

            System.out.println("hit by projectile for " + this.takeDamage(removeProjectile.getDamage()));
        }

        if (secondEntity.getNameID().equals("Floor")) {
            this.grounded = true;
            System.out.println("Grounded");
        }

    }

    public Sprite getHealthBarSprite() {
        return this.healthBarSprite;
    }

    public Body getBody() {

        if (this.body == null) {
            System.out.println("you got a null that will crash");
            return null;
        }

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

    public float takeDamage(float damage) {

        Array<Fixture> fixtureList = new Array<Fixture>(1);
        fixtureList = this.bodyB.getFixtureList();

        fixtureList.get(0).getShape();

        texture = new Texture(Gdx.files.internal("GreenHealthBar.png"));
        healthBarSprite = new Sprite(texture,0,0,32,8);

        //healthBarSprite.setScale(.1f);

        MainApplication.damageParticleList.add(new DamageParticle(this.body,damage,MainApplication.getParty().font, new Color(Color.RED)));

        health -= damage;

        newScale = (health/100f)*.1f;

        if (newScale < 0) {
            newScale = 0;
        }

        healthBarSprite.setScale(newScale/2,.05f);

        return damage;

    }

    public float setHealth(float damage) {

        Array<Fixture> fixtureList = new Array<Fixture>(1);
        fixtureList = this.bodyB.getFixtureList();

        fixtureList.get(0).getShape();

        texture = new Texture(Gdx.files.internal("GreenHealthBar.png"));
        healthBarSprite = new Sprite(texture,0,0,32,8);

        //healthBarSprite.setScale(.1f);

        //new

        health = damage;

        newScale = (health/100f)*.1f;

        if (newScale < 0) {
            newScale = 0;
        }

        healthBarSprite.setScale(newScale/2,.05f);

        return damage;

    }

    public void updateHealthBar() {

        if (this.bodyB == null) {
            System.out.println("you got a null that will crash");
            return;
        }

        //this.body.applyLinearImpulse(new Vector2(0f,20f), this.body.getPosition(),false);
        this.bodyB.setLinearVelocity(0,20);
        this.bodyB.setGravityScale(0);
        healthBarSprite.setPosition(bodyB.getPosition().x-healthBarSprite.getWidth()/2,bodyB.getPosition().y-healthBarSprite.getHeight()/2);

    }

    public void createHealthBar(WorldInstance world) {

        Body bodyA = this.getBody();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(bodyA.getPosition().x,bodyA.getPosition().y + 1);

        this.bodyB = world.getWorld().createBody(bodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1f, .1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = .00001f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit

        fixtureDef.filter.categoryBits = 0x0000;
        fixtureDef.filter.maskBits = 0x0000;

        Fixture fixture = bodyB.createFixture(fixtureDef);
        fixture.setUserData("no");

        DistanceJointDef defJoint = new DistanceJointDef();
        defJoint.initialize(bodyA, bodyB, bodyA.getPosition(), bodyB.getPosition());
        defJoint.length = 2f;

        world.getWorld().createJoint(defJoint);

        newScale = (health/100f)*.1f;

        healthBarSprite.setScale(newScale/2,.05f);

    }

    public boolean checkIfDead() {

        if (health <= 0) {
            return true;
        }

        return false;
    }

    public void updatePlayerSprite() {

        if (this.body == null) {
            System.out.println("you got a null that will crash");
            return;
        }

        playerSprite.setPosition(body.getPosition().x-playerSprite.getWidth()/2,body.getPosition().y-playerSprite.getHeight()/2);
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void addWeapon(Weapon weapon) {
        this.currentWeapon = weapon;
        weapon.setEntity(this);

    }

    public Sprite getWeaponSprite() {
        return this.currentWeapon.getSprite();
    }

    public void drawWeapon() {
        this.currentWeapon.drawWeapon();
    }

    public Weapon getCurrentWeapon() {
        return this.currentWeapon;
    }

    public void useWeapon() {
        this.currentWeapon.weaponProjectileFactory();
    }

    public WorldInstance getWorldInstance() {
        return this.world;
    }

    public int getNumber() {
        return this.number;
    }

    public void flip(boolean facingRight) {
        if(facingRight) {

            if (this.getPlayerSprite().isFlipX()) {
                this.getPlayerSprite().flip(true,false);
            }

        } else {

            if (!this.getPlayerSprite().isFlipX()) {
                this.getPlayerSprite().flip(true,false);
            }

        }
    }

    public boolean grounded() {
        return grounded;
    }

    public void unground() {
        grounded = false;
    }

    //sprite

}
