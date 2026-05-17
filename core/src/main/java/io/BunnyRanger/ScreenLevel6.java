package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class ScreenLevel6 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("sprites/backgrounds/bg_valley.png");
    }

    public void makeEnemies() {
        EnemyBanditFox enemy1 = new EnemyBanditFox(worldHandler, 22, 5);
        EnemyBanditFox enemy2 = new EnemyBanditFox(worldHandler, 27, 5);
        EnemyHare enemy3 = new EnemyHare(worldHandler, 32, 5);
        EnemyViper enemy4 = new EnemyViper(worldHandler, 36, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        enemy1.addWeapon(new WeaponPistol2(false));
        enemy2.addWeapon(new WeaponBow2(false));
        enemy3.addWeapon(new WeaponSword2(false));
        enemy4.addWeapon(new WeaponSword2(false));

        addArena();
        GameHandler.makeSign(GameHandler.screenLevel7);
        drawSign = false;
    }

    public void render(float delta) {
        super.render(delta);
        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 6 --- Bandit Burrow", 350, 650);
            GameHandler.batch.end();
        }
        WorldHandler.camera.update();
    }
}
