package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class WorldHandler {

    private static WorldHandler worldHandler;
    private static World world;
    private WorldContactListener worldContactListener;
    Array<Body> bodyDestroyList;
    boolean removeingTime;

    public static final int SCREENWIDTH = 1280;
    public static final int SCREENHEIGHT = 720;

    // make and hold an instance of a world
    //Box box1;
    Box2DDebugRenderer debugRenderer;
    static OrthographicCamera camera;
    static OrthographicCamera textCamera;
    static public int battleSizeWidth;
    static public int battleSizeHeight;

    //players
    public static Party party;
    //enemies
    public static Enemies enemies;
    //Wall
    public static ArrayList<Wall> wallList = new ArrayList<Wall>();
    //Signs
    public static ArrayList<Sign> signList = new ArrayList<Sign>();
    //DamageParticles
    public static ArrayList<Particle> particleList = new ArrayList<Particle>();
    // projectiles
    static public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    static public ArrayList<Projectile> projectileListRemove = new ArrayList<Projectile>();
    //level name

    //cursors
    public static Cursor regular;
    public static Cursor grab;
    public static Cursor open;

    public WorldHandler() {

        worldHandler = this;
        this.worldContactListener = new WorldContactListener();

        world = new World(new Vector2(0, -80), false);
        world.setContactListener(this.worldContactListener);

        debugRenderer = new Box2DDebugRenderer();

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

        party = new Party(worldHandler, 10, 5, camera, new Wall(worldHandler,9999,9999,1,1,"dirt.png"), projectileList);

        enemies = new Enemies(worldHandler, projectileList);

    }

    public static World getWorld() {
        return world;
    }
    public static WorldHandler getWorldHandler() {
        return worldHandler;
    }

    public void destroyBodies() {

        if (!removeingTime) {
            return;
        }

        this.bodyDestroyList = new Array<Body>(world.getBodyCount());
        world.getBodies(bodyDestroyList);

        for (Body body : bodyDestroyList) {
            if (body != null && !(body.getUserData() == null) && body.getUserData().equals("remove")) {
                world.destroyBody(body);
            }
        }
        this.removeingTime = false;

    }

    public void addDestroyBody(Body body) {
        body.setUserData("remove");
        this.removeingTime = true;
    }

    static Party getParty() {
        return party;
    }
    static Enemies getEnemies() {
        return enemies;
    }

}
