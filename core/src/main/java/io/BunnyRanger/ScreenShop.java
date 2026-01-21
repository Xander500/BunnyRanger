package io.BunnyRanger;

import static io.BunnyRanger.GameHandler.fontShader;

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

public class ScreenShop extends InputAdapter implements Screen {
    OrthographicCamera camera;

    ShopScreenStage shopScreenStage;

    Texture background = new Texture(Gdx.files.internal("menuShop.png"));
    Sprite sprite;

    BitmapFont font;

    public ScreenShop() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 192, 188);
        camera.setToOrtho(false, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);

        this.shopScreenStage = new ShopScreenStage(this);

        sprite = new Sprite(background);
        sprite.setSize(WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT); // Set the size of the sprite
        sprite.setPosition(0, 0); // Set the position of the sprite

        font = GameHandler.getFont();
        font.getData().scale(.20f);

    }

    public void render(float delta) {

        camera.update();
        GameHandler.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 0);

        GameHandler.batch.begin();

        sprite.draw(GameHandler.batch);

        GameHandler.batch.setShader(fontShader);
        font.getData().setScale(2f);

        WorldHandler.getParty().getGoldFont().draw(GameHandler.batch,"GOLD " + WorldHandler.getParty().getGold(),70,420);

        float tmpX = font.getScaleX();
        float tmpY = font.getScaleY();

        font.getData().setScale(1f);
        if (shopScreenStage.selected != null && ((InventorySpotActor) shopScreenStage.selected).getSpot() != null) {

            Item current = ((InventorySpotActor) shopScreenStage.selected).getSpot();

            GameHandler.batch.setShader(fontShader);

            // I don't make this polymorphic due to it being simpler to keep it all here, not enough types to warrant
            if (current instanceof Weapon) {
                this.font.draw(GameHandler.batch, "Name - " + ((Weapon) current).getName() + "\n" + "Dmg - " + ((Weapon) current).getDamageMin() + "~" + ((Weapon) current).getDamageMax() + "\n" + "Count - " + ((Weapon) current).getCount() + "\n" + "Range - " + ((Weapon) current).getRange() + "\n" + "Delay - " + (int) ((Weapon) current).getBaseDelay() + "\n" + "Value - " + ((Weapon) current).getBuy(), 950, 675);
            }

            if (current instanceof Card) {
                this.font.draw(GameHandler.batch, "Name - " + ((Card) current).getName() + "\n" + "Dmg - " + ((Card) current).getDescription(), 950, 675);
            }
        }
        GameHandler.batch.setShader(null);
        font.getData().setScale(tmpX,tmpY);

        GameHandler.batch.end();

        GameHandler.batch.begin();

        shopScreenStage.instance().draw();

        GameHandler.batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            GameHandler.instance.setScreen(GameHandler.screenInventory);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            GameHandler.instance.setScreen(GameHandler.currentScreen);
        }

    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        shopScreenStage.parseHit(shopScreenStage.instance().hit(camera.unproject(new Vector3(screenX,0,0)).x, camera.unproject(new Vector3(0,screenY,0)).y,false),button);
        //System.out.println("Y Cute " + (Gdx.graphics.getHeight() - Gdx.input.getY()) * (camera.viewportHeight / Gdx.graphics.getHeight()) + " Y " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
        return true;
    }

    public void show() {
        Gdx.input.setInputProcessor(this); // IMPORTANT
    }
    public void resize(int width, int height) {
    }
    public void pause() {
    }
    public void resume() {
    }
    public void hide() {
        GameHandler.screenInventory.inventoryScreenStage.hydrate();
    }
    public void dispose() {
    }
}
