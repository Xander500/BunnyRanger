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
    Texture levelButtonTexture;

    private static final float LEVEL_BUTTON_MARGIN = 16f;
    private static final float LEVEL_BUTTON_WIDTH = 170f;
    private static final float LEVEL_BUTTON_HEIGHT = 48f;

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

        backgroundTexture = new Texture(Gdx.files.internal("sprites/backgrounds/bg_black.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);
        vallyBackground.setCenterX(battleSizeWidth/2f);
        vallyBackground.setCenterY(battleSizeHeight/2f);
        vallyBackground.setScale(1/2f);
        levelButtonTexture = new Texture(Gdx.files.internal("sprites/ui/buttons/button_buy_enabled.png"));

        party = WorldHandler.getParty();
        enemies = WorldHandler.getEnemies();
        wallList = WorldHandler.wallList;
        signList = WorldHandler.signList;
        particleList = WorldHandler.particleList;
        projectileList = WorldHandler.projectileList;
        projectileListRemove = WorldHandler.projectileListRemove;
        worldHandler = WorldHandler.getWorldHandler();

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

        WorldHandler.projectileList.addAll(WorldHandler.projectileListAdd);
        WorldHandler.projectileListAdd.clear();

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

        drawLevelButtons();

        GameHandler.batch.setProjectionMatrix(WorldHandler.camera.combined);

        GameHandler.batch.end();

        // show sign
        if (!drawSign && enemies.checkAllIfDead()) {

            drawSign = true;
            if (!WorldHandler.signList.isEmpty()) {
                WorldHandler.signList.get(0).changeBits();
            }

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

        handleLevelButtonClick();

    }

    private void drawLevelButtons() {
        drawLevelButton("Inn");
    }

    private void drawLevelButton(String text) {
        float x = getLevelButtonX();
        float y = getLevelButtonY();
        BitmapFont font = GameHandler.getFont();
        float tmpX = font.getScaleX();
        float tmpY = font.getScaleY();

        GameHandler.batch.draw(levelButtonTexture, x, y, LEVEL_BUTTON_WIDTH, LEVEL_BUTTON_HEIGHT);

        font.getData().setScale(2f);
        GameHandler.batch.setShader(fontShader);
        font.draw(GameHandler.batch, text, x + 14f, y + 32f);
        GameHandler.batch.setShader(null);
        font.getData().setScale(tmpX, tmpY);
    }

    private void handleLevelButtonClick() {
        if (!Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            return;
        }

        float textX = Gdx.input.getX() * WorldHandler.textCamera.viewportWidth / Gdx.graphics.getWidth();
        float textY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * WorldHandler.textCamera.viewportHeight / Gdx.graphics.getHeight();

        if (isLevelButtonHit(textX, textY)) {
            swapWithTemporarySign(GameHandler.levelScreenInn);
        }
    }

    private void swapWithTemporarySign(Screen target) {
        Sign sign = Sign.makeSwapRequest(0, target);
        WorldHandler.signList.add(sign);
        sign.startSwap();
    }

    private boolean isLevelButtonHit(float x, float y) {
        float buttonX = getLevelButtonX();
        float buttonY = getLevelButtonY();

        return x >= buttonX && x <= buttonX + LEVEL_BUTTON_WIDTH
            && y >= buttonY && y <= buttonY + LEVEL_BUTTON_HEIGHT;
    }

    private float getLevelButtonX() {
        return LEVEL_BUTTON_MARGIN;
    }

    private float getLevelButtonY() {
        return WorldHandler.textCamera.viewportHeight - LEVEL_BUTTON_MARGIN - LEVEL_BUTTON_HEIGHT;
    }

    public void changeBackground(String path) {
        backgroundTexture = new Texture(Gdx.files.internal(path));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, WorldHandler.SCREENWIDTH, WorldHandler.SCREENHEIGHT);
        vallyBackground.setCenterX(WorldHandler.battleSizeWidth/2f);
        vallyBackground.setCenterY(WorldHandler.battleSizeHeight/2f);
        vallyBackground.setScale(1/2f);
    }

    public void addArena() {
        wallList.add(new Wall(worldHandler, 0, 0f, 40, 1, "sprites/tiles/tile_grass.png"));
        wallList.add(new Wall(worldHandler, 0, 22.5f, 40, 1, "sprites/tiles/tile_dirt.png"));
        wallList.add(new Wall(worldHandler, -1, 0, 1, 21.5f, "sprites/tiles/tile_dirt.png"));
        wallList.add(new Wall(worldHandler, 40, 0, 1, 21.5f, "sprites/tiles/tile_dirt.png"));
    }
}
