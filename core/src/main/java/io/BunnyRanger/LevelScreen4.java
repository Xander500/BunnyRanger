package io.BunnyRanger;

public class LevelScreen4 extends LevelScreen {

    public void makeEnemies() {

        EnemySnake enemy1 = new EnemySnake(world1, 35, 5);
        EnemyBunny enemy2 = new EnemyBunny(world1, 40, 5);
        EnemyBunny enemy3 = new EnemyBunny(world1, 45, 5);
        EnemyBunny enemy4 = new EnemyBunny(world1, 50, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        WeaponSword bow1 = new WeaponSword(false);
        WeaponBow bow2 = new WeaponBow(false);
        WeaponBow bow3 = new WeaponBow(false);
        WeaponBow bow4 = new WeaponBow(false);

        enemy1.addWeapon(bow1);
        enemy2.addWeapon(bow2);
        enemy3.addWeapon(bow3);
        enemy4.addWeapon(bow4);

        Floor floor1 = new Floor(world1, 32, 1, 32, 1, "grass.png");
        Floor floor2 = new Floor(world1, 32, 31, 32, 1, "dirt.png");
        Floor wall1 = new Floor(world1, 63, 16, 1, 16, "dirt.png");
        Floor wall2 = new Floor(world1, 1, 16, 1, 16, "dirt.png");

        floorList.add(floor1);
        floorList.add(floor2);
        floorList.add(wall1);
        floorList.add(wall2);

        MainMenu.makeSign(MainMenu.levelScreen5);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            batch.begin();
            this.displayTitle("LEVEL 3 --- Sword Sneks",350,650);
            batch.end();
        }

        //debugRenderer.render(world1.getWorld(), camera.combined);
        camera.update();

    }

}
