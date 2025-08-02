package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public abstract class Enemy implements Entity, Damageable {

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    CircleShape circle;
    Body body;
    String nameID = "Enemy";
    WorldInstance world;
    Fixture fixture;

    float damage;

    //Damageable
    Body bodyB;
    float health = 1;
    float maxHealth = 1;


    //healthbar
    Sprite healthBarSprite;
    private Texture texture;
    float newScale = 1;

    //weapon
    Weapon currentWeapon;

    //actual sprite
    Sprite enemySprite;
    Texture enemyTexture;

    // items drops

    HashMap<Integer,Item> dropList = new HashMap<Integer,Item>();
    private boolean hasNotDied = true;

    public Enemy(WorldInstance world, float x, float y) {

        damage = 7;

        this.world = world;

        this.bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x*16, y*16);

        this.body = world.getWorld().createBody(bodyDef);
        this.body.setUserData("enemy body");

        this.circle = new CircleShape();
        circle.setRadius(10f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 10.0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit

        fixtureDef.filter.categoryBits = 0x0004;
        fixtureDef.filter.maskBits = 0x0001 | 0x0002 | 0x0010;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        this.fixture = fixture;

        circle.dispose();

        texture = new Texture(Gdx.files.internal("healthBar.png"));
        healthBarSprite = new Sprite(texture,0,0,32,8);

        healthBarSprite.setScale(1f);

        this.createHealthBar(world);

        // actual hitbox

        enemyTexture = new Texture(Gdx.files.internal("bun.png"));
        enemySprite = new Sprite(enemyTexture,0,0,32,32);

        enemySprite.setScale(1f);

        //weewoo
        this.enemySprite.flip(true,false);

        //ENEMY DROPS TEST
        this.dropList.put(20,new WeaponBow(true));
        this.dropList.put(30,new WeaponBow2(true));
        this.dropList.put(40,new WeaponPistol(true));

        System.out.println("asdasd" + this.dropList.keySet());

    }

    public String getNameID() {
        return this.nameID;
    }

    public void executeContact(Entity secondEntity) {

        if (secondEntity.getNameID().equals("Box")) {

            Box removeBox = (Box) secondEntity;

            //destroy function
            world.addDestroyBody(removeBox.getBody());

            //System.out.println("removed");
        }

        if (secondEntity.getNameID().equals("Player")) {
            //
        }

        if (secondEntity.getNameID().equals("Projectile")) {

            Projectile removeProjectile = (Projectile) secondEntity;

            System.out.println("hit by projectile for " + this.takeDamage(removeProjectile.getDamage()));

        }

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

    public WorldInstance getWorldInstance() {
        return this.world;
    }

    public FixtureDef getFixtureDef() {
        return this.fixtureDef;
    }

    public CircleShape getCircleShape() {
        return this.circle;
    }

    public float getDamage() {
        return this.damage;
    }

    public float takeDamage(float damage) {

        System.out.println("LOLOLOL " + this.bodyB.getPosition().x);


        if (this.bodyB == null) {
            System.out.println("you got a null that will crash");
            return 1;
        }

        Array<Fixture> fixtureList = new Array<Fixture>(1);
        fixtureList = this.bodyB.getFixtureList();

        fixtureList.get(0).getShape();

        texture = new Texture(Gdx.files.internal("healthBar.png"));
        healthBarSprite = new Sprite(texture,0,0,32,8);

        //healthBarSprite.setScale(.1f);

        //MAKING PARTICLES
        MainApplication.damageParticleList.add(new ParticleDamage(this.body,damage,MainApplication.getParty().font, new Color(Color.GREEN)));

        if (health - damage <= 0 && this.hasNotDied) {
            System.out.println("died and time to try to drop item: ");
            try {
                MainApplication.damageParticleList.add(new ParticleItem(this.body, this.dropList, MainApplication.getParty().font, new Color(Color.GOLD)));
            } catch (Exception e)  {

            }
            this.hasNotDied = false;
        }

        //new

        health -= damage;

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

        newScale = (health/100f)*.1f;

        healthBarSprite.setScale(newScale/2,.05f);

        //this.body.applyLinearImpulse(new Vector2(0f,20f), this.body.getPosition(),false);
        this.bodyB.setLinearVelocity(0,20);
        this.bodyB.setGravityScale(0);
        healthBarSprite.setPosition(bodyB.getPosition().x-healthBarSprite.getWidth()/2,bodyB.getPosition().y-healthBarSprite.getHeight()/2);

    }

    public Sprite getHealthBarSprite() {
        return this.healthBarSprite;
    }

    public void createHealthBar(WorldInstance world) {

        Body bodyA = this.getBody();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(bodyA.getPosition().x,bodyA.getPosition().y + 1);

        this.bodyB = world.getWorld().createBody(bodyDef);

        this.bodyB.setUserData("Enemy Healthbar");

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1f, .1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = .00001f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit
        fixtureDef.filter.categoryBits = 0;

        Fixture fixture = bodyB.createFixture(fixtureDef);
        fixture.setUserData(this);


        DistanceJointDef defJoint = new DistanceJointDef();
        defJoint.initialize(bodyA, bodyB, bodyA.getPosition(), bodyB.getPosition());
        defJoint.length = 3f;

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

    public void updateEnemySprite() {

        if (this.body == null) {
            System.out.println("you got a null that will crash");
            return;
        }

        enemySprite.setPosition(body.getPosition().x-enemySprite.getWidth()/2,body.getPosition().y-enemySprite.getHeight()/2);
    }

    public void flip(boolean facingRight) {
        if(facingRight) {

            if (this.getEnemySprite().isFlipX()) {
                this.getEnemySprite().flip(true,false);
            }

        } else {

            if (!this.getEnemySprite().isFlipX()) {
                this.getEnemySprite().flip(true,false);
            }

        }
    }

    public Sprite getEnemySprite() {
        return this.enemySprite;
    }

    public void move(boolean facingRight) {

        //this.getBody().applyLinearImpulse(-1,0,this.getBody().getPosition().x,this.getBody().getPosition().y,false);

    }

    public void moveAttack(boolean facingRight) {

        //this.getBody().applyLinearImpulse(-1,0,this.getBody().getPosition().x,this.getBody().getPosition().y,false);

    }
}
