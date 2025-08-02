package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

import java.util.ArrayList;
import java.util.Random;

public class WeaponBow implements Weapon, Item {

    //Bow stuff
    ProjectileArrow type;
    float bowX;
    float bowY;
    float bowAngle;
    Texture bowTexture;
    Sprite bowSprite;
    Batch batch;
    boolean facingRight;

    String name;

    WorldInstance world;
    Entity entity;

    //calc stuff
    float enemyPositionX;
    float enemyPositionY;
    float enemyPositionXVel;
    float enemyPositionYVel;
    float rangeArrow;

    //ArrowProjectile stuff
    float xArrow;
    float yArrow;
    float xSizeArrow;
    float ySizeArrow;
    float angleArrow;
    Vector2 magnitudeArrow;
    float damageMaxArrow;
    float damageMinArrow;
    float damageCurrentArrow;
    int count;
    float disBetweenShotsX;
    float disBetweenShotsY;
    float densityArrow;
    boolean friendly;

    float weaponDelay;

    // for stupid calculation, don't touchy
    float far;

    ArrayList<Projectile> projectileList = MainApplication.projectileList;
    int sellPrice = 5;
    int buyPrice = 10;

    // shop

    Texture inventoryTexture;

    // CARDS

    int baseCount;
    float baseWeaponDelay;
    float baseDamageMinArrow;
    float baseDamageMaxArrow;
    float baseRangeArrow;

    float distanceToClostestTarget;

    public WeaponBow(boolean friendly) {

        name = "WoodBow";

        this.bowTexture = new Texture(Gdx.files.internal("bow1.png"));
        this.bowSprite = new Sprite(bowTexture,0,0,16,16);
        this.bowSprite.setScale(1f);
        this.bowAngle = 0;

        this.xSizeArrow = .5f;
        this.ySizeArrow = .25f;

        //might break stuff
        this.disBetweenShotsX = 5;
        this.disBetweenShotsY = 0;

        this.angleArrow = (float) Math.PI/6;

        this.densityArrow = 1;

        this.friendly = friendly;

        this.damageMaxArrow = 2;
        this.baseDamageMaxArrow = 2;

        this.damageMinArrow = 1;
        this.baseDamageMinArrow = 1;

        this.count = 1;
        this.baseCount = 1;

        this.rangeArrow = 50;
        this.baseRangeArrow = 50;

        this.weaponDelay = 100;
        this.baseWeaponDelay = 100;

        if (friendly) {
            this.facingRight = true;
        } else {
            this.facingRight = false;
        }

        distanceToClostestTarget = 0;

        // shop

        this.inventoryTexture = new Texture(Gdx.files.internal("bow1.png"));

    }

    public Sprite getWeaponBowSprite() {
        return this.bowSprite;
    }

    public Weapon createWeapon() {
        return this;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        this.world = entity.getWorldInstance();
    }

    public Entity getEntity() {
        return this.entity;
    }

    public Sprite getSprite() {
        return this.bowSprite;
    }

    public void drawWeapon() {

        this.bowX = entity.getBody().getPosition().x;
        this.bowY = entity.getBody().getPosition().y;
        //this.bowAngle = player.getBody().getAngle(); no

        this.getEntity().flip(this.facingRight);

        if(this.facingRight) {

            if (this.bowSprite.isFlipX()) {
                this.bowSprite.flip(true,false);
            }
            this.bowSprite.setPosition(this.bowX - this.bowSprite.getWidth()/2 + entity.getFixture().getShape().getRadius() * 1.5f, this.bowY - this.bowSprite.getWidth());

        } else {

            if (!this.bowSprite.isFlipX()) {
                this.bowSprite.flip(true,false);
            }
            this.bowSprite.setPosition(this.bowX - this.bowSprite.getWidth()/2 - entity.getFixture().getShape().getRadius() * 1.5f, this.bowY - this.bowSprite.getWidth());

        }

        this.bowSprite.setRotation(this.bowAngle); // maybe no

    }

    public void weaponProjectileFactory() {

        if (Math.abs(enemyPositionX - bowX) > rangeArrow) {
            return;
        }

        if (this.facingRight) {
            this.xArrow = this.bowX + entity.getFixture().getShape().getRadius() * 2;
        } else {
            this.xArrow = this.bowX - entity.getFixture().getShape().getRadius() * 2;
        }

        this.yArrow = this.bowY;

        for (float i = 0-this.count/2f; i < this.count/2f; i++) {

            this.magnitudeArrow = getArrowMagnitude(enemyPositionX,enemyPositionY,this.xArrow,this.yArrow,enemyPositionXVel,enemyPositionYVel);

            this.magnitudeArrow.x = this.magnitudeArrow.x;
            this.magnitudeArrow.y = this.magnitudeArrow.y;

            //this.magnitudeArrow.x += i*this.disBetweenShotsX/30f;
            //this.magnitudeArrow.y += i*this.disBetweenShotsY/30f;

            this.damageCurrentArrow = (float) Math.round((Math.random() * (damageMaxArrow - damageMinArrow)) + damageMinArrow);

            Projectile projectile = new ProjectileArrow(world, xArrow, yArrow, xSizeArrow, ySizeArrow, angleArrow, magnitudeArrow, damageCurrentArrow, densityArrow, friendly, facingRight);

            this.projectileList.add(projectile);

        }

    }

