package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LevelScreen1 extends LevelScreen {

    @Override
    public void show() {
        super.show();

        backgroundTexture = new Texture(Gdx.files.internal("vally.png"));
        vallyBackground = new Sprite(backgroundTexture, 0, 0, MainApplication.SCREENWIDTH,MainApplication.SCREENHEIGHT);
        vallyBackground.setCenterX(MainApplication.battleSizeWidth/2f);
        vallyBackground.setCenterY(MainApplication.battleSizeHeight/2f);
        vallyBackground.setScale(1/2f);

    }

    public void makeEnemies() {

        for (int i = 0; i < 15; i++) {
            Enemy enemy1 = new EnemySlime(world1, 16 + 1.7f*i, 5);
            enemies.addEnemy(enemy1);
            WeaponEmpty bow1 = new WeaponEmpty(false);
            enemy1.addWeapon(bow1);
        }

        Wall floor1 = new Wall(world1, 0, 0f, 40, 1, "grass.png");
        Wall floor2 = new Wall(world1, 0, 22.5f, 40, 1, "dirt.png");
        Wall wall1 = new Wall(world1, -1, 0, 1, 21.5f, "dirt.png");
        Wall wall2 = new Wall(world1, 40, 0, 1, 21.5f, "dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        wallList.add(floor1);

        MainMenu.makeSign(MainMenu.levelScreen2);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            batch.begin();
            this.displayTitle("wowzer" + Gdx.graphics.getFramesPerSecond(),350,650);
            batch.end();
        }

        //debugRenderer.render(world1.getWorld(), camera.combined);
        camera.update();

    }

}
