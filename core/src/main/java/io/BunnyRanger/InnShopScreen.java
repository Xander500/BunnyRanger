package io.BunnyRanger;

import static io.BunnyRanger.MainMenu.fontShader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class InnShopScreen extends MainApplication implements Screen, ScreenType {

    Texture backgroundTexture;
    Sprite vallyBackground;

    //tmp

    boolean reset = true;

    boolean alreadyShown = false;

    boolean drawSign;

    Floor floor1;
    Floor floor2;
    Floor floor3;
    Floor wall1;
    Floor wall2;
    Floor wall3;
    Floor wall4;
    Floor wall5;
    Floor wall6;

    public static String levelName = "Level 0 - Inn";
    public static int levelDisplayCounter = 0;

    public static boolean firstBarrier = false;
    public static boolean secondBarrier = false;
    public static boolean thirdBarrier = false;
    public static boolean fourthBarrier = false;

    public void show() {

        if (reset) {
            System.out.println("showed second screen while reset");
        }

        if (alreadyShown) {
            return;
        }


        super.create();

        //Unique
        //make world

        backgroundTexture = new Texture(Gdx.files.internal("innShop.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, MainApplication.SCREENWIDTH,MainApplication.SCREENHEIGHT);
        vallyBackground.setCenterX(MainApplication.battleSizeWidth/2f);
        vallyBackground.setCenterY(MainApplication.battleSizeHeight/2f);
        vallyBackground.setScale(.5f);

        this.myContactListener = new MyContactListener();

        world1.getWorld().setContactListener(myContactListener);

        //Box Box1 = new Box(world1.getWorld(),32,16);

    }

    public void render(float delta) {

        super.render();

        //Unique

        world1.getWorld().step(1 / 30f, 6, 2);

        world1.destroyBodies();

        System.out.println(Gdx.graphics.getFramesPerSecond());

        if (reset && enemies != null && enemies.enemyList.isEmpty()) {

            makeEnemies();

            reset = false;

        }

        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();

        try {
            //debugRenderer.render(world1.getWorld(), camera.combined);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ASDASDASDASDSADASDASDASDASDASDSADASDASDASDASDASDASDASDASDASDASDASDASDASDASDASDASDASD");
        }

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

        if (drawSign && !MainApplication.signList.isEmpty()) {
            MainApplication.signList.get(0).updateEnemySprite();
            MainApplication.signList.get(0).getEnemySprite().draw(batch);

        }

        if (firstBarrier && MainApplication.signList.size() >= 2) {

            //BARRIER DRAW

            for (int i = 1; i < MainApplication.signList.size(); i++) {

                MainApplication.signList.get(i).changeBits();

                MainApplication.signList.get(i).updateEnemySprite();
                MainApplication.signList.get(i).getEnemySprite().draw(batch);

            }

        }

        // projectile stuffy

        projectileList.removeIf(projectile -> projectile.spriteDestroyDelay <= 0);

        for (Projectile projectile : projectileList) {

            if (projectile != null && projectile.getProjectileSprite() != null) {
                projectile.getProjectileSprite().draw(batch);
            }

        }

        //levelname

        if (levelDisplayCounter < 400) {

            camera.setToOrtho(false, MainApplication.SCREENWIDTH,MainApplication.SCREENHEIGHT);
            camera.update();
            batch.setProjectionMatrix(camera.combined);

            BitmapFont font = MainMenu.getFont();

            font.getData().setScale(4f);
            batch.setShader(fontShader);
            MainApplication.getParty().getGoldFont().draw(batch,"LEVEL 0 --- INN & SHOP",350,650);
            batch.setShader(null);

            levelDisplayCounter += 1;

            camera.setToOrtho(false, battleSizeWidth, battleSizeHeight);
            camera.update();
            batch.setProjectionMatrix(camera.combined);

        }

        batch.end();

        if (Gdx.input.isKeyPressed(43)) {
            MainMenu.currentScreen = MainMenu.instance.getScreen();
            alreadyShown = true;
            MainMenu.instance.setScreen(MainMenu.inventoryScreen);
        }

        if (Gdx.input.isKeyPressed(44)) {
            MainMenu.currentScreen = MainMenu.instance.getScreen();
            alreadyShown = true;
            MainMenu.instance.setScreen(MainMenu.shopScreen);
        }

        if (!drawSign && enemies.checkAllIfDead()) {

            alreadyShown = true;
            drawSign = true;
            System.out.println("changed Sign");
            MainApplication.signList.get(0).changeBits();

        }

        //MainApplication.getParty().removeMouseJoints();
        Gdx.input.setInputProcessor(party.getInputProcessor());

    }

    public void makeEnemies() {

        floor1 = new Floor(world1, 0, 1, 40, 1, "grass.png");
        floor3 = new Floor(world1, 32, 33, 32, 1, "dirt.png");
        wall1 = new Floor(world1, 65, 16, 1, 16, "dirt.png");
        wall2 = new Floor(world1, -1, 16, 1, 16, "dirt.png");

        wall3 = new Floor(world1, 28, 22, 3, 1, "dirt.png");
        wall4 = new Floor(world1, 36, 19, 3, 1, "dirt.png");
        wall5 = new Floor(world1, 45, 19, 3, 1, "dirt.png");
        wall6 = new Floor(world1, 54, 22, 3, 1, "dirt.png");



        floorList.add(floor1);
        floorList.add(floor3);
        floorList.add(wall1);
        floorList.add(wall2);
        floorList.add(wall3);
        floorList.add(wall4);
        floorList.add(wall5);
        floorList.add(wall6);

        MainMenu.makeSign(MainMenu.levelScreen1,60,33);

        MainMenu.makeSign(MainMenu.levelScreen2,28,32); // WHERE FIRST WARP SIGN SHOULD GO
        MainMenu.makeSign(MainMenu.levelScreen3,36,32); // WHERE FIRST WARP SIGN SHOULD GO
        MainMenu.makeSign(MainMenu.levelScreen4,45,32); // WHERE FIRST WARP SIGN SHOULD GO
        MainMenu.makeSign(MainMenu.levelScreen5,54,32); // WHERE FIRST WARP SIGN SHOULD GO

        firstBarrier = true;
        secondBarrier = true;
        thirdBarrier = true;
        fourthBarrier = true;

        drawSign = false;

    }

    public void dispose() {
        super.dispose();
    }

    public void hide() {

    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }
}
