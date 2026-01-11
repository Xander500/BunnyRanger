package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class Party extends InputAdapter {

    Player player1;
    Player player2;
    Player player3;
    Player player4;

    float x;
    float y;

    Player[] playerList = new Player[4];

    int[] counterList = new int[4];

    ArrayList<Projectile> projectileList;

    //draggable

    public static Array<Joint> jointList = new Array<Joint>();

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();

    MouseJointDef jointDef = null;
    MouseJoint joint = null;

    Camera camera;
    WorldHandler world;

    BitmapFont font;

    //GOLD AND XP
    int gold = 100;
    int xp = 1;

    //position save

    boolean[] playerAliveStatusList = new boolean[4];

    public Party(WorldHandler world, float x, float y, Camera camera, Wall wall, ArrayList<Projectile> projectileList) {

        this.x = x;
        this.y = y;

        this.player1 = new Player(world,x,y,camera, wall,0);
        this.player2 = new Player(world,x+5,y,camera, wall,1);
        this.player3 = new Player(world,x+10,y,camera, wall,2);
        this.player4 = new Player(world,x+15,y,camera, wall,3);

        this.playerList[0] = this.player1;
        this.playerList[1] = this.player2;
        this.playerList[2] = this.player3;
        this.playerList[3] = this.player4;

        this.counterList[0] = 0;
        this.counterList[1] = 0;
        this.counterList[2] = 0;
        this.counterList[3] = 0;

        this.projectileList = projectileList;

        //draggable

        this.jointDef = new MouseJointDef();

        this.jointDef.bodyA = wall.getBody();
        this.jointDef.bodyB = playerList[0].getBody(); /////

        this.jointDef.collideConnected = true;
        this.jointDef.maxForce = 5000;

        this.camera = camera;
        this.world = world;

        //temp weapons so game does not crash <_>

        WeaponBow bow1 = new WeaponBow(true);
        WeaponBow bow2 = new WeaponBow(true);
        WeaponBow bow3 = new WeaponBow(true);
        WeaponBow bow4 = new WeaponBow(true);

        this.playerList[0].addWeapon(bow1);
        this.playerList[1].addWeapon(bow2);
        this.playerList[2].addWeapon(bow3);
        this.playerList[3].addWeapon(bow4);

        font = GameHandler.getFont();
        font.setColor(Color.WHITE);

        font.getData().scale(1);


    }

    public void damageAll(float damage) {
        for (int i = 0; i < 4; i++) {
            System.out.println("Damaged all due to dead player for " + damage);
            this.playerList[i].takeDamage(damage);
        }
    }

    public void resetParty(int pX,int pY) {

        this.player1.setHealth(player1.maxHealth);
        this.player2.setHealth(player2.maxHealth);
        this.player3.setHealth(player3.maxHealth);
        this.player4.setHealth(player4.maxHealth);

        this.player1.getBody().setTransform(pX*16,pY*16,0);
        this.player2.getBody().setTransform((pX+2)*16,pY*16,0);
        this.player3.getBody().setTransform((pX+4)*16,pY*16,0);
        this.player4.getBody().setTransform((pX+6)*16,pY*16,0);

        this.player1.getBody().setLinearVelocity(0,0);
        this.player2.getBody().setLinearVelocity(0,0);
        this.player3.getBody().setLinearVelocity(0,0);
        this.player4.getBody().setLinearVelocity(0,0);

        System.out.println("aa");

    }

    public void resetPartyInventory() {

        this.player1.setHealth(player1.maxHealth);
        this.player2.setHealth(player2.maxHealth);
        this.player3.setHealth(player3.maxHealth);
        this.player4.setHealth(player4.maxHealth);

        this.player1.getBody().setTransform(10*16,30*16,0);
        this.player2.getBody().setTransform((10+2)*16,30*16,0);
        this.player3.getBody().setTransform((10+4)*16,30*16,0);
        this.player4.getBody().setTransform((10+6)*16,30*16,0);

        this.player1.getBody().setLinearVelocity(0,0);
        this.player2.getBody().setLinearVelocity(0,0);
        this.player3.getBody().setLinearVelocity(0,0);
        this.player4.getBody().setLinearVelocity(0,0);

        System.out.println("aa");

    }

    public Player getPlayer(int i) {
        return this.playerList[i];
    }

    public void populatePartyWeapons() {
        //
    }

    public void updateParty(Batch batch) {

        for (int i = 0; i < 4; i++) {

            if (playerList[i].checkIfDead()) {

                // WAITS UNTIL EVERYONE DIES
                if (playerList[0].checkIfDead() && playerList[1].checkIfDead() && playerList[2].checkIfDead() && playerList[3].checkIfDead()) {
                    System.out.println("DEAD LOL");
                    break;
                }

                // KILLS PLAYERS /////////////
                for (Fixture fixture : playerList[i].body.getFixtureList()) {

                    // Get the current filter data
                    Filter filter = fixture.getFilterData();

                    // Set the new category bits
                    filter.categoryBits = 0x2000;
                    filter.maskBits = 0x0001;
                    // Apply the updated filter data to the fixture
                    fixture.setFilterData(filter);

                    playerAliveStatusList[i] = false;
                }


            } else {

                playerList[i].updateHealthBar();

                if (playerList[i].grounded()) {
                    counterList[i]++;
                } else {
                    counterList[i] = 0;
                }

                if (counterList[i] > playerList[i].getCurrentWeapon().getDelay(5)) {

                    ((Weapon) playerList[i].getCurrentWeapon()).getClosestTarget();
                    playerList[i].useWeapon();
                    counterList[i] = 0;

                }


                playerList[i].drawWeapon();

                //1234
                //projectileList.addAll(playerList[i].getCurrentWeapon().getProjectiles());

                Gdx.input.setInputProcessor(this);

                this.drawAll(i, batch);

                if (!playerAliveStatusList[i]) {

                    for (Fixture fixture : playerList[i].body.getFixtureList()) {

                        // Get the current filter data
                        Filter filter = fixture.getFilterData();

                        // Set the new category bits
                        filter.categoryBits = 0x0002;
                        filter.maskBits = 0x0004 | 0x0001 | 0x0020;
                        // Apply the updated filter data to the fixture
                        fixture.setFilterData(filter);

                    }

                    playerAliveStatusList[i] = true;

                }

            }

        }

    }

    public void drawAll(int i, Batch batch) {

        playerList[i].getHealthBarSpriteBack().draw(batch);
        playerList[i].getHealthBarSprite().draw(batch);

        playerList[i].updatePlayerSprite();

        playerList[i].getPlayerSprite().draw(batch);

        playerList[i].getWeaponSprite().draw(batch);

    }

    //DRAGGING

    QueryCallback queryCallback = new QueryCallback() {

        public boolean reportFixture(Fixture fixture) {

            if (!(fixture.getUserData() instanceof Player && ((Player) fixture.getUserData()).body.getUserData().equals("Player body"))) {
                return true;
            }

            Gdx.graphics.setCursor(WorldHandler.grab);

            int i = ((Player) fixture.getUserData()).number;

            jointDef.bodyB = playerList[i].getBody();
            jointDef.target.set(playerList[i].getBody().getPosition().x - player1.getPlayerSprite().getWidth() / 2,playerList[i].getBody().getPosition().y + player1.getPlayerSprite().getHeight() / 2);

            jointDef.maxForce = 3000; // max force it apply on trying to drag an object
            jointDef.frequencyHz = 2f; // how snappy and responsive it is
            jointDef.dampingRatio = .3f; // how fast to goes without overshooting 0 - 1 where 1 is no overshooting


            joint = (MouseJoint) world.getWorld().createJoint(jointDef);

            jointList.add(joint);

            return false;
        }
    };

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        tmp.set(screenX,screenY,0);
        tmp = camera.unproject(tmp);

        this.world.getWorld().QueryAABB(queryCallback, tmp.x -8, tmp.y -24, tmp.x+24, tmp.y +24);
        return true;

    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        removeMouseJoints();
        return true;
    }

    public void removeMouseJoints() {

        Gdx.graphics.setCursor(WorldHandler.open);

        for (Joint joint : jointList) {

            if (joint != null) {
                world.getWorld().destroyJoint(joint);
                this.joint = null;
            }

        }

        jointList.clear();
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {

            if (this.joint == null || world.getWorld().isLocked()) {
                return false;
            }

            tmp.set(screenX, screenY, 0);
            tmp = camera.unproject(tmp);

            joint.setTarget(tmp2.set(tmp.x, tmp.y));

        return true;
    }

    public InputProcessor getInputProcessor() {
        return this;
    }

    public BitmapFont getGoldFont() {
        return font;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int i) {
        this.gold = i;
    }

    public void addGold(int i) {
        this.gold += i;
    }

    public boolean testGold(int i) {
        return gold >= i;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int i) {
        this.xp = i;
    }

    public void addXp(int i) {
        this.xp += i;
    }

    public boolean testLevelUp(int i) {
        return this.xp >= i;
    }

}
