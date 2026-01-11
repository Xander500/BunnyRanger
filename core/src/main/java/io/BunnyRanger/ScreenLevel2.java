package io.BunnyRanger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ScreenLevel2 extends ScreenLevel {

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

        EnemyFox enemy1 = new EnemyFox(WorldHandler.getWorldHandler(), 35, 5);
        EnemyFox enemy2 = new EnemyFox(WorldHandler.getWorldHandler(), 40, 5);
        EnemyFox enemy3 = new EnemyFox(WorldHandler.getWorldHandler(), 45, 5);
        EnemyFox enemy4 = new EnemyFox(WorldHandler.getWorldHandler(), 50, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        WeaponBow bow1 = new WeaponBow(false);
        WeaponBow bow2 = new WeaponBow(false);
        WeaponBow bow3 = new WeaponBow(false);
        WeaponBow bow4 = new WeaponBow(false);

        enemy1.addWeapon(bow1);
        enemy2.addWeapon(bow2);
        enemy3.addWeapon(bow3);
        enemy4.addWeapon(bow4);

        Wall floor1 = new Wall(WorldHandler.getWorldHandler(), 32, 1, 32, 1, "grass.png");
        Wall floor2 = new Wall(WorldHandler.getWorldHandler(), 32, 31, 32, 1, "dirt.png");
        Wall wall1 = new Wall(WorldHandler.getWorldHandler(), 63, 16, 1, 16, "dirt.png");
        Wall wall2 = new Wall(WorldHandler.getWorldHandler(), 1, 16, 1, 16, "dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        GameHandler.makeSign(GameHandler.screenLevel3);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 2 --- Division",350,650);
            GameHandler.batch.end();
        }

        //debugRenderer.render(world1.getWorld(), camera.combined);
        WorldHandler.camera.update();

    }

}
