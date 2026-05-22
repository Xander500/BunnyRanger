package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class ScreenLevel10 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("sprites/backgrounds/bg_valley.png");
    }

    public void makeEnemies() {
        EnemyBossBunny boss = new EnemyBossBunny(worldHandler, 29, 5);
        EnemyRangerBunny enemy1 = new EnemyRangerBunny(worldHandler, 20, 5);
        EnemyBombardierSlime enemy2 = new EnemyBombardierSlime(worldHandler, 38, 5);
        EnemyEagle enemy3 = new EnemyEagle(worldHandler, 24, 14);
        EnemyEagle enemy4 = new EnemyEagle(worldHandler, 34, 14);

        enemies.addEnemy(boss);
        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        enemy1.addWeapon(new WeaponSword3(false));
        enemy2.addWeapon(new WeaponBomb3(false));

        addArena();
        GameHandler.makeSign(GameHandler.levelScreenInn);
        drawSign = false;
    }

    public void render(float delta) {
        super.render(delta);
        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 10 --- Boss Trial", 350, 650);
            GameHandler.batch.end();
        }

        if (enemies.checkAllIfDead()) {
            ScreenLevelInn.secondBarrier = true;
        }

        WorldHandler.camera.update();
    }
}
