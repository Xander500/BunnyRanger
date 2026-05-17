package io.BunnyRanger;

public class ScreenLevel4 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("sprites/backgrounds/bg_valley.png");
    }
    @Override
    public void show() {
        super.show();
    }

    public void makeEnemies() {

        EnemySnake enemy1 = new EnemySnake(worldHandler, 25, 5);
        EnemyHare enemy2 = new EnemyHare(worldHandler, 29, 5);
        EnemyHare enemy3 = new EnemyHare(worldHandler, 33, 5);
        EnemyFox enemy4 = new EnemyFox(worldHandler, 37, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        WeaponSword bow1 = new WeaponSword(false);
        WeaponBow2 bow2 = new WeaponBow2(false);
        WeaponBow2 bow3 = new WeaponBow2(false);
        WeaponPistol bow4 = new WeaponPistol(false);

        enemy1.addWeapon(bow1);
        enemy2.addWeapon(bow2);
        enemy3.addWeapon(bow3);
        enemy4.addWeapon(bow4);

        Wall floor1 = new Wall(worldHandler, 0, 0f, 40, 1, "sprites/tiles/tile_grass.png");
        Wall floor2 = new Wall(worldHandler, 0, 22.5f, 40, 1, "sprites/tiles/tile_dirt.png");
        Wall wall1 = new Wall(worldHandler, -1, 0, 1, 21.5f, "sprites/tiles/tile_dirt.png");
        Wall wall2 = new Wall(worldHandler, 40, 0, 1, 21.5f, "sprites/tiles/tile_dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        GameHandler.makeSign(GameHandler.screenLevel5);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 4 --- Hare Ambush",350,650);
            GameHandler.batch.end();
        }

        //debugRenderer.render(world1.getWorld(), camera.combined);
        WorldHandler.camera.update();

    }

}
