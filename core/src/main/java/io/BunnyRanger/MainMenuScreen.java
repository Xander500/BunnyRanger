package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    MainMenu game;
    OrthographicCamera camera;

    public MainMenuScreen(MainMenu game) {

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 256, 128);

    }

    public void show() {

    }

    public void render(float delta) {

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Collision Test!!! ", 36, 84);
        game.font.draw(game.batch, "Tap anywhere to begin!", 52, 60);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(MainMenu.innShopScreen);
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
