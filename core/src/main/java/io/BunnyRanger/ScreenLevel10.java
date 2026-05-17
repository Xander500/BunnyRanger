package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class ScreenLevel10 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("sprites/backgrounds/bg_valley.png");
    }

    public void makeEnemies() {
        EnemyRangerBunny enemy1 = new EnemyRangerBunny(worldHandler, 19, 5);
        EnemyRangerBunny enemy2 = new EnemyRangerBunny(worldHandler, 24, 5);
        EnemyBombardierSlime enemy3 = new EnemyBombardierSlime(worldHandler, 29, 5);
        EnemyViper enemy4 = new EnemyViper(worldHandler, 34, 5);
        EnemyBanditFox enemy5 = new EnemyBanditFox(worldHandler, 38, 5);
        EnemyEagle enemy6 = new EnemyEagle(worldHandler, 24, 14);
        EnemyEagle enemy7 = new EnemyEagle(worldHandler, 34, 14);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);
        enemies.addEnemy(enemy5);
        enemies.addEnemy(enemy6);
        enemies.addEnemy(enemy7);

        enemy1.addWeapon(new WeaponSword3(false));
        enemy2.addWeapon(new WeaponBow3(false));
        enemy3.addWeapon(new WeaponBomb3(false));
        enemy4.addWeapon(new WeaponSword3(false));
        enemy5.addWeapon(new WeaponPistol3(false));

        addArena();
        GameHandler.makeSign(GameHandler.levelScreenInn);
        drawSign = false;
    }

    public void render(float delta) {
        super.render(delta);
        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 10 --- Ranger Trial", 350, 650);
            GameHandler.batch.end();
        }

        if (enemies.checkAllIfDead()) {
            ScreenLevelInn.secondBarrier = true;
        }

        WorldHandler.camera.update();
    }
}
