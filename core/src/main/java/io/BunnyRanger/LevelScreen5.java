package io.BunnyRanger;

public class LevelScreen5 extends LevelScreen {

    public void makeEnemies() {

        EnemySnake enemy1 = new EnemySnake(world1, 35, 5);
        EnemySnake enemy2 = new EnemySnake(world1, 40, 5);
        EnemyBunny enemy3 = new EnemyBunny(world1, 45, 5);
        EnemyBunny enemy4 = new EnemyBunny(world1, 50, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        WeaponSword bow1 = new WeaponSword(false);
        WeaponSword bow2 = new WeaponSword(false);
        WeaponBow bow3 = new WeaponBow(false);
        WeaponBow bow4 = new WeaponBow(false);

        enemy1.addWeapon(bow1);
        enemy2.addWeapon(bow2);
        enemy3.addWeapon(bow3);
        enemy4.addWeapon(bow4);

        Wall floor1 = new Wall(world1, 32, 1, 32, 1, "grass.png");
        Wall floor2 = new Wall(world1, 32, 31, 32, 1, "dirt.png");
        Wall wall1 = new Wall(world1, 63, 16, 1, 16, "dirt.png");
        Wall wall2 = new Wall(world1, 1, 16, 1, 16, "dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        MainMenu.makeSign(MainMenu.levelScreenInn);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            batch.begin();
            this.displayTitle("LEVEL 5 --- AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH",350,650);
            batch.end();
        }

        if (enemies.checkAllIfDead()) {
            LevelScreenInn.firstBarrier = true; // MAKES FIRST WARP SIGN APPEAR
            System.out.println("made thingy");
        }

        //debugRenderer.render(world1.getWorld(), camera.combined);
        camera.update();

    }

}
