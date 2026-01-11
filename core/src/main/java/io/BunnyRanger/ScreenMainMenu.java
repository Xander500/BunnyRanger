package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenMainMenu implements Screen {

    OrthographicCamera camera;

    Sprite vallyBackground;

    public ScreenMainMenu() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        Texture backgroundTexture = new Texture(Gdx.files.internal("titleScreen.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);
        vallyBackground.setScale(1f);
        vallyBackground.setCenterX(640);
        vallyBackground.setCenterY(360);

    }

    public void show() {

    }

    public void render(float delta) {

        camera.update();
        GameHandler.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 0);

        GameHandler.batch.begin();
        vallyBackground.draw(GameHandler.batch);
        GameHandler.batch.end();

        if (Gdx.input.isTouched()) {
            //game.setScreen(MainMenu.innShopScreen);
            GameHandler.instance.setScreen(GameHandler.levelScreenInn);
        }

        if (Gdx.input.isKeyPressed(33)) {
            //e key // do e stuff
        }
    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }

}
