package io.BunnyRanger;

import static com.badlogic.gdx.Gdx.input;
import static io.BunnyRanger.GameHandler.fontShader;
import static io.BunnyRanger.WorldHandler.battleSizeHeight;
import static io.BunnyRanger.WorldHandler.battleSizeWidth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class ScreenLevel implements Screen {

    Viewport viewport;

    Texture backgroundTexture;
    Sprite vallyBackground;

    //tmp
    boolean reset = true;

    boolean drawSign;

    public static String levelName = "NULL";
    public static int levelDisplayCounter = 0;

    float timer = 0;


    //references to WorldHandler
    public static Party party;
    public static Enemies enemies;
    public static ArrayList<Wall> wallList;
    public static ArrayList<Sign> signList;
    public ArrayList<Particle> particleList;
    public ArrayList<Projectile> projectileList;
    public ArrayList<Projectile> projectileListRemove;
    public WorldHandler worldHandler;

    public void create() {

        backgroundTexture = new Texture(Gdx.files.internal("black.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);
        vallyBackground.setCenterX(battleSizeWidth/2f);
        vallyBackground.setCenterY(battleSizeHeight/2f);
        vallyBackground.setScale(1/2f);

        party = WorldHandler.getParty();
        enemies = WorldHandler.getEnemies();
        wallList = WorldHandler.wallList;
        signList = WorldHandler.signList;
        particleList = WorldHandler.particleList;
        projectileList = WorldHandler.projectileList;
        projectileListRemove = WorldHandler.projectileListRemove;
        worldHandler = new WorldHandler();

        viewport = new FitViewport(battleSizeWidth, battleSizeHeight, WorldHandler.camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

    }

    public void show() {

        WorldHandler.camera.position.set(battleSizeWidth / 2f, battleSizeHeight / 2f, 0);

        //Gdx.graphics.setForegroundFPS(80);

        if (reset) {
            System.out.println("showed first screen while reset");
        }

        Gdx.input.setInputProcessor(party.getInputProcessor());

    }

    public void render(float delta) {
        normal();
    }

    public void displayTitle(String name, int x, int y) {

        WorldHandler.camera.setToOrtho(false, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);
        WorldHandler.camera.update();
        GameHandler.batch.setProjectionMatrix(WorldHandler.camera.combined);

        BitmapFont font = GameHandler.getFont();

        font.getData().setScale(4f);
        GameHandler.batch.setShader(fontShader);
        WorldHandler.getParty().getGoldFont().draw(GameHandler.batch, name, x, y);
        GameHandler.batch.setShader(null);

        WorldHandler.camera.setToOrtho(false, battleSizeWidth, battleSizeHeight);
        WorldHandler.camera.update();
        GameHandler.batch.setProjectionMatrix(WorldHandler.camera.combined);

    }

    public void makeEnemies() {

    }

    public void dispose() {
        //super.dispose();
    }

    public void hide() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    private void normal() {

        for (int i = 0; i < 10; i++) {
            WorldHandler.getWorld().step(1 / 400f, 8, 3);
        }
        WorldHandler.getWorldHandler().destroyBodies();

        if (reset && enemies != null && enemies.enemyList.isEmpty()) {
            makeEnemies();
            reset = false;
        }

        //Unique

        ScreenUtils.clear(0, 0, 0, 0);

        //camera.update();
        //GameHandler.batch.setProjectionMatrix(camera.combined);

        GameHandler.batch.begin();

        WorldHandler.camera.setToOrtho(false, battleSizeWidth, battleSizeHeight);
        WorldHandler.camera.update();
        GameHandler.batch.setProjectionMatrix(WorldHandler.camera.combined);

        // Drawing goes here!

        vallyBackground.draw(GameHandler.batch);

        for (Wall wall : wallList) {
            wall.getSprite().draw(GameHandler.batch);
        }

        WorldHandler.getParty().updateParty(GameHandler.batch);

        try {
            enemies.update(GameHandler.batch);
        } catch (Exception e) {

        }

        if (drawSign && !WorldHandler.signList.isEmpty()) {
            WorldHandler.signList.get(0).updateEnemySprite();
            WorldHandler.signList.get(0).getEnemySprite().draw(GameHandler.batch);
        }

        // projectile stuffy

        //projectileList.removeIf(projectile -> projectile.spriteDestroyDelay <= 0);

        //update projectile list
        WorldHandler.projectileList.removeAll(WorldHandler.projectileListRemove);
        WorldHandler.projectileListRemove.clear();

        for (Projectile projectile : projectileList) {

            // projectile != null && projectile.body != null && projectile.body.getUserData() != null
            if (projectile.getProjectileSprite() != null) {
                projectile.getProjectileSprite().draw(GameHandler.batch);
            }

        }

        GameHandler.batch.end();

        ////

        GameHandler.batch.begin();

        GameHandler.batch.setProjectionMatrix(WorldHandler.textCamera.combined);

        GameHandler.batch.setShader(fontShader);

        ArrayList<Particle> tempParticles = new ArrayList<Particle>();

        for (Particle particle : particleList) {

            if (!particle.drawParticle(GameHandler.batch)) {

                tempParticles.add(particle);

            }

        }

        GameHandler.batch.setShader(null);

        particleList.removeAll(tempParticles);

        GameHandler.batch.setProjectionMatrix(WorldHandler.camera.combined);

        GameHandler.batch.end();

        // show sign
        if (!drawSign && enemies.checkAllIfDead()) {

            drawSign = true;
            WorldHandler.signList.get(0).changeBits();

        }

        // p is pressed
        if (input.isKeyJustPressed(Input.Keys.P)) {

        }

        // o is pressed
        if (input.isKeyJustPressed(Input.Keys.O)) {
            GameHandler.currentScreen = GameHandler.instance.getScreen();
            GameHandler.instance.setScreen(GameHandler.screenInventory);
        }

        // f is pressed
        if (input.isKeyJustPressed(Input.Keys.F1)) {
            if (Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setWindowedMode(1280, 720);
            } else {
                Graphics.DisplayMode dm = Gdx.graphics.getDisplayMode();
                Gdx.graphics.setFullscreenMode(dm);
                viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            }
        }

    }

    public void changeBackground(String path) {
        backgroundTexture = new Texture(Gdx.files.internal(path));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);
        vallyBackground.setCenterX(WorldHandler.battleSizeWidth/2f);
        vallyBackground.setCenterY(WorldHandler.battleSizeHeight/2f);
        vallyBackground.setScale(1/2f);
    }
}






