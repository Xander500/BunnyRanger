package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class LevelScreen1 extends LevelScreen {

    public void makeEnemies() {

        EnemyFox enemy1 = new EnemyFox(world1, 30, 5);
        EnemyFox enemy2 = new EnemyFox(world1, 35, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);

        WeaponBow bow1 = new WeaponBow(false);
        WeaponBow bow2 = new WeaponBow(false);

        enemy1.addWeapon(bow1);
        enemy2.addWeapon(bow2);

        Floor floor1 = new Floor(world1, 0, 0f, 40, 1, "grass.png");
        Floor floor2 = new Floor(world1, 0, 21.5f, 40, 1, "dirt.png");
        //Floor floor3 = new Floor(world1, 64, 31, 5, 1, "dirt.png");
        Floor wall1 = new Floor(world1, 0, 0, 1, 21.5f, "dirt.png");
        Floor wall3 = new Floor(world1, 39, 0, 1, 21.5f, "dirt.png");
        //Floor wall2 = new Floor(world1, 1, 16, 1, 16, "dirt.png");

        floorList.add(floor1);
        floorList.add(floor2);
        //floorList.add(floor3);
        floorList.add(wall1);
        //floorList.add(wall2);
        floorList.add(wall3);

        floorList.add(floor1);


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