    public ArrayList<Projectile> getProjectiles() {

        //ArrayList<Projectile> tmp = (ArrayList<Projectile>) this.projectileList.clone();

        //this.projectileList.clear();

        return null;

    }

    public void removeDestroyedProjectiles() {

        projectileList.removeIf(projectile -> projectile.spriteDestroyDelay <= 0);

    }

    //calc stuffy wuffy

    public Vector2 getArrowMagnitude(float EnemyPositionX,float EnemyPositionY,float bowpX,float bowpY,float xVel,float yVel) {

        //the worst algorithm, feel free to fine tune it james

        float t = 2f;


        double dx = (EnemyPositionX - bowpX) + xVel * t;
        double dy = (EnemyPositionY - bowpY);

        /*
        if (!friendly) {
            dx = (EnemyPositionX - bowpX);
            dy = (EnemyPositionY - bowpY) + yVel * t;
        }
         */

        if (!friendly) {
            dx = (EnemyPositionX - bowpX);
            dy = (EnemyPositionY - bowpY) + yVel * t;
        }

        double vx = dx / t;
        double vy = (dy - (0.5 * world.getWorld().getGravity().y) * t * t) / t;

        if(!facingRight) {
            //vx += 20;
        } else {
            //vx -= 20;
        }

        return new Vector2((float) vx, (float) vy);

        //float temp = (EnemyPositionX - bowX)*12f + 12;
    }

    QueryCallback queryCallback = new QueryCallback() {

        public boolean reportFixture(Fixture fixture) {

            if (friendly && !(fixture.getUserData() instanceof Enemy)) {
                return true;
            }

            if (!friendly && !(fixture.getUserData() instanceof Player)) {
                return true;
            }

            if (((Damageable) fixture.getUserData()).checkIfDead()) {
                return true;
            }

            if (fixture.getUserData() instanceof Sign) {
                return true;
            }

            float fun = ((Entity) fixture.getUserData()).getBody().getPosition().x;

            if (Math.abs(bowX - fun) < far) {

                if (bowX - fun <= 0) {
                    facingRight = true;
                } else {
                    facingRight = false;
                }

            }

            if (Math.abs(bowX - fun) < far) { // get this working

                far = Math.abs(bowX - fun);

                enemyPositionX = ((Entity) fixture.getUserData()).getBody().getPosition().x;
                enemyPositionY = ((Entity) fixture.getUserData()).getBody().getPosition().y;

                enemyPositionXVel = ((Entity) fixture.getUserData()).getBody().getLinearVelocity().x;
                enemyPositionYVel = ((Entity) fixture.getUserData()).getBody().getLinearVelocity().y;

            }

            distanceToClostestTarget = Math.abs(bowX - fun);

            return true;

        }
    };

    public void getClosestTarget() {

        //maybe resets closest target
        enemyPositionX = 1001;
        enemyPositionY = 1001;

        enemyPositionXVel = 0;
        enemyPositionYVel = 0;

        far = 10001;

        this.world.getWorld().QueryAABB(queryCallback,this.bowX-this.rangeArrow,this.bowY-this.rangeArrow,this.bowX+this.rangeArrow,this.bowY+this.rangeArrow);
    }

    public String getName() {
        return this.name;
    }

    public int getDamageMax() {
        return (int) this.damageMaxArrow;
    }

    public int getDamageMin() {
        return (int) this.damageMinArrow;
    }

    public int getCount() {
        return this.count;
    }

    public int getRange() {
        return (int) this.rangeArrow;
    }

    public int getBuy() {
        return this.buyPrice;
    }

    public int getBaseProjectile() {
        return this.baseCount;
    }

    public float getBaseRange() {
        return -1;
    }

    public float getBaseMaxDamage() {
        return -1;
    }

    public float getBaseMinDamage() {
        return -1;
    }

    public float getBaseDelay() {
        return -1;
    }

    public void resetToBase() {

        this.count = this.baseCount;
        this.rangeArrow = baseRangeArrow;
        this.damageMinArrow = baseDamageMinArrow;
        this.damageMaxArrow =  baseDamageMaxArrow;
        this.weaponDelay = baseWeaponDelay;

    }

    @Override
    public boolean getDirection() {
        return facingRight;
    }

    public Texture getTexture() {
        return this.bowTexture;
    }

    public Texture getInventoryTexture() {
        return this.inventoryTexture;
    }

    public int getBuyPrice() {
        return this.buyPrice;
    }

    public int getSellPrice() {
        return this.sellPrice;
    }

    public float getDelay() {
        return this.weaponDelay;
    }

    public void setProjectileCount(int num) {
        this.count = num;
    }
}
