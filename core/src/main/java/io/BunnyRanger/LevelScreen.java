package io.BunnyRanger;

import static io.BunnyRanger.MainMenu.fontShader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import jdk.internal.net.http.common.Log;

public class LevelScreen extends MainApplication implements Screen, ScreenType {

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

        backgroundTexture = new Texture(Gdx.files.internal("vally.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, 1280, 720);
        vallyBackground.setCenterX(32);
        vallyBackground.setCenterY(16);
        vallyBackground.setScale(.05f);

        this.myContactListener = new MyContactListener();

        world1.getWorld().setContactListener(myContactListener);

        //Box Box1 = new Box(world1.getWorld(),32,16);

    }

    public void render(float delta) {

        super.render();

        manualSlow();

        //normal();

    }

    public void displayTitle(String name, int x, int y) {

        camera.setToOrtho(false, 1280f,720f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        BitmapFont font = MainMenu.getFont();

        font.getData().setScale(4f);
        batch.setShader(fontShader);
        MainApplication.getParty().getGoldFont().draw(batch,name,x,y);
        batch.setShader(null);

        camera.setToOrtho(false, 64, 32);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        levelDisplayCounter += 1;

    }

    public void makeEnemies() {

    }

    public void dispose() {
        //super.dispose();
    }

    public void hide() {

    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    private void normal() {

        world1.getWorld().step(1 / 60f, 6, 2);

        world1.destroyBodies();

        Array<Body> bodies = new Array<Body>();
        MainApplication.world1.getWorld().getBodies(bodies);

        //System.out.println(bodies);
        int count = 0;
        for (Body body : bodies) {
            count++;
            //System.out.println(Gdx.graphics.getFramesPerSecond());
            //System.out.println(body.getUserData() + " " + count);

            if (body.getUserData() == null) {
                //System.out.println(body.getType());
            }
        }
        count = 0;

        //System.out.println(delta*60);

        if (reset && enemies != null && enemies.enemyList.isEmpty()) {

            makeEnemies();

            reset = false;

        }

        //Unique

        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        vallyBackground.draw(batch);

        // Drawing goes here!

        for (Floor floor : floorList) {
            floor.getSprite().draw(batch);
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

        ArrayList<DamageParticle> tempParticles = new ArrayList<DamageParticle>();

        for (DamageParticle particle : damageParticleList) {

            if (!particle.drawParticle(batch)) {

                tempParticles.add(particle);

            }

        }

        batch.setShader(null);

        damageParticleList.removeAll(tempParticles);

        batch.setProjectionMatrix(camera.combined);

        batch.end();

        if (Gdx.input.isKeyPressed(43)) {
            MainMenu.currentScreen = MainMenu.instance.getScreen();
            alreadyShown = true;
            MainMenu.instance.setScreen(MainMenu.inventoryScreen);
        }

        if (Gdx.input.isKeyPressed(44)) {

        }

        if (!drawSign && enemies.checkAllIfDead()) {

            alreadyShown = true;
            drawSign = true;
            MainApplication.signList.get(0).changeBits();

        }

        Gdx.input.setInputProcessor(party.getInputProcessor());
    }
    private void manualSlow() {
        timer += Gdx.graphics.getDeltaTime();
        if (timer <= 1/140f) {
            //return;
        } else {
            timer = 0;

            world1.getWorld().step(1 / 60f, 6, 2);

            world1.destroyBodies();

            if (reset && enemies != null && enemies.enemyList.isEmpty()) {

                makeEnemies();

                reset = false;

            }

            //Unique

            ScreenUtils.clear(0, 0, 0, 0);

            camera.update();
            batch.setProjectionMatrix(camera.combined);

            batch.begin();

            vallyBackground.draw(batch);

            // Drawing goes here!

            for (Floor floor : floorList) {
                floor.getSprite().draw(batch);
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

            ArrayList<DamageParticle> tempParticles = new ArrayList<DamageParticle>();

            for (DamageParticle particle : damageParticleList) {

                if (!particle.drawParticle(batch)) {

                    tempParticles.add(particle);

                }

            }

            batch.setShader(null);

            damageParticleList.removeAll(tempParticles);

            batch.setProjectionMatrix(camera.combined);

            batch.end();

            if (Gdx.input.isKeyPressed(44)) {

            }

            if (!drawSign && enemies.checkAllIfDead()) {

                alreadyShown = true;
                drawSign = true;
                MainApplication.signList.get(0).changeBits();

            }

        }

        Gdx.input.setInputProcessor(party.getInputProcessor());

        if (Gdx.input.isKeyPressed(43)) {
            MainMenu.currentScreen = MainMenu.instance.getScreen();
            alreadyShown = true;
            MainMenu.instance.setScreen(MainMenu.inventoryScreen);
        }

    }

}




