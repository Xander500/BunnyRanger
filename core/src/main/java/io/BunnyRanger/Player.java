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

public class Player implements Entity, Damageable {

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    Body body;
    Fixture fixture;

    WorldHandler world;
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
    Sprite playerSpriteAir;
    Texture playerTextureAir;

    boolean grounded;

    Sprite healthBarSpriteBack;

    public Player(WorldHandler world, float x, float y, Camera camera, Wall wall, int number) {

        this.world = world;
        this.player = this;
        this.camera = camera;

        this.number = number;

        // MAKING PLAYER
        this.bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x*16, y*16);

        this.body = world.getWorld().createBody(bodyDef);

        this.body.setLinearDamping(.5f);

        this.body.setGravityScale(2);

        this.body.setFixedRotation(true);

        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.01f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit

        fixtureDef.filter.categoryBits = 0x0002;
        fixtureDef.filter.maskBits = 0x0004 | 0x0001 | 0x0020;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        this.fixture = fixture;

        circle.dispose();
        /////////

        //WASH
        this.jointDef = new MouseJointDef();

        this.jointDef.bodyA = wall.getBody();
        this.jointDef.bodyB = player.getBody();

        this.jointDef.collideConnected = true;
        this.jointDef.maxForce = 150;

        texture = new Texture(Gdx.files.internal("GreenHealthBar.png"));
        healthBarSprite = new Sprite(texture);
        healthBarSprite.setScale(1f);

        texture = new Texture(Gdx.files.internal("healthBar.png"));
        healthBarSpriteBack = new Sprite(texture);
        healthBarSpriteBack.setScale(1f);

        this.createHealthBar(world);

        //actual sprite
        playerTexture = new Texture(Gdx.files.internal("player.png"));
        playerSprite = new Sprite(playerTexture,0,0,16,16);

        playerTextureAir = new Texture(Gdx.files.internal("playerAir.png"));
        playerSpriteAir = new Sprite(playerTextureAir,0,0,16,16);

        playerSprite.setScale(1f);

        this.body.setUserData("Player body");
        this.bodyB.setUserData("Player healthBar");

    }

    public String getNameID() {
        return this.nameID;
    }

    public void executeBegin(Entity secondEntity) {

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
            this.body.setLinearVelocity(this.body.getLinearVelocity().x/10f,this.body.getLinearVelocity().y/10f);
            this.body.setAngularVelocity(this.body.getAngularVelocity()/10f);
            System.out.println("Grounded");
        }

    }

    public void executeEnd(Entity secondEntity) {
        if (secondEntity.getNameID().equals("Floor")) {
            this.unground();
            System.out.println("Grounded END");
        }
    }


    public Sprite getHealthBarSprite() {
        return this.healthBarSprite;
    }

    public Sprite getHealthBarSpriteBack() {
        return this.healthBarSpriteBack;
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

    public float setHealth(float damage) {

        health = damage;

        healthBarSprite.setScale(Math.max(health / maxHealth, 0),1f);

        return damage;

    }

    public float takeDamage(float damage) {

        if (this.checkIfDead()) {
            //MainApplication.getParty().damageAll(damage);
            return damage;
        }

        WorldHandler.particleList.add(new Particle(this.body,damage, WorldHandler.getParty().font, new Color(Color.RED)));

        health -= damage;

        //healthBarSprite.setScale(Math.max(((health / maxHealth) / 2) * 2, 0),1f);

        float healthRatio = Math.max(health / maxHealth, 0f);
        float baseWidthPx = healthBarSprite.getWidth();
        float desiredWidthPx = baseWidthPx * healthRatio;

        //snap to even pixels only
        float snappedWidthPx = Math.round(desiredWidthPx) * 1f;

        float snappedScaleX = snappedWidthPx / baseWidthPx;
        healthBarSprite.setScale(snappedScaleX, 1f);

        return damage;

    }

    /*
    public float takeDamageAll(float damage) {

        MainApplication.damageParticleList.add(new Particle(this.body,damage,MainApplication.getParty().font, new Color(Color.RED)));

        health -= damage;

        healthBarSprite.setScale(Math.max(health/maxHealth,0),1f);

        return damage;

    }
     */

    public void updateHealthBar() {

        if (this.bodyB == null) {
            System.out.println("you got a null that will crash");
            return;
        }

        //this.bodyB.applyForceToCenter(0,1,true);
        this.bodyB.setLinearVelocity(0,20);
        healthBarSprite.setPosition(bodyB.getPosition().x+2-healthBarSprite.getWidth()/2-1,bodyB.getPosition().y+1-healthBarSprite.getHeight()/2);
        healthBarSpriteBack.setPosition(bodyB.getPosition().x-healthBarSprite.getWidth()/2,bodyB.getPosition().y-healthBarSprite.getHeight()/2);

    }

    public void createHealthBar(WorldHandler world) {

        Body bodyA = this.getBody();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(bodyA.getPosition().x,bodyA.getPosition().y + 1);

        bodyDef.gravityScale = 0f;

        this.bodyB = world.getWorld().createBody(bodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(.1f, .1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = .0001f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        fixtureDef.filter.categoryBits = 0x0000;
        fixtureDef.filter.maskBits = 0x0000;

        Fixture fixture = bodyB.createFixture(fixtureDef);
        fixture.setUserData("no");

        DistanceJointDef defJoint = new DistanceJointDef();
        defJoint.initialize(bodyA, bodyB, bodyA.getPosition(), bodyB.getPosition());
        defJoint.length = 18f;
        defJoint.dampingRatio = 1;
        defJoint.frequencyHz = 8;

        world.getWorld().createJoint(defJoint);

        newScale = (health/maxHealth);

        healthBarSprite.setScale(newScale,1f);

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

        getPlayerSprite().setPosition(body.getPosition().x-playerSprite.getWidth()/2,body.getPosition().y-playerSprite.getHeight()/4);
    }

    public Sprite getPlayerSprite() {

        if (grounded) {
            return playerSprite;
        } else {
            return playerSpriteAir;
        }
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

    public WorldHandler getWorldInstance() {
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
