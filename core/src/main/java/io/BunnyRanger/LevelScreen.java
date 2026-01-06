package io.BunnyRanger;

import static com.badlogic.gdx.Gdx.input;
import static io.BunnyRanger.MainMenu.fontShader;

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

public class LevelScreen extends MainApplication implements Screen, ScreenType {

    Screen screen;
    Viewport viewport = new FitViewport(battleSizeWidth, battleSizeHeight, camera);

    Texture backgroundTexture;
    Sprite vallyBackground;

    //tmp

    boolean reset = true;

    boolean alreadyShown = false;

    boolean drawSign;

    public static String levelName = "NULL";
    public static int levelDisplayCounter = 0;

    float timer = 0;

    public void show() {

        viewport = new FitViewport(battleSizeWidth, battleSizeHeight, camera);
        camera.position.set(battleSizeWidth / 2f, battleSizeHeight / 2f, 0);

        //Gdx.graphics.setForegroundFPS(80);

        if (reset) {
            System.out.println("showed first screen while reset");
        }

        if (alreadyShown) {
            return;
        }

        super.create();

        //sign = new Sign(world1,60,20,0,MainMenu.secondLevelScreen);

        //Unique
        //make world

        backgroundTexture = new Texture(Gdx.files.internal("black.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, MainApplication.SCREENWIDTH,MainApplication.SCREENHEIGHT);
        vallyBackground.setCenterX(MainApplication.battleSizeWidth/2f);
        vallyBackground.setCenterY(MainApplication.battleSizeHeight/2f);
        vallyBackground.setScale(1/2f);


        this.myContactListener = new MyContactListener();

        world1.getWorld().setContactListener(myContactListener);

        //Box Box1 = new Box(world1.getWorld(),32,16);

    }

    public void render(float delta) {

        super.render();

        normal();

    }

    public void displayTitle(String name, int x, int y) {

        camera.setToOrtho(false, MainApplication.SCREENWIDTH, MainApplication.SCREENHEIGHT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        BitmapFont font = MainMenu.getFont();

        font.getData().setScale(4f);
        batch.setShader(fontShader);
        MainApplication.getParty().getGoldFont().draw(batch, name, x, y);
        batch.setShader(null);

        camera.setToOrtho(false, battleSizeWidth, battleSizeHeight);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

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

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    private void normal() {

        for (int i = 0; i < 10; i++) {
            world1.getWorld().step(1 / 400f, 8, 3);
        }
        world1.destroyBodies();

        if (reset && enemies != null && enemies.enemyList.isEmpty()) {

            makeEnemies();

            reset = false;

        }

        //Unique

        ScreenUtils.clear(0, 0, 0, 0);

        //camera.update();
        //batch.setProjectionMatrix(camera.combined);

        batch.begin();

        camera.setToOrtho(false, battleSizeWidth, battleSizeHeight);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Drawing goes here!

        vallyBackground.draw(batch);

        for (Wall wall : wallList) {
            wall.getSprite().draw(batch);
        }

        party.updateParty(batch);

        try {
            enemies.update(batch);
        } catch (Exception e) {

        }

        if (drawSign) {
            MainApplication.signList.get(0).updateEnemySprite();
            MainApplication.signList.get(0).getEnemySprite().draw(batch);
        }

        // projectile stuffy

        //projectileList.removeIf(projectile -> projectile.spriteDestroyDelay <= 0);

        //update projectile list
        MainApplication.projectileList.removeAll(MainApplication.projectileListRemove);
        MainApplication.projectileListRemove.clear();

        for (Projectile projectile : projectileList) {

            // projectile != null && projectile.body != null && projectile.body.getUserData() != null
            if (projectile.getProjectileSprite() != null) {
                projectile.getProjectileSprite().draw(batch);
            }

        }

        batch.end();

        ////

        batch.begin();

        batch.setProjectionMatrix(textCamera.combined);

        batch.setShader(fontShader);

        ArrayList<Particle> tempParticles = new ArrayList<Particle>();

        for (Particle particle : particleList) {

            if (!particle.drawParticle(batch)) {

                tempParticles.add(particle);

            }

        }

        batch.setShader(null);

        particleList.removeAll(tempParticles);

        batch.setProjectionMatrix(camera.combined);

        batch.end();

        // show sign
        if (!drawSign && enemies.checkAllIfDead()) {

            alreadyShown = true;
            drawSign = true;
            MainApplication.signList.get(0).changeBits();

        }

        //maybe
        //Gdx.input.setInputProcessor(party.getInputProcessor());

        // p is pressed
        if (input.isKeyJustPressed(Input.Keys.P)) {

        }

        // o is pressed
        if (input.isKeyJustPressed(Input.Keys.O)) {
            MainMenu.currentScreen = MainMenu.instance.getScreen();
            alreadyShown = true;
            MainMenu.instance.setScreen(MainMenu.inventoryScreen);
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

}






