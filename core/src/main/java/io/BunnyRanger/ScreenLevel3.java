package io.BunnyRanger;

public class ScreenLevel3 extends ScreenLevel {

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

        EnemySnake enemy1 = new EnemySnake(WorldHandler.getWorldHandler(), 35, 5);
        EnemySnake enemy2 = new EnemySnake(WorldHandler.getWorldHandler(), 40, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);

        WeaponSword bow1 = new WeaponSword(false);
        WeaponSword bow2 = new WeaponSword(false);

        enemy1.addWeapon(bow1);
        enemy2.addWeapon(bow2);

        Wall floor1 = new Wall(worldHandler, 32, 1, 32, 1, "grass.png");
        Wall floor2 = new Wall(worldHandler, 32, 31, 32, 1, "dirt.png");
        Wall wall1 = new Wall(worldHandler, 63, 16, 1, 16, "dirt.png");
        Wall wall2 = new Wall(worldHandler, 1, 16, 1, 16, "dirt.png");

        wallList.add(floor1);
        wallList.add(floor2);
        wallList.add(wall1);
        wallList.add(wall2);

        GameHandler.makeSign(GameHandler.screenLevel4);

        drawSign = false;

    }

    public void render(float delta) {

        super.render(delta);

        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 3 --- Sword Sneks",350,650);
            GameHandler.batch.end();
        }

        //debugRenderer.render(world1.getWorld(), camera.combined);
        WorldHandler.camera.update();

    }

}
