package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class ScreenLevel9 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("vally.png");
    }

    public void makeEnemies() {
        EnemyBombardierSlime enemy1 = new EnemyBombardierSlime(worldHandler, 20, 5);
        EnemyBombardierSlime enemy2 = new EnemyBombardierSlime(worldHandler, 25, 5);
        EnemyRangerBunny enemy3 = new EnemyRangerBunny(worldHandler, 30, 5);
        EnemyViper enemy4 = new EnemyViper(worldHandler, 35, 5);
        EnemyBanditFox enemy5 = new EnemyBanditFox(worldHandler, 38, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);
        enemies.addEnemy(enemy5);

        enemy1.addWeapon(new WeaponBomb2(false));
        enemy2.addWeapon(new WeaponBomb3(false));
        enemy3.addWeapon(new WeaponBow3(false));
        enemy4.addWeapon(new WeaponSword3(false));
        enemy5.addWeapon(new WeaponPistol3(false));

        addArena();
        GameHandler.makeSign(GameHandler.screenLevel10);
        drawSign = false;
    }

    public void render(float delta) {
        super.render(delta);
        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 9 --- Warren Breakers", 350, 650);
            GameHandler.batch.end();
        }
        WorldHandler.camera.update();
    }
}
