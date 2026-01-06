package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LevelScreenInn extends LevelScreen implements Screen, ScreenType {

    boolean reset = true;

    boolean alreadyShown = false;

    boolean drawSign;

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

        super.render(delta);

        batch.begin();

        if (levelDisplayCounter < 300) {
            this.displayTitle("LEVEL 0 --- INNSHOP",350,650);
        }

        if (!drawSign && enemies.checkAllIfDead()) {

            alreadyShown = true;
            drawSign = true;
            System.out.println("changed Sign");
            MainApplication.signList.get(0).changeBits();

        }

        if (firstBarrier && MainApplication.signList.size() >= 2) {

            //BARRIER DRAW

            for (int i = 1; i < MainApplication.signList.size(); i++) {

                MainApplication.signList.get(i).changeBits();

                MainApplication.signList.get(i).updateEnemySprite();
                MainApplication.signList.get(i).getEnemySprite().draw(batch);

            }

        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            MainMenu.currentScreen = MainMenu.instance.getScreen();
            alreadyShown = true;
            MainMenu.instance.setScreen(MainMenu.shopScreen);
        }

        Gdx.input.setInputProcessor(party.getInputProcessor());

    }

    public void makeEnemies() {

        Wall floor1 = new Wall(world1, 0, 0f, 40, 1, "grass.png");
        Wall floor2 = new Wall(world1, 0, 22.5f, 40, 1, "dirt.png");
        Wall wall1 = new Wall(world1, -1, 0, 1, 21.5f, "dirt.png");
        Wall wall2 = new Wall(world1, 40, 0, 1, 21.5f, "dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        wallList.add(floor1);


        drawSign = false;

        MainMenu.makeSign(MainMenu.levelScreen1);

        MainMenu.makeSign(MainMenu.levelScreen2,28,32); // WHERE FIRST WARP SIGN SHOULD GO
        MainMenu.makeSign(MainMenu.levelScreen3,36,32); // WHERE SECOND WARP SIGN SHOULD GO
        MainMenu.makeSign(MainMenu.levelScreen4,45,32); // WHERE THIRD WARP SIGN SHOULD GO
        MainMenu.makeSign(MainMenu.levelScreen5,54,32); // WHERE FOURTH WARP SIGN SHOULD GO

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
