package io.BunnyRanger;

import static io.BunnyRanger.MainMenu.fontShader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class InventoryScreen extends InputAdapter implements Screen {

    MainMenu game;
    OrthographicCamera camera;

    InventoryScreenStage inventoryScreenStage;

    Texture background = new Texture(Gdx.files.internal("menuInventory.png"));
    Sprite sprite;

    BitmapFont font;

    public InventoryScreen(MainMenu game) {

        this.game = game;

        camera = new OrthographicCamera();
        //camera.setToOrtho(false, 360, 180);
        camera.setToOrtho(false, MainApplication.SCREENWIDTH,MainApplication.SCREENHEIGHT);

        this.inventoryScreenStage = new InventoryScreenStage(this);

        sprite = new Sprite(background);
        sprite.setSize(MainApplication.SCREENWIDTH,MainApplication.SCREENHEIGHT); // Set the size of the sprite
        sprite.setPosition(0, 0); // Set the position of the sprite
        //Gdx.input.setInputProcessor(this.inventoryScreenStage);

        font = MainMenu.getFont();

    }

    public void render(float delta) {

        Gdx.input.setInputProcessor(this); // IMPORTANT

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();

        sprite.draw(game.batch);

        float tmpX = font.getScaleX();
        float tmpY = font.getScaleY();

        font.getData().setScale(4f);
        MainMenu.batch.setShader(fontShader);
        MainApplication.getParty().getGoldFont().draw(game.batch,"GOLD " + MainApplication.getParty().getGold(),70,415);

        if (inventoryScreenStage.selected != null && ((InventorySpotActor) inventoryScreenStage.selected).getSpot() != null) {

            Item current = ((InventorySpotActor) inventoryScreenStage.selected).getSpot();
            font.getData().setScale(2f);

            if (current instanceof Weapon) {
                this.font.draw(MainMenu.batch, "Name - " + ((Weapon) current).getName() + "\n" + "Dmg - " + ((Weapon) current).getDamageMin() + "~" + ((Weapon) current).getDamageMax() + "\n" + "Count - " + ((Weapon) current).getCount() + "\n" + "Range - " + ((Weapon) current).getRange() + "\n" + "Delay - " + (int) ((Weapon) current).getDelay(0) + "\n" + "Value - " + ((Weapon) current).getBuy(), 950, 675);
            }

            if (current instanceof Card) {
                this.font.draw(MainMenu.batch, "Name - " + ((Card) current).getName() + "\n\n" + "Disc - " + ((Card) current).getDescription(), 950, 675);
            }

        }
        game.batch.setShader(null);
        font.getData().setScale(tmpX,tmpY);

        game.batch.end();

        game.batch.begin();

        inventoryScreenStage.instance().draw();

        game.batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            game.setScreen(MainMenu.currentScreen);
            inventoryScreenStage.populatePartyWeapons();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            game.setScreen(MainMenu.shopScreen);
            inventoryScreenStage.populatePartyWeapons();
        }

    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        inventoryScreenStage.parseHit(inventoryScreenStage.instance().hit(camera.unproject(new Vector3(screenX,0,0)).x, camera.unproject(new Vector3(0,screenY,0)).y,false),button);
        //System.out.println("Y Cute " + (Gdx.graphics.getHeight() - Gdx.input.getY()) * (camera.viewportHeight / Gdx.graphics.getHeight()) + " Y " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
        return true;
    }

    public void show() {

        MainApplication.getParty().removeMouseJoints();
        Gdx.graphics.setCursor(MainApplication.regular);

    }
    public void resize(int width, int height) {
    }
    public void pause() {
    }
    public void resume() {
    }
    public void hide() {
        MainMenu.shopScreen.shopScreenStage.hydrate();
    }
    public void dispose() {
    }
}

