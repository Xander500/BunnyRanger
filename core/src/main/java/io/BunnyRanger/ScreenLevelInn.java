package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ScreenLevelInn extends ScreenLevel implements Screen, ScreenType {

    boolean reset = true;

    boolean drawSign;

    public static String levelName = "Level 0 - Inn";
    public static int levelDisplayCounter = 0;

    public static boolean firstBarrier = false;
    public static boolean secondBarrier = false;
    public static boolean thirdBarrier = false;
    public static boolean fourthBarrier = false;

    public void create() {
        super.create();

        backgroundTexture = new Texture(Gdx.files.internal("innShop.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);
        vallyBackground.setCenterX(WorldHandler.battleSizeWidth/2f);
        vallyBackground.setCenterY(WorldHandler.battleSizeHeight/2f);
        vallyBackground.setScale(.5f);

    }

    public void show() {

        if (reset) {
            System.out.println("showed second screen while reset");
        }

        super.show();

    }

    public void render(float delta) {

        //Unique
        super.render(delta);

        GameHandler.batch.begin();

        if (levelDisplayCounter < 300) {
            this.displayTitle("LEVEL 0 --- INNSHOP",350,650);
        }

        if (!drawSign && enemies.checkAllIfDead()) {

            drawSign = true;
            System.out.println("changed Sign");
            signList.get(0).changeBits();

        }

        if (firstBarrier && signList.size() >= 2) {

            //BARRIER DRAW

            for (int i = 1; i < signList.size(); i++) {

                signList.get(i).changeBits();

                signList.get(i).updateEnemySprite();
                signList.get(i).getEnemySprite().draw(GameHandler.batch);

            }

        }

        GameHandler.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            GameHandler.currentScreen = GameHandler.instance.getScreen();
            GameHandler.instance.setScreen(GameHandler.screenShop);
        }

    }

    public void makeEnemies() {

        Wall floor1 = new Wall(worldHandler, 0, 0f, 40, 1, "grass.png");
        Wall floor2 = new Wall(worldHandler, 0, 22.5f, 40, 1, "dirt.png");
        Wall wall1 = new Wall(worldHandler, -1, 0, 1, 21.5f, "dirt.png");
        Wall wall2 = new Wall(worldHandler, 40, 0, 1, 21.5f, "dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        wallList.add(floor1);

        drawSign = false;

        GameHandler.makeSign(GameHandler.screenLevel1);

        GameHandler.makeSign(GameHandler.screenLevel2,28,32); // WHERE FIRST WARP SIGN SHOULD GO
        GameHandler.makeSign(GameHandler.screenLevel3,36,32); // WHERE SECOND WARP SIGN SHOULD GO
        GameHandler.makeSign(GameHandler.screenLevel4,45,32); // WHERE THIRD WARP SIGN SHOULD GO
        GameHandler.makeSign(GameHandler.screenLevel5,54,32); // WHERE FOURTH WARP SIGN SHOULD GO

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
