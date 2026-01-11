package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ScreenLevel1 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("vally.png");
    }
    @Override
    public void show() {
        super.show();
    }

    public void makeEnemies() {

        for (int i = 0; i < 15; i++) {
            Enemy enemy1 = new EnemySlime(worldHandler, 16 + 1.7f*i, 5);
            enemies.addEnemy(enemy1);
            WeaponEmpty bow1 = new WeaponEmpty(false);
            enemy1.addWeapon(bow1);
        }

        Wall floor1 = new Wall(worldHandler, 0, 0f, 40, 1, "grass.png");
        Wall floor2 = new Wall(worldHandler, 0, 22.5f, 40, 1, "dirt.png");
        Wall wall1 = new Wall(worldHandler, -1, 0, 1, 21.5f, "dirt.png");
        Wall wall2 = new Wall(worldHandler, 40, 0, 1, 21.5f, "dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        wallList.add(floor1);

        GameHandler.makeSign(GameHandler.screenLevel2);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("wowzer" + Gdx.graphics.getFramesPerSecond(),350,650);
            GameHandler.batch.end();
        }

        //debugRenderer.render(world1.getWorld(), camera.combined);
        WorldHandler.camera.update();

    }

}
