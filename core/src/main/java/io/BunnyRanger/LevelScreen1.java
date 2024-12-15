package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class LevelScreen1 extends LevelScreen {

    public void makeEnemies() {

        EnemyBunny enemy1 = new EnemyBunny(world1, 35, 5);
        EnemyBunny enemy2 = new EnemyBunny(world1, 40, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);

        WeaponBow bow1 = new WeaponBow(false);
        WeaponBow bow2 = new WeaponBow(false);

        enemy1.addWeapon(bow1);
        enemy2.addWeapon(bow2);

        Floor floor1 = new Floor(world1, 32, 1, 32, 1, "grass.png");
        Floor floor2 = new Floor(world1, 32, 31, 32, 1, "dirt.png");
        Floor wall1 = new Floor(world1, 63, 16, 1, 16, "dirt.png");
        Floor wall2 = new Floor(world1, 1, 16, 1, 16, "dirt.png");

        floorList.add(floor1);
        floorList.add(floor2);
        floorList.add(wall1);
        floorList.add(wall2);

        MainMenu.makeSign(MainMenu.levelScreen2);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            batch.begin();
            this.displayTitle("LEVEL 1 --- BEGINNING",350,650);
            batch.end();
        }

        debugRenderer.render(world1.getWorld(), camera.combined);
        camera.update();

    }

}
