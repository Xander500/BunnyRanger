package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class ScreenLevel7 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("sprites/backgrounds/bg_valley.png");
    }

    public void makeEnemies() {
        EnemyBombardierSlime enemy1 = new EnemyBombardierSlime(worldHandler, 22, 5);
        EnemyMossSlime enemy2 = new EnemyMossSlime(worldHandler, 27, 5);
        EnemyBanditFox enemy3 = new EnemyBanditFox(worldHandler, 32, 5);
        EnemyBanditFox enemy4 = new EnemyBanditFox(worldHandler, 36, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        enemy1.addWeapon(new WeaponEnemyBigArrowDown());
        enemy2.addWeapon(new WeaponEnemyBigArrowDown());
        enemy3.addWeapon(new WeaponEnemyPelletShot());
        enemy4.addWeapon(new WeaponEnemyArrowLob());

        addArena();
        GameHandler.makeSign(GameHandler.screenLevel8);
        drawSign = false;
    }

    public void render(float delta) {
        super.render(delta);
        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 7 --- Bomb Garden", 350, 650);
            GameHandler.batch.end();
        }
        WorldHandler.camera.update();
    }
}
