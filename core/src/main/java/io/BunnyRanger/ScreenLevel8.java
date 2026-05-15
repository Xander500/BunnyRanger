package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class ScreenLevel8 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("vally.png");
    }

    public void makeEnemies() {
        EnemyRangerBunny enemy1 = new EnemyRangerBunny(worldHandler, 21, 5);
        EnemyRangerBunny enemy2 = new EnemyRangerBunny(worldHandler, 26, 5);
        EnemyViper enemy3 = new EnemyViper(worldHandler, 31, 5);
        EnemyBanditFox enemy4 = new EnemyBanditFox(worldHandler, 36, 5);
        EnemyEagle enemy5 = new EnemyEagle(worldHandler, 29, 13);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);
        enemies.addEnemy(enemy5);

        enemy1.addWeapon(new WeaponBow3(false));
        enemy2.addWeapon(new WeaponSword3(false));
        enemy3.addWeapon(new WeaponSword2(false));
        enemy4.addWeapon(new WeaponPistol3(false));

        addArena();
        GameHandler.makeSign(GameHandler.screenLevel9);
        drawSign = false;
    }

    public void render(float delta) {
        super.render(delta);
        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 8 --- Moonbranch Patrol", 350, 650);
            GameHandler.batch.end();
        }
        WorldHandler.camera.update();
    }
}
