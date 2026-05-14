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

        for (int i = 0; i < 3; i++) {
            Enemy enemy = new EnemySnake(worldHandler, 26 + 2.5f * i, 5);
            enemies.addEnemy(enemy);
            enemy.addWeapon(new WeaponSword(false));
        }

        for (int i = 0; i < 2; i++) {
            Enemy enemy = new EnemyFox(worldHandler, 32 + 2.5f * i, 5);
            enemies.addEnemy(enemy);
            enemy.addWeapon(new WeaponBow(false));
        }

        Wall floor1 = new Wall(worldHandler, 0, 0f, 40, 1, "grass.png");
        Wall floor2 = new Wall(worldHandler, 0, 22.5f, 40, 1, "dirt.png");
        Wall wall1 = new Wall(worldHandler, -1, 0, 1, 21.5f, "dirt.png");
        Wall wall2 = new Wall(worldHandler, 40, 0, 1, 21.5f, "dirt.png");

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
