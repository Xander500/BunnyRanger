package io.BunnyRanger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.ArrayList;

public class MainApplication extends ApplicationAdapter {

    public static final int SCREENWIDTH = 1280;
    public static final int SCREENHEIGHT = 720;
    public static boolean needToMake = true;

    // make and hold an instance of a world
    //Box box1;
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;
    OrthographicCamera textCamera;
    MyContactListener myContactListener;

    static public int battleSizeWidth;
    static public int battleSizeHeight;

    SpriteBatch batch;

    // projectiles

    static public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    static public ArrayList<Projectile> projectileListRemove = new ArrayList<Projectile>();

    //players

    public static WorldInstance world1 = new WorldInstance();
    public static Floor floor = new Floor(world1,1000,1000,1,1,"dirt.png");
    static public Party party;

    //enemies

    static public Enemies enemies;

    //Floor

    static public ArrayList<Floor> floorList = new ArrayList<Floor>();

    //Signs

    static public ArrayList<Sign> signList = new ArrayList<Sign>();

    //DamageParticles

    static public ArrayList<ParticleDamage> damageParticleList = new ArrayList<ParticleDamage>();

    //level name

    public MainApplication() {
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        //test
        battleSizeWidth = (int) (16*40f);
        battleSizeHeight = (int) (9*40f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, battleSizeWidth, battleSizeHeight);

        textCamera = new OrthographicCamera();
        textCamera.setToOrtho(false, SCREENWIDTH, SCREENWIDTH);
    }

    public void create() {

        if(needToMake) {

            party = new Party(world1, 10, 5, camera, floor, projectileList);

            enemies = new Enemies(world1, projectileList);

            needToMake = false;

        }

    }

    public void render() {

        super.render();

    }

    static Party getParty() {
        return party;
    }
    static Enemies getEnemies() {
        return enemies;
    }
}
