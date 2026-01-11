package io.BunnyRanger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.physics.box2d.Joint;

public class GameHandler extends Game {

    public static boolean swappingScreen;
    public static int indexScreen;

    public static SpriteBatch batch;

    public static Game instance;
    private static WorldHandler worldHandler;

    public static ScreenMainMenu screenMainMenu;
    public static ScreenLevelInn levelScreenInn;

    public static ScreenLevel screenLevel1;
    public static ScreenLevel screenLevel2;
    public static ScreenLevel screenLevel3;
    public static ScreenLevel screenLevel4;
    public static ScreenLevel screenLevel5;

    public static ScreenInventory screenInventory;
    public static ScreenShop screenShop;

    public static Screen[] screenLevelList;

    public static Screen currentScreen;

    public static BitmapFont font; // use libGDX's default Arial font
    private static final float TIME_STEP = 1 / 60f;
    private static float timer = 0;

    public static ShaderProgram fontShader;

    public void create() {

        instance = this;

        Texture texture = new Texture(Gdx.files.internal("pixelFirst.png"), true); // true enables mipmaps

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

        font = new BitmapFont(Gdx.files.internal("pixelFirst.fnt"), new TextureRegion(texture), false);

        fontShader = new ShaderProgram(Gdx.files.internal("font.vert"), Gdx.files.internal("font.frag"));

        if (!fontShader.isCompiled()) {
            Gdx.app.error("fontShader", "compilation failed:\n" + fontShader.getLog());
        }

        batch = new SpriteBatch();

        screenMainMenu = new ScreenMainMenu();
        screenInventory = new ScreenInventory();
        screenShop = new ScreenShop();
        screenShop.shopScreenStage.hydrate();

        //screenLevelList = new Screen[2];

        levelScreenInn = new ScreenLevelInn();
        levelScreenInn.create();

        screenLevel1 = new ScreenLevel1();
        screenLevel1.create();
        screenLevel2 = new ScreenLevel2();
        screenLevel2.create();
        screenLevel3 = new ScreenLevel3();
        screenLevel3.create();
        screenLevel4 = new ScreenLevel4();
        screenLevel4.create();
        screenLevel5 = new ScreenLevel5();
        screenLevel5.create();

        worldHandler = new WorldHandler();

        this.setScreen(screenMainMenu); // poggers

    }

    public void render() {

        timer += Math.min(Gdx.graphics.getDeltaTime(), 0.25f);

        if (timer >= TIME_STEP) {
            timer -= TIME_STEP;
            //System.out.println("GO OFF QUEEN");
            super.render(); // important!

        } else {
            //System.out.println("NOT ENOUGH TIME");
            return;
        }

        //physics stuff


        if (swappingScreen) {
            try {
                for (Enemy enemy : WorldHandler.getEnemies().enemyList) {

                    if (enemy != null && enemy.getBody() != null && enemy.getBody().getUserData() != null) {
                        WorldHandler.getWorld().destroyBody(enemy.getBody());
                    }

                    if (enemy != null && enemy.bodyB != null && enemy.bodyB.getUserData() != null) {
                        WorldHandler.getWorld().destroyBody(enemy.bodyB);
                    }
                }

                WorldHandler.getEnemies().removeAll();

                for (Projectile projectile : WorldHandler.projectileList) {

                    if (projectile.getBody() != null && projectile.getBody().getUserData() != null) {
                        WorldHandler.getWorld().destroyBody(projectile.getBody());
                    }

                }

                WorldHandler.projectileList.clear();

                for (Joint joint : Party.jointList) {

                    if (joint != null) {
                       // MainApplication.getWorld().destroyJoint(joint);
                    }

                }

                System.out.println(8);
                for (Wall wall : WorldHandler.wallList) {
                    if (wall != null && wall.getBody() != null && wall.getBody().getUserData() != null) {
                        WorldHandler.getWorld().destroyBody(wall.getBody());
                    }
                }
                WorldHandler.wallList.clear();

                System.out.println(9);
                swappingScreen = false;
                System.out.println(10);

                for (int i = 0; i < WorldHandler.signList.size(); i++) {
                    if (WorldHandler.signList.get(i) != null && WorldHandler.signList.get(i).illDoIt) {
                        WorldHandler.signList.get(i).executeSwap();
                    }
                }

                //MORE EXITS ADD MORE HERE, CAN CHANGE TO FOR LOOP BUT HONESTLY I DONT THINK WE WILL GET MORE THAN 4 EXITS
                // did it :)

                for (Sign sign : WorldHandler.signList) {
                    WorldHandler.getWorld().destroyBody(sign.getBody());
                }

                WorldHandler.signList.clear();

            } catch(Exception e) {
                System.out.println("error in MainMenu - destroying bodies between scene changes");
            }
        }

    }

    public void dispose() {

        GameHandler.batch.dispose();
        font.dispose();

    }

    public static void makeSign(Screen target) {
        Sign sign = new Sign(WorldHandler.getWorldHandler(),35,6,0,target);
        WorldHandler.signList.add(sign);
    }

    public static void makeSign(Screen target,int x,int y) {
        Sign sign = new Sign(WorldHandler.getWorldHandler(),x,y,0,target);
        WorldHandler.signList.add(sign);
    }

    static public BitmapFont getFont() {
        return font;
    }

}
