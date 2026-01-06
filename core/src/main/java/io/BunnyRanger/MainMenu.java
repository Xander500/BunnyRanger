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

public class MainMenu extends Game {

    public static boolean swappingScreen;
    public static int indexScreen;

    public static SpriteBatch batch;

    public static Game instance;

    public static MainMenuScreen mainMenuScreen;
    public static LevelScreenInn levelScreenInn;

    public static LevelScreen1 levelScreen1;
    public static LevelScreen2 levelScreen2;
    public static LevelScreen3 levelScreen3;
    public static LevelScreen levelScreen4;
    public static LevelScreen levelScreen5;

    public static InventoryScreen inventoryScreen;
    public static ShopScreen shopScreen;

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

        mainMenuScreen = new MainMenuScreen(this);
        inventoryScreen = new InventoryScreen(this);
        shopScreen = new ShopScreen(this);
        shopScreen.shopScreenStage.hydrate();

        //screenLevelList = new Screen[2];

        levelScreenInn = new LevelScreenInn();

        levelScreen1 = new LevelScreen1();
        levelScreen2 = new LevelScreen2();
        levelScreen3 = new LevelScreen3();
        levelScreen4 = new LevelScreen4();
        levelScreen5 = new LevelScreen5();


        this.setScreen(mainMenuScreen); // poggers

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

                for (Enemy enemy : MainApplication.getEnemies().enemyList) {

                    if (enemy != null && enemy.getBody() != null && enemy.getBody().getUserData() != null) {
                        MainApplication.world1.getWorld().destroyBody(enemy.getBody());
                    }

                    if (enemy != null && enemy.bodyB != null && enemy.bodyB.getUserData() != null) {
                        MainApplication.world1.getWorld().destroyBody(enemy.bodyB);
                    }
                }

                MainApplication.getEnemies().removeAll();

                for (Projectile projectile : MainApplication.projectileList) {

                    if (projectile.getBody() != null && projectile.getBody() != null && projectile.getBody().getUserData() != null) {
                        MainApplication.world1.getWorld().destroyBody(projectile.getBody());
                    }

                }

                MainApplication.projectileList.clear();

                for (Joint joint : Party.jointList) {

                    if (joint != null) {
                       // MainApplication.world1.getWorld().destroyJoint(joint);
                    }

                }

                System.out.println(8);
                for (Wall wall : MainApplication.wallList) {
                    if (wall != null && wall.getBody() != null && wall.getBody().getUserData() != null) {
                        MainApplication.world1.getWorld().destroyBody(wall.getBody());
                    }
                }
                MainApplication.wallList.clear();

                System.out.println(9);
                swappingScreen = false;
                System.out.println(10);

                for (int i = 0; i < MainApplication.signList.size(); i++) {
                    if (MainApplication.signList.size() >= 1 && MainApplication.signList.get(i) != null && MainApplication.signList.get(i).illDoIt) {
                        MainApplication.signList.get(i).executeSwap();
                    }
                }

                //MORE EXITS ADD MORE HERE, CAN CHANGE TO FOR LOOP BUT HONESTLY I DONT THINK WE WILL GET MORE THAN 4 EXITS
                // did it :)

                for (Sign sign : MainApplication.signList) {
                    MainApplication.world1.getWorld().destroyBody(sign.getBody());
                }

                MainApplication.signList.clear();

            } catch(Exception e) {
                System.out.println("error in MainMenu - destroying bodies between scene changes");
            }
        }

    }

    public void dispose() {

        batch.dispose();
        font.dispose();

    }

    public static void makeSign(Screen target) {
        Sign sign = new Sign(MainApplication.world1,35,6,0,target);
        MainApplication.signList.add(sign);
    }

    public static void makeSign(Screen target,int x,int y) {
        Sign sign = new Sign(MainApplication.world1,x,y,0,target);
        MainApplication.signList.add(sign);
    }

    static public BitmapFont getFont() {
        return font;
    }

}
