package io.BunnyRanger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
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
    static OrthographicCamera camera;
    static OrthographicCamera textCamera;
    MyContactListener myContactListener;

    static public int battleSizeWidth;
    static public int battleSizeHeight;

    SpriteBatch batch;

    // projectiles

    static public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    static public ArrayList<Projectile> projectileListRemove = new ArrayList<Projectile>();

    //players

    public static WorldInstance world1 = new WorldInstance();
    public static Party party;

    //enemies

    public static Enemies enemies;

    //Floor

    public static ArrayList<Wall> wallList = new ArrayList<Wall>();

    //Signs

    public static ArrayList<Sign> signList = new ArrayList<Sign>();

    //DamageParticles

    public static ArrayList<Particle> particleList = new ArrayList<Particle>();

    //level name

    //cursors

    public static Cursor regular;
    public static Cursor grab;
    public static Cursor open;

    public MainApplication() {
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        //test
        battleSizeWidth = (16*40);
        battleSizeHeight = (9*40);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, battleSizeWidth, battleSizeHeight);

        textCamera = new OrthographicCamera();
        textCamera.setToOrtho(false, SCREENWIDTH, SCREENWIDTH);

        // cursor
        regular = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("pointerA.png")), 0, 0);
        open = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("pointerC.png")), 0, 0);
        grab = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("pointerB.png")), 0, 0);

        Gdx.graphics.setCursor(open);
    }

    public void create() {

        if(needToMake) {

            party = new Party(world1, 10, 5, camera, new Wall(world1,9999,9999,1,1,"dirt.png"), projectileList);

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
